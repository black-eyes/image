package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


//progress bar
        final ProgressBar prg = findViewById(R.id.progressBar);

        new Thread(new Runnable() {
            public void run() {
                while (prg.getProgress() < 100) {
                    prg.setProgress(prg.getProgress()+10);
                    // Update the progress bar and display the
                    //current value in the text view

                    try {
                        // Sleep for 200 milliseconds.
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i=new Intent(Splash.this,MainActivity.class);
                startActivity(i);
            }
        }, 2000);
    }
}
