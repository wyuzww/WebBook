package com.ethan.service.impl;

import com.ethan.dao.BorrowLevelDao;
import com.ethan.entity.BorrowLevel;
import com.ethan.factory.Factory;
import com.ethan.service.BorrowLevelService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/10.
 */
public class BorrowLevelServiceImpl implements BorrowLevelService {
    BorrowLevelDao borrowLevelDao = Factory.getBorrowLevelDaoInstance();

    @Override
    public List<BorrowLevel> allBorroeLevel(BorrowLevel borrowLevel, int page, int rows) throws SQLException {
        List<BorrowLevel> book_catagory_rooms = null;
        book_catagory_rooms = borrowLevelDao.allBorroeLevel(borrowLevel, page, rows);


        return book_catagory_rooms;
    }
}
