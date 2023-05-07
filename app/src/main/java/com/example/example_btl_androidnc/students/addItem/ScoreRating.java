package com.example.example_btl_androidnc.students.addItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.adapter.ScoreRatingAdapter;
import com.example.example_btl_androidnc.students.adapter.StudentListAdapter;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.model.IntegerValueFormatter;
import com.example.example_btl_androidnc.students.model.Schedule;
import com.example.example_btl_androidnc.students.model.UserRankResponse;
import com.example.example_btl_androidnc.students.model.Users;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScoreRating extends AppCompatActivity {
    RecyclerView recyclerView;
    ScoreRatingAdapter adapter;
    TextView  scorerating,qualified,unsatisfactory;
    PieChart pieChart;
    BarChart barChart;

    private List<UserRankResponse> userRankResponses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_rating);
        recyclerView = findViewById(R.id.recyclerView);
        scorerating = findViewById(R.id.scorerating);
        qualified = findViewById(R.id.qualified);
        unsatisfactory = findViewById(R.id.unsatisfactory);
         pieChart = findViewById(R.id.pieChart);

       // barChart = findViewById(R.id.barChart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String courseId = getIntent().getStringExtra("courseId");


        getData(courseId);
        scorerating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<UserRankResponse> sortedList = sortDescendingByAvg(userRankResponses);
                updateData(sortedList);
                updatePieChartData(userRankResponses);
            }
        });

        qualified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<UserRankResponse> filteredList = filterAvgGreaterOrEqualFive(userRankResponses);
                updateData(filteredList);
                updatePieChartData(filteredList);
            }
        });

        unsatisfactory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<UserRankResponse> filteredList = filterAvgLessThanFive(userRankResponses);
                updateData(filteredList);
                updatePieChartData(filteredList);
            }
        });
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                // e là đối tượng chứa thông tin về giá trị được chọn
                // h là đối tượng chứa thông tin về vị trí được chọn trong biểu đồ
                int index = (int) h.getX(); // Lấy vị trí của cột được chọn

                // Thực hiện các hành động khi cột được chọn, ví dụ:
                if(index ==0){
                    Toast.makeText(ScoreRating.this, "0", Toast.LENGTH_SHORT).show();
                }else  if(index==1){
                    Toast.makeText(ScoreRating.this, "1", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(ScoreRating.this, "2", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });


    }

 //biểu đồ tròn
    private void updatePieChartData(List<UserRankResponse> userRankResponses) {
        int group1 = 0, group2 = 0, group3 = 0;

        for (UserRankResponse userRankResponse : userRankResponses) {
            float avg = userRankResponse.getRank().getAvg();
            if (avg <= 4) {
                group1++;
            } else if (avg <= 6) {
                group2++;
            } else {
                group3++; // Sửa lại điều kiện cho group3 từ "avg > 8" thành "avg > 6"
            }
        }

        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(group1, "avg<4"));
        entries.add(new PieEntry(group2, "4<=avg<=6"));
        entries.add(new PieEntry(group3, "avg>8")); // Sửa lại label cho group3 từ "avg > 8" thành "avg > 6"

        PieDataSet dataSet = new PieDataSet(entries, "Tóm tắt");
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        PieData pieData = new PieData(dataSet);

        Legend legend = pieChart.getLegend();
        legend.setTextColor(Color.BLACK); // Thay đổi màu chữ thành màu đen (hoặc màu bạn muốn)
        pieData.setValueFormatter(new IntegerValueFormatter());
        pieData.setValueTextSize(12f);

        // thay đổi màu nhãn
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setData(pieData);
        pieChart.invalidate(); // refresh
    }


    // biểu đồ cột
