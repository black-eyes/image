package com.example.myapplication.cameraCrop;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.Display;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Final;
import com.example.myapplication.GlobalData;
import com.example.myapplication.IConfig;
import com.example.myapplication.ImageUtil;
import com.example.myapplication.R;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.nio.ByteBuffer;

import me.pqpo.smartcropperlib.view.CropImageView;

public class Crop extends AppCompatActivity {

    CropImageView ivCrop;
    FloatingActionButton btnOk;
    public int tag;
    private int bitmapwidth;
    private int bitmapheight;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);

        Intent intent_2 = getIntent();
        tag = Integer.parseInt(intent_2.getStringExtra("face"));
        ivCrop = (CropImageView) findViewById(R.id.iv_crop);
        btnOk = (FloatingActionButton) findViewById(R.id.btn_ok);

        String myImageStr;
        if(IConfig.myPicture != null){
            myImageStr = ImageUtil.convert(IConfig.getResizedBitmap
                    (IConfig.myPicture,IConfig.myPicture.getWidth() / 4,IConfig.myPicture.getHeight() / 4));

            Bitmap bitmap = IConfig.convert(myImageStr);

            ivCrop.setImageToCrop(bitmap);

            bitmapwidth =bitmap.getWidth();
            bitmapheight =bitmap.getHeight();


            IConfig.log("bitmap Width "+bitmapwidth);
            IConfig.log(" bitmap Height "+bitmapheight);

            IConfig.log("ivCrop Width "+ivCrop.getWidth());
            IConfig.log(" ivCrop Height "+ivCrop.getHeight());
        }
        else{
            IConfig.log("null IConfig.myPicture ");
        }

        Point point[] =ivCrop.getCropPoints();

        for(int i = 0; i< point.length; i++){
            IConfig.log("ivCrop.getCropPoints() index  "+ point[i] +" x : "+point[i].x +" y : "+point[i].y);
            //  Math.sqrt((point[i+1].y - point[i].y) * (point[i+1].y - point[i].y) + (point[i+1].x - point[i].x) * point[i+1].x - point[i].x);

        }

        double d = calculDistance(point[0], point[1]);
        IConfig.log("distance between p0 et p1 "+d);
        double d1 = calculDistance(point[0], point[2]);
        IConfig.log("distance between p0 et p2 "+d1);
        double d2 = calculDistance(point[0], point[3]);
        IConfig.log("distance between p0 et p3 "+d2);
        double d3 = calculDistance(point[1], point[2]);
        IConfig.log("distance between p1 et p2 "+d3);
        double d4 = calculDistance(point[1], point[3]);
        IConfig.log("distance between p1 et p2 "+d4);
        double d5 = calculDistance(point[2], point[3]);
        IConfig.log("distance between p2 et p4 "+d5);

    /*    double d3 = calculDistance(point[0], point[1]);
        IConfig.log("distance between p0 et p1 "+d);*/

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        IConfig.log("Width screen "+width);
        IConfig.log("height screen "+height);

        double dp = Double.parseDouble("NaN");
        if((d < 200 | d1 < 200 | d2 < 200 | d3 < 200| d4 < 200| d5 < 200| d == dp| d1 == dp | d2 == dp | d3 == dp| d4 == dp| d5 == dp)  ){
            ivCrop.setAutoScanEnable(false);
            Point p0,p1,p2,p3;

            IConfig.log("<200 d "+d+" d1 "+d1+" d2 "+d2+" d3 "+d3+" d4 "+d4+" d5 "+d5);

   /*     p0 = new Point(width * 10 / 100,height * 60 / 100);
        p1 = new Point(width * 90 / 100,height * 60 / 100);
        p2 = new Point(width * 90 / 100,height * 40 / 100);
        p3 = new Point(width * 10 / 100,height * 40 / 100);*/

            p0 = new Point(bitmapwidth * 10 / 100,bitmapheight * 35 / 100);
            p1 = new Point(bitmapwidth * 65 / 100,bitmapheight * 35 / 100);
            p2 = new Point(bitmapwidth * 65 / 100,bitmapheight * 60 / 100);
            p3 = new Point(bitmapwidth * 10 / 100,bitmapheight * 60 / 100);

       /* p0 = new Point(0,0);
        p1 = new Point(width,0);
        p2 = new Point(width,height);
        p3 = new Point(0,height);*/

       /* p0 = new Point(0,0);
        p1 = new Point(bitmapwidth,0);
        p2 = new Point(bitmapwidth,bitmapheight);
        p3 = new Point(0,bitmapheight);*/

            Point[] points=null;
/*        if (Build.VERSION.SDK_INT < 23) {
            //points = new Point[]{p3, p0, p1, p2};

            points = new Point[]{p0, p1, p2, p3};

        *//*    p0 = new Point(425,216);
            p1 = new Point(639,206);
            p2 = new Point(635,569);
            p3 = new Point(414,557);*//*

        }else{
          *//*  p0 = new Point(225,543);
            p1 = new Point(610,552);
            p2 = new Point(624,787);
            p3 = new Point(223,797);*//*

             *//*   p0 = new Point(width * 15 / 100,height * 27 / 100);
            p1 = new Point(width * 45 / 100,height * 27 / 100);
            p2 = new Point(width * 45 / 100,height * 39 / 100);
            p3 = new Point(width * 15 / 100,height * 39 / 100);*//*

            points = new Point[]{p0, p1, p2, p3};
        }*/
            // Point[] points = {p0, p1, p2, p3};
            points = new Point[]{p0, p1, p2, p3};

            for (int i = 0; i < points.length; i++) {
                IConfig.log("p index " + points[i] + " x : " + points[i].x + " y : " + points[i].y);
            }
            ivCrop.setCropPoints(points);
            ivCrop.setEdgeMidPoints();

        }else{
            IConfig.log(">+++++200 d "+d+" d1 "+d1+" d2 "+d2+" d3 "+d3+" d4 "+d4+" d5 "+d5);
        }

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                if (ivCrop.canRightCrop()) {
                    IConfig.log("ivCrop.getWidth() "+ivCrop.getWidth());
                    IConfig.log("ivCrop.getHeight() "+ivCrop.getHeight());

                    Bitmap crop = ivCrop.crop();

                    IConfig.log("crop.getWidth() "+crop.getWidth());
                    IConfig.log("crop.getHeight() "+crop.getHeight());

                    if(crop.getHeight() > crop.getWidth()){

                        IConfig.log("ifififif");
                        Matrix matrix = new Matrix();

                        //int rotationDegrees = 270;
                        //my phone
                        int rotationDegrees = 90;
                        matrix.postRotate(rotationDegrees);

                        crop = Bitmap.createBitmap(crop, 0, 0, crop.getWidth(), crop.getHeight(), matrix, true);
                    }

                    if (crop != null) {
                        GlobalData.setDetectedBitmap(crop);
                        IConfig.log("MMMMMMMM"+crop);
                        String vr = getTextFromImage().replace(" ", "");
                        IConfig.code = vr;
                        restartActivityExtras(Final.class,tag+"");


                        setResult(RESULT_OK);
                        getTextFromImage();
                    } else {
                        setResult(RESULT_CANCELED);
                    }
                    finish();
                }
            }
        });
    }

    public String getTextFromImage(){

        Bitmap bitmap = GlobalData.getDetectedBitmap();
        StringBuilder ab = new StringBuilder();
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        if (!textRecognizer.isOperational()){
            Toast.makeText(getApplicationContext(), "test text ....", Toast.LENGTH_SHORT).show();
        } else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> items = textRecognizer.detect(frame);
            textRecognizer.detect(frame);


            for (int i = 0 ; i < items.size() ; i++){

                TextBlock textBlock = items.get(i);
                ab.append(textBlock.getValue());
                //ab.append("\n");


            }

            IConfig.log(ab+"tttttttttttt");
            IConfig.log( String.valueOf(ab).replace(" ","")+"tttttttttttt");
        }

        return String.valueOf(ab);
    }

    public void restartActivityExtras(Class<?> cls, String tag) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("face", tag+"");
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        restartActivityExtras(MyScan.class,tag+"");
    }

    public static byte[] convertBitmapToByteArrayUncompressed(Bitmap bitmap){
        ByteBuffer byteBuffer = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(byteBuffer);
        byteBuffer.rewind();
        return byteBuffer.array();
    }

    public double calculDistance(Point p1, Point p2){

        double d = Math.sqrt((p2.y - p1.y) * (p2.y - p1.y) + (p2.x - p1.x) * p2.x - p1.x);

        return d;
    }

    /** get screen size in "pixels", i.e. touchevent/view units.
     * on my droid 4, this is 360x640 or 540x960
     * depending on whether the app is in screen compatibility mode
     * (i.e. targetSdkVersion<=10 in the manifest) or not. */
    public void getScreenSizePixels(int widthHeightInPixels[])
    {
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        // Note, screenHeightDp isn't reliable
        // (it seems to be too small by the height of the status bar),
        // but we assume screenWidthDp is reliable.
        // Note also, dm.widthPixels,dm.heightPixels aren't reliably pixels
        // (they get confused when in screen compatibility mode, it seems),
        // but we assume their ratio is correct.
        double screenWidthInPixels = (double)config.screenWidthDp * dm.density;
        double screenHeightInPixels = screenWidthInPixels * dm.heightPixels / dm.widthPixels;
        widthHeightInPixels[0] = (int)(screenWidthInPixels + .5);
        widthHeightInPixels[1] = (int)(screenHeightInPixels + .5);
    }
}