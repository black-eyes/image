package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.cameraCrop.MyScan;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Test.this, MyScan.class);
                i.putExtra("face", IConfig.RECTO+"");
                startActivity(i);
                finish();
            }
        });
    }

    public void getTextFromImage(){

        Bitmap bitmap = GlobalData.getDetectedBitmap();

        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        if (!textRecognizer.isOperational()){
            Toast.makeText(getApplicationContext(), "test text ....", Toast.LENGTH_SHORT).show();
        } else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> items = textRecognizer.detect(frame);
            StringBuilder ab = new StringBuilder();

            for (int i = 0 ; i < items.size() ; i++){

                TextBlock textBlock = items.valueAt(i);
                ab.append(textBlock.getValue());
                ab.append("\n");


            }
        }

    }
}
