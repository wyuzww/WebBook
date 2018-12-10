package com.ethan.entity;

import java.util.Date;

/**
 * Created by zhangwenyu on 2018/12/10.
 */
public class Ticket {
    private int ticket_id;
    private String ticket_bcid;
    private String ticket_ISBN;
    private float ticket_fineMoney;
    private Date ticket_fineDate;

    public Ticket() {
    }

    public Ticket(int ticket_id, String ticket_bcid, String ticket_ISBN, float ticket_fineMoney, Date ticket_fineDate) {
        this.ticket_id = ticket_id;
        this.ticket_bcid = ticket_bcid;
        this.ticket_ISBN = ticket_ISBN;
        this.ticket_fineMoney = ticket_fineMoney;
        this.ticket_fineDate = ticket_fineDate;
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


    @Override
    public String toString() {
        return "Ticket{" +
                "ticket_id=" + ticket_id +
                ", ticket_bcid='" + ticket_bcid + '\'' +
                ", ticket_ISBN='" + ticket_ISBN + '\'' +
                ", ticket_fineMoney=" + ticket_fineMoney +
                ", ticket_fineDate=" + ticket_fineDate +
                '}';
    }
}
