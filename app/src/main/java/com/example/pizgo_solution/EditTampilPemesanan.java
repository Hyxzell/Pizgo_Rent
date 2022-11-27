package com.example.pizgo_solution;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class EditTampilPemesanan extends AppCompatActivity implements View.OnClickListener {


     private EditText editTextNama_Customer, editTextNo_HP, editTextNo_Pol_Mobil, editTextMerk_Mobil,editTextTipe_Mobil, editTextLama_Sewa;
    private Button buttonUpdate_Pemesanan, buttonHapus_Pemesanan;
    private String no_pol_mobil;
    private String nama_customer;
    private String no_hp;
    private String merk_mobil;
    private String tipe_mobil;
    private String lama_sewa;
    /*private String nopolmobil,nmcustomer,nohp,tipembl,lmsewa;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_tampil_pemesanan);
        Intent intent = getIntent();
        no_pol_mobil = intent.getStringExtra(Configuration.NO_POL_MOBIL);
        nama_customer= intent.getStringExtra(Configuration.NAMA_CUSTOMER);
        no_hp = intent.getStringExtra(Configuration.NO_HP);
        merk_mobil = intent.getStringExtra(Configuration.MERK_MOBIL);
        tipe_mobil = intent.getStringExtra(Configuration.TIPE_MOBIL);
        lama_sewa = intent.getStringExtra(Configuration.LAMA_SEWA);


        /*nama_customer = intent.getStringExtra(Configuration.NAMA_CUSTOMER);
        no_hp = intent.getStringExtra(Configuration.NO_HP);
        merk_mobil = intent.getStringExtra(Configuration.MERK_MOBIL);
        tipe_mobil = intent.getStringExtra(Configuration.TIPE_MOBIL);
        lama_sewa = intent.getStringExtra(Configuration.LAMA_SEWA);*/

        editTextNama_Customer = (EditText) findViewById(R.id.editTextNama_Customer);
        editTextNo_HP = (EditText) findViewById(R.id.editTextNo_HP);
        editTextNo_Pol_Mobil = (EditText) findViewById(R.id.editTextNo_Pol_Mobil);
        editTextMerk_Mobil = (EditText) findViewById(R.id.editTextMerk_Mobil);
        editTextTipe_Mobil = (EditText) findViewById(R.id.editTextTipe_Mobil);
        editTextLama_Sewa = (EditText) findViewById(R.id.editTextLama_Sewa);

        buttonUpdate_Pemesanan =(Button) findViewById(R.id.buttonUpdate_Pemesanan);
        buttonHapus_Pemesanan = (Button) findViewById(R.id.buttonHapus_Pemesanan);

        buttonUpdate_Pemesanan.setOnClickListener(this);
        buttonHapus_Pemesanan.setOnClickListener(this);
//        /*editTextNo_Pol_Mobil.setText(no_pol_mobil);*/

        //biang kerok
        editTextNo_Pol_Mobil.setText(no_pol_mobil);
        editTextNama_Customer.setText(nama_customer);
        editTextNo_HP.setText(no_hp);
        editTextLama_Sewa.setText(lama_sewa);
        editTextTipe_Mobil.setText(tipe_mobil);
        editTextMerk_Mobil.setText(merk_mobil);
        getPemesanan();

    }

    private void getPemesanan() {
        class GetPemesanan extends AsyncTask<Void,Void,String> { ProgressDialog loading;
            @Override
            protected void onPreExecute() { super.onPreExecute();
                loading = ProgressDialog.show(EditTampilPemesanan.this,"Fetching...","Wait...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showPemesanan(s);
            }

            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Configuration.URL_GET,no_pol_mobil);
                return s;
            }
        }
        GetPemesanan ge = new GetPemesanan();
        ge.execute();
    }
//json
            private void showPemesanan(String json) {
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    JSONArray result = jsonObject.getJSONArray(Configuration.TAG_JSON_ARRAY);
                    JSONObject c = result.getJSONObject(0);
                    String no_pol_mobil = c.getString(Configuration.TAG_NO_POL_MOBIL);
                    String merk_mobil = c.getString(Configuration.TAG_MERK_MOBIL);
                    String tipe_mobil  = c.getString(Configuration.TAG_TIPE_MOBIL);
                    String nama_customer = c.getString(Configuration.TAG_NAMA_CUSTOMER);
                    String lama_sewa = c.getString(Configuration.TAG_LAMA_SEWA);
                    String no_hp  = c.getString(Configuration.TAG_NO_HP);


                    //edittext
                    /*editTextNama_Customer.setText(nama_customer);
                    editTextNo_HP.setText(no_hp);
                    editTextNo_Pol_Mobil.setText(no_pol_mobil);
                    editTextMerk_Mobil.setText(merk_mobil);
                    editTextTipe_Mobil.setText(tipe_mobil);
                    editTextLama_Sewa.setText(lama_sewa);
*/
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
    private void updatePemesanan(){
        final String no_pol_mobil = editTextNo_Pol_Mobil.getText().toString().trim();
        final String merk_mobil = editTextMerk_Mobil.getText().toString().trim();
        final String tipe_mobil = editTextTipe_Mobil.getText().toString().trim();
        final String nama_customer = editTextNama_Customer.getText().toString().trim();
        final String lama_sewa = editTextLama_Sewa.getText().toString().trim();
        final String no_hp = editTextNo_HP.getText().toString().trim();

        class UpdatePemesanan extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditTampilPemesanan.this,"Updating...","Wait...",false,
                        false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(EditTampilPemesanan.this,s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... params) { HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Configuration.KEY_NO_POL_MOBIL,no_pol_mobil);
                hashMap.put(Configuration.KEY_MERK_MOBIL,merk_mobil);
                hashMap.put(Configuration.KEY_NAMA_CUSTOMER,nama_customer);
                hashMap.put(Configuration.KEY_LAMA_SEWA,lama_sewa);
                hashMap.put(Configuration.KEY_NO_HP,no_hp);
                hashMap.put(Configuration.KEY_TIPE_MOBIL,tipe_mobil);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Configuration.URL_UPDATE,hashMap);
                return s;
            }
        }
        UpdatePemesanan ue = new UpdatePemesanan();
        ue.execute();
    }
    private void deletePemesanan(){
        class DeletePemesanan extends AsyncTask<Void,Void,String>
        { ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditTampilPemesanan.this, "Updating...", "Tunggu...", false, false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(EditTampilPemesanan.this, s, Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... params) { RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Configuration.URL_DELETE, no_pol_mobil);
                return s;
            }
        }
        DeletePemesanan de = new DeletePemesanan();
        de.execute();
    }
    private void confirmDeletePemesanan(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Data ini?");
        alertDialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deletePemesanan();
                        startActivity(new Intent(EditTampilPemesanan.this,EditTampilPemesanan.class));
                    }
                }
        );
        alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) { } });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    @Override
    public void onClick(View v) {
        if(v == buttonUpdate_Pemesanan){ updatePemesanan();
        }
        if(v == buttonHapus_Pemesanan) {
            confirmDeletePemesanan();

        }
    }
}

