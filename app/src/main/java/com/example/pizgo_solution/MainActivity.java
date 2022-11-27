package com.example.pizgo_solution;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    public void input_kendaraan(View view) {
        startActivity(new Intent(MainActivity.this, Input_Kendaraan.class));
    }

    public void pemesanan(View view) {
        startActivity(new Intent(MainActivity.this, Pemesanan.class));
    }

    /*public void Invoice(View view) {
        startActivity(new Intent(MainActivity.this, Invoice.class));
    }*/

    public void lihatkendaraan(View view) {
        startActivity(new Intent(MainActivity.this, Lihat_Kendaraan.class));
    }

    public void lihatpemesanan(View view) {
        startActivity(new Intent(MainActivity.this, Lihat_Pemesanan.class));
    }
}