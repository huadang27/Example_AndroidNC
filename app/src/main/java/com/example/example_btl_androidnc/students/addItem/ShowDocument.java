package com.example.example_btl_androidnc.students.addItem;

import static com.example.example_btl_androidnc.students.api.RetrofitClient.BASE_IMG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.model.Document;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ShowDocument extends AppCompatActivity {

    String scheduleId;
    TextView tai, ngaydang, mota;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_document);
        Intent intent = getIntent();
        scheduleId = intent.getStringExtra("scheduleId");
        tai = findViewById(R.id.txtTai);
        ngaydang = findViewById(R.id.txtngaydang);
        mota = findViewById(R.id.txtMota);
        progressBar = findViewById(R.id.progressBar);

        getDocument(scheduleId);

        tai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadFile(tai.getText().toString());
            }
        });
    }

    private void getDocument(String scheduleId) {
        GetAPI_Service apiService = RetrofitClient.getClient().create(GetAPI_Service.class);
        Call<Document> call = apiService.getDocumentByScheduleId(scheduleId);
        call.enqueue(new Callback<Document>() {
            @Override
            public void onResponse(Call<Document> call, Response<Document> response) {
                if (response.isSuccessful()) {
                    Document document = response.body();
                    // Hiển thị dữ liệu của Document
                    tai.setText(document.getFilePath());
                    ngaydang.setText(document.getDate());
                    mota.setText(document.getDescription());
                } else {
                    // Xử lý trường hợp không thành công
                }
            }

            @Override
            public void onFailure(Call<Document> call, Throwable t) {
                // Xử lý trường hợp gặp lỗi
            }
        });
    }

    private void downloadFile(String fileUrl) {
        String baseUrl = BASE_IMG; // Thay đổi địa chỉ IP phù hợp
        String fullUrl = baseUrl + fileUrl;
        tai.setEnabled(false);

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fullUrl));
        request.setDescription("Downloading document");
        request.setTitle("Document Download");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileUrl.substring(fileUrl.lastIndexOf('/') + 1));

        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        long downloadId = manager.enqueue(request);

        progressBar.setVisibility(View.VISIBLE);

        BroadcastReceiver onComplete = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long completedDownloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                if (downloadId == completedDownloadId) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(ShowDocument.this, "Download completed!", Toast.LENGTH_SHORT).show();
                    tai.setEnabled(true); // Kích hoạt lại nút tai
                }
            }
        };

        registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }
}
