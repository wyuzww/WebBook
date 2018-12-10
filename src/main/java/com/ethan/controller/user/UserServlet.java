package com.ethan.controller.user;

import com.alibaba.fastjson.JSON;
import com.ethan.entity.User;
import com.ethan.factory.Factory;
import com.ethan.service.impl.UserServiceImpl;
import com.ethan.utils.Md5Util;
import com.ethan.utils.ResponseUtil;
import com.ethan.utils.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    User user = Factory.getUser();
    UserServiceImpl userService = Factory.getUserServiceInstance();
    Map<String, Object> map = new HashMap<String, Object>();
    String result = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 接收客户端信息

        String flagText = request.getParameter("flagText");

        if (flagText != null && flagText.equals("login")) {

            String account = request.getParameter("user_account");
            String password = request.getParameter("user_password");

            System.out.println(account);
            System.out.println(password);

            if (StringUtil.isEmpty(account) || StringUtil.isEmpty(password)) {
                request.setAttribute("error", "用户名或密码为空！");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
                return;
            }

            user.setUser_account(account);
            user.setUser_password(password);

            System.out.println(user.toString());

            User currentUser = null;
            try {
                currentUser = userService.login(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (currentUser == null) {
                request.setAttribute("error", "用户名或密码错误！");
//                response.sendRedirect("login");
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
                return;
            } else {
                // 获取Session
                HttpSession session = request.getSession();
                session.setAttribute("currentUser", currentUser);
                System.out.println(currentUser.toString());
                // 客户端跳转
                response.sendRedirect("main");
            }
        } else if (flagText != null && flagText.equals("allUser")) {
            String page = request.getParameter("page");
            String rows = request.getParameter("rows");
            String account = request.getParameter("user_account");
            String name = request.getParameter("user_name");
//            System.out.println(account);
//            System.out.println(name);
            user.setUser_account(account);
            user.setUser_name(name);

            List<User> users = null;
            int total = 0;
            try {
                users = userService.allUser(user, Integer.parseInt(page), Integer.parseInt(rows));
                total = userService.userCount(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            map.put("rows", users);
            map.put("total", total);

        } else if (flagText != null && flagText.equals("delete")) {
            String delIds = request.getParameter("delIds");

            int delNums = 0;
            try {
                delNums = userService.deleteUser(delIds);
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

        } else if (flagText != null && flagText.equals("add")) {
            String account = request.getParameter("user_account");
            String password = request.getParameter("user_password");
            String name = request.getParameter("user_name");
            String sex = request.getParameter("user_sex");
            String phone = request.getParameter("user_phone");
            String level = request.getParameter("user_level");

            user.setUser_account(account);
            user.setUser_password(password);
            user.setUser_name(name);
            user.setUser_sex(sex);
            user.setUser_phone(phone);
            user.setUser_level(level);

            boolean isAccount = false;
            try {
                isAccount = userService.isAccount(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (isAccount) {
                map.put("success", "false");
                map.put("msg", "此帐号已存在！");
            } else {
                int code = 0;
                try {
                    code = userService.addUser(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (code > 0) {
                    map.put("success", "true");
                    map.put("msg", "添加成功！");
                } else {
                    map.put("success", "false");
                    map.put("msg", "添加失败，请重试！");
                }
            }

        } else if (flagText != null && flagText.equals("update")) {
            String old_user = request.getParameter("old_user");
            String old_password = request.getParameter("old_password");
            String id = request.getParameter("user_id");
            String account = request.getParameter("user_account");
            String password = request.getParameter("user_password");
            String name = request.getParameter("user_name");
            String sex = request.getParameter("user_sex");
            String phone = request.getParameter("user_phone");
            String level = request.getParameter("user_level");

            if (Md5Util.md5Encode(old_password).equals(password)) {
                password = old_password;
            }

            user.setUser_id(Integer.parseInt(id));
            user.setUser_account(account);
            user.setUser_password(password);
            user.setUser_name(name);
            user.setUser_sex(sex);
            user.setUser_phone(phone);
            user.setUser_level(level);


            boolean isAccount = false;
            if (!old_user.equals(account)) {
                try {
                    isAccount = userService.isAccount(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


            if (isAccount) {
                map.put("success", "false");
                map.put("msg", "此帐号已存在！");
            } else {
                int code = 0;
                try {
                    code = userService.update(user);
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
