package com.example.example_btl_androidnc.addItem;

import static com.example.example_btl_androidnc.api.RetrofitClient.BASE_IMG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.example_btl_androidnc.api.GetAPI_Service;
import com.example.example_btl_androidnc.api.RetrofitClient;
import com.example.example_btl_androidnc.controller.RegisterCourseController;
import com.example.example_btl_androidnc.model.Course;
import com.example.example_btl_androidnc.model.Users;
import com.example.example_btl_androidnc.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterCourseActivity extends AppCompatActivity {
    TextView name, infor_class, price, infor_date_start, infor_date_end, teacher;
    ImageView imageView;
    Button btn_Register;

    private RegisterCourseController registerCourseController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_course);

        registerCourseController = new RegisterCourseController(this);

        getViews();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Course course = (Course) getIntent().getSerializableExtra("putCourse");
            registerCourseController.loadCourseData(course);
        }
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetAPI_Service getAPI_service = RetrofitClient.getClient().create(GetAPI_Service.class);

                Call<List<Users>> call = getAPI_service. getUser();
                call.enqueue(new Callback<List<Users>>() {
                    @Override
                    public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                        Toast.makeText(RegisterCourseActivity.this, response.toString() +"hihi", Toast.LENGTH_SHORT).show();
                        Log.d("test","đã chạy vào đây");
                    }

                    @Override
                    public void onFailure(Call<List<Users>> call, Throwable t) {
                        Log.d("tets", t +" onFailure ");
                    }
                });


            }
        });

    }

    public void getViews() {
        name = findViewById(R.id.name_Class);
        infor_class = findViewById(R.id.infor_class);
        imageView = findViewById(R.id.image_Class);
        infor_date_end = findViewById(R.id.infor_date_end);
        infor_date_start = findViewById(R.id.infor_date_start);
        price = findViewById(R.id.price);
        teacher = findViewById(R.id.infor_teacher);
        btn_Register = findViewById(R.id.btn_Register);
    }

    public void setName(String nameText) {
        name.setText(nameText);
    }

    public void setInforClass(String inforClassText) {
        infor_class.setText(inforClassText);
    }

    public void setImage(String imageUrl) {
        Glide.with(imageView)
                .load(BASE_IMG +imageUrl)
                .into(imageView);
    }
    public void setPrice(String priceText) {
        price.setText(priceText);
    }

    public void setTeacher(String teacherText) {
        teacher.setText(teacherText);
    }

    public void setInforDateStart(String dateStartText) {
        infor_date_start.setText(dateStartText);
    }

    public void setInforDateEnd(String dateEndText) {
        infor_date_end.setText(dateEndText);
    }

}
