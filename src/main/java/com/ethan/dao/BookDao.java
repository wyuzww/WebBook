package com.ethan.dao;

import com.ethan.entity.AllEntity;
import com.ethan.entity.Book;


import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/6.
 */
public interface BookDao {
    /**
     * @param page
     * @param rows
     * @return
     * @throws SQLException
     */
    public List<AllEntity> allBook(Book book, int page, int rows) throws SQLException;

    /**
     * @param book
     * @return
     * @throws SQLException
     */
    public int addBook(Book book) throws SQLException;

    /**
     * @param book
     * @return
     * @throws SQLException
     */
    public List<Book> findBook(Book book) throws SQLException;


    /**
     * 计算图书总记录
     *
     * @param book
     * @return
     * @throws SQLException
     */
    public int bookCount(Book book) throws SQLException;


    /**
     * 更新
     *
     * @param book
     * @return
     * @throws SQLException
     */
    public int update(Book book) throws SQLException;


    /**
     * 删除图书
     *
     * @return
     * @throws SQLException
     */
    public int deleteBook(String ids) throws SQLException;

    public int borrowbBook(Book book, int a) throws SQLException;

}
