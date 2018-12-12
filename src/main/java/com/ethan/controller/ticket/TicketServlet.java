package com.ethan.controller.ticket;

import com.alibaba.fastjson.JSON;
import com.ethan.entity.BookRoom;
import com.ethan.entity.Ticket;
import com.ethan.factory.Factory;
import com.ethan.service.BookRoomService;
import com.ethan.service.BookService;
import com.ethan.service.TicketService;
import com.ethan.utils.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangwenyu on 2018/12/6.
 */

@WebServlet("/ticket")
public class TicketServlet extends HttpServlet {
    TicketService ticketService = Factory.getTicketServiceInstance();
    Ticket ticket = Factory.getTicketInstance();

    Map<String, Object> map = new HashMap<String, Object>();
    String result = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 接收客户端信息
        String flagText = request.getParameter("flagText");

        if (flagText != null && flagText.equals("allTicket")) {
            String page = request.getParameter("page");
            String rows = request.getParameter("rows");

            String ticket_bcid = request.getParameter("ticket_bcid");
            ticket.setTicket_bcid(ticket_bcid);

            List<Ticket> tickets = null;
            int total = 0;


            try {
                tickets = ticketService.allTicket(ticket, Integer.parseInt(page), Integer.parseInt(rows));
                total = tickets.size();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            map.put("rows", tickets);
            map.put("total", total);

        } else if (flagText != null && flagText.equals("update")) {

        } else if (flagText != null && flagText.equals("add")) {

        } else if (flagText != null && flagText.equals("delete")) {

            String delIds = request.getParameter("delIds");

            int delNums = 0;

            try {
                delNums = ticketService.deleteTicket(delIds);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (delNums > 0) {
                map.put("success", "true");
                map.put("msg", "删除成功！");
                map.put("delNums", delNums);
            } else {
                map.put("success", "false");
                map.put("msg", "删除失败，请重试！");
            }

        } else {
            response.sendRedirect("error");
        }

        result = JSON.toJSONString(map);
        map.clear();
        if (result != null) {
            try {
                ResponseUtil.write(response, result);
                result = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return;


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
