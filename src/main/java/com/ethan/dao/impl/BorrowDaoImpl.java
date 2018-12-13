package com.ethan.dao.impl;

import com.ethan.dao.BorrowDao;
import com.ethan.entity.AllEntity;
import com.ethan.entity.Borrow;
import com.ethan.utils.StringUtil;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/10.
 */
public class BorrowDaoImpl extends BaseDao implements BorrowDao {
    @Override
    public List<AllEntity> allBorrow(Borrow borrow, int page, int rows) throws SQLException {
        String sql = " select * from borrow,user,borrowcard,book where borrow.borrow_bcid=borrowcard.borrowcard_id and borrowcard.borrowcard_rid=user.user_id and borrow.borrow_ISBN = book.book_ISBN ";

        if (StringUtil.isNotEmpty(borrow.getBorrow_bcid())) {
            sql += " and borrow.borrow_bcid like '%" + borrow.getBorrow_bcid() + "%'";
        }

        if (page > 0 && rows > 0) {
            sql += " limit " + (page - 1) * rows + "," + rows;
        }
        List<AllEntity> borrows = queryRunner.query(sql, new BeanListHandler<AllEntity>(AllEntity.class));
        return borrows;
    }

    @Override
    public List<AllEntity> notDueBorrow(Borrow borrow, int page, int rows) throws SQLException {
        String sql = " select * from borrow,user,borrowcard,book where borrow.borrow_bcid=borrowcard.borrowcard_id and borrowcard.borrowcard_rid=user.user_id and borrow.borrow_ISBN = book.book_ISBN and borrow.borrow_dueDate is null ";

        if (StringUtil.isNotEmpty(borrow.getBorrow_bcid())) {
            sql += " and borrow.borrow_bcid like '%" + borrow.getBorrow_bcid() + "%'";
        }

        if (page > 0 && rows > 0) {
            sql += " limit " + (page - 1) * rows + "," + rows;
        }
        List<AllEntity> borrows = queryRunner.query(sql, new BeanListHandler<AllEntity>(AllEntity.class));
        return borrows;
    }

    @Override
    public int deleteBorrow(String ids) throws SQLException {
        String sql = "delete from borrow where borrow_id in(" + ids + ")";
        int code = queryRunner.execute(sql);
        return code;
    }

    @Override
    public int updateBorrow(Borrow borrow) throws SQLException {
        String sql = "update borrow set borrow_id=?,borrow_bcid=?,borrow_ISBN=?,borrow_borrowDate=?,borrow_exprireDate=?,borrow_dueDate=? where borrow_id=?";
        int code = queryRunner.update(sql, borrow.getBorrow_id(), borrow.getBorrow_bcid(), borrow.getBorrow_ISBN(), borrow.getBorrow_borrowDate(), borrow.getBorrow_expireDate(), borrow.getBorrow_dueDate(), borrow.getBorrow_id());


        return code;
    }

    @Override
    public int addBorrow(Borrow borrow) throws SQLException {
        String sql = "insert into borrow(borrow_bcid,borrow_ISBN,borrow_borrowDate,borrow_expireDate) values(?,?,?,?)";
        int code = queryRunner.update(sql, borrow.getBorrow_bcid(), borrow.getBorrow_ISBN(), borrow.getBorrow_borrowDate(), borrow.getBorrow_expireDate());

        String sql1 = "update book set book_inNumber=book_inNumber-1 where book_ISBN=?";
        queryRunner.update(sql1, borrow.getBorrow_ISBN());

        String sql2 = "update borrowcard set borrowcard_quantity=borrowcard_quantity+1 where borrowcard_id=?";
        queryRunner.update(sql2, borrow.getBorrow_bcid());

        return code;
    }

    @Override
    public int dueBorrow(Borrow borrow) throws SQLException {
        String sql = "update borrow set borrow_dueDate=? where borrow_id=?";
        int code = queryRunner.update(sql, borrow.getBorrow_dueDate(), borrow.getBorrow_id());

        String sql1 = "update book set book_inNumber=book_inNumber+1 where book_ISBN=?";
        queryRunner.update(sql1, borrow.getBorrow_ISBN());

        String sql2 = "update borrowcard set borrowcard_quantity=borrowcard_quantity-1 where borrowcard_id=?";
        queryRunner.update(sql2, borrow.getBorrow_bcid());

        return code;
    }

    @Override
    public Borrow findBorrow(Borrow borrow) throws SQLException {
        Borrow borrowResult = null;
        String sql = "select * from borrow where borrow_id=? ";
        borrowResult = queryRunner.query(sql, new BeanHandler<Borrow>(Borrow.class), borrow.getBorrow_id());

        return borrowResult;
    }

    @Override
    public AllEntity findUserBorrow(Borrow borrow) throws SQLException {
        AllEntity allEntity = null;
        String sql = " select * from borrowcard,borrowlevel where borrowcard.borrowcard_blid=borrowlevel.borrowlevel_id and borrowcard.borrowcard_id=? ";

        allEntity = queryRunner.query(sql, new BeanHandler<AllEntity>(AllEntity.class), borrow.getBorrow_bcid());
        return allEntity;
    }

    @Override
    public AllEntity findUserDueBorrow(Borrow borrow) throws SQLException {
        AllEntity allEntity = null;
        String sql = " select * from borrow,borrowcard,borrowlevel where borrow.borrow_bcid=borrowcard.borrowcard_id and borrowcard.borrowcard_blid=borrowlevel.borrowlevel_id and borrow.borrow_id=? ";

        allEntity = queryRunner.query(sql, new BeanHandler<AllEntity>(AllEntity.class), borrow.getBorrow_id());
        return allEntity;
    }
}
