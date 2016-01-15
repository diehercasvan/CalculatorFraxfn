package com.edibca.fraxfnGTRD;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edibca.fraxfn.R;

import class_fraxfn.DataBaseManager;
import class_fraxfn.General;

/**
 * Created by DIEGO CASALLAS on 01/10/2015.
 */
public class Login extends Activity implements View.OnClickListener {
    private DataBaseManager manager;
    private EditText editPassword;
    private Button btnLogin;
    private String sTexPassword;
    private Cursor cursor;
    private String sLogin;

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
        this.sLogin=getResources().getString(R.string.login);

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
        cursor=manager.buscarContacto(sLogin);

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
        if (sTexPassword.equals(sLogin)) {
            bValidate = true;
        } else {
            editPassword.setText("");

            Toast.makeText(getApplication(),R.string.erroLogin,Toast.LENGTH_LONG).show();

        }


        return bValidate;

    }
}
