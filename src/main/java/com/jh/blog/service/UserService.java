package com.jh.blog.service;

import com.jh.blog.pojo.User;

public interface UserService {

    public User checkUser(String username, String password);
}
