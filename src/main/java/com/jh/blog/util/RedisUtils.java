package com.jh.blog.util;

import com.jh.blog.pojo.Blog;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author jh
 * @create 2021-08-14-15:23
 */
public class RedisUtils {

    /**
     * 给blog设置views属性
     */
    public static List<Blog> setView(List<Blog> blogs,RedisTemplate redisTemplate){
        for (Blog blog :blogs ) {
            String keyView = "View_"+blog.getId();
            ValueOperations ops = redisTemplate.opsForValue();
            Integer views = (Integer) ops.get(keyView);
            if (views == null){
                blog.setViews(0);
            }else {
                blog.setViews(views);
            }
        }
        return blogs;
    }

    /**
     * views初始化+自增
     */

    public static Blog initializeView(Long id,Blog blog,RedisTemplate redisTemplate){
        String keyView = "View_"+id;
        ValueOperations ops = redisTemplate.opsForValue();
        Object o = ops.get(keyView);
        if (o == null){
            ops.set(keyView,1);
        }else {
            redisTemplate.boundValueOps(keyView).increment(1);
        }
        blog.setViews((Integer) ops.get(keyView));
        return blog;
    }

    /**
     * 存储热榜博客id以及对应点击量信息
     *判断博客是否第一次点击
     * 是，添加到redis中
     * 否，获取KeyHot对应的值，自增
     * @param id
     * @param redisTemplate
     * @return
     */
    public static void setHot(Long id,RedisTemplate redisTemplate){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat();
        String s = format.getDateInstance().format(date);
        String keyHot =s+":";
        ZSetOperations ops = redisTemplate.opsForZSet();
        Long rank = ops.rank(keyHot, id);
        if (rank==null){
            ops.add(keyHot,id,1);
        }else {
            ops.incrementScore(keyHot,id,1);
        }
    }

    public static List getHot(RedisTemplate redisTemplate){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat();
        String s = format.getDateInstance().format(date);
        String keyHot =s+":";
        ZSetOperations ops = redisTemplate.opsForZSet();
        Set set = ops.range(keyHot, 0, 19);
        System.out.println(set.size());
        if (set.size()==0){
            return new ArrayList();
        }else {
            ArrayList list = new ArrayList();
            for (Object o  :set ) {
                list.add(o);
            }
            set = null;
            return list;
        }

    }



}
