package com.example.example_btl_androidnc.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Users implements Serializable {
    private String id;
    private String address;
    private String avatar;
    private String email;
    private String gender;
    private String name;

    private String password;
    private String phone;
    private String status;
    private String accessToken;
    private String refreshToken;
    private String type;
    private List<String> roles;

    public Users(String id, String address, String avatar, String email, String gender, String name, String password, String phone, String status) {
        this.id = id;
        this.address = address;
        this.avatar = avatar;
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.status = status;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.type = type;
        this.roles = roles;
    }

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
