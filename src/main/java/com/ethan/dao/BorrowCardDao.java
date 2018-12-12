package com.ethan.dao;

import com.ethan.entity.AllEntity;
import com.ethan.entity.BorrowCard;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/10.
 */
public interface BorrowCardDao {
    public List<AllEntity> allBorrowCard(BorrowCard borrowCard, int page, int rows) throws SQLException;

    public int updateBC(BorrowCard borrowCard) throws SQLException;

    public int deleteBC(String ids) throws SQLException;

    public int addBC(BorrowCard borrowCard) throws SQLException;
}
