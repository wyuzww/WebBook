package com.ethan.service;

import com.ethan.entity.AllEntity;
import com.ethan.entity.Borrow;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/10.
 */
public interface BorrowService {
    /**
     * @param borrow
     * @param page
     * @param rows
     * @return
     * @throws SQLException
     */
    public List<AllEntity> allBorrow(Borrow borrow, int page, int rows) throws SQLException;

    /**
     * @param ids
     * @return
     * @throws SQLException
     */
    public int deleteBorrow(String ids) throws SQLException;

    /**
     * @param borrow
     * @return
     * @throws SQLException
     */
    public int updateBorrow(Borrow borrow) throws SQLException;

    public int addBorrow(Borrow borrow) throws SQLException;

    public List<AllEntity> notDueBorrow(Borrow borrow, int page, int rows) throws SQLException;

    public Borrow findBorrow(Borrow borrow) throws SQLException;

    public int dueBorrow(Borrow borrow) throws SQLException;

    public AllEntity findUserBorrow(Borrow borrow) throws SQLException;

    public AllEntity findUserDueBorrow(Borrow borrow) throws SQLException;
}
