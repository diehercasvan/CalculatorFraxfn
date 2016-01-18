package class_fraxfn;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.io.File;

/**
 * Created by Diego Casallas on 09/06/2015.
 */
public class Execute_Files {
    private String sFile_Route;
    private Activity activity;
    private Context context;
    private File file;
    private boolean bValidaConexion;
    private DownloadFile downloadFile;
    private DownloadFileDirect downloadFileDirect;
    private Connections connections;
    private Class_control Obj_Class_control;
    private  boolean bType;

    public Execute_Files(){
        context= General.CONTEXT;
        sFile_Route= General.FILE_ROUTE;
        activity= General.ACTIVITY;
        Obj_Class_control=new Class_control(activity);
    }

    public  void executeFiles(String route, String name_File,boolean type,boolean typeDownload){

        bType=type;
        String sRoute=sFile_Route+"/"+name_File;

        file=new File(sRoute);
        if(file.exists()){
            if(typeDownload) {
                Obj_Class_control.executeFile(sRoute, type);
            }
        }
        else{
            file=new File(sFile_Route);
            if(!file.isDirectory()){
                file.mkdir();
            }
            if(file.exists()){
                if(typeDownload){
                    if(ConnectionValidate()){
                        downloadFile = new DownloadFile(activity, context, sFile_Route, name_File, bType);

                        downloadFile.execute(route);
                    }
                }
                else{
                    if(ConnectionValidate()) {
                        downloadFileDirect = new DownloadFileDirect(activity, context, sFile_Route, name_File);
                        downloadFileDirect.execute(route);
                    }

                }

            }

        }
        

    }
    //Connection to  validate
    public  boolean  ConnectionValidate(){
        bValidaConexion=false;
        connections=new Connections(activity);
        bValidaConexion=false;
        final AlertDialog.Builder JOptionPane =new AlertDialog.Builder(activity);
        if(connections.redConexion()==0){
            bValidaConexion=true;
        }
        else if(connections.redConexion()==1){
            JOptionPane.setTitle("Downloadable Content");
            JOptionPane.setMessage("You want downloading");
            JOptionPane.setPositiveButton("Accept",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    bValidaConexion=true;
                }
            });
            JOptionPane.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    bValidaConexion =false;
                }
            });

            JOptionPane.create();
            JOptionPane.show();
        }
        else if(connections.redConexion()==2){

            JOptionPane.setTitle("Downloadable content");
            JOptionPane.setMessage("Check your internet connection");
            JOptionPane.create();
            JOptionPane.show();
            bValidaConexion=false;
        }
        return bValidaConexion;

    }
}
