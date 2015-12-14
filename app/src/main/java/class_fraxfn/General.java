package class_fraxfn;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.animation.Animation;

import com.edibca.fraxfnGTRD.MyCallback;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by DIEGO CASALLAS  on 09/09/2015.
 */
public class General {
    public static Activity ACTIVITY;
    public static Context CONTEXT;
    public static String[] MAIL_CONTENT;
    public static String NAME_ARCHIVE;
    public static String ROUTE;
    public static String NAME_FOLDER;
    public static String NAME_FOLDER_MAIL;
    public static String FILE_URL ;
    public static String FILE_ROUTE ;
    public static ArrayList<Integer> aList_Layout_Frament;
    public static Resources RESOURCE;
    public static Animation animation;
    public static Animation_Selection animation_selection;
    public static String URL_VIDEO;
    public static Resources RESOURCES;
    public  static  String sData;
    public static  MyCallback myCallback;




    public General (Activity act,Context context,String route,Resources resources){

        this.ACTIVITY=act;
        this.CONTEXT=context;
        this.RESOURCE=resources;
        this.NAME_FOLDER="Brilinta";
        this.FILE_URL = "file:///android_asset/content/";
        this.FILE_ROUTE = route + "/" + NAME_FOLDER;
        this.ROUTE=route;
        this.RESOURCES=act. getResources();
        this.MAIL_CONTENT= new String []{"","Atlas Information",""};
        this.NAME_FOLDER_MAIL="Pictures";
        this.NAME_ARCHIVE="";
        this.URL_VIDEO="android.resource://"+act.getPackageName();
        animation_selection=new Animation_Selection();
    }
    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {}
    }
    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
    public static void Reboot_Page(){

        Intent intent=ACTIVITY.getIntent();
        ACTIVITY.finish();
        ACTIVITY.startActivity(intent);

    }
    public static void closePage(){

        Intent intent=ACTIVITY.getIntent();
        ACTIVITY.finish();


    }
}
