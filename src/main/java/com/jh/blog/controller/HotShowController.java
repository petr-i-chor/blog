package com.jh.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jh.blog.pojo.Blog;
import com.jh.blog.pojo.Tag;
import com.jh.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author jh
 * @create 2021-08-16-11:59
 */
@Controller
public class HotShowController {
    /**
     * 从导航过来
     */
    @Autowired
    private BlogService blogService;

    @GetMapping("/hot")
    public String search(@RequestParam(required = false,defaultValue = "1",value = "pageNum")int pageNum,
                          Model model){

        PageHelper.startPage(pageNum, 5);
        List<Blog> blogs = blogService.getAllHotBlog();
        PageInfo pageInfo = new PageInfo(blogs);
        model.addAttribute("pageInfo", pageInfo);
        return "hot";
    }


}
