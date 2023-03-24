package com.example.example_btl_androidnc.AddItem;

import static com.example.example_btl_androidnc.API.RetrofitClient.BASE_IMG;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.example_btl_androidnc.Model.Course;
import com.example.example_btl_androidnc.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Register_Course_Activity extends AppCompatActivity {


    TextView name,infor_class,price,infor_date_start,infor_date_end,teacher;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_course);
        getViews();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LoadData();
        }
    }

    public void getViews(){
        name = findViewById(R.id.name_Class);
        infor_class = findViewById(R.id.infor_class);
        imageView = findViewById(R.id.image_Class);
        infor_date_end = findViewById(R.id.infor_date_end);
        infor_date_start = findViewById(R.id.infor_date_start);
        price = findViewById(R.id.price);
        teacher = findViewById(R.id.infor_teacher);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void LoadData(){
        Course course = (Course) getIntent().getSerializableExtra("putCourse");
        name.setText(course.getName());
        infor_class.setText(course.getDescription());
        Glide.with(imageView)
                .load( BASE_IMG
                        +course.getImage())
                .into(imageView);
        price.setText(course.getPrice());
        teacher.setText(course.getTeacher().getName());
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            LocalDate startDate = LocalDate.parse(course.getPublishedAt().toString(), inputFormat);
            LocalDate endDate = LocalDate.parse(course.getExpiredAt().toString(), inputFormat);

            infor_date_start.setText(startDate.format(outputFormat));
            infor_date_end.setText(endDate.format(outputFormat));
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

    }

}
