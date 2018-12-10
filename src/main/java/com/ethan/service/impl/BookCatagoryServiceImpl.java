package com.ethan.service.impl;

import com.ethan.dao.BookCatagoryDao;
import com.ethan.dao.BookRoomDao;
import com.ethan.entity.BookCatagory;
import com.ethan.entity.BookRoom;
import com.ethan.entity.Book_Catagory_Room;
import com.ethan.factory.Factory;
import com.ethan.service.BookCatagoryService;
import com.ethan.service.BookRoomService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/9.
 */
public class BookCatagoryServiceImpl implements BookCatagoryService {
    BookCatagoryDao bookCatagoryDao = Factory.getBookCatagoryDaoInstance();


    @Override
    public List<Book_Catagory_Room> allCatagory(BookCatagory bookCatagory, int page, int rows) throws SQLException {
        List<Book_Catagory_Room> acr = null;
        acr = bookCatagoryDao.allCatagory(bookCatagory, page, rows);

        return acr;
    }

    @Override
    public int updateBC(BookCatagory bookCatagory) throws SQLException {
        int code = 0;
        code = bookCatagoryDao.updateBC(bookCatagory);
        return code;
    }

    @Override
    public int deleteBC(String ids) throws SQLException {
        int code = 0;
        code = bookCatagoryDao.deleteBC(ids);
        return code;
    }

    @Override
    public int addBC(BookCatagory bookCatagory) throws SQLException {
        int code = 0;
        code = bookCatagoryDao.addBC(bookCatagory);
        return code;
    }
}
