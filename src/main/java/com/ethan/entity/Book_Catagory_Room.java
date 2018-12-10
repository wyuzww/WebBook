package com.ethan.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by zhangwenyu on 2018/12/8.
 */
public class Book_Catagory_Room {
    private int bookroom_id;
    private String bookroom_name;
    private String bookroom_address;
    private String bookroom_phone;

    private int bookcatagory_id;
    private String bookcatagory_name;
    private int bookcatagory_brid;
    private String bookcatagory_demo;

    private String book_ISBN;
    private int book_catagoryId = -1;
    private String book_name;
    private String book_publish;
    private String book_author;
    @JSONField(format = "yyyy-MM-dd")
    private Date book_publishDate;
    private float book_price;
    @JSONField(format = "yyyy-MM-dd")
    private Date book_storageDate;
    private int book_stockNumber;
    private int book_inNumber;


    public Book_Catagory_Room() {
    }

    public Book_Catagory_Room(int bookroom_id, String bookroom_name, String bookroom_address, String bookroom_phone, int bookcatagory_id, String bookcatagory_name, int bookcatagory_brid, String bookcatagory_demo, String book_ISBN, int book_catagoryId, String book_name, String book_publish, String book_author, Date book_publishDate, float book_price, Date book_storageDate, int book_stockNumber, int book_inNumber) {
        this.bookroom_id = bookroom_id;
        this.bookroom_name = bookroom_name;
        this.bookroom_address = bookroom_address;
        this.bookroom_phone = bookroom_phone;
        this.bookcatagory_id = bookcatagory_id;
        this.bookcatagory_name = bookcatagory_name;
        this.bookcatagory_brid = bookcatagory_brid;
        this.bookcatagory_demo = bookcatagory_demo;
        this.book_ISBN = book_ISBN;
        this.book_catagoryId = book_catagoryId;
        this.book_name = book_name;
        this.book_publish = book_publish;
        this.book_author = book_author;
        this.book_publishDate = book_publishDate;
        this.book_price = book_price;
        this.book_storageDate = book_storageDate;
        this.book_stockNumber = book_stockNumber;
        this.book_inNumber = book_inNumber;
    }


    public int getBookroom_id() {
        return bookroom_id;
    }

    public void setBookroom_id(int bookroom_id) {
        this.bookroom_id = bookroom_id;
    }

    public String getBookroom_name() {
        return bookroom_name;
    }

    public void setBookroom_name(String bookroom_name) {
        this.bookroom_name = bookroom_name;
    }

    public String getBookroom_address() {
        return bookroom_address;
    }

    public void setBookroom_address(String bookroom_address) {
        this.bookroom_address = bookroom_address;
    }

    public String getBookroom_phone() {
        return bookroom_phone;
    }

    public void setBookroom_phone(String bookroom_phone) {
        this.bookroom_phone = bookroom_phone;
    }

    public int getBookcatagory_id() {
        return bookcatagory_id;
    }

    public void setBookcatagory_id(int bookcatagory_id) {
        this.bookcatagory_id = bookcatagory_id;
    }

    public String getBookcatagory_name() {
        return bookcatagory_name;
    }

    public void setBookcatagory_name(String bookcatagory_name) {
        this.bookcatagory_name = bookcatagory_name;
    }

    public int getBookcatagory_brid() {
        return bookcatagory_brid;
    }

    public void setBookcatagory_brid(int bookcatagory_brid) {
        this.bookcatagory_brid = bookcatagory_brid;
    }

    public String getBookcatagory_demo() {
        return bookcatagory_demo;
    }

    public void setBookcatagory_demo(String bookcatagory_demo) {
        this.bookcatagory_demo = bookcatagory_demo;
    }

    public String getBook_ISBN() {
        return book_ISBN;
    }

    public void setBook_ISBN(String book_ISBN) {
        this.book_ISBN = book_ISBN;
    }

    public int getBook_catagoryId() {
        return book_catagoryId;
    }

    public void setBook_catagoryId(int book_catagoryId) {
        this.book_catagoryId = book_catagoryId;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_publish() {
        return book_publish;
    }

    public void setBook_publish(String book_publish) {
        this.book_publish = book_publish;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public Date getBook_publishDate() {
        return book_publishDate;
    }

    public void setBook_publishDate(Date book_publishDate) {
        this.book_publishDate = book_publishDate;
    }

    public float getBook_price() {
        return book_price;
    }

    public void setBook_price(float book_price) {
        this.book_price = book_price;
    }

    public Date getBook_storageDate() {
        return book_storageDate;
    }

    public void setBook_storageDate(Date book_storageDate) {
        this.book_storageDate = book_storageDate;
    }

    public int getBook_stockNumber() {
        return book_stockNumber;
    }

    public void setBook_stockNumber(int book_stockNumber) {
        this.book_stockNumber = book_stockNumber;
    }

    public int getBook_inNumber() {
        return book_inNumber;
    }

    public void setBook_inNumber(int book_inNumber) {
        this.book_inNumber = book_inNumber;
    }

    @Override
    public String toString() {
        return "Book_Catagory_Room{" +
                "bookroom_id=" + bookroom_id +
                ", bookroom_name='" + bookroom_name + '\'' +
                ", bookroom_address='" + bookroom_address + '\'' +
                ", bookroom_phone='" + bookroom_phone + '\'' +
                ", bookcatagory_id=" + bookcatagory_id +
                ", bookcatagory_name='" + bookcatagory_name + '\'' +
                ", bookcatagory_brid=" + bookcatagory_brid +
                ", bookcatagory_demo='" + bookcatagory_demo + '\'' +
                ", book_ISBN='" + book_ISBN + '\'' +
                ", book_catagoryId=" + book_catagoryId +
                ", book_name='" + book_name + '\'' +
                ", book_publish='" + book_publish + '\'' +
                ", book_author='" + book_author + '\'' +
                ", book_publishDate=" + book_publishDate +
                ", book_price=" + book_price +
                ", book_storageDate=" + book_storageDate +
                ", book_stockNumber=" + book_stockNumber +
                ", book_inNumber=" + book_inNumber +
                '}';
    }
}
