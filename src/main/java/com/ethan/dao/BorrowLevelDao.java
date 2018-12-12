package com.ethan.dao;

import com.ethan.entity.AllEntity;
import com.ethan.entity.BorrowLevel;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/10.
 */
public interface BorrowLevelDao {

    /**
     * @param borrowLevel
     * @param page
     * @param rows
     * @return
     * @throws SQLException
     */
    public List<BorrowLevel> allBorroeLevel(BorrowLevel borrowLevel, int page, int rows) throws SQLException;

}
