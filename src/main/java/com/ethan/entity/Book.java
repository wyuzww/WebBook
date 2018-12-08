package com.ethan.entity;

import java.util.Date;

/**
 * Created by zhangwenyu on 2018/12/6.
 */
public class Book {
    private String ISBN;
    private int catagoryId;
    private String name;
    private String publish;
    private String author;
    private Date publishDate;
    private float price;
    private Date storageDate;
    private int stockNumber;
    private int inNumber;

    public Book() {
    }

    public Book(String ISBN, int catagoryId, String name, String publish, String author, Date publishDate, float price, Date storageDate, int stockNumber, int inNumber) {
        this.ISBN = ISBN;
        this.catagoryId = catagoryId;
        this.name = name;
        this.publish = publish;
        this.author = author;
        this.publishDate = publishDate;
        this.price = price;
        this.stockNumber = stockNumber;
        this.storageDate = storageDate;
        this.inNumber = inNumber;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getCatagoryId() {
        return catagoryId;
    }

    public void setCatagoryId(int catagoryId) {
        this.catagoryId = catagoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getStorageDate() {
        return storageDate;
    }

    public void setStorageDate(Date storageDate) {
        this.storageDate = storageDate;
    }

    public int getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(int stockNumber) {
        this.stockNumber = stockNumber;
    }

    public int getInNumber() {
        return inNumber;
    }

    public void setInNumber(int inNumber) {
        this.inNumber = inNumber;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", catagoryId=" + catagoryId +
                ", name='" + name + '\'' +
                ", publish='" + publish + '\'' +
                ", author='" + author + '\'' +
                ", publishData='" + publishDate + '\'' +
                ", price=" + price +
                ", storageData='" + storageDate + '\'' +
                ", stockNumber=" + stockNumber +
                ", inNumber=" + inNumber +
                '}';
    }
}
