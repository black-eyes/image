package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/*import de.hdodenhof.circleimageview.CircleImageView;
import ma.app.filahi.alf.myapplication.soap.PaiementWS.VectorCreancierVals;
import ma.app.filahi.alf.myapplication.soap.authenticateWS.VectorByte;
import ma.app.filahi.alf.myapplication.view.dashboard.catalogueProduit.ClassItemCatalogueProduit;
import ma.app.filahi.alf.myapplication.view.dashboard.paiements.paiementfactures.Factures;
import ma.app.filahi.alf.myapplication.view.dashboard.paiements.paiementfactures.ItemSelectionner;
import ma.app.filahi.alf.myapplication.view.dashboard.paiements.recharges_prepayes.ParametreList;*/

/**
 * Created by aminealf on 16/01/2018.
 */

public class IConfig {

    public static Context mainContext = null ;

    public static String MAC_KEY = "9F18B1DCF4CC874B331CE1E679C2739D";
    public static String NAMESPACE = "http://tempuri.org/";

    public static final String API_KEY_GOOGLE_VISION = "AIzaSyDoYpocKfOjVQzrgBxGYOXzqa8OvkosrTM";

    public static final String LICENSE_KEY = "2ZKENR7S-TNUGMMD6-I56PE574-G5U53VOU-3YXTIRUJ-XIPPZRGF-V27HNEA3-MR6PX3CD";

    public static final String DB_NAME = "afilahibank00026501788.db";

    public static final String MAIN_FOLDER = "cam";
    public static final String TEST = "test";

    public static final String AVATAR_FILE = "pic.jpg";
    public static final String CIN_RECTO_FILE = "cin_recto.jpg";
    public static final String CIN_VERSO_FILE = "cin_verso.jpg";


    public static final int RECTO = 0;
    public static final int VERSO = 1;

    public static int pocom = 0;

    public static final String CARTES = "file:///android_asset/app/cartes.html";
    public static final String COMPTES = "file:///android_asset/app/comptes.html";
    public static final String CONTACT = "file:///android_asset/app/contact.html";
    public static final String CREDITS = "file:///android_asset/app/credits.html";
    public static final String DASHBOARD = "file:///android_asset/app/dashboard.html";
    public static final String DEMANDE_PAY_RECUS = "file:///android_asset/app/demande-pay-recus.html";
    public static final String SUIVI_DEMANDE_PAY = "file:///android_asset/app/suivi-demande-pay.html";
    public static final String DEMANDE_PAY = "file:///android_asset/app/demande-pay.html";
    public static final String DETAIL_CARTE = "file:///android_asset/app/detail-carte.html";
    public static final String DETAIL_COMPTE = "file:///android_asset/app/detail-compte.html";
    public static final String DETAIL_CREDIT = "file:///android_asset/app/detail-credit.html";
    public static final String FILAHI_PACKS = "file:///android_asset/app/filahi_packs.html";
    public static final String HOME = "file:///android_asset/app/home.html";
    public static final String LOGIN = "file:///android_asset/app/login.html";
    public static final String PRESENTATION = "file:///android_asset/app/presentation.html";
    public static final String REGLEMENT_PAY = "file:///android_asset/app/reglement-pay.html";
    public static final String SIGNIN = "file:///android_asset/app/signin.html";
    public static final String CHOIX_OFFRE = "file:///android_asset/app/choix-offre.html";
    public static final String CHOIX_CARTE = "file:///android_asset/app/choix-carte.html";
    public static final String PAY_FLASH = "file:///android_asset/app/pay-flash.html";

