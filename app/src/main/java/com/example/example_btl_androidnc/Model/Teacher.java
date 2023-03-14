package com.example.example_btl_androidnc.Model;

public class Teacher {
    private String id_Teacher;
    private String name_Teacher;
    private String date_of_birth;
    private String phone_number;
    private String email;
    private String subject_id;
    private String role;

    public Teacher(String id_Teacher, String name_Teacher, String date_of_birth, String phone_number, String email, String subject_id, String role) {
        this.id_Teacher = id_Teacher;
        this.name_Teacher = name_Teacher;
        this.date_of_birth = date_of_birth;
        this.phone_number = phone_number;
        this.email = email;
        this.subject_id = subject_id;
        this.role = role;
    }

    public String getId_Teacher() {
        return id_Teacher;
    }

    public void setId_Teacher(String id_Teacher) {
        this.id_Teacher = id_Teacher;
    }

    public String getName_Teacher() {
        return name_Teacher;
    }

    public void setName_Teacher(String name_Teacher) {
        this.name_Teacher = name_Teacher;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id_Teacher='" + id_Teacher + '\'' +
                ", name_Teacher='" + name_Teacher + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", email='" + email + '\'' +
                ", subject_id='" + subject_id + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
