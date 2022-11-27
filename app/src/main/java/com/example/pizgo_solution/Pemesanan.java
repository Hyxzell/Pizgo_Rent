package com.example.pizgo_solution;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Pemesanan extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextNama_Customer,editTextNo_HP,editTextNo_Pol_Mobil,editTextMerk_Mobil,editTextTipe_Mobil,editTextLama_Sewa,editTextID_Mobil;
    private Button buttonInput_Pemesanan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pemesanan);

        editTextNama_Customer = (EditText) findViewById(R.id.editTextNama_Customer);
        editTextNo_HP = (EditText) findViewById(R.id.editTextNo_HP);
        editTextNo_Pol_Mobil = (EditText) findViewById(R.id.editTextNo_Pol_Mobil);
        editTextMerk_Mobil = (EditText) findViewById(R.id.editTextMerk_Mobil);
        editTextTipe_Mobil = (EditText) findViewById(R.id.editTextTipe_Mobil);
        editTextLama_Sewa = (EditText) findViewById(R.id.editTextLama_Sewa);


        buttonInput_Pemesanan = (Button) findViewById(R.id.buttonInput_Pemesanan);

        buttonInput_Pemesanan.setOnClickListener(this);

    }
    private void addPemesanan(){
        final String nama_customer = editTextNama_Customer.getText().toString().trim();
        final String no_hp = editTextNo_HP.getText().toString().trim();
        final String no_pol_mobil = editTextNo_Pol_Mobil.getText().toString().trim();
        final String merk_mobil = editTextMerk_Mobil.getText().toString().trim();
        final String tipe_mobil = editTextTipe_Mobil.getText().toString().trim();
        final String lama_sewa = editTextLama_Sewa.getText().toString().trim();


        class AddPemesanan extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Pemesanan.this,"Menambahkan...","Tunggu...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Pemesanan.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Configuration.KEY_NO_POL_MOBIL, no_pol_mobil);
                params.put(Configuration.KEY_NAMA_CUSTOMER, nama_customer);
                params.put(Configuration.KEY_NO_HP, no_hp);
                params.put(Configuration.KEY_MERK_MOBIL, merk_mobil);
                params.put(Configuration.KEY_TIPE_MOBIL, tipe_mobil);
                params.put(Configuration.KEY_LAMA_SEWA, lama_sewa);



                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Configuration.URL_ADD, params);
                Log.d("res",res);
                return res;
            }
        }
        AddPemesanan ae = new AddPemesanan() ;
        ae.execute();

    }
    @Override
    public void onClick(View view) {
        if (view == buttonInput_Pemesanan){
            addPemesanan();
        }
    }
}