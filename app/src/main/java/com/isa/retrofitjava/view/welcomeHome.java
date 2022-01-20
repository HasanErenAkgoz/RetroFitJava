package com.isa.retrofitjava.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.isa.retrofitjava.databinding.ActivityWelcomeHomeBinding;

public class welcomeHome extends AppCompatActivity {
        private ActivityWelcomeHomeBinding binding;
        public String UserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeHomeBinding.inflate(getLayoutInflater()); //verilere daha kolay erişmek için binding kullandığımız kısım
        View view = binding.getRoot();
        setContentView(view);
    }
    public void Login(View view){

        if (!binding.userName.getText().toString().matches(""))  //sayfa değiştirirken kullandığımız kod blogu
        {        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Sayfa Değiştir");
        alert.setMessage("Sayfayı Değiştirmek İstediğinizinden emin misiniz?");
        alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(welcomeHome.this,"Giriş Başarılı...",Toast.LENGTH_SHORT).show();
                ChangeActivity();
            }
        });
        alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(welcomeHome.this, "Seçiminiz Kabul Edildi", Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
        }
        else
        {
            Toast.makeText(welcomeHome.this, "Lütfen Önce İsminizi Giriniz", Toast.LENGTH_SHORT).show();

        }
    }
    public  void ChangeActivity(){
        if (!binding.userName.getText().toString().matches(""))
        {
            UserName=binding.userName.getText().toString();
            Intent ıntent=new Intent(welcomeHome.this,MainActivity.class);
            ıntent.putExtra("userName",UserName); // ikinci sayfaya userName bilgisini aktardım.
            startActivity(ıntent);
        }
        else{

            Toast.makeText(welcomeHome.this, "Lütfen Önce İsminizi Giriniz", Toast.LENGTH_SHORT).show();
        }
            

    }
}