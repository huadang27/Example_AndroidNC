package com.example.example_btl_androidnc.students.database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.example_btl_androidnc.students.model.ImageHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ImageDownloader  extends AsyncTask<String, Void, MultipartBody.Part> {
    private Context context;
    private OnImageDownloadedListener listener;

    public ImageDownloader(Context context, OnImageDownloadedListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected MultipartBody.Part doInBackground(String... strings) {
        String imageUrl = strings[0];
        try {
            URL url = new URL(imageUrl);
            InputStream inputStream = (InputStream) url.getContent();
            byte[] imageBytes = ImageHelper.getBytesFromInputStream(inputStream);
            File file = ImageHelper.convertBytesToFile(imageBytes, context);
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);

            return MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(MultipartBody.Part part) {
        if (listener != null) {
            listener.onImageDownloaded(part);
        }
    }

    public interface OnImageDownloadedListener {
        void onImageDownloaded(MultipartBody.Part imagePart);
    }
}
