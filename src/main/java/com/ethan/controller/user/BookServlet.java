package com.ethan.controller.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhangwenyu on 2018/12/6.
 */

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 接收客户端信息
        String flagText = request.getParameter("flagText");
        if (flagText != null && flagText.equals("allBook")) {

        } else if (flagText != null && flagText.equals("find")) {

        } else if (flagText != null && flagText.equals("update")) {

        } else if (flagText != null && flagText.equals("add")) {

        } else if (flagText != null && flagText.equals("delete")) {

        } else {
            request.setAttribute("error", "选项不能为空！");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
