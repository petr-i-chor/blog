package com.jh.blog.service.impl;

import com.jh.blog.exception.NotFoundException;
import com.jh.blog.pojo.Tag;
import com.jh.blog.util.MarkdownUtils;
import com.jh.blog.dao.BlogDao;
import com.jh.blog.pojo.Blog;
import com.jh.blog.pojo.BlogAndTag;
import com.jh.blog.service.BlogService;
import com.jh.blog.util.RedisUtils;
import com.jh.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Blog getBlog(Long id) {
        return blogDao.getBlog(id);
    }

    @Override
    public Blog getDetailedBlog(Long id) {
        Blog blog = blogDao.getDetailedBlog(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        RedisUtils.initializeView(id,blog,redisTemplate);
        RedisUtils.setHot(id,redisTemplate);
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));  //将Markdown格式转换成html
        return blog;
    }

    //评论加载获取的blog，反正view反复增加
    @Override
    public Blog getDetailedBlog2(Long id) {
        Blog blog = blogDao.getDetailedBlog(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));  //将Markdown格式转换成html
        return blog;
    }

    @Override
    public List<Blog> getAllBlog() {
        return blogDao.getAllBlog();
    }

    @Override
    public List<Blog> getByTypeId(Long typeId) {
        List<Blog> blogs = blogDao.getByTypeId(typeId);
       RedisUtils.setView(blogs,redisTemplate);
        return blogs;
    }

    @Override
    public List<Blog> getByTagId(Long tagId) {
        List<Blog> blogs = blogDao.getByTagId(tagId);
        RedisUtils.setView(blogs,redisTemplate);
        return blogs;
    }

    @Override
    public List<Blog> getIndexBlog() {
        List<Blog> blogs = blogDao.getIndexBlog();
        RedisUtils.setView(blogs,redisTemplate);
        return blogs;
    }

    @Override
    public List<Blog> getAllRecommendBlog() {
        List<Blog> blogs = blogDao.getAllRecommendBlog();
        return blogs;
    }

    @Override
    public List<Blog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    /**
     * 获取热门博客
     * @return
     */
    @Override
    public List<Blog> getAllHotBlog() {

        List list= RedisUtils.getHot(redisTemplate);
        if (list.size()==0){
            return list;
        }else {
            List<Blog> blogs = blogDao.getAllHotBlog(list);
            RedisUtils.setView(blogs,redisTemplate);
            return blogs;
        }
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogDao.findGroupYear();
        Set<String> set = new HashSet<>(years);  //set去掉重复的年份
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : set) {
            map.put(year, blogDao.findByYear(year));
        }
        return map;
    }

    @Override
    public int countBlog() {
        return blogDao.getAllBlog().size();
    }

    @Override
    public List<Blog> searchAllBlog(BlogQuery blog) {
        if ("true".equals(blog.getRecommend())){
            blog.setRecommend("1");
        }else {
            blog.setRecommend(null);
        }
        return blogDao.searchAllBlog(blog);
    }


    @Override    //新增博客
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        //保存博客
        blogDao.saveBlog(blog);
        //保存博客后才能获取自增的id
        Long id = blog.getId();
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            //新增时无法获取自增的id,在mybatis里修改
            blogAndTag = new BlogAndTag(tag.getId(), id);
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return 1;
    }

    @Override   //编辑博客
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        //先把t_blog_tag表中的标签删掉
        /**
         * 先获取blog_id
         * 调用deleteBlogAndTag方法删除对应列
         */
        BlogAndTag blogAndTag = null;
        blogAndTag = new BlogAndTag();
        blogAndTag.setBlogId(blog.getId());
        blogDao.deleteBlogAndTag(blogAndTag);

        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(), blog.getId());
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return blogDao.updateBlog(blog);
    }

    @Override
    public int deleteBlog(Long id) {
        String keyView = "View_"+id;
        redisTemplate.delete(keyView);

        return blogDao.deleteBlog(id);
    }

}
