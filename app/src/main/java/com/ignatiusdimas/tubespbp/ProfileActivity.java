package com.ignatiusdimas.tubespbp;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private static final int PERMISSION_CODE =1000 ;
    private static final int IMAGE_CAPTURE_CODE = 1001;
    Button mCaptureBtn;
    ImageView mImageView;
    Uri image_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mImageView = findViewById(R.id.image_view);
        mCaptureBtn = findViewById(R.id.capture_image_btn);

        TextView nama = findViewById(R.id.tvNama);
        TextView alamat = findViewById(R.id.tvAlamat);
        TextView no = findViewById(R.id.tvNo);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUser", Context.MODE_PRIVATE);
        String namast = sp.getString("nama", "");
        String alamatst = sp.getString("alamat", "");
        String nost = sp.getString("no handphone", "");

        nama.setText(namast);
        alamat.setText(alamatst);
        no.setText(nost);

        mCaptureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT > -Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) ==
                            PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED) {
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE);
                    } else {
                        openCamera();

                    }
                } else {
                    openCamera();

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.menu_home){
            startActivity(new Intent(this, MenuActivity.class));
        } else if (item.getItemId() == R.id.menu_Booking) {
            startActivity(new Intent(this, BookingActivity.class));
        } else if (item.getItemId() == R.id.menu_profile) {
            startActivity(new Intent(this, ProfileActivity.class));
        }

        return true;
    }

    //Orientasi screen pengguna portrait/landscape
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE,"New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION,"From the camera");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent,IMAGE_CAPTURE_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0]==
                        PackageManager.PERMISSION_GRANTED){
                    openCamera();
                }
                else {
                    Toast.makeText(this,"Permission denied...",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            mImageView.setImageURI(image_uri);
        }
    }
}
