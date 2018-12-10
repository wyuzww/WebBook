package com.ethan.dao.impl;

import com.ethan.dao.BookDao;
import com.ethan.entity.Book;
import com.ethan.entity.Book_Catagory_Room;
import com.ethan.utils.StringUtil;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/6.
 */
public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public List<Book_Catagory_Room> allBook(Book book, int page, int rows) throws SQLException {
        String sql = "select * from book,bookcatagory,bookroom where book.book_catagoryId = bookcatagory.bookcatagory_id AND bookcatagory.bookcatagory_brid = bookroom.bookroom_id";
        if (StringUtil.isNotEmpty(book.getBook_ISBN())) {
            sql += " and book.book_ISBN like '%" + book.getBook_ISBN() + "%'";
        }
        if (book.getBook_catagoryId() > 0) {
            sql += " and book.book_catagoryId=" + book.getBook_catagoryId() + " ";
        }
        if (StringUtil.isNotEmpty(book.getBook_name())) {
            sql += " and book.book_name like '%" + book.getBook_name() + "%'";
        }
        if (StringUtil.isNotEmpty(book.getBook_author())) {
            sql += " and book.book_author like '%" + book.getBook_author() + "%'";
        }
        if (page > 0 && rows > 0) {
            sql += " limit " + (page - 1) * rows + "," + rows;
        }

        List<Book_Catagory_Room> books = queryRunner.query(sql, new BeanListHandler<Book_Catagory_Room>(Book_Catagory_Room.class));
        return books;
    }

    @Override
    public int addBook(Book book) throws SQLException {
        String sql = "insert into book  (book_ISBN,book_catagoryId,book_name,book_publish,book_author,book_publishDate,book_price,book_storageDate,book_stockNumber,book_inNumber) values(?,?,?,?,?,?,?,?,?,?) ";
        int code = queryRunner.update(sql, book.getBook_ISBN(), book.getBook_catagoryId(), book.getBook_name(), book.getBook_publish(), book.getBook_author(), book.getBook_publishDate(), book.getBook_price(), book.getBook_storageDate(), book.getBook_stockNumber(), book.getBook_inNumber());
        return code;
    }

    @Override
    public List<Book> findBook(Book book) throws SQLException {
        String sql = "select * from book where 1=1";
        if (!book.getBook_name().equals("")) {
            sql += " and book_name like '%" + book.getBook_name() + "%'";
        }
        if (!book.getBook_ISBN().equals("")) {
            sql += " and book_ISBN like '%" + book.getBook_ISBN() + "%'";
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
        String sql = "update book set book_ISBN=?, book_catagoryId=?,book_name=?,book_publish=?,book_author=?,book_publishDate=?,book_price=?,book_storageDate=?,book_stockNumber=?,book_inNumber=? where book_ISBN=?";
        int code = queryRunner.update(sql, book.getBook_ISBN(), book.getBook_catagoryId(), book.getBook_name(), book.getBook_publish(), book.getBook_author(), book.getBook_publishDate(), book.getBook_price(), book.getBook_storageDate(), book.getBook_stockNumber(), book.getBook_inNumber(), book.getBook_ISBN());
        return code;
    }

    @Override
    public int deleteBook(String ids) throws SQLException {
        String sql = "delete from book where book_ISBN in(" + ids + ") ";
        int code = queryRunner.execute(sql);

        return code;
    }


}
