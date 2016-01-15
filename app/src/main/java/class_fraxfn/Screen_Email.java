package class_fraxfn;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.edibca.fraxfn.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by DIEGO CASALLAS on 20/05/2015.
 */
public class Screen_Email {

    private File file;
    private String sRoute;
    private String sNameFolder;
    private String sFilePath;
    private FileOutputStream fileOutputStream;
    private View view;
    private Bitmap bitmap;
    private Activity activity;

    public Screen_Email(RelativeLayout frameLayout){

        view=frameLayout;
        //view=getWindow().getDecorView().getRootView() General;
        // View v1 = iv.getRootView(); //even this works
        // View v1 = findViewById(android.R.id.content); //this works too

        sRoute= General.ROUTE;
        sNameFolder= General.NAME_FOLDER_MAIL;
        activity= General.ACTIVITY;
    }
    public void createScreen(){

        view.setDrawingCacheEnabled(true);
        bitmap=view.getDrawingCache();
        saveBitmap(bitmap);
        view.destroyDrawingCache();
    }


    public void saveBitmap(Bitmap bitmap) {


        sFilePath = Environment.getExternalStorageDirectory()
                + File.separator + sNameFolder+"/screenshot.png";
        file = new File(sFilePath);


        try {
            fileOutputStream = new FileOutputStream(file);


            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            sendMail(sFilePath);
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }
    public void sendMail(String path) {
        try
        {
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.putExtra(Intent.EXTRA_EMAIL,
                    new String[] { General.MAIL_CONTENT[0] });
            emailIntent.putExtra(Intent.EXTRA_SUBJECT,
                    General.MAIL_CONTENT[1]);
            emailIntent.putExtra(Intent.EXTRA_TEXT,
                    General.MAIL_CONTENT[2]);

            emailIntent.setType("image/png");
            Uri myUri = Uri.parse("file://" + path);
            emailIntent.putExtra(Intent.EXTRA_STREAM, myUri);
            activity.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        }
        catch(Exception e)
        {

            General.messageToast(R.string.errorEmail);

        }

    }
    public void diary(String path) {
        Intent emailIntent = new Intent(Intent.ACTION_EDIT);
        emailIntent.setType("vnd.android.cursor.item/event");
       /* emailIntent.putExtra(Intent.EXTRA_EMAIL,
                new String[] { DTO_General.MAIL_CONTENT[0] });
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,
                DTO_General.MAIL_CONTENT[1]);
        emailIntent.putExtra(Intent.EXTRA_TEXT,
                DTO_General.MAIL_CONTENT[2]);

        emailIntent.setType("image/png");
        Uri myUri = Uri.parse("file://" + path);
        emailIntent.putExtra(Intent.EXTRA_STREAM, myUri);*/
        activity.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }



}

