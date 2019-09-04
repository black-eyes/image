package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.IconCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.cameraCrop.MyScan;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img1 = findViewById(R.id.imageView);
        ImageView img2 = findViewById(R.id.imageView2);
        ImageView img3= findViewById(R.id.imageView3);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,MyScan.class);
                i.putExtra("face", IConfig.RECTO+"");
                startActivity(i);
                Log.e("Tag","img1");
                IConfig.oper = "IAM";
            }
        });


        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,MyScan.class);
                i.putExtra("face", IConfig.RECTO+"");
                startActivity(i);
                Log.e("Tag","img2");
                IConfig.oper = "ORANGE";

            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, MyScan.class);
                i.putExtra("face", IConfig.RECTO+"");
                startActivity(i);
                finish();
                Log.e("Tag","img3");
                IConfig.oper = "INWI";

            }
        });
    }
}
