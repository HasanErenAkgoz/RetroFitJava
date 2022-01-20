package com.isa.retrofitjava.service;

import com.isa.retrofitjava.model.CryptoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CryptoApi  {
    @GET("prices?key=2187154b76945f2373394aa34f7dc98a") //get isteği yaptık
    Observable<List<CryptoModel>> getData(); // web apiden gelen veriyi almak için observable türünde bir Liste oluşturduk.

}
