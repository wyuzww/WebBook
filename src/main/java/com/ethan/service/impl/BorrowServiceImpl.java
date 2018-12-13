package com.ethan.service.impl;

import com.ethan.dao.BorrowDao;
import com.ethan.entity.AllEntity;
import com.ethan.entity.Borrow;
import com.ethan.factory.Factory;
import com.ethan.service.BorrowService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/10.
 */
public class BorrowServiceImpl implements BorrowService {
    BorrowDao borrowDao = Factory.getBorrowDaoInstance();

    @Override
    public List<AllEntity> allBorrow(Borrow borrow, int page, int rows) throws SQLException {
        List<AllEntity> borrows = null;
        borrows = borrowDao.allBorrow(borrow, page, rows);

        return borrows;
    }

    @Override
    public List<AllEntity> notDueBorrow(Borrow borrow, int page, int rows) throws SQLException {
        List<AllEntity> borrows = null;
        borrows = borrowDao.notDueBorrow(borrow, page, rows);

        return borrows;
    }

    @Override
    public int deleteBorrow(String ids) throws SQLException {
        int code = 0;
        code = borrowDao.deleteBorrow(ids);
        return code;
    }

    @Override
    public int updateBorrow(Borrow borrow) throws SQLException {
        int code = 0;
        code = borrowDao.updateBorrow(borrow);
        return code;
    }

    @Override
    public int addBorrow(Borrow borrow) throws SQLException {
        int code = 0;
        code = borrowDao.addBorrow(borrow);
        return code;
    }

    @Override
    public Borrow findBorrow(Borrow borrow) throws SQLException {
        Borrow borrowResult = null;
        borrowResult = borrowDao.findBorrow(borrow);

        return borrowResult;
    }

    @Override
    public int dueBorrow(Borrow borrow) throws SQLException {
        int code = 0;
        code = borrowDao.dueBorrow(borrow);

        return code;
    }

    @Override
    public AllEntity findUserBorrow(Borrow borrow) throws SQLException {
        AllEntity allEntity = null;
        allEntity = borrowDao.findUserBorrow(borrow);

        return allEntity;
    }

    @Override
    public AllEntity findUserDueBorrow(Borrow borrow) throws SQLException {
        AllEntity allEntity = null;
        allEntity = borrowDao.findUserDueBorrow(borrow);

        return allEntity;
    }

}
