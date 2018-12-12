package com.ethan.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * Created by zhangwenyu on 2018/12/10.
 */
public class Borrow {
    private int borrow_id;
    private String borrow_bcid;
    private String borrow_ISBN;
    @JSONField(format = "yyyy-MM-dd")
    private Date borrow_borrowDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date borrow_expireDate;
    @JSONField(format = "yyyy-MM-dd")
    private Date borrow_dueDate;


    public Borrow() {
    }

    public Borrow(int borrow_id, String borrow_bcid, String borrow_ISBN, Date borrow_borrowDate, Date borrow_expireDate, Date borrow_dueDate) {
        this.borrow_id = borrow_id;
        this.borrow_bcid = borrow_bcid;
        this.borrow_ISBN = borrow_ISBN;
        this.borrow_borrowDate = borrow_borrowDate;
        this.borrow_expireDate = borrow_expireDate;
        this.borrow_dueDate = borrow_dueDate;
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

    public String getBorrow_ISBN() {
        return borrow_ISBN;
    }

    public void setBorrow_ISBN(String borrow_ISBN) {
        this.borrow_ISBN = borrow_ISBN;
    }

    public Date getBorrow_borrowDate() {
        return borrow_borrowDate;
    }

    public void setBorrow_borrowDate(Date borrow_borrowDate) {
        this.borrow_borrowDate = borrow_borrowDate;
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


    @Override
    public String toString() {
        return "Borrow{" +
                "borrow_id=" + borrow_id +
                ", borrow_bcid='" + borrow_bcid + '\'' +
                ", borrow_ISBN='" + borrow_ISBN + '\'' +
                ", borrow_borrowDate=" + borrow_borrowDate +
                ", borrow_expireDate=" + borrow_expireDate +
                ", borrow_dueDate=" + borrow_dueDate +
                '}';
    }
}
