package com.example.example_btl_androidnc.students.model;

public class SelectedStudent {
    private String userId;
    private boolean attendance;

    public SelectedStudent(String userId, boolean attendance) {
        this.userId = userId;
        this.attendance = attendance;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

}
