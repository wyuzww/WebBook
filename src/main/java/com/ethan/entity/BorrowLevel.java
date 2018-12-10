package com.ethan.entity;

/**
 * Created by zhangwenyu on 2018/12/10.
 */
public class BorrowLevel {
    private int borrowlevel_id;
    private String borrowlevel_name;
    private int borrowlevel_quantity;
    private int borrowlevel_days;
    private float borrowlevel_fine;
    private String borrowlevel_demo;

    public BorrowLevel() {
    }

    public BorrowLevel(int borrowlevel_id, String borrowlevel_name, int borrowlevel_quantity, int borrowlevel_days, float borrowlevel_fine, String borrowlevel_demo) {
        this.borrowlevel_id = borrowlevel_id;
        this.borrowlevel_name = borrowlevel_name;
        this.borrowlevel_quantity = borrowlevel_quantity;
        this.borrowlevel_days = borrowlevel_days;
        this.borrowlevel_fine = borrowlevel_fine;
        this.borrowlevel_demo = borrowlevel_demo;
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

    public int getBorrowlevel_quantity() {
        return borrowlevel_quantity;
    }

    public void setBorrowlevel_quantity(int borrowlevel_quantity) {
        this.borrowlevel_quantity = borrowlevel_quantity;
    }

    public int getBorrowlevel_days() {
        return borrowlevel_days;
    }

    public void setBorrowlevel_days(int borrowlevel_days) {
        this.borrowlevel_days = borrowlevel_days;
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


    @Override
    public String toString() {
        return "BorrowLevel{" +
                "borrowlevel_id=" + borrowlevel_id +
                ", borrowlevel_name='" + borrowlevel_name + '\'' +
                ", borrowlevel_quantity=" + borrowlevel_quantity +
                ", borrowlevel_days=" + borrowlevel_days +
                ", borrowlevel_fine=" + borrowlevel_fine +
                ", borrowlevel_demo='" + borrowlevel_demo + '\'' +
                '}';
    }
}