//    private void updatePieChartData(List<UserRankResponse> userRankResponses) {
//        int group1 = 0, group2 = 0, group3 = 0;
//
//        for (UserRankResponse userRankResponse : userRankResponses) {
//            float avg = userRankResponse.getRank().getAvg();
//            if (avg <= 4) {
//                group1++;
//            } else if (avg <= 6) {
//                group2++;
//            } else {
//                group3++;
//            }
//        }
//
//        List<BarEntry> entries = new ArrayList<>();
//        entries.add(new BarEntry(0, group1));
//        entries.add(new BarEntry(1, group2));
//        entries.add(new BarEntry(2, group3));
//
//        BarDataSet dataSet = new BarDataSet(entries, "Tóm tắt");
//
//        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
//
//        BarData barData = new BarData(dataSet);
//        barData.setValueFormatter(new IntegerValueFormatter());
//        barData.setValueTextSize(12f);
//        barData.setValueTextColor(Color.BLACK);
//
//        barChart.setData(barData);
//        barChart.setFitBars(true);
//        barChart.getDescription().setEnabled(false);
//        barChart.getAxisRight().setEnabled(false);
//        barChart.getXAxis().setEnabled(false);
//        barChart.getAxisLeft().setAxisMinimum(0f);
//
//        XAxis xAxis = barChart.getXAxis();
//        xAxis.setEnabled(true);
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawGridLines(false);
//        xAxis.setTextColor(Color.BLACK);
//        xAxis.setValueFormatter(new IndexAxisValueFormatter(Arrays.asList("avg <= 4", "4 < avg <= 6", "avg > 6")));
//
//
//        Legend legend = barChart.getLegend();
//        legend.setTextColor(Color.BLACK);
//
//        barChart.invalidate(); // refresh
//    }


    private void getData(String courseId) {
        GetAPI_Service getAPI_service = RetrofitClient.getClient().create(GetAPI_Service.class);
        Call<List<UserRankResponse>> call = getAPI_service.getRanksByCourseId(courseId);
        call.enqueue(new Callback<List<UserRankResponse>>() {
            @Override
            public void onResponse(Call<List<UserRankResponse>> call, Response<List<UserRankResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    userRankResponses = response.body();
                    Log.d("test111", userRankResponses.toString());

                    // Cập nhật recyclerView với danh sách userRankResponses ban đầu
                    updateData(userRankResponses);
                    updatePieChartData(userRankResponses);
                } else {
                    // Xử lý trường hợp không có dữ liệu hoặc lỗi trả về từ API
                    Log.d("test111", "No data or error from API");
                }
            }

            @Override
            public void onFailure(Call<List<UserRankResponse>> call, Throwable t) {

            }
        });
    }

    private void updateData(List<UserRankResponse> userRankResponses) {
        adapter = new ScoreRatingAdapter(ScoreRating.this, userRankResponses);
        recyclerView.setAdapter(adapter);
    }

    private List<UserRankResponse> sortDescendingByAvg(List<UserRankResponse> userRankResponses) {
        if (userRankResponses == null) {
            return new ArrayList<>();
        }

        Collections.sort(userRankResponses, new Comparator<UserRankResponse>() {
            @Override
            public int compare(UserRankResponse o1, UserRankResponse o2) {
                return Double.compare(o2.getRank().getAvg(), o1.getRank().getAvg());
            }
        });
        return userRankResponses;
    }

    private List<UserRankResponse> filterAvgGreaterOrEqualFive(List<UserRankResponse> userRankResponses) {
        if (userRankResponses == null) {
            return new ArrayList<>();
        }

        List<UserRankResponse> filteredList = new ArrayList<>();
        for (UserRankResponse userRankResponse : userRankResponses) {
            if (userRankResponse.getRank().getAvg() >= 5) {
                filteredList.add(userRankResponse);
            }
        }
        return filteredList;
    }

    private List<UserRankResponse> filterAvgLessThanFive(List<UserRankResponse> userRankResponses) {
        if (userRankResponses == null) {
            return new ArrayList<>();
        }

        List<UserRankResponse> filteredList = new ArrayList<>();
        for (UserRankResponse userRankResponse : userRankResponses) {
            if (userRankResponse.getRank().getAvg() < 5) {
                filteredList.add(userRankResponse);
            }
        }
        return filteredList;
    }






}