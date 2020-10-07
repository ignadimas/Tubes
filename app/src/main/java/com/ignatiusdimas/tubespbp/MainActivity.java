package com.ignatiusdimas.tubespbp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;

    EditText namaLengkap, alamat, no_telp;
    String namastr, alamatr, nostr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadPreferences();
        setProfile();

        namaLengkap = findViewById(R.id.inputNama);
        alamat = findViewById(R.id.inputAlamat);
        no_telp = findViewById(R.id.inputNoHp);
        preferences =getSharedPreferences("MyUser", Context.MODE_PRIVATE);

        Button addBtn = findViewById(R.id.btnMulai);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namastr = namaLengkap.getText().toString();
                alamatr = alamat.getText().toString();
                nostr = no_telp.getText().toString();
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("nama", namastr);
                editor.putString("alamat", alamatr);
                editor.putString("no handphone", nostr);
                editor.commit();
                Toast.makeText(MainActivity.this, "Profile saved", Toast.LENGTH_SHORT).show();
                //Intent Main = new Intent(MainActivity.this, ProfileActivity.class);
                //startActivity(Main);
                savePreferences();
            }
        });

    }

    private void setProfile(){
        TextInputEditText namaLengkapText = findViewById(R.id.inputNama);
        TextInputEditText alamatText = findViewById(R.id.inputAlamat);
        TextInputEditText no_telpText = findViewById(R.id.inputNoHp);
        namaLengkapText.setText(namastr);
        alamatText.setText(alamatr);
        no_telpText.setText(nostr);
    }

    private void loadPreferences(){
        preferences = getSharedPreferences("MyUser", Context.MODE_PRIVATE);
        if(preferences!=null){
            namastr = preferences.getString("nama", "");
            alamatr = preferences.getString("alamat", "");
            nostr = preferences.getString("no handphone", "");
        }
    }

    private void savePreferences(){

        final TextInputEditText namaLengkapText = findViewById(R.id.inputNama);
        final TextInputEditText alamatText = findViewById(R.id.inputAlamat);
        final TextInputEditText no_telpText = findViewById(R.id.inputNoHp);
        SharedPreferences.Editor editor = preferences.edit();
        final Intent Main = new Intent(this, MenuActivity.class);
        if(!namaLengkapText.getText().toString().isEmpty() && !alamatText.getText().toString().isEmpty() && !no_telpText.getText().toString().isEmpty()){
            editor.putString("nama", namaLengkapText.getText().toString());
            editor.putString("alamat", alamatText.getText().toString());
            editor.putString("no handphone", no_telpText.getText().toString());
            editor.apply();
            //Toast.makeText(this, "Profile saved", Toast.LENGTH_SHORT).show();
            startActivity(Main);
        }else{
            Toast.makeText(this, "Fill correctly", Toast.LENGTH_SHORT).show();

        }
    }

}