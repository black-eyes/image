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
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.cameraCrop.MyScan;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

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
    private List<String> spinnerArray;
    private String ser = "";
    private Spinner sItems;
    private String[] items2;
    private String str = "";
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        ImageView img_ope = findViewById(R.id.imageView4);
        final EditText number = findViewById(R.id.editText);

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });

        spinnerArray =  new ArrayList<String>();

        if (IConfig.oper.equals("IAM")){

            //spinnerArray =  new ArrayList<String>();
            items2 = new String[]{" Choisir le type de Recharge "," Normale"," SMS *1"," Tout Compris *2"," Internet *3"," MT Talk *6"," Premimum *9"};
            /*spinnerArray.add(" Choisir le type de Recharge ");
            spinnerArray.add(" Normale");
            spinnerArray.add(" SMS *1");
            spinnerArray.add(" Tout Compris *2");
            spinnerArray.add(" Internet *3");
            spinnerArray.add(" MT Talk *6");
            spinnerArray.add(" Premimum *9");*/

        } else if (IConfig.oper.equals("ORANGE")){

            //spinnerArray =  new ArrayList<String>();
            items2 = new String[]{" Choisir le type de Recharge "," Normale"," Appels et SMS *1","Communauté Orange *2"," Internet *3","vers le national et l'international *4"," Reseaux sociaux *6"};
            /*spinnerArray.add(" Choisir le type de Recharge ");
            spinnerArray.add(" Normale");
            spinnerArray.add(" SMS *1");
            spinnerArray.add(" Tout Compris *2");
            spinnerArray.add(" Internet *3");
            spinnerArray.add(" MT Talk *6");
            spinnerArray.add(" Premimum *9");*/

        } else if (IConfig.oper.equals("INWI")){


            //spinnerArray =  new ArrayList<String>();
            items2 = new String[]{" Choisir le type de Recharge "," Normale"," Réseaux sociaux 7j *6"," Voix+SMS illimites 3j *8"," 1h national 3j *1"
            ," 1h ou 1Go 7j *2"," 1 Go valable 3j *3"};
            /*spinnerArray.add(" Choisir le type de Recharge ");
            spinnerArray.add(" Normale");
            spinnerArray.add(" Réseaux sociaux 7j *6");
            spinnerArray.add(" Voix+SMS illimites 3j *8");
            spinnerArray.add(" 1h national 3j *1");
            spinnerArray.add(" 1h ou 1Go 7j *2");
            spinnerArray.add(" 1 Go valable 3j *3");*/


        }



        sItems = (Spinner) findViewById(R.id.spinner);
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_test, spinnerArray);
        sItems.setAdapter(adapter);*/

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sItems.setAdapter(adapter2);

        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                IConfig.service = sItems.getSelectedItem().toString();
                IConfig.log(IConfig.service+" yyyyy");
                showInterstitalAd();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button btnValider = findViewById(R.id.button4Valider);
        Button button5anuller = findViewById(R.id.button5anuller);

        image = GlobalData.getDetectedBitmap();

        if (image != null){
            img_ope.setImageBitmap(image);
        } else {
            IConfig.log("test not valide");
        }

        if (IConfig.code != null){
            str = IConfig.code.replaceAll("\\D+","");
            number.setText(str);
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

                if (IConfig.service.contains("*2")){
                    ser = "*2";
                    IConfig.log(ser+"llll *2");
                }
                if (IConfig.service.contains("*3")){
                    ser = "*3";
                    IConfig.log(ser+"llll *3");
                }
                if (IConfig.service.contains("*1")){
                    ser = "*1";
                    IConfig.log(ser+"llll *1");
                }
                if (IConfig.service.contains("*6")){
                    ser = "*6";
                    IConfig.log(ser+"llll *6");
                }
                if (IConfig.service.contains("*8")){
                    ser = "*8";
                    IConfig.log(ser+"llll *8");
                }
                if (IConfig.service.contains("*9")){
                    ser = "*9";
                    IConfig.log(ser+"llll *9");
                }

                if (IConfig.service.contains("*4")){
                    ser = "*4";
                    IConfig.log(ser+"llll *4");
                }





                if(IConfig.oper == "ORANGE")
                {
                    /*
                    Intent intent = new Intent(Intent.ACTION_CALL);// or ACTION_DIAL
                    intent.setData(Uri.parse("tel:0707040613*2"));
                    startActivity(intent);
                    */


                    String phone = "555";

                    String finalnumber = phone+number.getText().toString()+ser;
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", finalnumber, null));
                    startActivity(intent);


                }else
                    {
                     /*Intent intent = new Intent(Intent.ACTION_CALL);// or ACTION_DIAL
                        intent.setData(Uri.parse("tel:0707040613*2"));
                        startActivity(intent);*/

                        //final String result = stripNonDigits(input);

                        Intent send = new Intent(Intent.ACTION_VIEW);
                        send.putExtra("address","555");
                        send.putExtra("sms_body", number.getText().toString()+ser);
                        send.setType("vnd.android-dir/mms-sms");
                        startActivity(send);

                    }




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

    public void showInterstitalAd(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }
}
