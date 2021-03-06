package com.jh.blog.controller;

import com.jh.blog.pojo.Comment;
import com.jh.blog.service.BlogService;
import com.jh.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")  //展示留言
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments", commentService.getCommentByBlogId(blogId));
        model.addAttribute("blog", blogService.getDetailedBlog2(blogId));
        return "blog :: commentList";
    }

    /**保存留言
     *
     */



    @PostMapping("/comments")   //提交留言
    public String post(Comment comment, HttpSession session){
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getDetailedBlog2(blogId));  //绑定博客与评论
        comment.setBlogId(blogId);
        comment.setAvatar(avatar);
        /*User user = (User) session.getAttribute("user");
        if (user != null){   //用户为管理员
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        }else {
            comment.setAvatar(avatar);
        }*/
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }
}
