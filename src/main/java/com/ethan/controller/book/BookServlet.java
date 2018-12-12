package com.ethan.controller.book;

import com.alibaba.fastjson.JSON;
import com.ethan.entity.Book;
import com.ethan.entity.AllEntity;
import com.ethan.factory.Factory;
import com.ethan.service.BookService;
import com.ethan.utils.DateUtil;
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

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    Book book = Factory.getBook();
    BookService bookService = Factory.getBookServiceInstance();

    Map<String, Object> map = new HashMap<String, Object>();
    String result = null;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 接收客户端信息
        String flagText = request.getParameter("flagText");

        if (flagText != null && flagText.equals("allBook")) {
            String page = request.getParameter("page");
            String rows = request.getParameter("rows");
            String book_ISBN = request.getParameter("book_ISBN");
            String book_name = request.getParameter("book_name");
            String book_author = request.getParameter("book_author");
            String book_catagoryId = request.getParameter("book_catagoryId");//int

            book.setBook_ISBN(book_ISBN);
            book.setBook_name(book_name);
            book.setBook_author(book_author);
            if (StringUtil.isNotEmpty(book_catagoryId)) {
                book.setBook_catagoryId(Integer.parseInt(book_catagoryId));
            } else {
                book.setBook_catagoryId(-1);
            }

//            System.out.println(book_catagory_room.toString());

            List<AllEntity> book_catagory_rooms = null;
            int total = 0;


            try {
                book_catagory_rooms = bookService.allBook(book, Integer.parseInt(page), Integer.parseInt(rows));
                total = book_catagory_rooms.size();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            map.put("rows", book_catagory_rooms);
            map.put("total", total);

        } else if (flagText != null && flagText.equals("update")) {
            String book_ISBN = request.getParameter("book_ISBN");
            String book_name = request.getParameter("book_name");
            String book_author = request.getParameter("book_author");
            String book_catagoryId = request.getParameter("book_catagoryId");
            String book_price = request.getParameter("book_price");
            String book_publish = request.getParameter("book_publish");
            String book_stockNumber = request.getParameter("book_stockNumber");
            String book_publishDate = request.getParameter("book_publishDate");
            String book_storageDate = request.getParameter("book_storageDate");
            String book_inNumber = request.getParameter("book_inNumber");

            int code = 0;
            try {
                Book book = new Book(book_ISBN, Integer.parseInt(book_catagoryId), book_name, book_publish, book_author, DateUtil.formatString(book_publishDate, "yyyy-MM-dd"), Float.valueOf(book_price), DateUtil.formatString(book_storageDate, "yyyy-MM-dd"), Integer.parseInt(book_stockNumber), Integer.parseInt(book_inNumber));
                code = bookService.update(book);
            } catch (Exception e) {
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

            String book_ISBN = request.getParameter("book_ISBN");
            String book_name = request.getParameter("book_name");
            String book_author = request.getParameter("book_author");
            String book_catagoryId = request.getParameter("book_catagoryId");
            String book_price = request.getParameter("book_price");
            String book_publish = request.getParameter("book_publish");
            String book_stockNumber = request.getParameter("book_stockNumber");
            String book_publishDate = request.getParameter("book_publishDate");
            String book_storageDate = request.getParameter("book_storageDate");
            String book_inNumber = request.getParameter("book_inNumber");

//            Book_Catagory_Room book_catagory_room = new Book_Catagory_Room(book_ISBN,bbook_inNumber)

            int code = 0;
            try {
                Book book = new Book(book_ISBN, Integer.parseInt(book_catagoryId), book_name, book_publish, book_author, DateUtil.formatString(book_publishDate, "yyyy-MM-dd"), Float.valueOf(book_price), DateUtil.formatString(book_storageDate, "yyyy-MM-dd"), Integer.parseInt(book_stockNumber), Integer.parseInt(book_inNumber));
                code = bookService.addBook(book);
            } catch (Exception e) {
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
                delNums = bookService.deleteBook(delIds);
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
