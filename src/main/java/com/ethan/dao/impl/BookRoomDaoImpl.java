package com.ethan.dao.impl;

import com.ethan.dao.BookRoomDao;
import com.ethan.entity.BookRoom;
import com.ethan.utils.StringUtil;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/9.
 */
public class BookRoomDaoImpl extends BaseDao implements BookRoomDao {

    @Override
    public List<BookRoom> allBookRoom(BookRoom bookRoom, int page, int rows) throws SQLException {
        String sql = "select * from bookroom where 1=1";

        if (StringUtil.isNotEmpty(bookRoom.getBookroom_name())) {
            sql += " and bookroom.bookroom_name like '%" + bookRoom.getBookroom_name() + "%'";
        }

        if (page > 0 && rows > 0) {
            sql += " limit " + (page - 1) * rows + "," + rows;
        }

        List<BookRoom> bookrooms = queryRunner.query(sql, new BeanListHandler<BookRoom>(BookRoom.class));
        return bookrooms;


    }

    @Override
    public int updateBR(BookRoom bookRoom) throws SQLException {
        String sql = "update bookroom set bookroom_name=?,bookroom_address=?,bookroom_phone=? where bookroom_id=?";
        int code = queryRunner.update(sql, bookRoom.getBookroom_name(), bookRoom.getBookroom_address(), bookRoom.getBookroom_phone(), bookRoom.getBookroom_id());
        return code;
    }

    @Override
    public int addBR(BookRoom bookRoom) throws SQLException {
        String sql = "insert into bookroom (bookroom_name,bookroom_address,bookroom_phone) values(?,?,?)";
        int code = queryRunner.update(sql, bookRoom.getBookroom_name(), bookRoom.getBookroom_address(), bookRoom.getBookroom_phone());
        return code;
    }

    @Override
    public int deleteBR(BookRoom bookRoom) throws SQLException {
        String sql = "delete from bookroom where bookroom_id=?";
        int code = queryRunner.execute(sql, bookRoom.getBookroom_id());

        return code;
    }


}
