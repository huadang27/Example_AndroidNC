package com.example.example_btl_androidnc.API;

import com.example.example_btl_androidnc.Model.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CourseService {

    // lay du lieu

    @GET("/api/admin/course")
    Call<List<Course>> getCourse();

}
