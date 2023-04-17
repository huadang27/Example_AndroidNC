package com.example.example_btl_androidnc.students.addItem;

import static com.example.example_btl_androidnc.students.api.RetrofitClient.BASE_IMG;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.example_btl_androidnc.R;
import com.example.example_btl_androidnc.students.api.GetAPI_Service;
import com.example.example_btl_androidnc.students.api.RetrofitClient;
import com.example.example_btl_androidnc.students.database.MySharedPreferences;
import com.example.example_btl_androidnc.students.model.AvatarResponse;
import com.example.example_btl_androidnc.students.model.ImageHelper;
import com.example.example_btl_androidnc.students.model.Users;
import com.google.android.gms.common.internal.Constants;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.concurrent.ExecutionException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Edit_Profile extends AppCompatActivity {
    ImageView imgBack;
    EditText edt_name, edt_address, edt_phone;
    Button Btn_save;
    ImageHelper avatarImageView;
    RadioButton male_radiobutton, female_radiobutton;

    private MySharedPreferences mySharedPreferences;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        mySharedPreferences = new MySharedPreferences(Edit_Profile.this);
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolbar);
        imgBack = findViewById(R.id.img_back);
        edt_name = findViewById(R.id.edt_name);
        edt_address = findViewById(R.id.edt_address);
        edt_phone = findViewById(R.id.edt_phone);
        male_radiobutton = findViewById(R.id.male_radiobutton);
        female_radiobutton = findViewById(R.id.female_radiobutton);
        Btn_save = findViewById(R.id.btn_save);
        avatarImageView = findViewById(R.id.avatar_imageview);
        getControls();
        Btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUserProfile();
            }

        });
        avatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImage();

            }
        });
    }
    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Hàm getRealPathFromURI
    private String getRealPathFromURI(Uri imageUri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(imageUri, projection, null, null, null);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String realPath = cursor.getString(columnIndex);
        cursor.close();
        return realPath;
    }

    // Hàm onActivityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            String realPath = getRealPathFromURI(imageUri);
            // Thực hiện các thao tác với đường dẫn thực tế của ảnh ở đây
            uploadImageToServer(imageUri);
        }
    }

    // Hàm để nhận ảnh được chọn
    private void uploadImageToServer(Uri imageUri) {
        String authToken = mySharedPreferences.getToken();
        // Đoạn code để lấy đường dẫn thực của ảnh từ Uri
      //  realPart = getRealPathFromURI(imageUri);
        String realPart = "/api/files" + getRealPathFromURI(imageUri);
        Picasso.get().load(imageUri).into(avatarImageView);

        File file = new File(realPart);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        Users users = new Users();
        users.setImage(realPart.toString());
        // Gửi request lên server
    //    RequestBody userIdRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(userId));
        GetAPI_Service getAPI_service = RetrofitClient.getClient(authToken).create(GetAPI_Service.class);
        Call<ResponseBody> call = getAPI_service.updateAvatar(users);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Xử lý response từ server
                if (response.isSuccessful()) {
                    // Thông báo cập nhật ảnh đại diện thành công
                    Toast.makeText(Edit_Profile.this, "Cập nhật ảnh đại diện thành công", Toast.LENGTH_SHORT).show();
                } else {
                    // Hiển thị thông báo lỗi
                    Log.d("demo ","a"+realPart);
                    Toast.makeText(Edit_Profile.this, "Cập nhật ảnh đại diện thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // Hiển thị thông báo lỗi
               // Log.d("a","zzzzzzzzzzzzzzzzzzzzzz"+realPart);
                Toast.makeText(Edit_Profile.this, "Lỗi kết nối "+imageUri, Toast.LENGTH_SHORT).show();
            }
        });
    }
//    private void updateAvatar(Uri uri, Context context) {
//
//        // Lấy đường dẫn thực tế của file ảnh
//        String filePath = getRealPathFromURI(uri, context);
//        //Bitmap newBitmap = BitmapFactory.decodeFile(filePath);
//        //avatarImageView.setImageBitmap(newBitmap);
//        if (filePath == null) {
//            // Xử lý lỗi nếu không lấy được đường dẫn
//            return;
//        }
//
//        // Tạo đối tượng File từ đường dẫn
//        File file = new File(filePath);
//        // Tạo request body
//        RequestBody requestBody = RequestBody.create(MediaType.parse("/api/files/*"), file);
//
//// Tạo part từ request body
//        String authToken = mySharedPreferences.getToken();
//        MultipartBody.Part filePart = MultipartBody.Part.createFormData("avatar", file.getName(), requestBody);
//        GetAPI_Service getAPI_service = RetrofitClient.getClient(authToken).create(GetAPI_Service.class);
//        // users = new Users();
//        Call<AvatarResponse> call = getAPI_service.updateAvatar(authToken,filePart);
//
//// Tạo call và gửi request
//        call.enqueue(new Callback<AvatarResponse>() {
//            @Override
//            public void onResponse(Call<AvatarResponse> call, Response<AvatarResponse> response) {
//                if (response.isSuccessful()) {
//                    // Cập nhật ảnh đại diện thành công, xử lý logic tại đây
//                String newAvatarUrl = response.body().getAvatarUrl();
//                mySharedPreferences.saveAvatarUrl(newAvatarUrl);
//
//                    // Cập nhật ảnh mới lên ImageView
//                    Glide.with(avatarImageView.getContext())
//                            .load(BASE_IMG + filePath)
//                            .into(avatarImageView);
//                    Toast.makeText(Edit_Profile.this, "Thành công", Toast.LENGTH_SHORT).show();
//
//                } else {
//                    // Xử lý lỗi nếu có
//                    Toast.makeText(Edit_Profile.this, "Upload failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AvatarResponse> call, Throwable t) {
//                Toast.makeText(Edit_Profile.this, "Lỗi Call", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    //-------------------------------------
    private void updateUserProfile() {
        String authToken = mySharedPreferences.getToken();
        // Lấy thông tin người dùng từ các trường nhập liệu trên giao diện
        String name = edt_name.getText().toString().trim();
        String address = edt_address.getText().toString().trim();
        String phone = edt_phone.getText().toString().trim();

        // Tạo đối tượng User chứa thông tin người dùng cần cập nhật
        Users users = new Users();
        users.setName(name);
        users.setAddress(address);
        users.setPhone(phone);
        if (male_radiobutton.isChecked()) {
            users.setGender("female");
        } else if (female_radiobutton.isChecked()) {
            users.setGender("male");
        } else {
            // Xử lý trường hợp không có RadioButton nào được chọn
        }


        // Tạo Interceptor để thêm token vào Header của yêu cầu

        GetAPI_Service getAPI_service = RetrofitClient.getClient(authToken).create(GetAPI_Service.class);
        // users = new Users();
        Call<ResponseBody> call = getAPI_service.updateUser(users);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(Edit_Profile.this, "Update successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Edit_Profile.this, SetAdmin_Activity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Xử lý trường hợp yêu cầu thất bại
                    Toast.makeText(Edit_Profile.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Edit_Profile.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //--------------------------------------
    private void getControls() {
        // nút thoát
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}