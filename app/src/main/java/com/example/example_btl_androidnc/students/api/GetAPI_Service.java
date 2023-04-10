package com.example.example_btl_androidnc.students.api;

import com.example.example_btl_androidnc.students.model.ChangePass;
import com.example.example_btl_androidnc.students.model.RefreshTokenRequest;
import com.example.example_btl_androidnc.students.model.UserCourse;
import com.example.example_btl_androidnc.students.model.Users;
import com.example.example_btl_androidnc.students.model.Course;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetAPI_Service {
    //hiển thị lớp học ở trang chủ
    @GET("/api/admin/course/list")
    Call<List<Course>> getCourse();

    @GET("/api/profile")
    Call<List<Users>> getUser();

    // đăng kí lớp học theo user đã đăng nhập
    @POST("/api/enrollCourse/{courseId}")
    Call<Void> enrollCourse(@Path("courseId") String courseId);

    @GET("/user/course/{id}")
    Call<List<UserCourse>> getCourseById(@Path("id") String id);


    // danh sách học viên của lớp học
    @GET("/api/course/{id}/users/role_user")
    Call<List<Users>> getUsersWithRoleUserInCourse(@Path("id") String courseId);

    // Đăng kí tài khoản
    @POST("/api/auth/register")
    Call<Users> createAccount(@Body Users req);

    // đăng nhập
    @POST("/api/auth/login")
    Call<Users> login(@Body Users users);
    @POST("api/auth/refresh")
    Call<Users> refreshToken(@Body RefreshTokenRequest refreshTokenRequest);

    @GET("users/{username}")
    Call<Users> getUser(@Path("username") String username, @Header("Authorization") String token);
    //profile

    @GET("/api/profile")
    Call<Users> getUserProfile(@Header("Authorization") String token);
// đổi mật khẩu
    @POST("/api/change-password")
    Call<ResponseBody> changePassword(@Body ChangePass changePass);

    // quên mật khẩu
    @POST("/api/forgot-password")
    Call<Void> processForgotPassword(@Query("email") String email);


}
