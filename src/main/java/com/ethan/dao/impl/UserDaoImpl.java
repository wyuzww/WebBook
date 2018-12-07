package com.ethan.dao.impl;

import com.ethan.dao.UserDao;
import com.ethan.entity.User;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl extends BaseDao implements UserDao{
    @Override
    public User login(User user) throws SQLException {
        String sql = "select * from user where account=? and password=?";
        User userResult = queryRunner.query(sql,new BeanHandler<User>(User.class),user.getAccount(),user.getPassword());
        return userResult;
    }

}
