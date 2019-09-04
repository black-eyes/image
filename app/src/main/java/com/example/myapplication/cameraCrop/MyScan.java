package com.example.myapplication.cameraCrop;


import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.GlobalData;
import com.example.myapplication.IConfig;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.google.android.cameraview.AspectRatio;
import com.google.android.cameraview.CameraView;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * Created by YASSIR on 27/11/2018.
 */


public class MyScan extends AppCompatActivity implements
        ActivityCompat.OnRequestPermissionsResultCallback,
        AspectRatioFragment.Listener {

    public int tag;

    private static final String TAG = "MyScan";

    private static final int REQUEST_CAMERA_PERMISSION = 1;

    private static final String FRAGMENT_DIALOG = "dialog";

    private CameraView mCameraView;

    private Handler mBackgroundHandler;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.take_picture_2:
                    if (mCameraView != null) {

                            Switch sw;
                            sw = (Switch)findViewById(R.id.switch3);
                            if(sw.isChecked())
                            {
                                mCameraView.setFlash(CameraView.FLASH_TORCH);
                            }else
                                {
                                    mCameraView.setFlash(CameraView.FLASH_OFF);
                                }


                        mCameraView.takePicture();
                    }


            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scan);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorPrimaryDark));
        }

        mCameraView = (CameraView) findViewById(R.id.camera);
        if (mCameraView != null) {
            mCameraView.addCallback(mCallback);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.take_picture_2);
        if (fab != null) {
            fab.setOnClickListener(mOnClickListener);
        }


        Intent intent_2 = getIntent();
        tag = Integer.parseInt(intent_2.getStringExtra("face"));




    }


    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            mCameraView.start();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            ConfirmationDialogFragment
                    .newInstance(R.string.camera_permission_confirmation,
                            new String[]{Manifest.permission.CAMERA},
                            REQUEST_CAMERA_PERMISSION,
                            R.string.camera_permission_not_granted)
                    .show(getSupportFragmentManager(), FRAGMENT_DIALOG);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA_PERMISSION);
        }
    }

    @Override
    protected void onPause() {
        mCameraView.stop();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBackgroundHandler != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                mBackgroundHandler.getLooper().quitSafely();
            } else {
                mBackgroundHandler.getLooper().quit();
            }
            mBackgroundHandler = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION:
                if (permissions.length != 1 || grantResults.length != 1) {
                    throw new RuntimeException("Error on requesting camera permission.");
                }
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, R.string.camera_permission_not_granted,
                            Toast.LENGTH_SHORT).show();
                }
                // No need to start camera here; it is handled by onResume
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public void onAspectRatioSelected(@NonNull AspectRatio ratio) {
        if (mCameraView != null) {
            Toast.makeText(this, ratio.toString(), Toast.LENGTH_SHORT).show();
            mCameraView.setAspectRatio(ratio);
        }
    }

    private Handler getBackgroundHandler() {
        if (mBackgroundHandler == null) {
            HandlerThread thread = new HandlerThread("background");
            thread.start();
            mBackgroundHandler = new Handler(thread.getLooper());
        }
        return mBackgroundHandler;
    }

    private CameraView.Callback mCallback
            = new CameraView.Callback() {

        @Override
        public void onCameraOpened(CameraView cameraView) {
            Log.d(TAG, "onCameraOpened");
        }

        @Override
        public void onCameraClosed(CameraView cameraView) {
            Log.d(TAG, "onCameraClosed");
        }

        @Override
        public void onPictureTaken(CameraView cameraView, final byte[] data) {
            Log.d(TAG, "La photo est prise avec succès" + data.length);
            Toast.makeText(cameraView.getContext(), R.string.picture_taken, Toast.LENGTH_SHORT)
                    .show();
            getBackgroundHandler().post(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void run() {
                    File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                            "picture.jpg");
                    OutputStream os = null;
                    try {
                        os = new FileOutputStream(file);
                        os.write(data);
                        os.close();

                        Bitmap img = BitmapFactory.decodeFile(file.getPath());
                        Log.e("img",img+"");

                        int rotationDegrees = 0;
                        if (Build.VERSION.SDK_INT >= 23) {
                            ExifInterface exifInterface = null;
                            try {
                                exifInterface = new ExifInterface(new ByteArrayInputStream(data));
                                int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);

                                switch (orientation) {
                                    case ExifInterface.ORIENTATION_ROTATE_90:
                                        rotationDegrees = 90;
                                        break;
                                    case ExifInterface.ORIENTATION_ROTATE_180:
                                        rotationDegrees = 180;
                                        break;
                                    case ExifInterface.ORIENTATION_ROTATE_270:
                                        rotationDegrees = 270;
                                        break;
                                }
                                IConfig.log("imageimage"+rotationDegrees);
                            } catch (IOException e) {
                                e.printStackTrace();
                                rotationDegrees = 270;
                            }
                        } else {
                            rotationDegrees = 90;
                        }

                        Matrix matrix = new Matrix();

                        matrix.postRotate(rotationDegrees);

                        img = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);

                        IConfig.myPicture = img;

                        file.delete();


                        restartActivityExtras(Crop.class,tag+"");


                    } catch (IOException e) {
                        Log.w(TAG, "Cannot write to " + file, e);
                    } finally {
                        if (os != null) {
                            try {
                                os.close();
                            } catch (IOException e) {
                                // Ignore
                            }
                        }
                    }
                }
            });
        }

    };

    public void restartActivityExtras(Class<?> cls, String tag) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("face", tag+"");
        startActivity(intent);
    }

    public static class ConfirmationDialogFragment extends DialogFragment {

        private static final String ARG_MESSAGE = "message";
        private static final String ARG_PERMISSIONS = "permissions";
        private static final String ARG_REQUEST_CODE = "request_code";
        private static final String ARG_NOT_GRANTED_MESSAGE = "not_granted_message";

        public static ConfirmationDialogFragment newInstance(@StringRes int message,
                                                             String[] permissions, int requestCode, @StringRes int notGrantedMessage) {
            ConfirmationDialogFragment fragment = new ConfirmationDialogFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_MESSAGE, message);
            args.putStringArray(ARG_PERMISSIONS, permissions);
            args.putInt(ARG_REQUEST_CODE, requestCode);
            args.putInt(ARG_NOT_GRANTED_MESSAGE, notGrantedMessage);
            fragment.setArguments(args);
            return fragment;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Bundle args = getArguments();
            return new AlertDialog.Builder(getActivity())
                    .setMessage(args.getInt(ARG_MESSAGE))
                    .setPositiveButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String[] permissions = args.getStringArray(ARG_PERMISSIONS);
                                    if (permissions == null) {
                                        throw new IllegalArgumentException();
                                    }
                                    ActivityCompat.requestPermissions(getActivity(),
                                            permissions, args.getInt(ARG_REQUEST_CODE));
                                }
                            })
                    .setNegativeButton(android.R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getActivity(),
                                            args.getInt(ARG_NOT_GRANTED_MESSAGE),
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                    .create();
        }

    }
//
    public void getTextFromImage(){

        Bitmap bitmap = GlobalData.getDetectedBitmap();

        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

        if (!textRecognizer.isOperational()){
            Toast.makeText(getApplicationContext(), "test text ....", Toast.LENGTH_SHORT).show();
        } else {
            if (bitmap != null){
               Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                SparseArray<TextBlock> items = textRecognizer.detect(frame);
                StringBuilder ab = new StringBuilder();

                for (int i = 0 ; i < items.size() ; i++){

                    TextBlock textBlock = items.valueAt(i);
                    ab.append(textBlock.getValue());
                    ab.append("\n");


                }
            }




           // IConfig.log(ab+"tttttttttttt");
        }

    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(MyScan.this, MainActivity.class);
        i.putExtra("file","a");
        startActivity(i);
        finish();
        IConfig.log("fonction khadmat");
        getTextFromImage();
        //Intent intent = new Intent(MyScan.this, MainActivityFront.class);
        //startActivity(intent);
        //finish();
    }

}