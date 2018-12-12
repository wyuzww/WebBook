package com.ethan.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by zhangwenyu on 2018/12/8.
 */
public class AllEntity {

    //book,bookcatagory,bookroom,borrowcard,borrow,borrowlevel,user
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

    private String borrowcard_id;
    private int borrowcard_rid;
    private int borrowcard_quantity;
    private int borrowcard_blid = -1;

    private int borrow_id;
    private String borrow_bcid;
    private String borrow_ISBN;
    @JSONField(format = "yyyy-MM-dd")
    private Date borrow_borrowDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date borrow_expireDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date borrow_dueDate;

    private int borrowlevel_id;
    private String borrowlevel_name;
    private int borrowlevel_quantity;
    private int borrowlevel_days;
    private float borrowlevel_fine;
    private String borrowlevel_demo;

    private int ticket_id;
    private String ticket_bcid;
    private String ticket_ISBN;
    private float ticket_fineMoney;
    @JSONField(format = "yyyy-MM-dd")
    private Date ticket_fineDate;

    private int user_id;
    private String user_account;
    private String user_password;
    private String user_name;
    private String user_sex;
    private String user_phone;
    private String user_photo;
    private String user_level;


    public AllEntity() {
    }

    public AllEntity(int bookroom_id, String bookroom_name, String bookroom_address, String bookroom_phone, int bookcatagory_id, String bookcatagory_name, int bookcatagory_brid, String bookcatagory_demo, String book_ISBN, int book_catagoryId, String book_name, String book_publish, String book_author, Date book_publishDate, float book_price, Date book_storageDate, int book_stockNumber, int book_inNumber, String borrowcard_id, int borrowcard_rid, int borrowcard_quantity, int borrowcard_blid, int borrow_id, String borrow_bcid, String borrow_ISBN, Date borrow_borrowDate, Date borrow_expireDate, Date borrow_dueDate, int borrowlevel_id, String borrowlevel_name, int borrowlevel_quantity, int borrowlevel_days, float borrowlevel_fine, String borrowlevel_demo, int ticket_id, String ticket_bcid, String ticket_ISBN, float ticket_fineMoney, Date ticket_fineDate, int user_id, String user_account, String user_password, String user_name, String user_sex, String user_phone, String user_photo, String user_level) {
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
        this.borrowcard_id = borrowcard_id;
        this.borrowcard_rid = borrowcard_rid;
        this.borrowcard_quantity = borrowcard_quantity;
        this.borrowcard_blid = borrowcard_blid;
        this.borrow_id = borrow_id;
        this.borrow_bcid = borrow_bcid;
        this.borrow_ISBN = borrow_ISBN;
        this.borrow_borrowDate = borrow_borrowDate;
        this.borrow_expireDate = borrow_expireDate;
        this.borrow_dueDate = borrow_dueDate;
        this.borrowlevel_id = borrowlevel_id;
        this.borrowlevel_name = borrowlevel_name;
        this.borrowlevel_quantity = borrowlevel_quantity;
        this.borrowlevel_days = borrowlevel_days;
        this.borrowlevel_fine = borrowlevel_fine;
        this.borrowlevel_demo = borrowlevel_demo;
        this.ticket_id = ticket_id;
        this.ticket_bcid = ticket_bcid;
        this.ticket_ISBN = ticket_ISBN;
        this.ticket_fineMoney = ticket_fineMoney;
        this.ticket_fineDate = ticket_fineDate;
        this.user_id = user_id;
        this.user_account = user_account;
        this.user_password = user_password;
        this.user_name = user_name;
        this.user_sex = user_sex;
        this.user_phone = user_phone;
        this.user_photo = user_photo;
        this.user_level = user_level;
    }

    public int getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(int borrow_id) {
        this.borrow_id = borrow_id;
    }

    public String getBorrow_bcid() {
        return borrow_bcid;
    }

    public void setBorrow_bcid(String borrow_bcid) {
        this.borrow_bcid = borrow_bcid;
    }

