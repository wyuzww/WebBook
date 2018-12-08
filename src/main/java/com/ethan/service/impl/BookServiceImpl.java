package com.ethan.service.impl;

import com.ethan.dao.BookDao;
import com.ethan.entity.Book;
import com.ethan.factory.Factory;
import com.ethan.service.BookService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/6.
 */
public class BookServiceImpl implements BookService {

    public List<Book> allBook(int page, int rows) throws SQLException {
        BookDao bookDao = Factory.getBookDaoInstance();
        List<Book> books = null;
        try {
            books = bookDao.allBook(page, rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public int addBook(Book book) {
        BookDao bookDao = Factory.getBookDaoInstance();
        int code = 0;
        try {
            code = bookDao.addBook(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    @Override
    public List<Book> findBook(Book book) throws SQLException {
        BookDao bookDao = Factory.getBookDaoInstance();
        List<Book> books = null;
        try {
            books = bookDao.findBook(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;

    }

    @Override
    public int bookCount(Book book) throws SQLException {

        BookDao bookDao = Factory.getBookDaoInstance();
        int code = 0;
        code = bookDao.bookCount(book);


        return code;
    }

    @Override
    public int update(Book book) throws SQLException {
        BookDao bookDao = Factory.getBookDaoInstance();
        int code = 0;
        code = bookDao.update(book);

        return code;
    }

    @Override
    public int deleteBook(Book book) throws SQLException {
        BookDao bookDao = Factory.getBookDaoInstance();
        int code = 0;
        code = bookDao.deleteBook(book);

        return code;
    }
}