    public static final String FINGERPRINT = "file:///android_asset/app/fingerprint.html";
    public static final String FINGERPRINT_2 = "file:///android_asset/app/fingerprint2.html";
    public static final String CAMERA_PHONE = "file:///android_asset/app/camera.html";
    public static final String FACE_CIN = "file:///android_asset/app/recto.html";
    public static final String FACE_CIN2 = "file:///android_asset/app/verso.html";
    public static final String FILAHI_DJEUNZ = "file:///android_asset/app/filahidjeunz.html";
    public static final String FILAHI_PART = "file:///android_asset/app/filahipart.html";
    public static final String FILAHI_PRO = "file:///android_asset/app/filahipro.html";
    public static final String FILAHI_CARD_DJEUNZPOURLUI = "file:///android_asset/app/djeunzpourlui.html";
    public static final String FILAHI_CARD_DJEUNZPOURELLE = "file:///android_asset/app/djeunzpourelle.html";
    public static final String FILAHI_CARD_PARTPOURLUI = "file:///android_asset/app/partpourlui.html";
    public static final String FILAHI_CARD_PARTPOURELLE = "file:///android_asset/app/partpourelle.html";
    public static final String FILAHI_CARD_PRO = "file:///android_asset/app/pro.html";
    public static final String NONE = "file:///android_asset/app/none.html";
    public static final String SimulationCredit = "file:///android_asset/s/menu2.html?2-0-0";
    public static final String LiveChat = "file:///android_asset/s/test.html";

    public static final int CODE_CARTES = 1;
    public static final int CODE_COMPTES = 2;
    public static final int CODE_CONTACT = 3;
    public static final int CODE_CREDITS = 4;
    public static final int CODE_DASHBOARD = 5;
    public static final int CODE_DEMANDE_PAY_RECUS = 6;
    public static final int CODE_DEMANDE_PAY = 7;
    public static final int CODE_DETAIL_CARTE = 8;
    public static final int CODE_DETAIL_COMPTE = 9;
    public static final int CODE_DETAIL_CREDIT = 10;
    public static final int CODE_FILAHI_PACKS = 11;
    public static final int CODE_HOME = 12;
    public static final int CODE_LOGIN = 13;
    public static final int CODE_PRESENTATION = 14;
    public static final int CODE_REGLEMENT_PAY = 15;
    public static final int CODE_SIGNIN = 16;

    public static final int FINGER_OK = 1;
    public static final int FINGER_NOT_OK = 0;


    public static String code = "";
    public static String oper = "";

    public static final double F_Dash = 0;
    public static final double F_Compte = 1;
    public static final double F_Detail_Compte = 1.1;
    public static final double F_Carte = 2;
    public static final double F_Detail_Cart = 2.1;
    public static final double F_Credit = 3;
    public static final double F_Infos_Credit = 3.1;
    public static final double F_Demande_Pay = 4;
    public static final double F_Demande_Pay_History = 4.1;
    public static final double F_Reglement = 5;
    public static final double F_Reglement_History = 5.1;
    public static final double F_Reglement_Reg = 5.2;
    public static final double F_Reglement_Detail = 5.3;
    public static final double F_Pay_Flash = 6;
    public static final double F_Pay_Flash_History = 6.1;
    public static final double F_Recharge_Mob = 7;
    public static final double F_Recharge_Mob_Suivant = 7.1;
    public static final double F_Pay_Fac = 8;
    public static final double F_Pay_Fac_detail = 8.1;
    public static final double F_Pay_Fac_form = 8.2;
    public static final double F_Pay_Fac_Impays = 8.3;
    public static final double F_Pay_Fac_Confirm = 8.4;
    public static final double F_Virement = 9;
    public static final double F_Virement_History = 9.1;
    public static final double F_Transfert = 10;
    public static final double F_Transfert_History = 10.1;
    public static final double F_Depot_Dag = 11;
    public static final double F_Retrait_Dag = 12;
    public static final double F_Retrait_History = 12.1;
    public static final double F_Remise_Cheque = 13;
    public static final double F_Remise_Cheque_2 = 13.1;
    public static final double F_Commande_Carte = 14;
    public static final double F_Commande_Carte_2 = 14.1;
    public static final double F_Commande_Carte_Tracking_Agence_Fragment = 14.3;
    public static final double F_Commande_Carte_Fragment = 14;
    public static final double F_Commande_Cheque = 15;
    public static final double F_Commande_Cheque_2 = 15.1;
    public static final double F_Simulation_Credit = 16;
    public static final double F_Cours_Change = 17;
    public static final double F_Parametres = 18;
    public static final double F_Par_Info_pers = 18.1;
    public static final double F_Par_Sync_Cont = 18.2;
    public static final double F_Par_Change_mdp = 18.3;
    public static final double F_Par_Change_Email = 18.4;
    public static final double F_Addition_Sharing = 19;
    public static final double F_History_Addition = 19.1;
    public static final double F_Detail_History_Addition = 19.2;
    public static final double F_Affichr_Detail = 20;
    public static final double F_Change_Pro_Pic = 21;
    public static final double F_Notif = 22;
    public static final double F_rechargeprepaye = 23;
    public static final double F_Pay_rechargeprepaye_detail = 23.1;
    public static final double F_Pay_rechargeprepaye_form = 23.2;
    public static final double F_Pay_rechargeprepaye_Impays = 23.3;
    public static final double F_Pay_rechargeprepaye_Confirm = 23.4;
    public static final double F_Catalogue_Pro_Image = 24;
    public static final double F_Catalogue_Pro_Gre_Title = 24.1;
    public static final double F_Catalogue_Pro_View = 24.2;



