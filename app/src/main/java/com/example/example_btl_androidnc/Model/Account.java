package com.example.example_btl_androidnc.Model;

public class Account {
    private String name;
    private String email;
    private String password;
    private String Photo;

    private String role;

    public Account(String name, String email, String password, String photo, String role) {

        this.name = name;
        this.email = email;
        this.password = password;
        Photo = photo;
        this.role = role;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" +

                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", Photo='" + Photo + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
