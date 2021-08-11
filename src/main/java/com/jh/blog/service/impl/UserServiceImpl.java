package com.jh.blog.service.impl;

import com.jh.blog.util.MD5Utils;
import com.jh.blog.dao.UserDao;
import com.jh.blog.pojo.User;
import com.jh.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.queryByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
