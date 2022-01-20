package com.isa.retrofitjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.isa.retrofitjava.R;
import com.isa.retrofitjava.adaptor.RecyclerViewAdapter;
import com.isa.retrofitjava.model.CryptoModel;
import com.isa.retrofitjava.service.CryptoApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL = "https://api.nomics.com/v1/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Toast.makeText(MainActivity.this, "Hoşgeldin: " + intent.getStringExtra("userName"), Toast.LENGTH_LONG).show();
        //Toast mesajımızı yayınladığımız kod
        recyclerView = findViewById(R.id.recyclerView2);

        //Retrofit & JSON

        Gson gson = new GsonBuilder().setLenient().create();
        //Retrofit kullandığım kısım

        //Retrofit Nedir?:
        //Retrofit temel olarak bir kütüphane, bir network kütüphanesidir. Retrofit açık kaynak kodlu bir rest istemcisidir.
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loadData();


    }

    private void loadData() {

        final CryptoApi cryptoAPI = retrofit.create(CryptoApi.class);

        compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(cryptoAPI.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse));

        //getData interfacesini kullandığımız kısım.

    }


    private void handleResponse(List<CryptoModel> cryptoModelList) {
        cryptoModels = new ArrayList<>(cryptoModelList);

        //RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewAdapter = new RecyclerViewAdapter(cryptoModels);
        recyclerView.setAdapter(recyclerViewAdapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //sayfadan giriş çıkış yaptığımız zaman çerezleri temizlemesi için yazdığımız kod
        compositeDisposable.clear();
    }
}