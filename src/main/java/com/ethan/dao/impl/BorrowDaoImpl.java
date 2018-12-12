package com.ethan.dao.impl;

import com.ethan.dao.BorrowDao;
import com.ethan.entity.AllEntity;
import com.ethan.entity.Borrow;
import com.ethan.utils.StringUtil;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/10.
 */
public class BorrowDaoImpl extends BaseDao implements BorrowDao {
    @Override
    public List<AllEntity> allBorrow(Borrow borrow, int page, int rows) throws SQLException {
        String sql = "select * from borrow where 1=1";
        if (StringUtil.isNotEmpty(String.valueOf(borrow.getBorrow_id()))) {
            sql += " and borrow.borrow_id=" + borrow.getBorrow_id();
        }
        if (StringUtil.isNotEmpty(borrow.getBorrow_bcid())) {
            sql += " and borrow.borrow_bcid like '%" + borrow.getBorrow_bcid() + "%'";
        }
        if (StringUtil.isNotEmpty(borrow.getBorrow_ISBN())) {
            sql += " and borrow.borrow_ISBN like '%" + borrow.getBorrow_ISBN() + "%'";
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
        String sql = "insert into borrow(borrow_id,borrow_bcid,borrow_ISBN,borrow_borrowDate,borrow_exprireDate,borrow_dueDate) values(?,?,?,?,?,?)";
        int code = queryRunner.update(sql, borrow.getBorrow_id(), borrow.getBorrow_bcid(), borrow.getBorrow_ISBN(), borrow.getBorrow_borrowDate(), borrow.getBorrow_expireDate(), borrow.getBorrow_dueDate());
        return code;
    }
}
