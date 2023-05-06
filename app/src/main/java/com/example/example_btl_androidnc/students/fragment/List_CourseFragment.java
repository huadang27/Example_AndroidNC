package com.example.example_btl_androidnc.students.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.adapter.ListCourseAdapter;
import com.example.example_btl_androidnc.students.addItem.ScheduleList;
import com.example.example_btl_androidnc.students.addItem.ScoreRating;
import com.example.example_btl_androidnc.students.addItem.StudentList;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.database.MySharedPreferences;
import com.example.example_btl_androidnc.students.model.UserCourse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class List_CourseFragment extends Fragment {

    private MySharedPreferences mySharedPreferences;
    private RecyclerView recyclerView;
    private List<UserCourse> userCourses;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SearchView  searchView;
    private TextView bt_infor_status;
    private ImageView list_item;
    private int check =0;

    public List_CourseFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_course, container, false);
        userCourses = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerview);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        searchView = view.findViewById(R.id.searchView);
        mySharedPreferences = new MySharedPreferences(getContext());
       // bt_infor_status= view.findViewById(R.id.bt_infor_status);
        list_item = view.findViewById(R.id.list_item);
        list_item.setVisibility(View.GONE);
        list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(view);
            }
        });


        fetchData(1);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData(1);
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                // bắt sự kiện khi ấn enter
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // bắt luôn sự kiện
                filterCourses(query);
                return false;
            }
        });

        return view;
    }

    private void fetchData(int check) {
        GetAPI_Service getAPI_service = RetrofitClient.getClient().create(GetAPI_Service.class);
        Call<List<UserCourse>> call = getAPI_service.getCourseById(mySharedPreferences.getName());
        call.enqueue(new Callback<List<UserCourse>>() {
            @Override
            public void onResponse(Call<List<UserCourse>> call, Response<List<UserCourse>> response) {
                if (response.code() != 200) {
                    Log.d("test", "Response code: " + response.code());
                    Log.d("test", "Response message: " + response.message());
                }
                userCourses = response.body();
                List<UserCourse> courseList = response.body();
                List<String> courseIds = new ArrayList<>();
                List<Integer> statuses = new ArrayList<>();
                for (UserCourse course : courseList) {
                    courseIds.add(course.getCourseId());
                    statuses.add(course.getStatus());
                }
                mySharedPreferences.saveCourseDataToSharedPreferences(courseIds, statuses);

                List<UserCourse> filteredCourseList = new ArrayList<>();
                for (UserCourse course : courseList) {
                    if (course.getStatus() == check) {
                        filteredCourseList.add(course);
                    }
                }
                Log.d("test",filteredCourseList.toString());
                PutDataIntoRecyclerView(filteredCourseList);
                swipeRefreshLayout.setRefreshing(false);

                HashMap<String, Integer> courseData = mySharedPreferences.getCourseDataFromSharedPreferences();
                Log.d("CourseData", "Dữ liệu courseId và status: " + courseData.toString());

            }

            @Override
            public void onFailure(Call<List<UserCourse>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void PutDataIntoRecyclerView(List<UserCourse> userCourses) {
        ListCourseAdapter adapter = new ListCourseAdapter(getContext(), userCourses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void filterCourses(String query) {
        List<UserCourse> filteredCourseList = new ArrayList<>();
        for (UserCourse course : userCourses) {
            if (course.getStatus() == 1 && course.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredCourseList.add(course);
            }
        }
        PutDataIntoRecyclerView(filteredCourseList);
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_list_course, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_total_score:
                        fetchData(0);
                        break;
                    case R.id.action_student_list:
                        fetchData(1);
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }



}