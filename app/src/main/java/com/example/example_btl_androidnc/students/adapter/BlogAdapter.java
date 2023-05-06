package com.example.example_btl_androidnc.students.adapter;

import static com.example.example_btl_androidnc.students.api.RetrofitClient.BASE_IMG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.addItem.DetailBlogActivity;
import com.example.example_btl_androidnc.students.addItem.RegisterCourseActivity;
import com.example.example_btl_androidnc.students.model.Blog;
import com.example.example_btl_androidnc.students.model.Course;

import java.io.Serializable;
import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.MyViewHolder>{
    private Context context;
    private List<Blog> BlogList;

    public BlogAdapter(Context context, List<Blog> blogList) {
        this.context = context;
        BlogList = blogList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v = layoutInflater.inflate(R.layout.list_blog, parent, false);
        return new MyViewHolder(v);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int i) {
        Blog blog = BlogList.get(i);
        setBlogData(holder, blog);
        holder.item.setOnClickListener(view -> openRegisterBlogActivity(blog));
    }

    @Override
    public int getItemCount() {
        return BlogList.size();
    }

    private void setBlogData(MyViewHolder holder, Blog blog) {
        holder.txtTitle_item.setText(blog.getTitle());
        holder.txtContent_item.setText(blog.getContent());

        Glide.with(holder.image_blog_item.getContext())
                .load(BASE_IMG + blog.getImage())
                .into(holder.image_blog_item);
    }

    private void openRegisterBlogActivity(Blog blog) {

        Intent intent = new Intent(context, DetailBlogActivity.class);
     intent.putExtra("putBlog",blog);
        context.startActivity(intent);
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image_blog_item;
        TextView  txtTitle_item,txtContent_item;
        RelativeLayout item;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle_item = itemView.findViewById(R.id.txtTitle_item);
            txtContent_item = itemView.findViewById(R.id.txtContent_item);
            image_blog_item = itemView.findViewById(R.id.image_blog_item);
            item = itemView.findViewById(R.id.item_blog);
        }
    }
}
