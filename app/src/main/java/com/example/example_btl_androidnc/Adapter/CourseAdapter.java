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
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.description.setText(CourseList.get(position).getDescription());
        holder.name.setText(CourseList.get(position).getName());

        Glide.with(holder.image.getContext())
                .load( "http://192.168.1.44:8088"
                        +CourseList.get(position).getImage())
                .into(holder.image);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, CourseList.get(position).getDescription(), Toast.LENGTH_SHORT).show();
            }
        });
    //   holder.image.setText(CourseList.get(position).getImage());
     //   holder.image.setImageBitmap(CourseList.get((position)).getImage());

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
