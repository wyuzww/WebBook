package com.ethan.controller.borrow;

import com.alibaba.fastjson.JSON;
import com.ethan.entity.AllEntity;
import com.ethan.entity.BookCatagory;
import com.ethan.entity.BorrowCard;
import com.ethan.factory.Factory;
import com.ethan.service.BookCatagoryService;
import com.ethan.service.BorrowCardService;
import com.ethan.utils.ResponseUtil;
import com.ethan.utils.StringUtil;

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

@WebServlet("/borrowcard")
public class BorrowCardServlet extends HttpServlet {
    BorrowCard borrowCard = Factory.getBorrowCardInstance();
    BorrowCardService borrowCardService = Factory.getBorrowCardServiceInstance();

    Map<String, Object> map = new HashMap<String, Object>();
    String result = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 接收客户端信息
        String flagText = request.getParameter("flagText");

        if (flagText != null && flagText.equals("allBorrowCard")) {

            String page = request.getParameter("page");
            String rows = request.getParameter("rows");

            String borrowcard_id = request.getParameter("borrowcard_id");
            String borrowcard_blid = request.getParameter("borrowcard_blid");

            borrowCard.setBorrowcard_id(borrowcard_id);
            if (StringUtil.isNotEmpty(borrowcard_blid)) {
                borrowCard.setBorrowcard_blid(Integer.parseInt(borrowcard_blid));
            } else {
                borrowCard.setBorrowcard_blid(-1);
            }

            List<AllEntity> borrowCards = null;
            int total = 0;

            try {
                borrowCards = borrowCardService.allBorrowCard(borrowCard, Integer.parseInt(page), Integer.parseInt(rows));
                total = borrowCards.size();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            map.put("rows", borrowCards);
            map.put("total", total);

        } else if (flagText != null && flagText.equals("update")) {
            String borrowcard_id = request.getParameter("borrowcard_id");
            String borrowcard_blid = request.getParameter("borrowcard_blid");

            borrowCard.setBorrowcard_id(borrowcard_id);
            if (StringUtil.isNotEmpty(borrowcard_blid)) {
                borrowCard.setBorrowcard_blid(Integer.parseInt(borrowcard_blid));
            } else {
                borrowCard.setBorrowcard_blid(1);
            }

            int code = 0;

            try {
                code = borrowCardService.updateBC(borrowCard);
            } catch (SQLException e) {
                e.printStackTrace();
            }


            if (code > 0) {
                map.put("success", "true");
                map.put("msg", "修改成功！");
            } else {
                map.put("success", "false");
                map.put("msg", "修改失败，请重试！");
            }


        } else if (flagText != null && flagText.equals("allBorrowCardList")) {
            List<AllEntity> borrowCards = null;
            int total = 0;

            try {
                borrowCards = borrowCardService.allBorrowCard(borrowCard, 0, 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            result = JSON.toJSONString(borrowCards);
            if (result != null) {
                try {
                    ResponseUtil.write(response, result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return;

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
