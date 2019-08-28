package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.IconCompat;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.cameraCrop.MyScan;

import java.util.ArrayList;
import java.util.List;

public class Final extends AppCompatActivity {

    /*void sendSMS(String phoneNumber,String message)
    {
        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
                new Intent(SENT), 0);

        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
                new Intent(DELIVERED), 0);

        //---when the SMS has been sent---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS sent",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(getBaseContext(), "Generic failure",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(getBaseContext(), "No service",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(getBaseContext(), "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(getBaseContext(), "Radio off",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        registerReceiver(new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(getBaseContext(), "SMS delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(getBaseContext(), "SMS not delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, sentPI, deliveredPI);
    }*/

    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        ImageView img_ope = findViewById(R.id.imageView4);
        EditText number = findViewById(R.id.editText);



        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("choisir le type de Recharge ");
        spinnerArray.add("item1");
        spinnerArray.add("item2");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);



        Spinner sItems = (Spinner) findViewById(R.id.spinner);
        sItems.setAdapter(adapter);


        Button btnValider = findViewById(R.id.button4Valider);
        Button button5anuller = findViewById(R.id.button5anuller);

        image = GlobalData.getDetectedBitmap();

        if (image != null){
            img_ope.setImageBitmap(image);
        } else {
            IConfig.log("test not valide");
        }

        if (IConfig.code != null){
            number.setText(IConfig.code);
        } else {
        }

        btnValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
i try to send an sms from the app
 */
               /* Log.e("Tag","btn Valider");
                SmsManager smgr = SmsManager.getDefault();
                smgr.sendTextMessage("555",null,"hello this is a message",null,null);

                Log.e("sms","after send sms");*/
              // sendSMS("0644527525","hello this is a test message");

                Intent send = new Intent(Intent.ACTION_VIEW);
                send.putExtra("address","555");
                send.putExtra("sms_body", IConfig.code+"*2");
                send.setType("vnd.android-dir/mms-sms");
                startActivity(send);




            }
        });






        button5anuller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("Tag","btn anuller");
                Intent i=new Intent(Final.this, MyScan.class);
                i.putExtra("face", IConfig.RECTO+"");
                startActivity(i);
                finish();

            }
        });
    }
}
