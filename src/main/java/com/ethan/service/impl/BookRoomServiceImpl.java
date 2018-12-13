package com.ethan.service.impl;

import com.ethan.dao.BookRoomDao;
import com.ethan.entity.BookRoom;
import com.ethan.factory.Factory;
import com.ethan.service.BookRoomService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/9.
 */
public class BookRoomServiceImpl implements BookRoomService {
    BookRoomDao bookRoomDao = Factory.getBookRoomDaoInstance();

    @Override
    public List<BookRoom> allBookRoom(BookRoom bookRoom, int page, int rows) throws SQLException {
        List<BookRoom> bookRooms = null;
        bookRooms = bookRoomDao.allBookRoom(bookRoom, page, rows);

        return bookRooms;
    }

    @Override
    public int updateBR(BookRoom bookRoom) throws SQLException {
        int code = 0;
        code = bookRoomDao.updateBR(bookRoom);
        return code;
    }

    @Override
    public int addBR(BookRoom bookRoom) throws SQLException {
        int code = 0;
        code = bookRoomDao.addBR(bookRoom);
        return code;

    }

    @Override
    public int deleteBR(BookRoom bookRoom) throws SQLException {

        int code = 0;
        code = bookRoomDao.deleteBR(bookRoom);
        return code;
    }
}
