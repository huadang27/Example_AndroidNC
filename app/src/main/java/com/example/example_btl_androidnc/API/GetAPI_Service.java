package com.example.example_btl_androidnc.API;

import com.example.example_btl_androidnc.Model.RefreshTokenRequest;
import com.example.example_btl_androidnc.Model.Users;
import com.example.example_btl_androidnc.Model.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetAPI_Service {
    //hiển thị lớp học ở trang chủ
    @GET("api/admin/course")
    Call<List<Course>> getCourse();

// Đăng kí tài khoản
    @POST("/api/auth/register")
    Call<Users> createAccount(@Body Users req);

    // đăng nhập
    @POST("/api/auth/login")
    Call<Users> login(@Body Users users);
    @POST("api/auth/refresh")
    Call<Users> refreshToken(@Body RefreshTokenRequest refreshTokenRequest);
}
