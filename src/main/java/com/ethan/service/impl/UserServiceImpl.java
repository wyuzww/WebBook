package com.ethan.service.impl;

import com.ethan.dao.UserDao;
import com.ethan.entity.User;
import com.ethan.factory.Factory;
import com.ethan.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    @Override
    public User login(User user){
        UserDao userDao = Factory.getUserDaoInstance();
        User userResult = null;
        try {
            userResult = userDao.login(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userResult;
    }
}
