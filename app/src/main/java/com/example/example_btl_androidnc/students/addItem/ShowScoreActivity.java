package com.example.example_btl_androidnc.students.addItem;

import static com.example.example_btl_androidnc.students.adapter.CourseAdapter.convertDateFormat;
import static com.example.example_btl_androidnc.students.api.RetrofitClient.BASE_IMG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.databinding.ActivityRankBinding;
import com.example.example_btl_androidnc.databinding.ActivityShowScoreBinding;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.database.MySharedPreferences;
import com.example.example_btl_androidnc.students.model.ImageHelper;
import com.example.example_btl_androidnc.students.model.Rank;
import com.example.example_btl_androidnc.students.model.Users;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowScoreActivity extends AppCompatActivity {
    private MySharedPreferences mySharedPreferences;
    ActivityShowScoreBinding binding;
    private Users users;
    TextView diem1, diem2, diem3, Medium_score, classification,name_user;
ImageHelper image_score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mySharedPreferences = new MySharedPreferences(ShowScoreActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);
        binding = ActivityShowScoreBinding.inflate(getLayoutInflater());
        //  users = (Users) getIntent().getSerializableExtra("users");
        diem1 = findViewById(R.id.diem1);
        diem2 = findViewById(R.id.diem2);
        diem3 = findViewById(R.id.diem3);
        Medium_score = findViewById(R.id.Medium_score);
        classification = findViewById(R.id.classification);
        name_user = findViewById(R.id.name_user);
        image_score = findViewById(R.id.image_score);
        String courseId = getIntent().getStringExtra("courseId");
        String userId = mySharedPreferences.getName();
        getDataDiem(courseId, userId);
    }

    public void getDataDiem(String courseId, String userId) {
        GetAPI_Service apiService = RetrofitClient.getClient().create(GetAPI_Service.class);
        Call<Rank> call = apiService.getGradesByUserIdAndCourseId(courseId, userId);
        call.enqueue(new Callback<Rank>() {
            @Override
            public void onResponse(Call<Rank> call, Response<Rank> response) {
                if (response.isSuccessful()) {
                    Rank rankReq = response.body();
                    // Use rankReq to update your UI or process the data
                    diem1.setText(String.valueOf(rankReq.getMidtermGrades()));
                    diem2.setText(String.valueOf(rankReq.getFinalGrades()));
                    diem3.setText(String.valueOf(rankReq.getExams()));
                    Medium_score.setText(String.valueOf(rankReq.getExams()));
                    classification.setText(String.valueOf(rankReq.getRanking()));
                    name_user.setText(String.valueOf(mySharedPreferences.getUsername()));
                    Log.d("ggg",mySharedPreferences.getImage());
                    Glide.with(image_score.getContext())
                            .load(BASE_IMG + mySharedPreferences.getImage())
                            .into(image_score);
                    Toast.makeText(ShowScoreActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle error case
                }
            }
//check
            @Override
            public void onFailure(Call<Rank> call, Throwable t) {
                Toast.makeText(ShowScoreActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setTextIfExists(TextView textView, String text) {
        if (text != null) {
            textView.setText(text);
        }
    }

    private void loadImageIfExists(ImageView imageView, String imageUrl) {
        if (imageUrl != null) {
            Glide.with(imageView.getContext())
                    .load(BASE_IMG + imageUrl)
                    .into(imageView);
        }
    }


}