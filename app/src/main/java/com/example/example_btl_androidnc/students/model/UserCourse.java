package com.example.example_btl_androidnc.students.model;

import java.util.List;

public class UserCourse {
    private String courseId;
    private String enrollDate;
    private int status;

    private String image;
    private String name;

    private String address;

    private List<String> teacheNames;

    public UserCourse(String courseId, int status) {
        this.courseId = courseId;
        this.status = status;
    }

    public UserCourse() {
    }

    public UserCourse(String courseId, String enrollDate, int status) {
        this.courseId = courseId;
        this.enrollDate = enrollDate;
        this.status = status;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(String enrollDate) {
        this.enrollDate = enrollDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getTeacheNames() {
        return teacheNames;
    }

    public void setTeacheNames(List<String> teacheNames) {
        this.teacheNames = teacheNames;
    }

    @Override
    public String toString() {
        return "UserCourse{" +
                "courseId='" + courseId + '\'' +
                ", enrollDate='" + enrollDate + '\'' +
                ", status=" + status +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}