    private static Dialog dialog19 = null;
    private static Button btn_reessayer9;
    public static String string;
    public static int position;
    //public static LinkedList<NonCreance> NonCreance;
    public static String nonCreace,nonCreanceRecharge;
    public static String new_gsm = "";
    public static String CinDes = "";
    public static String Intent_Front = "";
    public static String tier ;
    public static Bitmap myPicture;

   public static boolean bool=false;


    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    public static Bitmap convert(String base64Str) throws IllegalArgumentException
    {
        byte[] decodedBytes = Base64.decode(
                base64Str.substring(base64Str.indexOf(",")  + 1),
                Base64.DEFAULT
        );

        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static String convert(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }
    public static int sizeOf(Bitmap data) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
            return data.getRowBytes() * data.getHeight();
        } else if (Build.VERSION.SDK_INT< Build.VERSION_CODES.KITKAT){
            return data.getByteCount();
        } else{
            return data.getAllocationByteCount();
        }
    }
    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
    /*public static void setImg(Context context, CircleImageView circleImageView, String url){
        if(url.contains("http://") || url.contains("https://")){
            List<String> urlpart = Arrays.asList(url.split("/"));
            List<String> namelist = Arrays.asList(urlpart.get(urlpart.size()-1).split("\\."));
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier(namelist.get(0), "drawable",
                    context.getPackageName());
            circleImageView.setImageDrawable(resources.getDrawable(resourceId));
        }
        else{
            Bitmap bitmap = BitmapFactory.decodeFile(url);
            if(bitmap != null){
                circleImageView.setImageBitmap(bitmap);
            }

        }
    }*/

    public static void log(String msg){
        Log.i("aminetest",msg);
    }
    public static void log(String msg, Object o){
        Log.i("aminetest",msg);
    }

    public static String getDateCIN(String d){
        String dd = "1990-01-01";
        List<String> stringList = Arrays.asList(d.split("/"));
        if(stringList.size() == 3){
            String d1 = stringList.get(0);
            String m = stringList.get(1);
            if(d1.length() == 1){
                d1 = "0" + d1;
            }
            if(m.length() == 1){
                m = "0" + m;
            }
            dd = stringList.get(2) + "-" + m + "-" + d1;
        }
        return dd;
    }

    public static String convertDateCIN(String d){
        String dd = "";
/*        String day = d.substring(0,2);
        String month = d.substring(3,5);
        String year= d.substring(6,9);*/

        if (d.length() == 10){
            d= d.replace(d.substring(2,3),"/");
            d= d.replace(d.substring(5,6),"/");
        }


        IConfig.log("convertDateCIN "+d);


        return d;


    }

    public static String getRandomString(int length) {
        final String characters = "1234567890";
        StringBuilder result = new StringBuilder();
        while(length > 0) {
            Random rand = new Random();
            result.append(characters.charAt(rand.nextInt(characters.length())));
            length--;
        }
        return result.toString();
    }

    public static String getMD5Hex(String inputString) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(inputString.getBytes());

        byte[] digest = md.digest();

        return convertByteToHex(digest);
    }

    public static String convertByteToHex(byte[] byteData) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public static Boolean checkDateN(String d, String dcompare){
        Boolean re = false;
        List<String> stringList = Arrays.asList(d.split("/"));
        List<String> dcompareList = Arrays.asList(dcompare.split("/"));
        if(stringList.size() < 3 && dcompareList.size() < 3){
            re = false;
        }
        else{
            try{
                int mYear = Integer.parseInt(stringList.get(2));
                int mMonth = Integer.parseInt(stringList.get(1));
                int mDay = Integer.parseInt(stringList.get(0));

                int cYear = Integer.parseInt(dcompareList.get(2));
                int cMonth = Integer.parseInt(dcompareList.get(1));
                int cDay = Integer.parseInt(dcompareList.get(0));

                Date mDate = new Date(mYear,mMonth-1,mDay);
                Date cDate = new Date(cYear,cMonth-1,cDay);
                IConfig.log(mDate.getDate() + "/"
                        + (mDate.getMonth() + 1) + "/" + mDate.getYear());

                IConfig.log(cDate.getDate() + "/"
                        + (cDate.getMonth() + 1) + "/" + cDate.getYear());

                if(cDate.compareTo(mDate) > 0){
                    re = true;
                }
            }
            catch(NumberFormatException e){
                re = false;
            }
        }


        return re;
    }

    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }


    /*public static String crypte(String str){
        return Crypte.cry(str);
        //return str;
    }
    public static String deCrypte(String str){
        //return str;
        return Crypte.decry(str);
    }*/

