package com.example.example_btl_androidnc.students.addItem;

import static com.example.example_btl_androidnc.students.adapter.CourseAdapter.convertDateFormat;
import static com.example.example_btl_androidnc.students.api.RetrofitClient.BASE_IMG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.database.MySharedPreferences;
import com.example.example_btl_androidnc.students.model.Course;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.adapter.CourseAdapter;
import com.example.example_btl_androidnc.students.model.Schedule;
import com.example.example_btl_androidnc.students.model.UserCourse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterCourseActivity extends AppCompatActivity {
    TextView name, infor_class, price, infor_date_start, infor_date_end, teacher,school_day,School_shift;
    ImageView imageView;
    Button btn_Register;
    private Course course;
    private MySharedPreferences mySharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_course);
        mySharedPreferences = new MySharedPreferences(RegisterCourseActivity.this);
        getViews();
        course = (Course) getIntent().getSerializableExtra("putCourse");
        loadCourseData(course);
if (mySharedPreferences.getRole().equals("ROLE_TEACHER")){
    btn_Register.setVisibility(View.GONE);
}
else btn_Register.setVisibility(View.VISIBLE);
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String authToken = mySharedPreferences.getToken();
                if (authToken != null) {
                    GetAPI_Service getAPI_service = RetrofitClient.getClient(authToken).create(GetAPI_Service.class);
                    Call<Void> call = getAPI_service.enrollCourse(course.getId());
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            UserCourse userCourse = new UserCourse();
                            if (response.code() == 200) {
                                Toast.makeText(RegisterCourseActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                                sendTokenToServer();
                            } else if (userCourse.getStatus() == 0) {
                                Toast.makeText(RegisterCourseActivity.this, "Bạn đã đang đăng ký, đang đợi duyệt", Toast.LENGTH_SHORT).show();
                            } else if (userCourse.getStatus() == 1) {
                                Toast.makeText(RegisterCourseActivity.this, "Gà", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterCourseActivity.this, "Đăng kí không thành công", Toast.LENGTH_SHORT).show();
                                Log.d("testtoken", response.toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            // Xử lý lỗi
                        }
                    });

                } else {
                    Toast.makeText(RegisterCourseActivity.this, "Không có Token", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendTokenToServer() {
        mySharedPreferences = new MySharedPreferences(this);

        GetAPI_Service authService = RetrofitClient.getClient().create(GetAPI_Service.class);
        Call<Void> call = authService.updateTokenNotification(mySharedPreferences.getName(), mySharedPreferences.getKeyToken());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                // Handle response
                Log.d("FCM", "Gửi đến đến server token: " + mySharedPreferences.getKeyToken());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure
                Log.d("FCM", "Gửi đến server không thành công: ", t);
            }
        });
    }


    public void getViews() {
        name = findViewById(R.id.name_Class);
        infor_class = findViewById(R.id.infor_class);
        imageView = findViewById(R.id.image_Class);
//        infor_date_end = findViewById(R.id.infor_date_end);
        infor_date_start = findViewById(R.id.infor_date_start);
        price = findViewById(R.id.price);
        teacher = findViewById(R.id.infor_teacher);
        btn_Register = findViewById(R.id.btn_Register);
        school_day = findViewById(R.id.school_day);
        School_shift = findViewById(R.id.School_shift);

    }

    public void loadCourseData(Course course) {

        name.setText(course.getName());
        infor_class.setText(course.getDescription());
        Glide.with(imageView)
                .load(BASE_IMG + course.getImage())
                .into(imageView);
        price.setText(course.getPrice());
        teacher.setText(course.getTeacheNames().get(0));

        infor_date_start.setText(convertDateFormat(course.getPublishedAt()));
        Log.d("test11111",course.getScheduleList().toString());

        List<Schedule> scheduleList = course.getScheduleList();

        if (scheduleList != null && scheduleList.size() >= 2) {
            Schedule schedule0 = scheduleList.get(0);
            Schedule schedule1 = scheduleList.get(1);

            if (schedule0.getDayOfWeek() != null && schedule1.getDayOfWeek() != null) {
                school_day.setText(schedule0.getDayOfWeek() + " , " + schedule1.getDayOfWeek());
                if (schedule0.getDuration().equals("CA_1")){
                    School_shift.setText("6h - 8h");
                }
                else  School_shift.setText("8h - 10h");

            }
        }


    }

}
