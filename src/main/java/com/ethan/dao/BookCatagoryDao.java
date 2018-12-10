package com.ethan.dao;

import com.ethan.entity.BookCatagory;
import com.ethan.entity.BookRoom;
import com.ethan.entity.Book_Catagory_Room;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/9.
 */
public interface BookCatagoryDao {
    /**
     * Room与catagory级联查询
     *
     * @param page
     * @param rows
     * @return
     * @throws SQLException
     */
    public List<Book_Catagory_Room> allCatagory(BookCatagory bookCatagory, int page, int rows) throws SQLException;

    /**
     * updateBC 更新BookCatagory
     *
     * @return
     * @throws SQLException
     */
    public int updateBC(BookCatagory BookCatagory) throws SQLException;

    /**
     * deleteBC 删除BookCatagory
     *
     * @param ids
     * @return
     * @throws SQLException
     */
    public int deleteBC(String ids) throws SQLException;


    /**
     * addBC 插入BookCatagory
     *
     * @param bookCatagory
     * @return
     * @throws SQLException
     */
    public int addBC(BookCatagory bookCatagory) throws SQLException;


}
