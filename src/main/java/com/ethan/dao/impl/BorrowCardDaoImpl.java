package com.ethan.dao.impl;

import com.ethan.dao.BorrowCardDao;
import com.ethan.entity.AllEntity;
import com.ethan.entity.BorrowCard;
import com.ethan.utils.StringUtil;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/10.
 */
public class BorrowCardDaoImpl extends BaseDao implements BorrowCardDao {
    @Override
    public List<AllEntity> allBorrowCard(BorrowCard borrowCard, int page, int rows) throws SQLException {
        String sql = "select * from borrowcard,user,borrowlevel where borrowcard.borrowcard_rid = user.user_id and borrowcard.borrowcard_blid = borrowlevel.borrowlevel_id ";
        if (StringUtil.isNotEmpty(borrowCard.getBorrowcard_id())) {
            sql += " and borrowcard.borrowcard_id like '%" + borrowCard.getBorrowcard_id() + "%' ";
        }
        if (borrowCard.getBorrowcard_blid() > 0) {
            sql += " and borrowcard.borrowcard_blid=" + borrowCard.getBorrowcard_blid() + " ";
        }
        if (page > 0 && rows > 0) {
            sql += " limit " + (page - 1) * rows + "," + rows;
        }
        List<AllEntity> book_catagory_rooms = queryRunner.query(sql, new BeanListHandler<AllEntity>(AllEntity.class));
        return book_catagory_rooms;
    }

    @Override
    public int updateBC(BorrowCard borrowCard) throws SQLException {
        String sql = "update borrowcard set borrowcard_blid=? where borrowcard_id=?";
        int code = queryRunner.update(sql, borrowCard.getBorrowcard_blid(), borrowCard.getBorrowcard_id());


        return code;
    }

    @Override
    public int deleteBC(String ids) throws SQLException {
        String sql = "delete from borrowcard where borrowcard_id in(" + ids + ") ";
        int code = queryRunner.execute(sql);
        return code;
    }

    @Override
    public int addBC(BorrowCard borrowCard) throws SQLException {
        String sql = "insert into borrowcard (borrowcard_id,borrowcard_rid,borrowcard_quantity,borrowcard_blid) values(?,?,?,?)";
        int code = queryRunner.update(sql, borrowCard.getBorrowcard_id(), borrowCard.getBorrowcard_rid(), borrowCard.getBorrowcard_quantity(), borrowCard.getBorrowcard_blid());
        return code;
    }
}
