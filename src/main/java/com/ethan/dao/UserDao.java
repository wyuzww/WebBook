package com.ethan.dao;

import com.ethan.dao.impl.BaseDao;
import com.ethan.entity.User;

import java.sql.SQLException;

public interface UserDao{
    /**
     * user登录
     * @param user
     * @return user
     * @throws SQLException
     */
    public User login(User user) throws SQLException;

}
