package com.isa.retrofitjava.adaptor;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.isa.retrofitjava.R;
import com.isa.retrofitjava.model.CryptoModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {


    private ArrayList<CryptoModel> cryptoList; //wep apiden gelen veriyi ArrayList formatına çevirdik

    private String[] colors = {"#0D3C36","#98B148","#4859B1","#7E48B1","#B1488F","#B15948","#070951","#CC15B3"}; //recyclerview renklerimizi aldık

    public RecyclerViewAdapter(ArrayList<CryptoModel> cryptoList) {
        this.cryptoList = cryptoList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_leyaout,parent,false);
        return new RowHolder(view); //recyclerview kısmında göstermek için yazdığımız kodlar
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        holder.bind(cryptoList.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return cryptoList.size();
    } //Ekranda kaç tane nesne gözükücekse onların sayısını aldık

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textName;
        TextView textPrice;

        public RowHolder(@NonNull View itemView) {
            super(itemView);

        }

        public void bind(CryptoModel cryptoModel,String[] colors, Integer position) {
            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]));
            textName = itemView.findViewById(R.id.textName);
            textPrice = itemView.findViewById(R.id.textPrice);
            textName.setText(cryptoModel.currency);
            textPrice.setText(cryptoModel.price);
            //ekranda gözükücek nesnelerimizin recyclerview bağlantısını yaptık.
        }


    }
}
