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

public class Lihat_Kendaraan extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kendaraan);
        listView = (ListView) findViewById(R.id.listViewLihat_Kendaraan);
        listView.setOnItemClickListener(this);
        getJSON();
    }
    private void showMobil(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Configuration.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String no_pol = jo.getString(Configuration.TAG_NO_POL);
                String merk = jo.getString(Configuration.TAG_MERK);
                String tipe = jo.getString(Configuration.TAG_TIPE);

                HashMap<String,String> mbl = new HashMap<>();
                mbl.put(Configuration.TAG_NO_POL,no_pol);
                mbl.put(Configuration.TAG_MERK,merk);
                mbl.put(Configuration.TAG_TIPE,tipe);
                list.add(mbl);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                Lihat_Kendaraan.this, list, R.layout.list_item_lihat_kendaraan,
                new String[]{Configuration.TAG_NO_POL,Configuration.TAG_MERK, Configuration.TAG_TIPE},
                new int[]{R.id.no_pol, R.id.merk, R.id.tipe});
        listView.setAdapter(adapter);
    }
    private void getJSON(){

        class GetJSON extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Lihat_Kendaraan.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showMobil();
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




    }
}