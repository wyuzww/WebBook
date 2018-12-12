package com.ethan.controller.borrow;

import com.alibaba.fastjson.JSON;
import com.ethan.entity.BookRoom;
import com.ethan.entity.BorrowLevel;
import com.ethan.factory.Factory;
import com.ethan.service.BookRoomService;
import com.ethan.service.BookService;
import com.ethan.service.BorrowLevelService;
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

@WebServlet("/borrowlevel")
public class BorrowLevelServlet extends HttpServlet {
    BorrowLevelService borrowLevelService = Factory.getBorrowLevelServiceInstance();
    BorrowLevel borrowLevel = Factory.getBorrowLevelInstance();
    Map<String, Object> map = new HashMap<String, Object>();
    String result = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 接收客户端信息
        String flagText = request.getParameter("flagText");

        if (flagText != null && flagText.equals("allBorrowLevel")) {
            String page = request.getParameter("page");
            String rows = request.getParameter("rows");


            List<BorrowLevel> borrowLevels = null;
            int total = 0;


            try {
                borrowLevels = borrowLevelService.allBorroeLevel(borrowLevel, 0, 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            map.put("rows", borrowLevels);
            map.put("total", total);

        } else if (flagText != null && flagText.equals("update")) {

        } else if (flagText != null && flagText.equals("add")) {

        } else if (flagText != null && flagText.equals("delete")) {

        } else if (flagText != null && flagText.equals("allBorrowLevelList")) {

            List<BorrowLevel> borrowLevels = null;
            int total = 0;
            try {
                borrowLevels = borrowLevelService.allBorroeLevel(borrowLevel, 0, 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            borrowLevel.setBorrowlevel_id(-1);
            borrowLevel.setBorrowlevel_name("");

            borrowLevels.add(0, borrowLevel);

            result = JSON.toJSONString(borrowLevels);
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
