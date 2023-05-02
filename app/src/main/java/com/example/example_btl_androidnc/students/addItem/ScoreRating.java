package com.example.example_btl_androidnc.students.addItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.adapter.ScoreRatingAdapter;
import com.example.example_btl_androidnc.students.adapter.StudentListAdapter;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.model.Schedule;
import com.example.example_btl_androidnc.students.model.UserRankResponse;
import com.example.example_btl_androidnc.students.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScoreRating extends AppCompatActivity {
    RecyclerView recyclerView;
    ScoreRatingAdapter adapter;
    private List<UserRankResponse> userRankResponses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_rating);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String courseId = getIntent().getStringExtra("courseId");


        getData(courseId);
    }

    private void getData(String courseId) {
        GetAPI_Service getAPI_service = RetrofitClient.getClient().create(GetAPI_Service.class);
        Call<List<UserRankResponse>> call = getAPI_service.getRanksByCourseId(courseId);
        call.enqueue(new Callback<List<UserRankResponse>>() {
            @Override
            public void onResponse(Call<List<UserRankResponse>> call, Response<List<UserRankResponse>> response) {
                List<UserRankResponse> userRankResponses = response.body();
//                Log.d("test111",userRankResponses.toString());
                adapter = new ScoreRatingAdapter(ScoreRating.this,userRankResponses);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<UserRankResponse>> call, Throwable t) {

            }
        });

    }
}