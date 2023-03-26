package com.example.example_btl_androidnc.AddItem;

import static com.example.example_btl_androidnc.API.RetrofitClient.BASE_IMG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.example_btl_androidnc.Controller.RegisterCourseController;
import com.example.example_btl_androidnc.Model.Course;
import com.example.example_btl_androidnc.R;

public class RegisterCourseActivity extends AppCompatActivity {
    TextView name, infor_class, price, infor_date_start, infor_date_end, teacher;
    ImageView imageView;

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
    }

    public void getViews() {
        name = findViewById(R.id.name_Class);
        infor_class = findViewById(R.id.infor_class);
        imageView = findViewById(R.id.image_Class);
        infor_date_end = findViewById(R.id.infor_date_end);
        infor_date_start = findViewById(R.id.infor_date_start);
        price = findViewById(R.id.price);
        teacher = findViewById(R.id.infor_teacher);
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
