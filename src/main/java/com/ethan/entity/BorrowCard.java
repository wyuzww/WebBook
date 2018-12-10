package com.ethan.entity;

/**
 * Created by zhangwenyu on 2018/12/10.
 */
public class BorrowCard {
    private String borrowcard_id;
    private String borrowcard_rid;
    private int borrowcard_quantity;
    private int borrowcard_blid;

    public BorrowCard() {
    }

    public BorrowCard(String borrowcard_id, String borrowcard_rid, int borrowcard_quantity, int borrowcard_blid) {
        this.borrowcard_id = borrowcard_id;
        this.borrowcard_rid = borrowcard_rid;
        this.borrowcard_quantity = borrowcard_quantity;
        this.borrowcard_blid = borrowcard_blid;
    }

    public String getBorrowcard_id() {
        return borrowcard_id;
    }

    public void setBorrowcard_id(String borrowcard_id) {
        this.borrowcard_id = borrowcard_id;
    }

    public String getBorrowcard_rid() {
        return borrowcard_rid;
    }

    public void setBorrowcard_rid(String borrowcard_rid) {
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


    @Override
    public String toString() {
        return "BorrowCard{" +
                "borrowcard_id='" + borrowcard_id + '\'' +
                ", borrowcard_rid='" + borrowcard_rid + '\'' +
                ", borrowcard_quantity=" + borrowcard_quantity +
                ", borrowcard_blid=" + borrowcard_blid +
                '}';
    }
}
