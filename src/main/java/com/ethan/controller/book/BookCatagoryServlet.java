package com.ethan.controller.book;

import com.alibaba.fastjson.JSON;
import com.ethan.entity.BookCatagory;
import com.ethan.entity.Book_Catagory_Room;
import com.ethan.factory.Factory;
import com.ethan.service.BookCatagoryService;
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

@WebServlet("/bookcatagory")
public class BookCatagoryServlet extends HttpServlet {
    BookCatagory bookCatagory = Factory.getBookCatagoryInstance();
    BookCatagoryService bookCatagoryService = Factory.getBookCatagoryServiceInstance();

    Map<String, Object> map = new HashMap<String, Object>();
    String result = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 接收客户端信息
        String flagText = request.getParameter("flagText");

        if (flagText != null && flagText.equals("allBookCatagory")) {

            String page = request.getParameter("page");
            String rows = request.getParameter("rows");

            String bookcatagory_name = request.getParameter("bookcatagory_name");
            bookCatagory.setBookcatagory_name(bookcatagory_name);

//            System.out.println(book_catagory_room.toString());

            List<Book_Catagory_Room> book_catagory_rooms = null;
            int total = 0;

            try {
                book_catagory_rooms = bookCatagoryService.allCatagory(bookCatagory, Integer.parseInt(page), Integer.parseInt(rows));
                total = book_catagory_rooms.size();
            } catch (SQLException e) {
                e.printStackTrace();
            }


            map.put("rows", book_catagory_rooms);
            map.put("total", total);

        } else if (flagText != null && flagText.equals("update")) {
            String bookcatagory_id = request.getParameter("bookcatagory_id");
            String bookcatagory_name = request.getParameter("bookcatagory_name");
            String bookcatagory_brid = request.getParameter("bookcatagory_brid");
            String bookcatagory_demo = request.getParameter("bookcatagory_demo");

            bookCatagory = new BookCatagory(Integer.parseInt(bookcatagory_id), bookcatagory_name, Integer.parseInt(bookcatagory_brid), bookcatagory_demo);

            int code = 0;

            try {
                code = bookCatagoryService.updateBC(bookCatagory);
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

            String bookcatagory_name = request.getParameter("bookcatagory_name");
            String bookcatagory_brid = request.getParameter("bookcatagory_brid");
            String bookcatagory_demo = request.getParameter("bookcatagory_demo");

            bookCatagory = new BookCatagory(-1, bookcatagory_name, Integer.parseInt(bookcatagory_brid), bookcatagory_demo);

            int code = 0;

            try {
                code = bookCatagoryService.addBC(bookCatagory);
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

            try {
                delNums = bookCatagoryService.deleteBC(delIds);
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

        } else if (flagText != null && flagText.equals("allBookCatagoryList")) {

            List<Book_Catagory_Room> bookCatagories = null;
            int total = 0;

            try {
                bookCatagories = bookCatagoryService.allCatagory(bookCatagory, 0, 0);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Book_Catagory_Room book_catagory_room = new Book_Catagory_Room();
            book_catagory_room.setBookcatagory_id(-1);
            book_catagory_room.setBookcatagory_name("");

            bookCatagories.add(0, book_catagory_room);

            result = JSON.toJSONString(bookCatagories);
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
