package com.ignatiusdimas.tubespbp;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ignatiusdimas.tubespbp.adapter.RecyclerViewAdapterMobil;
import com.ignatiusdimas.tubespbp.databinding.ActivityMenuBinding;
import com.ignatiusdimas.tubespbp.model.DaftarMobil;
import com.ignatiusdimas.tubespbp.model.Mobil;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private ArrayList<Mobil> listMobil;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterMobil adapter;
    private ActivityMenuBinding binding;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu);
        binding.recyclerViewMobil.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewMobil.setHasFixedSize(true);

        listMobil = new DaftarMobil().MOBIL;

        recyclerView = findViewById(R.id.recycler_view_mobil);
        adapter = new RecyclerViewAdapterMobil(MenuActivity.this, listMobil);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
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