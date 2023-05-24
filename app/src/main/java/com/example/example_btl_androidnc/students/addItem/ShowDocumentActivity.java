package com.example.example_btl_androidnc.students.addItem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.example_btl_androidnc.R;

public class ShowDocumentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_document);
    }

//    private void downloadFile(String fileId) {
//        FileDownloadService service = retrofit.create(FileDownloadService.class);
//        Call<ResponseBody> call = service.downloadFile(fileId);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    // Tải file xuống và lưu vào bộ nhớ của thiết bị
//                    saveFile(response.body(), "myFile.pdf");
//                } else {
//                    // Xử lý lỗi
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                // Xử lý lỗi
//            }
//        });
//    }
//
//    private void saveFile(ResponseBody body, String fileName) {
//        try {
//            InputStream inputStream = null;
//            OutputStream outputStream = null;
//
//            try {
//                File dir = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
//                File file = new File(dir, fileName);
//
//                inputStream = body.byteStream();
//                outputStream = new FileOutputStream(file);
//
//                byte[] data = new byte[1024];
//                int count;
//                while ((count = inputStream.read(data)) != -1) {
//                    outputStream.write(data, 0, count);
//                }
//
//                // Hiển thị file đã tải về
//                showFile(file);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//                if (outputStream != null) {
//                    outputStream.close();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void showFile(File file) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        Uri uri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file);
//        intent.setDataAndType(uri, getMimeType(file.getAbsolutePath()));
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        startActivity(intent);
//    }
//
//    private String getMimeType(String url) {
//        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
//        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
//    }
}