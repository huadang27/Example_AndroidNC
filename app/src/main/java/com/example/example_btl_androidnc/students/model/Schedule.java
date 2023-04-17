package com.example.example_btl_androidnc.students.model;

public class Schedule {
    private  String id;
    private String dayOfWeek;
    private  String day;
    private String duration;
    private int status;

    public Schedule() {
    }

    public Schedule(String id, String dayOfWeek, String duration, int status) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.duration = duration;
        this.status = status;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int isStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id='" + id + '\'' +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", duration='" + duration + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
