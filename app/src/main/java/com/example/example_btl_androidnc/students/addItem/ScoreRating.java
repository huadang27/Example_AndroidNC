package com.example.example_btl_androidnc.students.addItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.adapter.ScoreRatingAdapter;
import com.example.example_btl_androidnc.students.adapter.StudentListAdapter;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.model.Schedule;
import com.example.example_btl_androidnc.students.model.UserRankResponse;
import com.example.example_btl_androidnc.students.model.Users;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScoreRating extends AppCompatActivity {
    RecyclerView recyclerView;
    ScoreRatingAdapter adapter;
    TextView  scorerating,qualified,unsatisfactory;
    private List<UserRankResponse> userRankResponses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_rating);
        recyclerView = findViewById(R.id.recyclerView);
        scorerating = findViewById(R.id.scorerating);
        qualified = findViewById(R.id.qualified);
        unsatisfactory = findViewById(R.id.unsatisfactory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String courseId = getIntent().getStringExtra("courseId");


        getData(courseId);
        scorerating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<UserRankResponse> sortedList = sortDescendingByAvg(userRankResponses);
                updateData(sortedList);
            }
        });

        qualified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<UserRankResponse> filteredList = filterAvgGreaterOrEqualFive(userRankResponses);
                updateData(filteredList);
            }
        });

        unsatisfactory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<UserRankResponse> filteredList = filterAvgLessThanFive(userRankResponses);
                updateData(filteredList);
            }
        });

    }

    private void getData(String courseId) {
        GetAPI_Service getAPI_service = RetrofitClient.getClient().create(GetAPI_Service.class);
        Call<List<UserRankResponse>> call = getAPI_service.getRanksByCourseId(courseId);
        call.enqueue(new Callback<List<UserRankResponse>>() {
            @Override
            public void onResponse(Call<List<UserRankResponse>> call, Response<List<UserRankResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    userRankResponses = response.body();
                    Log.d("test111", userRankResponses.toString());

                    // Cập nhật recyclerView với danh sách userRankResponses ban đầu
                    updateData(userRankResponses);
                } else {
                    // Xử lý trường hợp không có dữ liệu hoặc lỗi trả về từ API
                    Log.d("test111", "No data or error from API");
                }
            }

            @Override
            public void onFailure(Call<List<UserRankResponse>> call, Throwable t) {

            }
        });
    }

    private void updateData(List<UserRankResponse> userRankResponses) {
        adapter = new ScoreRatingAdapter(ScoreRating.this, userRankResponses);
        recyclerView.setAdapter(adapter);
    }

    private List<UserRankResponse> sortDescendingByAvg(List<UserRankResponse> userRankResponses) {
        if (userRankResponses == null) {
            return new ArrayList<>();
        }

        Collections.sort(userRankResponses, new Comparator<UserRankResponse>() {
            @Override
            public int compare(UserRankResponse o1, UserRankResponse o2) {
                return Double.compare(o2.getRank().getAvg(), o1.getRank().getAvg());
            }
        });
        return userRankResponses;
    }

    private List<UserRankResponse> filterAvgGreaterOrEqualFive(List<UserRankResponse> userRankResponses) {
        if (userRankResponses == null) {
            return new ArrayList<>();
        }

        List<UserRankResponse> filteredList = new ArrayList<>();
        for (UserRankResponse userRankResponse : userRankResponses) {
            if (userRankResponse.getRank().getAvg() >= 5) {
                filteredList.add(userRankResponse);
            }
        }
        return filteredList;
    }

    private List<UserRankResponse> filterAvgLessThanFive(List<UserRankResponse> userRankResponses) {
        if (userRankResponses == null) {
            return new ArrayList<>();
        }

        List<UserRankResponse> filteredList = new ArrayList<>();
        for (UserRankResponse userRankResponse : userRankResponses) {
            if (userRankResponse.getRank().getAvg() < 5) {
                filteredList.add(userRankResponse);
            }
        }
        return filteredList;
    }






}