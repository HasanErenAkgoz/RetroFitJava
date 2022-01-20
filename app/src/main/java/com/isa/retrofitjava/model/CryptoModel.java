package com.isa.retrofitjava.model;

import com.google.gson.annotations.SerializedName;

public class CryptoModel {   //wep apiden gelen bilgilere göre datamodelimizi oluşturdu.

    @SerializedName("currency")
   public String currency;
    @SerializedName("price")
   public String price;


}
