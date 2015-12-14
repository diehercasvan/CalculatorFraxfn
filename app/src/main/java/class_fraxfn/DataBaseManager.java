package class_fraxfn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Diego Casallas  on 12/05/2015.
 */
public class DataBaseManager {

    //Datos tabla
    public static final String TABLE_NAME="syvisc";
    public static final String CN_ID="_id";
    public static final String CN_PASSWORD="password";

    //Crear sentencia SQL

    public  static  final String CREATE_TABLE="create table "+TABLE_NAME+" ("
           + CN_ID +" integer primary key autoincrement,"
           + CN_PASSWORD +" text not null);";

    private DataBaseHelper helper;
    private SQLiteDatabase db;
    public  DataBaseManager(Context context){
        helper= new DataBaseHelper(context);
        db = helper.getWritableDatabase();

    }
    //Contenedor  de  valores
    private ContentValues generarContentValues(String sPassword){
        ContentValues valores=new ContentValues();
        valores.put(CN_PASSWORD,sPassword);

        return valores;
    }
    //Insertar  datos forma android
    public  long  insertar(String sPassword){//Valida la  inserccion si  es -1 hay  un error en  la  operacion

        //NullColumHack ->compatibilidad  con  otras  bases de  datos
        return db.insert(TABLE_NAME,null,generarContentValues(sPassword));

    }
    //Insertar  datos forma exexcSql
    public  void   insertarDos(String sPassword){//Valida la  inserccion si  es -1 hay  un error en  la  operacion

        String sSql="INSERT INTO "+TABLE_NAME+" VALUES (null,'"+sPassword+"')";

        db.execSQL(sSql);

    }
    //Eliminar Uno
    public void eliminar(String sPassword){

        db.delete(TABLE_NAME,CN_PASSWORD+"=?",new String[]{sPassword});
    }
    public Cursor cargarCursorContactos(){
        String[]columnas=new String[]{CN_ID,CN_PASSWORD};
        //query (String table, String []columns,String campos  )
        return db.query(TABLE_NAME,columnas,null,null,null,null,null);

    }
    public Cursor buscarContacto(String sPassword){
        String[]columnas=new String[]{CN_ID,CN_PASSWORD};
        //query (String table, String []columns,String campos  )

        return db.query(TABLE_NAME,columnas,CN_PASSWORD+"=?",new String[]{sPassword},null,null,null);

    }

}