    public Date getBorrow_borrowDate() {
        return borrow_borrowDate;
    }

    public void setBorrow_borrowDate(Date borrow_borrowDate) {
        this.borrow_borrowDate = borrow_borrowDate;
    }

    public String getBorrow_ISBN() {
        return borrow_ISBN;
    }

    public void setBorrow_ISBN(String borrow_ISBN) {
        this.borrow_ISBN = borrow_ISBN;
    }

    public Date getBorrow_expireDate() {
        return borrow_expireDate;
    }

    public void setBorrow_expireDate(Date borrow_expireDate) {
        this.borrow_expireDate = borrow_expireDate;
    }

    public Date getBorrow_dueDate() {
        return borrow_dueDate;
    }

    public void setBorrow_dueDate(Date borrow_dueDate) {
        this.borrow_dueDate = borrow_dueDate;
    }

    public int getBorrowlevel_id() {
        return borrowlevel_id;
    }

    public void setBorrowlevel_id(int borrowlevel_id) {
        this.borrowlevel_id = borrowlevel_id;
    }

    public String getBorrowlevel_name() {
        return borrowlevel_name;
    }

    public void setBorrowlevel_name(String borrowlevel_name) {
        this.borrowlevel_name = borrowlevel_name;
    }

    public int getBorrowlevel_days() {
        return borrowlevel_days;
    }

    public void setBorrowlevel_days(int borrowlevel_days) {
        this.borrowlevel_days = borrowlevel_days;
    }

    public int getBorrowlevel_quantity() {
        return borrowlevel_quantity;
    }

    public void setBorrowlevel_quantity(int borrowlevel_quantity) {
        this.borrowlevel_quantity = borrowlevel_quantity;
    }

    public float getBorrowlevel_fine() {
        return borrowlevel_fine;
    }

    public void setBorrowlevel_fine(float borrowlevel_fine) {
        this.borrowlevel_fine = borrowlevel_fine;
    }

    public String getBorrowlevel_demo() {
        return borrowlevel_demo;
    }

    public void setBorrowlevel_demo(String borrowlevel_demo) {
        this.borrowlevel_demo = borrowlevel_demo;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getTicket_bcid() {
        return ticket_bcid;
    }

    public void setTicket_bcid(String ticket_bcid) {
        this.ticket_bcid = ticket_bcid;
    }

    public String getTicket_ISBN() {
        return ticket_ISBN;
    }

    public void setTicket_ISBN(String ticket_ISBN) {
        this.ticket_ISBN = ticket_ISBN;
    }

    public float getTicket_fineMoney() {
        return ticket_fineMoney;
    }

    public void setTicket_fineMoney(float ticket_fineMoney) {
        this.ticket_fineMoney = ticket_fineMoney;
    }

    public Date getTicket_fineDate() {
        return ticket_fineDate;
    }

    public void setTicket_fineDate(Date ticket_fineDate) {
        this.ticket_fineDate = ticket_fineDate;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public String getUser_level() {
        return user_level;
    }

    public void setUser_level(String user_level) {
        this.user_level = user_level;
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

    public String getBorrowcard_id() {
        return borrowcard_id;
    }

    public void setBorrowcard_id(String borrowcard_id) {
        this.borrowcard_id = borrowcard_id;
    }

    public int getBorrowcard_rid() {
        return borrowcard_rid;
    }

    public void setBorrowcard_rid(int borrowcard_rid) {
        this.borrowcard_rid = borrowcard_rid;
    }

    public int getBorrowcard_quantity() {
        return borrowcard_quantity;
    }

    public void setBorrowcard_quantity(int borrowcard_quantity) {
        this.borrowcard_quantity = borrowcard_quantity;
    }

    public int getBorrowcard_blid() {
        return borrowcard_blid;
    }

    public void setBorrowcard_blid(int borrowcard_blid) {
        this.borrowcard_blid = borrowcard_blid;
    }


}
