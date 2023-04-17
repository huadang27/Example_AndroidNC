package com.example.example_btl_androidnc.students.addItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.adapter.StudentListAdapter;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.model.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentList extends AppCompatActivity {
    RecyclerView recyclerView;
    StudentListAdapter adapter;
    private List<Users> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        Intent intent = getIntent();
        String selectedItem = intent.getStringExtra("courseId");


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo Retrofit và UserService
        GetAPI_Service getAPI_service = RetrofitClient.getClient().create(GetAPI_Service.class);
        Call<List<Users>> call = getAPI_service.getUsersWithRoleUserInCourse(selectedItem);
        call.enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                Log.d("test",response.toString());
                if (response.isSuccessful()) {
                    usersList = response.body();
                    adapter = new StudentListAdapter(StudentList.this,usersList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(StudentList.this, "Lỗi khi lấy danh sách sinh viên từ server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Users>> call, Throwable t) {
                Toast.makeText(StudentList.this, "Lỗi khi kết nối tới server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}