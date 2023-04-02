package com.example.example_btl_androidnc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.model.Course;
import com.example.example_btl_androidnc.model.Users;
import com.google.firebase.firestore.auth.User;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {
    private Context context;
    private List<Users> users;

    public StudentListAdapter(Context context, List<Users> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_student, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Users user = users.get(position);
        holder.nameTextView.setText(user.getName());
        holder.emailTextView.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView emailTextView;
        // add other views as needed

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_view_name);
            emailTextView = itemView.findViewById(R.id.text_view_email);
            // find other views as needed
        }
    }
}
