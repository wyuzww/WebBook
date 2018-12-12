package com.ethan.dao.impl;

import com.ethan.dao.TicketDao;
import com.ethan.entity.Ticket;
import com.ethan.utils.StringUtil;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangwenyu on 2018/12/10.
 */
public class TicketDaoImpl extends BaseDao implements TicketDao {
    @Override
    public List<Ticket> allTicket(Ticket ticket, int page, int rows) throws SQLException {
        String sql = "select * from ticket where 1=1";

        if (StringUtil.isNotEmpty(ticket.getTicket_bcid())) {
            sql += " and ticket.ticket_bcid = " + ticket.getTicket_bcid() + " ";
        }

        if (page > 0 && rows > 0) {
            sql += " limit " + (page - 1) * rows + "," + rows;
        }
        List<Ticket> tickets = queryRunner.query(sql, new BeanListHandler<Ticket>(Ticket.class));
        return tickets;
    }

    @Override
    public int deleteTicket(String ids) throws SQLException {
        String sql = "delete from ticket where ticket_id in(" + ids + ")";
        int code = 0;
        code = queryRunner.execute(sql);
        return code;
    }

    @Override
    public int updateTicket(Ticket ticket) throws SQLException {
        String sql = "update ticket set ticket_id=?,ticket_bcid=?,ticket_ISBN=?,ticket_fineMoney=?,ticket_fineDate=? where ticket_id=?";
        int code = queryRunner.update(sql, ticket.getTicket_id(), ticket.getTicket_bcid(), ticket.getTicket_ISBN(), ticket.getTicket_fineMoney(), ticket.getTicket_fineDate(), ticket.getTicket_id());
        return code;
    }

    @Override
    public int addTicket(Ticket ticket) throws SQLException {
        String sql = "insert into ticket(ticket_id,ticket_bcid,ticket_ISBN,ticket_fineMoney,ticket_fineDate values (?,?,?,?,?)";
        int code = queryRunner.update(sql, ticket.getTicket_id(), ticket.getTicket_bcid(), ticket.getTicket_ISBN(), ticket.getTicket_fineMoney(), ticket.getTicket_fineDate());
        return code;
    }
}
