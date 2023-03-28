package com.example.example_btl_androidnc.api;

import com.example.example_btl_androidnc.model.RefreshTokenRequest;
import com.example.example_btl_androidnc.model.Users;
import com.example.example_btl_androidnc.model.Course;
import com.google.firebase.firestore.auth.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GetAPI_Service {
    //hiển thị lớp học ở trang chủ
    @GET("/api/admin/course/list")
    Call<List<Course>> getCourse();

    @GET("/api/profile")
    Call<List<Users>> getUser();

// Đăng kí tài khoản
    @POST("/api/auth/register")
    Call<Users> createAccount(@Body Users req);

    // đăng nhập
    @POST("/api/auth/login")
    Call<Users> login(@Body Users users);
    @POST("api/auth/refresh")
    Call<Users> refreshToken(@Body RefreshTokenRequest refreshTokenRequest);

    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username, @Header("Authorization") String token);
}
