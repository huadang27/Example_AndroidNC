package com.example.example_btl_androidnc.Model;

import android.graphics.Bitmap;

public class Course {
    private String id;
    private String name;
    private Long price;
    private int status;
    private int level;
    private Bitmap image;
    private String description;


    public Course(String id, String name, Long price, int status, int level, Bitmap image, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.level = level;
        this.image = image;
        this.description = description;
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", level=" + level +
                ", image=" + image +
                ", description='" + description + '\'' +
                '}';
    }
}
