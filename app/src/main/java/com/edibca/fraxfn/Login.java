package com.edibca.fraxfn;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import class_fraxfn.DataBaseManager;

/**
 * Created by DIEGO CASALLAS on 01/10/2015.
 */
public class Login extends Activity implements View.OnClickListener {
    private DataBaseManager manager;
    private EditText editPassword;
    private Button btnLogin;
    private String sTexPassword;
    private  boolean bValidaPassword;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loadPage();

    }

    public void loadPage() {

        editPassword = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btn_Login);
        btnLogin.setOnClickListener(this);
        manager = new DataBaseManager(this);
        //manager.eliminar("password");

        if(validaLogin()){
            reload ();

        }


    }
    public void reload (){

        Intent intent=new Intent(Login.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
    public boolean validaLogin(){
        cursor=manager.buscarContacto("bonviva");

        if(cursor.getCount()!=0 ){

            return true;
        }

        return false;
    }

    @Override
    public void onClick(View v) {
        sTexPassword = editPassword.getText().toString().toLowerCase().trim();
        if (searchData()) {

            insertarPassword(sTexPassword);
            editPassword.setText("");
            reload ();
        }

    }
    public  void insertarPassword(String sPassword){

        manager.insertar(sPassword);

    }
    public boolean searchData() {

        boolean bValidate = false;
        if (sTexPassword.equals("bonviva")) {
            bValidate = true;
        } else {
            editPassword.setText("");
            Toast.makeText(getApplicationContext(), "Contraseña  incorrecta", Toast.LENGTH_SHORT).show();

        }


        return bValidate;

    }
}
