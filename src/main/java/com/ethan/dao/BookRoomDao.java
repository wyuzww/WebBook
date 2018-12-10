package com.ethan.dao;

import com.ethan.entity.BookRoom;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/9.
 */
public interface BookRoomDao {
    /**
     * @param bookRoom
     * @param page
     * @param rows
     * @return
     * @throws SQLException
     */
    public List<BookRoom> allBookRoom(BookRoom bookRoom, int page, int rows) throws SQLException;

    /**
     * @param bookRoom
     * @return
     * @throws SQLException
     */
    public int updateBR(BookRoom bookRoom) throws SQLException;

    /**
     * @param bookRoom
     * @return
     * @throws SQLException
     */
    public int addBR(BookRoom bookRoom) throws SQLException;

    /**
     * @param bookRoom
     * @return
     * @throws SQLException
     */
    public int deleteBR(BookRoom bookRoom) throws SQLException;


}
