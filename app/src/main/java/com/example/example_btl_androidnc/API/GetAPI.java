package com.example.example_btl_androidnc.API;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example_btl_androidnc.Adapter.CourseAdapter;
import com.example.example_btl_androidnc.Model.Course;
import com.example.example_btl_androidnc.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetAPI extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Course> CourseList;
    private Retrofit retrofit = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_admin_home);
//        https://run.mocky.io/v3/81771c15-1dc6-4a9b-92fc-f991c48910dc
        recyclerView = findViewById(R.id.recyclerview);
        CourseList = new ArrayList<>();

        try {
            retrofit  = new Retrofit.Builder().baseUrl("http://192.168.3.199:8088/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("hihihihihiih");
        }



        GetAPI_Service getAPI_service = retrofit.create( GetAPI_Service.class);

        Call<List<Course>> call = getAPI_service.getCourse();
        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                Log.d("test",response.body().toString());
                if (response.code()!=200){
                    Log.d("test1","khong chay vao");
                    return;
                }
                List<Course>movies=  response.body();
                for(Course movie: movies) CourseList.add(movie);
                Log.d("test2","khong chay vao1111");
                PutDataIntoRecyclerView(CourseList);
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Log.d("hihi",t.toString() +" _______onfailue______");
            }

        });

    }
    private void PutDataIntoRecyclerView(List<Course> movieList) {
        CourseAdapter adapter = new CourseAdapter(this,movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
