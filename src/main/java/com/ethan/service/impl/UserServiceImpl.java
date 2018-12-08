package com.ethan.service.impl;

import com.ethan.dao.UserDao;
import com.ethan.entity.User;
import com.ethan.factory.Factory;
import com.ethan.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = Factory.getUserDaoInstance();

    @Override
    public User login(User user) throws SQLException {
        User userResult = null;
        userResult = userDao.login(user);
        return userResult;
    }

    @Override
    public int update(User user) throws SQLException {
        int code = 0;
        code = userDao.update(user);
        return code;
    }

    @Override
    public List<User> allUser(User user, int page, int rows) throws SQLException {
        List<User> users = null;
        users = userDao.allUser(user, page, rows);
        return users;
    }


    public int addUser(User user) throws SQLException {
        int code = 0;
        code = userDao.addUser(user);
        return code;
    }

    @Override
    public List<User> findUser(User user) throws SQLException {
        List<User> users = null;
        users = userDao.findUser(user);
        return users;
    }

    @Override
    public int deleteUser(String ids) throws SQLException {
        int code = 0;
        code = userDao.deleteUser(ids);
        return code;
    }

    @Override
    public int userCount(User user) throws SQLException {
        int code = 0;
        code = userDao.userCount(user);
        return code;
    }

    @Override
    public boolean isAccount(User user) throws SQLException {
        return userDao.isAccount(user);
    }

}
