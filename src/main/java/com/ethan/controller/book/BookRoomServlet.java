package com.ethan.controller.book;

import com.alibaba.fastjson.JSON;
import com.ethan.entity.BookRoom;
import com.ethan.factory.Factory;
import com.ethan.service.BookRoomService;
import com.ethan.service.BookService;
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

@WebServlet("/bookroom")
public class BookRoomServlet extends HttpServlet {
    BookRoomService bookRoomService = Factory.getBookRoomServiceInstance();
    BookRoom bookRoom = Factory.getBookRoomInstance();
    BookService bookService = Factory.getBookServiceInstance();

    Map<String, Object> map = new HashMap<String, Object>();
    String result = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 接收客户端信息
        String flagText = request.getParameter("flagText");

        if (flagText != null && flagText.equals("allBookRoom")) {
            String page = request.getParameter("page");
            String rows = request.getParameter("rows");

            String bookroom_name = request.getParameter("bookroom_name");
            bookRoom.setBookroom_name(bookroom_name);

            List<BookRoom> bookRooms = null;
            int total = 0;


            try {
                bookRooms = bookRoomService.allBookRoom(bookRoom, Integer.parseInt(page), Integer.parseInt(rows));
                total = bookRooms.size();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            map.put("rows", bookRooms);
            map.put("total", total);

        } else if (flagText != null && flagText.equals("update")) {
            String bookroom_id = request.getParameter("bookroom_id");
            String bookroom_name = request.getParameter("bookroom_name");
            String bookroom_phone = request.getParameter("bookroom_phone");
            String bookroom_address = request.getParameter("bookroom_address");

            bookRoom = new BookRoom(Integer.parseInt(bookroom_id), bookroom_name, bookroom_phone, bookroom_address);

            int code = 0;
            try {
                code = bookRoomService.updateBR(bookRoom);
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


        } else if (flagText != null && flagText.equals("add")) {

            String bookroom_name = request.getParameter("bookroom_name");
            String bookroom_phone = request.getParameter("bookroom_phone");
            String bookroom_address = request.getParameter("bookroom_address");

            bookRoom = new BookRoom(-1, bookroom_name, bookroom_phone, bookroom_address);

            int code = 0;

            try {
                code = bookRoomService.addBR(bookRoom);
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


        } else if (flagText != null && flagText.equals("delete")) {

            String delIds = request.getParameter("delIds");

            int delNums = 0;

            if (delNums > 0) {
                map.put("success", "true");
                map.put("msg", "删除成功！");
                map.put("delNums", delNums);
            } else {
                map.put("success", "false");
                map.put("msg", "删除失败，请重试！");
            }

        } else if (flagText != null && flagText.equals("allBookRoomList")) {

            List<BookRoom> bookRooms = null;
            int total = 0;
            try {
                bookRooms = bookRoomService.allBookRoom(bookRoom, 0, 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            bookRoom.setBookroom_id(-1);
            bookRoom.setBookroom_name("");

            bookRooms.add(0, bookRoom);

            result = JSON.toJSONString(bookRooms);
            if (result != null) {
                try {
                    ResponseUtil.write(response, result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return;

        } else {
            request.setAttribute("error", "选项不能为空！");
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
