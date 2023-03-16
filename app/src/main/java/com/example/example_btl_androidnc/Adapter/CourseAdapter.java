package com.example.example_btl_androidnc.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example_btl_androidnc.Model.Course;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.description.setText(CourseList.get(position).getDescription());
        holder.name.setText(CourseList.get(position).getName());
    //   holder.image.setText(CourseList.get(position).getImage());
        holder.image.setImageBitmap(CourseList.get((position)).getImage());

    }

    @Override
    public int getItemCount() {
        return CourseList.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{
    TextView name;
    TextView description;
  ImageView image;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        name =itemView.findViewById(R.id.name_Class);
        description = itemView.findViewById(R.id.infor_class);
       image = itemView.findViewById(R.id.image_Class);

    }
}}
