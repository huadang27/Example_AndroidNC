package com.example.example_btl_androidnc.students.addItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import androidx.appcompat.widget.SearchView;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.adapter.CourseAdapter;
import com.example.example_btl_androidnc.students.adapter.ListCourseAdapter;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.database.MySharedPreferences;
import com.example.example_btl_androidnc.students.model.Course;
import com.example.example_btl_androidnc.students.model.UserCourse;
import com.example.example_btl_androidnc.students.model.Users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCoursesActivity extends AppCompatActivity {
    private MySharedPreferences mySharedPreferences;
    private List<Course> coursesList;
    private RecyclerView recyclerView;
    Toolbar tbStudent;
  SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_courses);
        tbStudent = findViewById(R.id.bt_infor_mycourse);

        setSupportActionBar(tbStudent);
        coursesList = new ArrayList<>();
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
                coursesList = response.body();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.mnuSearch).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //adapter.getFilter().filter(s);
                filterCourse(s);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
            //   adapter.getFilter().filter(s);
                filterCourse(s);
                return false;
            }
        });
        return true;
    }
    private void filterCourse(String query) {
        List<Course> filteredCourseList = new ArrayList<>();
        for (Course course : coursesList) {
            if (course.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredCourseList.add(course);
            }
        }
        PutDataIntoRecyclerView(filteredCourseList);
    }
    private void PutDataIntoRecyclerView(List<Course> courses) {
        CourseAdapter adapter = new CourseAdapter(AllCoursesActivity.this, courses);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllCoursesActivity.this));
        recyclerView.setAdapter(adapter);
    }
}