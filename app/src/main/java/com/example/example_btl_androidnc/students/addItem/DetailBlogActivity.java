package com.example.example_btl_androidnc.students.addItem;

import static com.example.example_btl_androidnc.students.adapter.CourseAdapter.convertDateFormat;
import static com.example.example_btl_androidnc.students.api.RetrofitClient.BASE_IMG;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.model.Blog;
import com.example.example_btl_androidnc.students.model.Course;
import com.example.example_btl_androidnc.students.model.Schedule;

import java.util.List;

public class DetailBlogActivity extends AppCompatActivity {
ImageView image_Class;
TextView name_blog,content_blog;

    private Blog blog;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_blog);
        getViews();
        blog = (Blog) getIntent().getSerializableExtra("putBlog");
        loadBlogData(blog);

    }
    public void getViews() {
image_Class = findViewById(R.id.image_Class);
name_blog = findViewById(R.id.name_blog);
content_blog = findViewById(R.id.content_blog);

    }
    public void loadBlogData(Blog blog) {

        Glide.with(image_Class)
                .load(BASE_IMG + blog.getImage())
                .into(image_Class);
        name_blog.setText(blog.getTitle());
        content_blog.setText(blog.getContent());
    }
}