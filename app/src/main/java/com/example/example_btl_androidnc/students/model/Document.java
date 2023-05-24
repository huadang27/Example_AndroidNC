package com.example.example_btl_androidnc.students.model;

public class Document {

    private String id;
    private String date;
    private String description;
    private String filePath;
    // Thêm getter và setter cho các trường


    public Document() {
    }

    public Document(String id, String date, String description, String filePath) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.filePath = filePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
