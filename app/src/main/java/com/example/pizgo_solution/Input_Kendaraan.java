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

public class Input_Kendaraan extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextNo_Pol,editTextMerk,editTextTipe,editTextHarga_Sewa,editTextID_Mobil;
    private Button buttonInput_Kendaraan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_input_kendaraan);

        editTextNo_Pol = (EditText) findViewById(R.id.editTextNo_Pol);
        editTextMerk = (EditText) findViewById(R.id.editTextMerk);
        editTextTipe = (EditText) findViewById(R.id.editTextTipe);
        editTextHarga_Sewa = (EditText) findViewById(R.id.editTextHarga_Sewa);


        buttonInput_Kendaraan = (Button) findViewById(R.id.buttonInput_Kendaraan);

        buttonInput_Kendaraan.setOnClickListener(this);

    }
    private void addKendaraan(){
        final String No_Pol = editTextNo_Pol.getText().toString().trim();
        final String Merk = editTextMerk.getText().toString().trim();
        final String Tipe = editTextTipe.getText().toString().trim();
        final String Harga_Sewa = editTextHarga_Sewa.getText().toString().trim();


        class AddKendaraan extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Input_Kendaraan.this,"Menambahkan...","Tunggu...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Input_Kendaraan.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Configuration.KEY_NO_POL, No_Pol);
                params.put(Configuration.KEY_MERK, Merk);
                params.put(Configuration.KEY_TIPE, Tipe);
                params.put(Configuration.KEY_HARGA_SEWA, Harga_Sewa);



                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Configuration.URL_ADD, params);
                Log.d("res",res);
                return res;
            }
        }
        AddKendaraan ae = new AddKendaraan() ;
        ae.execute();

    }
    @Override
    public void onClick(View view) {
        if (view == buttonInput_Kendaraan){
            addKendaraan();
        }
    }
}