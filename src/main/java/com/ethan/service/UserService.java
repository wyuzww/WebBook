package com.ethan.service;

import com.ethan.entity.User;

import java.sql.SQLException;

public interface UserService {
    /**
     * user登录
     * @param user
     * @return user
     * @throws SQLException
     */
    public User login(User user) throws SQLException;
}
