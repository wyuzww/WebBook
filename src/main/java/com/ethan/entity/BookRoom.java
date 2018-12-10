package com.ethan.entity;

/**
 * Created by zhangwenyu on 2018/12/9.
 */
public class BookRoom {
    private int bookroom_id;
    private String bookroom_name;
    private String bookroom_phone;
    private String bookroom_address;


    public BookRoom() {
    }

    public BookRoom(int bookroom_id, String bookroom_name, String bookroom_phone, String bookroom_address) {
        this.bookroom_id = bookroom_id;
        this.bookroom_name = bookroom_name;
        this.bookroom_phone = bookroom_phone;
        this.bookroom_address = bookroom_address;
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

    @Override
    public String toString() {
        return "BookRoom{" +
                "bookroom_id=" + bookroom_id +
                ", bookroom_name='" + bookroom_name + '\'' +
                ", bookroom_address='" + bookroom_address + '\'' +
                ", bookroom_phone='" + bookroom_phone + '\'' +
                '}';
    }
}
