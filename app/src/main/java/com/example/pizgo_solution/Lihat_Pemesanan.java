package com.example.pizgo_solution;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Lihat_Pemesanan extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_pemesanan);
        listView = (ListView) findViewById(R.id.listViewLihat_Pemesanan);
        listView.setOnItemClickListener(this);
        getJSON();
    }
    private void showPemesanan(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Configuration.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String nama_customer = jo.getString(Configuration.TAG_NAMA_CUSTOMER);
                String no_hp = jo.getString(Configuration.TAG_NO_HP);
                String no_pol_mobil = jo.getString(Configuration.TAG_NO_POL_MOBIL);
                String merk_mobil = jo.getString(Configuration.TAG_MERK_MOBIL);
                String tipe_mobil = jo.getString(Configuration.TAG_TIPE_MOBIL);
                String lama_sewa = jo.getString(Configuration.TAG_LAMA_SEWA);

                HashMap<String,String> pesan = new HashMap<>();
                pesan.put(Configuration.TAG_NAMA_CUSTOMER,nama_customer);
                pesan.put(Configuration.TAG_NO_HP,no_hp);
                pesan.put(Configuration.TAG_NO_POL_MOBIL,no_pol_mobil);
                pesan.put(Configuration.TAG_MERK_MOBIL,merk_mobil);
                pesan.put(Configuration.TAG_TIPE_MOBIL,tipe_mobil);
                pesan.put(Configuration.TAG_LAMA_SEWA,lama_sewa);
                list.add(pesan);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                Lihat_Pemesanan.this, list, R.layout.list_item_lihat_pemesanan,
                new String[]{Configuration.TAG_NAMA_CUSTOMER,Configuration.TAG_NO_HP,Configuration.TAG_NO_POL_MOBIL,Configuration.TAG_MERK_MOBIL, Configuration.TAG_TIPE_MOBIL,Configuration.TAG_LAMA_SEWA},
                new int[]{R.id.nama_customer,R.id.no_hp,R.id.no_pol_mobil, R.id.merk_mobil, R.id.tipe_mobil, R.id.lama_sewa});
        listView.setAdapter(adapter);
    }
    private void getJSON(){

        class GetJSON extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Lihat_Pemesanan.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showPemesanan();
            }
            @Override protected String doInBackground(Void... params) { RequestHandler rh = new RequestHandler();
                Log.d("testing","test");
                String s = rh.sendGetRequest(Configuration.URL_GET_ALL);
                Log.d("response",s);

                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?>parent, View view, int position, long id) {
        Intent intent = new Intent(this, EditTampilPemesanan.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String no_pol_mobil = map.get(Configuration.TAG_NO_POL_MOBIL).toString();
        String nama_customer = map.get(Configuration.TAG_NAMA_CUSTOMER).toString();
        String no_hp = map.get(Configuration.TAG_NO_HP).toString();
        String merk_mobil = map.get(Configuration.TAG_MERK_MOBIL).toString();
        String tipe_mobil = map.get(Configuration.TAG_TIPE_MOBIL).toString();
        String lama_sewa = map.get(Configuration.TAG_LAMA_SEWA).toString();

        intent.putExtra(Configuration.NO_POL_MOBIL,no_pol_mobil);
        intent.putExtra(Configuration.NAMA_CUSTOMER,nama_customer);
        intent.putExtra(Configuration.NO_HP,no_hp);
        intent.putExtra(Configuration.MERK_MOBIL,merk_mobil);
        intent.putExtra(Configuration.TIPE_MOBIL,tipe_mobil);
        intent.putExtra(Configuration.LAMA_SEWA,lama_sewa);
        startActivity(intent);


    }
}