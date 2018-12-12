package com.ethan.service.impl;

import com.ethan.dao.TicketDao;
import com.ethan.entity.Ticket;
import com.ethan.factory.Factory;
import com.ethan.service.TicketService;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/10.
 */
public class TicketServiceImpl implements TicketService {
    TicketDao ticketDao = Factory.getTicketDaoInstance();

    @Override
    public List<Ticket> allTicket(Ticket ticket, int page, int rows) throws SQLException {
        List<Ticket> alltickets = null;
        alltickets = ticketDao.allTicket(ticket, page, rows);
        return alltickets;
    }

    @Override
    public int deleteTicket(String ids) throws SQLException {
        int code = 0;
        code = ticketDao.deleteTicket(ids);
        return code;
    }

    @Override
    public int updateTicket(Ticket ticket) throws SQLException {
        int code = 0;
        code = ticketDao.updateTicket(ticket);
        return code;
    }

    @Override
    public int addTicket(Ticket ticket) throws SQLException {
        int code = 0;
        code = ticketDao.addTicket(ticket);
        return code;
    }
}
