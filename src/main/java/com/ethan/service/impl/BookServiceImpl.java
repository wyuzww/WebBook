package com.ethan.service.impl;

import com.ethan.dao.BookDao;
import com.ethan.entity.Book;
import com.ethan.entity.Book_Catagory_Room;
import com.ethan.factory.Factory;
import com.ethan.service.BookService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/6.
 */
public class BookServiceImpl implements BookService {
    BookDao bookDao = Factory.getBookDaoInstance();

    public List<Book_Catagory_Room> allBook(Book book, int page, int rows) throws SQLException {
        List<Book_Catagory_Room> books = null;
        books = bookDao.allBook(book, page, rows);
        return books;
    }

    @Override
    public int addBook(Book book) throws SQLException {
        int code = 0;
        code = bookDao.addBook(book);

        return code;
    }

    @Override
    public List<Book> findBook(Book book) throws SQLException {
        List<Book> books = null;
        books = bookDao.findBook(book);
        return books;

    }

    @Override
    public int bookCount(Book book) throws SQLException {
        int code = 0;
        code = bookDao.bookCount(book);


        return code;
    }

    @Override
    public int update(Book book) throws SQLException {
        int code = 0;
        code = bookDao.update(book);

        return code;
    }

    @Override
    public int deleteBook(String ids) throws SQLException {
        int code = 0;
        code = bookDao.deleteBook(ids);

        return code;
    }
}
