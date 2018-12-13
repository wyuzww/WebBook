package com.ethan.controller.borrow;

import com.alibaba.fastjson.JSON;
import com.ethan.entity.*;
import com.ethan.factory.Factory;
import com.ethan.service.BorrowCardService;
import com.ethan.service.BorrowLevelService;
import com.ethan.service.BorrowService;
import com.ethan.service.TicketService;
import com.ethan.utils.DateUtil;
import com.ethan.utils.ResponseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangwenyu on 2018/12/6.
 */

@WebServlet("/borrow")
public class BorrowServlet extends HttpServlet {
    BorrowService borrowService = Factory.getBorrowServiceInstance();
    Borrow borrow = Factory.getBorrowInstance();
    Map<String, Object> map = new HashMap<String, Object>();
    String result = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 接收客户端信息
        String flagText = request.getParameter("flagText");

        if (flagText != null && flagText.equals("allBorrow")) {

            String page = request.getParameter("page");
            String rows = request.getParameter("rows");
            String borrow_bcid = request.getParameter("borrow_bcid");

            borrow.setBorrow_bcid(borrow_bcid);


            List<AllEntity> borrows = null;
            int total = 0;

            try {
                borrows = borrowService.allBorrow(borrow, Integer.parseInt(page), Integer.parseInt(rows));
                total = borrows.size();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            map.put("rows", borrows);
            map.put("total", total);

        } else if (flagText != null && flagText.equals("dueBook")) {
//            String delIds = request.getParameter("delIds");
            String borrow_id = request.getParameter("borrow_id");
            String borrow_dueDate = request.getParameter("borrow_dueDate");
//            Date borrow_dueDate  =new Date();
            Date borrow_expireDate = null;
            AllEntity allEntity = null;
            System.out.println(borrow_dueDate);

            borrow.setBorrow_id(Integer.parseInt(borrow_id));
            System.out.println(borrow.toString());


            try {
                allEntity = borrowService.findUserDueBorrow(borrow);
                System.out.println(allEntity);
                borrow_expireDate = allEntity.getBorrow_expireDate();
                System.out.println(borrow_expireDate);
                long days = 0;
                days = DateUtil.getDays(DateUtil.formatString(borrow_dueDate, "yyyy-MM-dd"), borrow_expireDate);
                if (days > 0) {
                    System.out.println(days);
                    float fine = allEntity.getBorrowlevel_fine() * days;

                    Ticket ticket = new Ticket(0, allEntity.getBorrow_bcid(), allEntity.getBorrow_ISBN(), fine, DateUtil.formatString(borrow_dueDate, "yyyy-MM-dd"));
                    TicketService ticketService = Factory.getTicketServiceInstance();
                    ticketService.addTicket(ticket);

                } else {
                    System.out.println(days);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            int code = 0;
            try {
                borrow = borrowService.findBorrow(borrow);
                borrow.setBorrow_dueDate(DateUtil.formatString(borrow_dueDate, "yyyy-MM-dd"));
//                borrow.setBorrow_dueDate( DateUtil.formatString(borrow_dueDate, "yyyy-MM-dd"));
                code = borrowService.dueBorrow(borrow);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (code > 0) {
                map.put("success", "true");
                map.put("msg", "还书成功！");
            } else {
                map.put("success", "false");
                map.put("msg", "还书失败，请重试！");
            }


        } else if (flagText != null && flagText.equals("borrowBook")) {
            String borrowcard_id = request.getParameter("borrowcard_id");
            String book_ISBN = request.getParameter("book_ISBN");
//            String borrow_borrowDate = request.getParameter("borrow_borrowDate");
            Date borrow_borrowDate = null;
            Date borrow_expireDate = null;
            try {
                borrow_borrowDate = DateUtil.formatString(request.getParameter("borrow_borrowDate"), "yyyy-MM-dd");
            } catch (Exception e) {
                e.printStackTrace();
            }

            AllEntity allEntity = null;
            int code = 0;
//            return;
            try {
                borrow.setBorrow_bcid(borrowcard_id);
                borrow.setBorrow_ISBN(book_ISBN);
                borrow.setBorrow_borrowDate(borrow_borrowDate);
                allEntity = borrowService.findUserBorrow(borrow);
//                System.out.println(allEntity);
                borrow_expireDate = DateUtil.addDate(borrow_borrowDate, allEntity.getBorrowlevel_days());
                borrow.setBorrow_expireDate(borrow_expireDate);
                code = borrowService.addBorrow(borrow);

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (code > 0) {
                map.put("success", "true");
                map.put("msg", "借阅成功！");
            } else {
                map.put("success", "false");
                map.put("msg", "借阅失败，请重试！");
            }


        } else if (flagText != null && flagText.equals("notDueBorrow")) {

            String page = request.getParameter("page");
            String rows = request.getParameter("rows");
            String borrow_bcid = request.getParameter("borrow_bcid");

            borrow.setBorrow_bcid(borrow_bcid);


            List<AllEntity> borrows = null;
            int total = 0;

            try {
                borrows = borrowService.notDueBorrow(borrow, Integer.parseInt(page), Integer.parseInt(rows));
                total = borrows.size();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            map.put("rows", borrows);
            map.put("total", total);

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
