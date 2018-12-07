package com.ethan.controller.user;

import com.ethan.entity.User;
import com.ethan.factory.Factory;
import com.ethan.service.impl.UserServiceImpl;
import com.ethan.utils.ResponseUtil;
import com.ethan.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 接收客户端信息

        String flagText = request.getParameter("flagText");

        System.out.println(flagText);

        if (flagText != null && flagText.equals("login")) {
            String account = request.getParameter("account");
            String password = request.getParameter("password");
            if (StringUtil.isEmpty(account) || StringUtil.isEmpty(password)) {
                request.setAttribute("error", "用户名或密码为空！");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
                return;
            }
            UserServiceImpl userService = Factory.getUserServiceInstance();
            User user = new User(0, account, password, "", "", "", "", 1);
            User currentUser = userService.login(user);
            if (currentUser == null) {
                request.setAttribute("error", "用户名或密码错误！");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
            } else {
                // 获取Session
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", currentUser);
                // 客户端跳转
                response.sendRedirect("main");
            }
        } else if (flagText != null && flagText.equals("allUser")) {
            String users = "{\"rows\":[{\n" +
                    "                \"id\":1,\n" +
                    "                \"account\":\"admin\",\n" +
                    "                \"password\":\"123456\",\n" +
                    "                \"name\":\"ADMIN\",\n" +
                    "                \"sex\":\"男\",\n" +
                    "                \"phone\":\"123456789\",\n" +
                    "                \"level\":1\n" +
                    "            },{\"id\":2,\n" +
                    "                \"account\":\"admin\",\n" +
                    "                \"password\":\"123456\",\n" +
                    "                \"name\":\"ADMIN\",\n" +
                    "                \"sex\":\"男\",\n" +
                    "                \"phone\":\"123456789\",\n" +
                    "                \"level\":5}]\n" +
                    ",\"total\":25}";
            try {
                System.out.println(users.toString());
                ResponseUtil.write(response, users);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (flagText != null && flagText.equals("delete")) {
            String result = "{\"success\":\"true\",\"delNums\":1}";
//            String result = "{\"success\":\"false\",\"errorMsg\":\"error\"}";
            System.out.println(result);
            try {
                ResponseUtil.write(response, result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (flagText != null && flagText.equals("add")) {
            String result = "{\"success\":\"false\",\"errorMsg\":\"error1\"}";
//            String result = "{\"errorMsg\":\"error\"}";
            System.out.println(result);

            try {
                ResponseUtil.write(response, result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (flagText != null && flagText.equals("update")) {
            String result = "{\"success\":\"true\",\"errorMsg\":\"error2\"}";
//            String result = "{\"errorMsg\":\"error\"}";
            System.out.println(result);
            try {
                ResponseUtil.write(response, result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("error");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
