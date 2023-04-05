package com.example.example_btl_androidnc.students.adapter;

import static com.example.example_btl_androidnc.students.api.RetrofitClient.BASE_IMG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.example_btl_androidnc.students.addItem.RegisterCourseActivity;
import com.example.example_btl_androidnc.students.model.Course;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.model.Users;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder>{
    private Context context;
    private List<Course> CourseList;

    public CourseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        CourseList = courseList;
        Log.d("testtoken", courseList.toString());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        v =layoutInflater.inflate(R.layout.item_news,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int i) {
        holder.description.setText(CourseList.get(i).getDescription());
        holder.name.setText(CourseList.get(i).getName());
        Log.d("test", CourseList.get(i).getPublishedAt() +"...");
        holder.info_date.setText(convertDateFormat(CourseList.get(i).getPublishedAt()));
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
        Users users= CourseList.get(i).getUsers();


        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Course course = new Course(maLop,name,description,status,price,level,image,ngayBatDau,ngayKetThuc,users);
                Intent i = new Intent(context, RegisterCourseActivity.class);
                i.putExtra("putCourse", course);
                context.startActivity(i);
            }
        });

    }
    public static String convertDateFormat(String inputDate) {
        String[] possibleFormats = {"MMM dd, yyyy", "yyyy-MM-dd'T'HH:mm:ss.SSSZ"};
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy/MM/dd");

        for (String inputFormat : possibleFormats) {
            try {
                SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat, Locale.ENGLISH);
                Date date = inputDateFormat.parse(inputDate);
                return outputDateFormat.format(date);
            } catch (ParseException e) {
                // continue to try the next format
            }
        }

        // if no format is matched
        return null;
    }



    @Override
    public int getItemCount() {
        return CourseList.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView name;
        TextView description,info_date;
        ImageView image;
        RelativeLayout item;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.name_Class);
            description = itemView.findViewById(R.id.infor_class);
            image = itemView.findViewById(R.id.image_Class);
            info_date = itemView.findViewById(R.id.info_date);
            item = itemView.findViewById(R.id.item);

        }
    }
}
