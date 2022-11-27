package com.example.pizgo_solution;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentPemesanan#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPemesanan extends Fragment implements View.OnClickListener {
    private EditText editTextNama_Customer,editTextNo_HP,editTextNo_Pol_Mobil,editTextMerk_Mobil,editTextTipe_Mobil,editTextLama_Sewa,editTextID_Mobil;
    private Button buttonInput_Pemesanan;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentPemesanan() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPemesanan.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPemesanan newInstance(String param1, String param2) {
        FragmentPemesanan fragment = new FragmentPemesanan();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_pemesanan, container, false);
        editTextNama_Customer = (EditText) v.findViewById(R.id.editTextNama_Customer);
        editTextNo_HP = (EditText) v.findViewById(R.id.editTextNo_HP);
        editTextNo_Pol_Mobil = (EditText) v.findViewById(R.id.editTextNo_Pol_Mobil);
        editTextMerk_Mobil = (EditText) v.findViewById(R.id.editTextMerk_Mobil);
        editTextTipe_Mobil = (EditText) v.findViewById(R.id.editTextTipe_Mobil);
        editTextLama_Sewa = (EditText) v.findViewById(R.id.editTextLama_Sewa);


        buttonInput_Pemesanan = (Button) v.findViewById(R.id.buttonInput_Pemesanan);

        buttonInput_Pemesanan.setOnClickListener(this);
        return v;

    }
    private void addPemesanan(){
        final String nama_customer = editTextNama_Customer.getText().toString().trim();
        final String no_hp = editTextNo_HP.getText().toString().trim();
        final String no_pol_mobil = editTextNo_Pol_Mobil.getText().toString().trim();
        final String merk_mobil = editTextMerk_Mobil.getText().toString().trim();
        final String tipe_mobil = editTextTipe_Mobil.getText().toString().trim();
        final String lama_sewa = editTextLama_Sewa.getText().toString().trim();


        class AddPemesanan extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;


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
