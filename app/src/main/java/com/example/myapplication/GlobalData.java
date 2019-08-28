package com.example.myapplication;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by techno-110 on 23/12/16.
 */
public class GlobalData implements Serializable {

    public static GlobalData instance;
    private static Bitmap detectedBitmap = null;
    public static boolean isCapturing = false;
    public static String descriptionRECTO;
    public static String descriptionVERSO;
    public static Bitmap RECTO;
    public static Bitmap VERSO;

    public static GlobalData getInstance() {
        if (instance == null)
            instance = new GlobalData();
        return instance;
    }

    public static Bitmap getDetectedBitmap() {
        return detectedBitmap;
    }

    public static void setDetectedBitmap(Bitmap bitmap) {
        detectedBitmap = bitmap;
    }
}
