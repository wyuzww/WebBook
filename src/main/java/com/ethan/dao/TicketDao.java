package com.ethan.dao;

import com.ethan.entity.AllEntity;
import com.ethan.entity.Ticket;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/10.
 */
public interface TicketDao {
    public List<Ticket> allTicket(Ticket ticket, int page, int rows) throws SQLException;

    public int deleteTicket(String ids) throws SQLException;

    public int updateTicket(Ticket ticket) throws SQLException;

    public int addTicket(Ticket ticket) throws SQLException;
}
