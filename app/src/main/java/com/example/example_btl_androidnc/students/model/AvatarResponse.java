package com.example.example_btl_androidnc.students.model;

import com.google.gson.annotations.SerializedName;

public class AvatarResponse {
    @SerializedName("avatar_url")
    private String avatarUrl;

    public String getAvatarUrl() {
        return avatarUrl;
    }
}