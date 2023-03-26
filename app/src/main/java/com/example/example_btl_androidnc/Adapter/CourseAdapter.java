package com.example.example_btl_androidnc.Adapter;

import static com.example.example_btl_androidnc.API.RetrofitClient.BASE_IMG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.example_btl_androidnc.AddItem.RegisterCourseActivity;
import com.example.example_btl_androidnc.Model.Course;
import com.example.example_btl_androidnc.Model.Teacher;
import com.example.example_btl_androidnc.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder>{
    private Context context;
    private List<Course> CourseList;

    public CourseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        CourseList = courseList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        v =layoutInflater.inflate(R.layout.item_infor_class,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int i) {
        holder.description.setText(CourseList.get(i).getDescription());
        holder.name.setText(CourseList.get(i).getName());

        Glide.with(holder.image.getContext())
                .load( BASE_IMG
                        +CourseList.get(i).getImage())
                .into(holder.image);
        String maLop = CourseList.get(i).getId();
        String name = CourseList.get(i).getName();
        String description = CourseList.get(i).getDescription();
        String status = CourseList.get(i).getStatus();
        String price = CourseList.get(i).getPrice();
        String level = CourseList.get(i).getLevel();
        String image = CourseList.get(i).getImage();
        String ngayBatDau = CourseList.get(i).getPublishedAt();
        String ngayKetThuc = CourseList.get(i).getExpiredAt();
        Teacher teacher= CourseList.get(i).getTeacher();
        Log.d("test" ,CourseList.toString());

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Course course = new Course(maLop,name,description,status,price,level,image,ngayBatDau,ngayKetThuc,teacher);
                Intent i = new Intent(context, RegisterCourseActivity.class);
                i.putExtra("putCourse", course);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return CourseList.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView name;
        TextView description;
        ImageView image;
        LinearLayout item;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.name_Class);
            description = itemView.findViewById(R.id.infor_class);
            image = itemView.findViewById(R.id.image_Class);
            item = itemView.findViewById(R.id.item);



        }
    }
}
