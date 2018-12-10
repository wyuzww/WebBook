package com.ethan.factory;

import com.ethan.dao.*;
import com.ethan.dao.impl.*;
import com.ethan.dbc.C3P0Util;
import com.ethan.entity.*;
import com.ethan.service.BookCatagoryService;
import com.ethan.service.impl.*;
import org.apache.commons.dbutils.QueryRunner;

public class Factory {
    /**
     * 获取一个QueryRunner实例
     * @return QueryRunner
     */
    public static QueryRunner getQueryRunnerInstance() {
        return new QueryRunner(C3P0Util.getDataSource());
    }

    /**
     * 获取一个User实例
     *
     * @return user
     */
    public static User getUser() {
        return new User();
    }
    /**
     * 获取一个UserDaoImpl实例
     * @return UserDaoImpl
     */
    public static UserDao getUserDaoInstance() {
        return new UserDaoImpl();
    }


    /**
     * 获取一个UserServiceImpl实例
     * @return UserServiceImpl
     */
    public static UserServiceImpl getUserServiceInstance() {
        return new UserServiceImpl();
    }

    /**
     * 获取一个BookDaoImpl实例
     *
     * @return BookDaoImpl
     */
    public static BookDao getBookDaoInstance() {
        return new BookDaoImpl();
    }
    /**
     * 获取一个BookServiceImpl实例
     *
     * @return BookServiceImpl
     */
    public static BookServiceImpl getBookServiceInstance() {
        return new BookServiceImpl();
    }

    public static Book getBook() {
        return new Book();
    }


    public static BookRoom getBookRoomInstance() {
        return new BookRoom();
    }

    public static BookRoomDao getBookRoomDaoInstance() {
        return new BookRoomDaoImpl();
    }

    public static BookRoomServiceImpl getBookRoomServiceInstance() {
        return new BookRoomServiceImpl();
    }


    public static BookCatagory getBookCatagoryInstance() {
        return new BookCatagory();
    }

    public static BookCatagoryDao getBookCatagoryDaoInstance() {
        return new BookCatagoryDaoImpl();
    }

    public static BookCatagoryService getBookCatagoryServiceInstance() {
        return new BookCatagoryServiceImpl();
    }
}
