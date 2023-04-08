package com.example.example_btl_androidnc.students.model;

public class courseLists {
    private  String course_id;
    private String enroll_date;
    private int status;

    public courseLists() {
    }

    public courseLists(String course_id, String enroll_date, int status) {
        this.course_id = course_id;
        this.enroll_date = enroll_date;
        this.status = status;
    }

    public courseLists(String course_id, int status) {
        this.course_id = course_id;
        this.status = status;
    }

    @Override
    public String toString() {
        return "courseLists{" +
                "course_id='" + course_id + '\'' +
                ", enroll_date='" + enroll_date + '\'' +
                ", status=" + status +
                '}';
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getEnroll_date() {
        return enroll_date;
    }

    public void setEnroll_date(String enroll_date) {
        this.enroll_date = enroll_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
