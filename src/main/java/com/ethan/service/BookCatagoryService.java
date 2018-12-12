package com.ethan.service;

import com.ethan.entity.AllEntity;
import com.ethan.entity.BookCatagory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/9.
 */
public interface BookCatagoryService {

    /**
     * Room与catagory级联查询
     *
     * @param page
     * @param rows
     * @return
     * @throws SQLException
     */
    public List<AllEntity> allCatagory(BookCatagory bookCatagory, int page, int rows) throws SQLException;

    /**
     * updateBC 更新BookCatagory
     *
     * @param bookCatagory
     * @return
     * @throws SQLException
     */
    public int updateBC(BookCatagory bookCatagory) throws SQLException;


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
