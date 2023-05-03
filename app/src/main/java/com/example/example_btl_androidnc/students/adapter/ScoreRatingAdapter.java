package com.example.example_btl_androidnc.students.adapter;

import static com.example.example_btl_androidnc.students.api.RetrofitClient.BASE_IMG;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.databinding.ScoreItemListBinding;
import com.example.example_btl_androidnc.students.model.Rank;
import com.example.example_btl_androidnc.students.model.UserRankResponse;
import com.example.example_btl_androidnc.students.model.Users;

import java.util.List;
import java.util.Locale;

public class ScoreRatingAdapter extends RecyclerView.Adapter<ScoreRatingAdapter.ScoreViewHolder> {

    private Context context;
    private List<UserRankResponse>userRankResponses;
    ScoreItemListBinding scoreItemListBinding;

    public ScoreRatingAdapter(Context context, List<UserRankResponse> userRankResponses) {
        this.context = context;
        this.userRankResponses = userRankResponses;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        scoreItemListBinding = ScoreItemListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ScoreViewHolder(scoreItemListBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        UserRankResponse currentUser = userRankResponses.get(position);

        if (currentUser != null) {
            Users users = currentUser.getUsers();
            Rank rank = currentUser.getRank();

            if (users != null) {
                if (users.getName() != null) {

                    holder.Name.setText("Name: "+users.getName());

                }
                if (users.getGender() != null) {
                    holder.gender.setText("Gender: "+users.getGender());
                }
                if (users.getImage() != null) {
                    Glide.with(holder.avatar.getContext())
                            .load(BASE_IMG + users.getImage())
                            .into(holder.avatar);
                }

            }

            if (rank != null) {
                holder.midtermGrades.setText("midtermGrades: "+formatFloat(rank.getMidtermGrades()));
                holder.finalGrades.setText("finalGrades: "+formatFloat(rank.getFinalGrades()));
                holder.exams.setText("exams: "+formatFloat(rank.getExams()));
                if(rank.getAvg()<5){
                    holder.imageView2.setBackgroundResource(R.drawable.custom_button_red);
                }
            }
        }
    }
    private String formatFloat(Float value) {
        if (value != null) {
            return String.format(Locale.getDefault(), "%.2f", value);
        }
        return "";
    }


    @Override
    public int getItemCount() {
        return userRankResponses != null ? userRankResponses.size() : 0;
    }


    public  static  class  ScoreViewHolder extends RecyclerView.ViewHolder{

        ImageView avatar;
        TextView Name, gender, midtermGrades, finalGrades, exams, imageView2;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.avatar);
            Name = itemView.findViewById(R.id.Name);
            gender = itemView.findViewById(R.id.gender);
            midtermGrades = itemView.findViewById(R.id.midtermGrades);
            finalGrades = itemView.findViewById(R.id.finalGrades);
            exams = itemView.findViewById(R.id.exams);
            imageView2 = itemView.findViewById(R.id.imageView2);
        }
    }
}
