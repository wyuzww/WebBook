package com.ethan.entity;

/**
 * Created by zhangwenyu on 2018/12/9.
 */
public class BookCatagory {
    private int bookcatagory_id;
    private String bookcatagory_name;
    private int bookcatagory_brid;
    private String bookcatagory_demo;

    public BookCatagory() {
    }

    public BookCatagory(int bookcatagory_id, String bookcatagory_name, int bookcatagory_brid, String bookcatagory_demo) {
        this.bookcatagory_id = bookcatagory_id;
        this.bookcatagory_name = bookcatagory_name;
        this.bookcatagory_brid = bookcatagory_brid;
        this.bookcatagory_demo = bookcatagory_demo;
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
}
