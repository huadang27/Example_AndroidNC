package com.example.example_btl_androidnc.students.addItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.adapter.CourseAdapter;
import com.example.example_btl_androidnc.students.adapter.ListCourseAdapter;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.database.MySharedPreferences;
import com.example.example_btl_androidnc.students.model.Course;
import com.example.example_btl_androidnc.students.model.UserCourse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCoursesActivity extends AppCompatActivity {
    private MySharedPreferences mySharedPreferences;
    private List<Course> Courses;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_courses);
        Courses = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview1);
        mySharedPreferences = new MySharedPreferences(AllCoursesActivity.this);
        fetchData();
    }

    private void fetchData() {
        GetAPI_Service getAPI_service = RetrofitClient.getClient().create(GetAPI_Service.class);
        Call<List<Course>> call = getAPI_service.getCourse();
        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if (response.code() != 200) {
                    Log.d("test", "Response code: " + response.code());
                    Log.d("test", "Response message: " + response.message());
                }
                Courses = response.body();
                List<Course> courseList = response.body();
                List<String> courseIds = new ArrayList<>();
                List<Integer> statuses = new ArrayList<>();
                for (Course course : courseList) {
                    courseIds.add(course.getId());
                    statuses.add(course.getStatus());
                }
//                mySharedPreferences.saveCourseDataToSharedPreferences(courseIds, statuses);

                List<Course> filteredCourseList = new ArrayList<>();
                for (Course course : courseList) {
                    filteredCourseList.add(course);

                }
                Log.d("test",filteredCourseList.toString());
                PutDataIntoRecyclerView(filteredCourseList);

                HashMap<String, Integer> courseData = mySharedPreferences.getCourseDataFromSharedPreferences();
                Log.d("CourseData", "Dữ liệu courseId và status: " + courseData.toString());

            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
            }
        });
    }
    private void PutDataIntoRecyclerView(List<Course> courses) {
        CourseAdapter adapter = new CourseAdapter(AllCoursesActivity.this, courses);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllCoursesActivity.this));
        recyclerView.setAdapter(adapter);
    }
}