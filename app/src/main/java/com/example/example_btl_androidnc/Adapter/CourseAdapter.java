package com.example.example_btl_androidnc.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.example_btl_androidnc.Model.Course;
import com.example.example_btl_androidnc.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.MyViewHolder>{
    private Context context;
    private List<Course> courseList;

    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public CourseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater= LayoutInflater.from(context);
        v =layoutInflater.inflate(R.layout.item_infor_class_cardview,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name.setText(courseList.get(position).getName());
        holder.description.setText(courseList.get(position).getDescription());

        Glide.with(holder.image.getContext())
                .load( "http://192.168.0.104:8082"
                        +courseList.get(position).getImage())
                .into(holder.image);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, courseList.get(position).getDescription(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    public int getItemCount() {
        return courseList.size();
    }


    public static class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView name;
        TextView description;
        ImageView image;
        LinearLayout  item;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name =itemView.findViewById(R.id.name_Class);
            description = itemView.findViewById(R.id.infor_class);
            image = itemView.findViewById(R.id.image_Class);
            item = itemView.findViewById(R.id.item);



        }
    }
}