/*    public static void openDialogClose9(Activity activity, int type, String msg , String btn, Context ctx){

        IConfig.log("openDialogClose9");
        if(dialog19 == null){
            dialog19 = new Dialog(ctx);
            dialog19.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog19.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog19.getWindow().getAttributes().windowAnimations = type;
            dialog19.setContentView(R.layout.dialog_demande_pay7);
            TextView textDialog = (TextView) dialog19.findViewById(R.id.textView69);
            textDialog.setText(msg+"");
            btn_reessayer9 = (Button) dialog19.findViewById(R.id.btn_reessayer);
            btn_reessayer9.setText(btn+"");
            btn_reessayer9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //dialogC.show();
                    dialog19.dismiss();
                    dialog19 = null;
                }
            });
            dialog19.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    dialog19 = null;
                }
            });
            dialog19.show();
            Window window = dialog19.getWindow();
            window.setLayout((int) (getScreenWidth(activity)* 0.8), (int)((getScreenHeight(activity) ) * 0.4  ) );

        }
//        if(dialog19.isShowing()){
//            IConfig.log("isShowing");
//        }
//        else{
//            IConfig.log("!isShowing");
//            dialog19.show();
//        }

    }*/

    public static int getScreenWidth(Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }

    public static int getScreenHeight(Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.y;
    }

    public static boolean isNetworkOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }




    /*public static VectorByte getVectorByte(byte[] tByte){
        VectorByte vectorByte = new VectorByte();
        for (int i = 0 ; i < tByte.length ; i++){
            vectorByte.add(tByte[i]);
        }
        return vectorByte;
    }*/


    public static Bitmap createSingleImageFromMultipleImages(Bitmap firstImage, Bitmap secondImage){

        Bitmap result = Bitmap.createBitmap(firstImage.getWidth(), firstImage.getHeight(), firstImage.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstImage, 0, 0, null);
        canvas.drawBitmap(secondImage, 0, 0, null);
        return result;
    }

}
