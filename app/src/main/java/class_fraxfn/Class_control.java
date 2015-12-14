package class_fraxfn;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

/**
 * Created by Diego Casallas on 08/05/2015.
 */
public class Class_control {

    private File dir;

    private Activity activity;

    public Class_control(Activity activity){

        this.activity=activity;
    }




    //Metodo  para ejecutar  archivos
    public  void executeFile(String sDireccion,boolean bValida){
        dir= new File(sDireccion);
        Uri uri = Uri.fromFile(dir);

        try{

            Intent intent = new Intent(Intent.ACTION_VIEW);
            //intent.setDataAndType(uri,"application/video");
            //intent.setDataAndType(uri, "application/"+sTipo+"");
            if(bValida){
                //intent.setData(uri);
                intent.setDataAndType(uri,"application/pdf");
            }
            else{
                intent.setDataAndType(uri, "video/mp4");
                //Toast.makeText(this,"Ejecuto",Toast.LENGTH_SHORT).show();
            }

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );

            activity.startActivity(intent);

            // finish();
        } catch (Exception e) {
            //txtLoading.setError("No hay aplicacion pdf");
        }

    }
}
