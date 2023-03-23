package com.example.example_btl_androidnc.Model;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Date;

public class Course implements Serializable {

    private String id;
    private String name;
    private String description;
    private String status;

    private String price;

    private String level;

    private String image;

    private  Date publishedAt;
    private Date expiredAt;


    public Course(String id, String name, String description, String status, String price, String level, String image, Date publishedAt, Date expiredAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.price = price;
        this.level = level;
        this.image = image;
        this.publishedAt = publishedAt;
        this.expiredAt = expiredAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }
}
