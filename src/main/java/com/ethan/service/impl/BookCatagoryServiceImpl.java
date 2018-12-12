package com.ethan.service.impl;

import com.ethan.dao.BookCatagoryDao;
import com.ethan.entity.AllEntity;
import com.ethan.entity.BookCatagory;
import com.ethan.factory.Factory;
import com.ethan.service.BookCatagoryService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/9.
 */
public class BookCatagoryServiceImpl implements BookCatagoryService {
    BookCatagoryDao bookCatagoryDao = Factory.getBookCatagoryDaoInstance();


    @Override
    public List<AllEntity> allCatagory(BookCatagory bookCatagory, int page, int rows) throws SQLException {
        List<AllEntity> acr = null;
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
