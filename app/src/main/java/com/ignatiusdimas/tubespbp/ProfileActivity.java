package com.ignatiusdimas.tubespbp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
}
