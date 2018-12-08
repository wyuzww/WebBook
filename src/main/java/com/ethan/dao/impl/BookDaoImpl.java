package com.ethan.dao.impl;

import com.ethan.dao.BookDao;
import com.ethan.entity.Book;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/6.
 */
public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public List<Book> allBook(int page, int rows) throws SQLException {

        String sql = "select * from book where 1=1";
        if (page > 0 && rows > 0) {
            sql += " limit " + (page - 1) * rows + "," + rows;
        }
        List<Book> books = queryRunner.query(sql, new BeanListHandler<Book>(Book.class));
        return books;
    }

    @Override
    public int addBook(Book book) throws SQLException {
        String sql = "insert into book  (ISBN,catagoryId,name,publish,author,publishDate,price,storageDate,stockNumber,inNumber) values(?,?,?,?,?,?,?,?,?,?) ";
        int code = queryRunner.update(sql, book.getISBN(), book.getCatagoryId(), book.getName(), book.getPublish(), book.getAuthor(), book.getPublishDate(), book.getPrice(), book.getStorageDate(), book.getStockNumber(), book.getInNumber());
        return code;
    }

    @Override
    public List<Book> findBook(Book book) throws SQLException {
        String sql = "select * from book where 1=1";
        if (!book.getName().equals("")) {
            sql += " and name like '%" + book.getName() + "%'";
        }
        if (!book.getISBN().equals("")) {
            sql += " and ISBN like '%" + book.getISBN() + "%'";
        }
//        List<List<User>> users = queryRunner.execute(sql, new BeanListHandler<User>(User.class),user.getName());
        List<Book> books = queryRunner.query(sql, new BeanListHandler<Book>(Book.class));
        return books;


    }

    @Override
    public int bookCount(Book book) throws SQLException {

        String sql = "select count(*) from book";
        long count = queryRunner.query(sql, new ScalarHandler<Long>());

        return (int) count;
    }

    @Override
    public int update(Book book) throws SQLException {
        String sql = "update book set  catagoryId=?,name=?,publish=?,author=?,publishDate=?,price=?,storageDate=?,stockNumber=?,inNumber=? where ISBN=? or name=? ";
        int code = queryRunner.update(sql, book.getCatagoryId(), book.getName(), book.getPublish(), book.getAuthor(), book.getPublishDate(), book.getPrice(), book.getStorageDate(), book.getStockNumber(), book.getInNumber(), book.getISBN(), book.getName());
        return code;
    }

    @Override
    public int deleteBook(Book book) throws SQLException {
        String sql = "delete from book where ISBN=?";
        int code = queryRunner.execute(sql, book.getISBN());

        return code;
    }


}
