package com.jh.blog.controller.admin;

import com.jh.blog.pojo.User;
import com.jh.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    //管理员登录的入口(跳转到登录页面)
    @GetMapping()
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username, password);
        if(user != null){
//            浏览器获取不到密码
            user.setPassword(null);
            session.setAttribute("user", user);
            return "admin/index";
        }else {

//            数据重定向需要用RedirectAttributes，用Model获取不到
            attributes.addFlashAttribute("msg", "用户名或密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
//        注销清空session域中信息
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
