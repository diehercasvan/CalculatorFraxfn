package com.edibca.fraxfn;



import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TableLayout;


import class_fraxfn.Animation_Selection;
import class_fraxfn.Blackboard;
import class_fraxfn.Color_Table;
import class_fraxfn.Execute_Files;
import class_fraxfn.General;
import class_fraxfn.Screen_Email;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends Activity implements View.OnClickListener{
    /*Create  variable page intro layout*/
    public static LinearLayout linear_Menu;
    private RelativeLayout layout_Container;
    private LinearLayout linear_TableSelection;
    private FrameLayout frameLayout_Blackboard;
    public  static FrameLayout frameLayoutDetail;

    /*Create  variable page intro buttom*/
    public static ImageView btn_Mail;
    public static ImageView btn_blackBoart;
    public static ImageView btn_Menu;
    private ImageView btn_Clear_Blackboard;
    private SeekBar btn_seekBar;
    private String hexColor = "";
    private TableLayout Table_Selection_color;
    public static ImageView btn_Information;


    //Load variables
    private Animation_Selection animation_selection=null;
    private int iProgress;
    private String sNameSection="";
    private String sName_Route_Download;
    private String sName_Folder;
    private Execute_Files execute_files;
    private int position = 0;
    private String sSelection = "anatomy";

    //Load object
    private General Obj_General;
    private Blackboard blackboard;
    private Color_Table obj_Color_Table;
    //Create Object Screen Email
    private Screen_Email OBJ_screen_email;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault("fonts/century.ttf");
        String sRoute = Environment.getExternalStorageDirectory().getAbsolutePath();
        Obj_General = new General(this, this, sRoute, getResources());

        setContentView(R.layout.activity_main);

        loadPage();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(new CalligraphyContextWrapper(newBase));
    }


    public void  loadPage(){
        //Buttom
        btn_Menu=(ImageView)findViewById(R.id.btnMenu);
        btn_Menu.setOnClickListener(this);
        btn_Mail=(ImageView)findViewById(R.id.btnMail);
        btn_Mail.setOnClickListener(this);
        btn_blackBoart=(ImageView)findViewById(R.id.btnBoard);
        btn_blackBoart.setOnClickListener(this);
        btn_blackBoart.setVisibility(View.INVISIBLE);
        btn_Mail.setVisibility(View.INVISIBLE);
        btn_Information=(ImageView)findViewById(R.id.btnInformation);
        btn_Information.setOnClickListener(this);

        btn_Clear_Blackboard=(ImageView)findViewById(R.id.btn_Clear_Blackboard);
        btn_Clear_Blackboard.setOnClickListener(this);

        //Layout
        linear_Menu=(LinearLayout)findViewById(R.id.linearMenu);
        layout_Container=(RelativeLayout)findViewById(R.id.layoutContainerGeneral);
        linear_TableSelection=(LinearLayout)findViewById(R.id.linearTableSelection);
        frameLayout_Blackboard=(FrameLayout)findViewById(R.id.frameLayoutBlackboard);
        frameLayoutDetail=(FrameLayout)findViewById(R.id.frameLayoutDetail);
        //layout_Container.setOnClickListener(this);

        //Oter object
        animation_selection= new Animation_Selection();



        //Selection blackboard
        Table_Selection_color = (TableLayout) findViewById(R.id.Table_Selection_color);
        obj_Color_Table = new Color_Table();
        obj_Color_Table.createTable(Table_Selection_color);

        btn_seekBar = (SeekBar) findViewById(R.id.seekBar);
        btn_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                iProgress = progress;
                View_Blackboard();
                if (hexColor.equals("")) {

                    hexColor = "#000000";
                }

                blackboard.iWidth = iProgress;
                blackboard.sColor = hexColor;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /*Load container screen mail*/
        OBJ_screen_email = new Screen_Email(layout_Container);
        General.deleteCache(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Animation animation=null;
        linear_TableSelection.clearAnimation();

        Drawable drawable = v.getBackground();
        //There validate background
        if (drawable instanceof ColorDrawable) {
            int color = Color.TRANSPARENT;
            color = ((ColorDrawable) drawable).getColor();
            hexColor = String.format("#%06X", (0xFFFFFF & color));
            // Toast.makeText(this,"Selection"+hexColor,Toast.LENGTH_LONG).show();
            View_Blackboard();
            blackboard.iWidth = iProgress;
            blackboard.sColor = hexColor;

        }

        switch(v.getId()){
            case R.id.btnMail:
                OBJ_screen_email.createScreen();
                break;
            case R.id.btnMenu:
                if(linear_Menu.getVisibility()==View.VISIBLE){

                    animation= animation_selection.Animation_selec(10);
                    btn_Menu.setImageResource(R.drawable.icon_menu);
                    btn_Menu.startAnimation(animation);
                    viewMenu(2);
                }
                else{
                    animation= animation_selection.Animation_selec(10);
                    btn_Menu.startAnimation(animation);
                    btn_Menu.setImageResource(R.drawable.icon_menu_close);
                    viewMenu(1);
                }
                break;
            case R.id.btnBoard:
                if(frameLayout_Blackboard.getVisibility()==View.VISIBLE){

                    close_blackboard();
                    btn_Menu.setEnabled(true);
                }
                else{
                    open_blackboard();
                    btn_Menu.setEnabled(false);
                }
                break;
            case R.id.btn_Clear_Blackboard:

                Clear_Blackboard();
                frameLayout_Blackboard.setVisibility(View.VISIBLE);
                //View_Blackboard();
               // blackboard.iWidth = iProgress;
                //blackboard.sColor = hexColor;

                break;
            case R.id.layoutContainerGeneral:
                animation= animation_selection.Animation_selec(10);
                btn_Menu.setImageResource(R.drawable.icon_menu);
                btn_Menu.startAnimation(animation);
                viewMenu(2);
                break;
            case R.id.btnInformation:

                position = 11;
                sName_Route_Download="http://creative-med.com/AZ/Descargas_Android/Pdf/";
                execute_files= new Execute_Files();
                sName_Folder="info.pdf";
                execute_files.executeFiles(sName_Route_Download+sName_Folder, sName_Folder, true,true);
                break;

        }


    }
    public void close_blackboard() {
        Animation animation=null;
        animation= animation_selection.Animation_selec(12);
        linear_TableSelection.startAnimation(animation);
        frameLayout_Blackboard.startAnimation(animation);
        linear_TableSelection.setVisibility(View.GONE);
        frameLayout_Blackboard.setVisibility(View.GONE);
        btn_seekBar.setProgress(0);
    }
    public void open_blackboard() {
        if(linear_Menu.getVisibility()==View.VISIBLE){
        viewMenu(2);
        }
        Animation animation=null;
        try {
            Clear_Blackboard();

            animation= animation_selection.Animation_selec(9);
            linear_TableSelection.startAnimation(animation);
            linear_TableSelection.setVisibility(View.VISIBLE);
            Thread.sleep(500);
            frameLayout_Blackboard.setVisibility(View.VISIBLE);
            View_Blackboard();
            blackboard.iWidth = 4;
            blackboard.sColor = "#000000";
        } catch (Exception e) {
            Log.i("Blackboard  ", "Error :" + e.getMessage());
        }
    }

    public  void viewMenu(int iSelection){

        Animation animation=null;
        ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.flip_3d);
        anim.cancel();
        linear_Menu.clearAnimation();
        switch(iSelection){
            case 1:
                anim.setTarget(linear_Menu);
                anim.setDuration(1000);
                anim.start();
                animation= animation_selection.Animation_selec(2);
                linear_Menu.startAnimation(animation);
                linear_Menu.setVisibility(View.VISIBLE);

                break;
            case 2:
                anim.setTarget(linear_Menu);
                anim.setDuration(1400);
                anim.start();
                animation= animation_selection.Animation_selec(3);
                linear_Menu.startAnimation(animation);
                linear_Menu.setVisibility(View.GONE);


                break;

        }



    }


    //Clear Blackboard
    public void Clear_Blackboard() {
        frameLayout_Blackboard.removeAllViews();

    }

    //Load Blackboard
    public void View_Blackboard() {

        blackboard = new Blackboard(this);
        try

        {

            frameLayout_Blackboard.addView(blackboard);
        } catch (Exception e) {
            Log.i("Blackboard  ", "Error :" + e.getMessage());

        }


    }



}
