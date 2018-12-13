package com.ethan.dao.impl;

import com.ethan.dao.UserDao;
import com.ethan.entity.User;
import com.ethan.utils.StringUtil;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User login(User user) throws SQLException {
        String sql = "select * from user where user_account=? and user_password=?";
        User userResult = queryRunner.query(sql, new BeanHandler<User>(User.class), user.getUser_account(), user.getUser_password());
        return userResult;
    }

    @Override
    public int update(User user) throws SQLException {
        int code = 0;
        if (user.getUser_level() != null) {
            String sql = "update user set user_account=?, user_password=?, user_name=?,user_sex=?,user_phone=?,user_level=? where user_id=?";
            code = queryRunner.update(sql, user.getUser_account(), user.getUser_password(), user.getUser_name(), user.getUser_sex(), user.getUser_phone(), user.getUser_level(), user.getUser_id());
        } else {
            String sql = "update user set user_account=?, user_password=?, user_name=?,user_sex=?,user_phone=? where user_id=?";
            code = queryRunner.update(sql, user.getUser_account(), user.getUser_password(), user.getUser_name(), user.getUser_sex(), user.getUser_phone(), user.getUser_id());
        }


        return code;
    }

    @Override
    public List<User> allUser(User user, int page, int rows) throws SQLException {
        String sql = "select * from user where 1=1";

        if (StringUtil.isNotEmpty(user.getUser_name())) {
            sql += " and user_name like '%" + user.getUser_name() + "%'";
        }
        if (StringUtil.isNotEmpty(user.getUser_account())) {
            sql += " and user_account like '%" + user.getUser_account() + "%'";
        }
        if (page > 0 && rows > 0) {
            sql += " limit " + (page - 1) * rows + "," + rows;
        }
        List<User> users = queryRunner.query(sql, new BeanListHandler<User>(User.class));
        return users;
    }

    @Override
    public int addUser(User user) throws SQLException {
        int code = 0;
        if (user.getUser_level() != null) {
            String sql = "insert into user (user_id,user_account,user_password,user_name,user_sex,user_phone, user_photo,user_level) values(null,?,?,?,?,?,?,?) ";
            code = queryRunner.update(sql, user.getUser_account(), user.getUser_password(), user.getUser_name(), user.getUser_sex(), user.getUser_phone(), user.getUser_photo(), user.getUser_level());
        } else {
            String sql = "insert into user (user_id,user_account,user_password,user_name,user_sex,user_phone, user_photo,user_level) values(null,?,?,?,?,?,?,\"读者\") ";
            code = queryRunner.update(sql, user.getUser_account(), user.getUser_password(), user.getUser_name(), user.getUser_sex(), user.getUser_phone(), user.getUser_photo());
        }

        if (code > 0) {

            String sql1 = "select * from user order by user_id DESC limit 1 ";
            User userResult = queryRunner.query(sql1, new BeanHandler<User>(User.class));
            String sql2 = "insert into borrowcard (borrowcard_id,borrowcard_rid,borrowcard_quantity,borrowcard_blid) values(?,?,0,1) ";
            queryRunner.update(sql2, "WYU" + userResult.getUser_account(), userResult.getUser_id());

        }


        return code;
    }

    @Override
    public List<User> findUser(User user) throws SQLException {
        String sql = "select * from user where 1=1";
//        if(!user.getName().equals("")) {
//            sql+=" and name like '%"+user.getName()+"%'";
//        }
//        if(!user.getAccount().equals("")) {
//            sql+=" and account like '%"+user.getAccount()+"%'";
//        }
//        List<List<User>> users = queryRunner.execute(sql, new BeanListHandler<User>(User.class),user.getName());
        List<User> users = queryRunner.query(sql, new BeanListHandler<User>(User.class));
        return users;
    }

    @Override
    public int deleteUser(String ids) throws SQLException {
        String sql = "delete from user where user_id in(" + ids + ")";
        int code = queryRunner.execute(sql);
        return code;
    }

    @Override
    public int userCount(User user) throws SQLException {
        String sql = "select count(*) from user where 1=1";

        if (StringUtil.isNotEmpty(user.getUser_name())) {
            sql += " and user_name like '%" + user.getUser_name() + "%'";
        }
        if (StringUtil.isNotEmpty(user.getUser_account())) {
            sql += " and user_account like '%" + user.getUser_account() + "%'";
        }
        long count = queryRunner.query(sql, new ScalarHandler<Long>());

        return (int) count;
    }

    @Override
    public boolean isAccount(User user) throws SQLException {
        String sql = "select * from user where user_account=?";
        User userResult = queryRunner.query(sql, new BeanHandler<User>(User.class), user.getUser_account());
        if (userResult != null) {
            return true;
        } else {
            return false;
        }
    }


}
