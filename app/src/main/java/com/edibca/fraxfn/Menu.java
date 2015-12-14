package com.edibca.fraxfn;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import class_fraxfn.*;

/**
 * Created by Diego Casallas on 22/06/2015.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Menu extends Fragment implements View.OnClickListener {

    private List<String> lGroups;
    private HashMap<String, List<String>> dataGroups;
    private int latestPosition = -1;
    private My_Adapter adapter;
    private ExpandableListView expandableListView;
    private View view;
    private Activity activity;
    private Container_fragment container_fragment;
    private String sSelection = "anatomy";
    private int position = 0;
    private ImageView Btn_Home;
    private ImageView Btn_Close;
    private Context context;
    private ImageView Btn_References;

    private String sName_Route_Download;
    private String sName_Folder;
    private Execute_Files execute_files;

    private String sNameSection="";
    private Tracker tracker;
    public Menu() {
        this.activity = General.ACTIVITY;
        this.context = General.CONTEXT;
        this.sName_Route_Download="";


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu, container, false);
        //********************************************
        Btn_Home = (ImageView) view.findViewById(R.id.btnHome);
        Btn_Home.setOnClickListener(this);

        Btn_Close = (ImageView) view.findViewById(R.id.btnClose);
        Btn_Close.setOnClickListener(this);

        Btn_References=(ImageView)view.findViewById(R.id.btnReferences);
        Btn_References.setOnClickListener(this);


        //********************************************


        expandableListView = (ExpandableListView) view.findViewById(R.id.explist_slidermenu);
        expandableListView.setGroupIndicator(null);//Indicador flecha desplegable izquierda oculta
        Load_data();

        expandableListView.setTextFilterEnabled(true);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (latestPosition != -1 && groupPosition != latestPosition) {
                    expandableListView.collapseGroup(latestPosition);
                }
                latestPosition = groupPosition;
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                int grup_pos = (int) adapter.getGroupId(groupPosition);
                int child_pos = (int) adapter.getChildId(groupPosition, childPosition);

                if (grup_pos == 0) {
                    switch (child_pos) {
                        case 0:
                            sSelection = "anatomy";
                            position = 0;
                            sNameSection="El hueso";
                            break;
                        case 1:
                            sSelection = "anatomyOne";
                            position = 1;
                            sNameSection="El corazón";
                            break;
                        case 2:
                            sSelection = "anatomyTwo";
                            position = 2;
                            sNameSection="Ciclo cardiaco";
                            break;
                        default:
                            break;
                    }
                }
                if (grup_pos == 1) {
                    switch (child_pos) {
                        case 0:
                            sSelection = "pathology";
                            position = 3;
                            sNameSection="Osteoporosis";
                            break;
                        case 1:
                            sSelection = "pathologyOne";
                            position = 4;
                            sNameSection="Factores de riesgo ";
                            break;
                        case 2:
                            sSelection = "pathologyTwo";
                            position = 5;
                            sNameSection="Tratamiento";
                            break;
                        case 3:
                            sSelection = "pathologyThree";
                            position = 6;
                            sNameSection="Agina de pecho";
                            break;
                        case 4:
                            sSelection = "pathologyFour";
                            position = 7;
                            sNameSection="Infarto agudo del miocardio ";
                            break;
                        default:
                            break;
                    }

                }
                if (grup_pos == 2) {
                    switch (child_pos) {
                        case 0:
                            sSelection = "treatment";
                            position = 8;
                            sNameSection="Calculadora de riesgo FRAX";
                            break;
                        case 1:
                            sSelection = "treatmentOne";
                            position = 9;
                            sNameSection="Calculadora de requerimientos de calcio";
                            break;

                        default:
                            break;
                    }
                }
                if (grup_pos == 3) {
                    switch (child_pos) {
                        case 0:
                            sSelection = "mechanism";
                            position = 10;
                            sName_Route_Download="http://creative-med.com/AZ/Descargas_Android/Video/";
                            execute_files= new Execute_Files();
                            sName_Folder="mecanismo_accion.mp4";
                            execute_files.executeFiles(sName_Route_Download+sName_Folder, sName_Folder, true,false);
                            sNameSection="Mecanismo de acción ";

                            break;
                        case 1:
                            sSelection = "ipp";
                            sNameSection="Ipp";
                            position = 11;
                            sName_Route_Download="http://creative-med.com/AZ/Descargas_Android/Pdf/";
                            execute_files= new Execute_Files();
                            sName_Folder="ipp.pdf";
                            execute_files.executeFiles(sName_Route_Download+sName_Folder, sName_Folder, true,true);
                            return false;

                        default:
                            break;
                    }
                }
                // mDrawerLayout.closeDrawer(expandableListView);

                displayView(sSelection, position);
                return false;
            }
        });
        if (savedInstanceState == null) {
            // displayView(sSelection,position);
        } else {

            //displayView(sSelection,position);
        }



        // Get tracker.
        tracker = ((Frax) activity.getApplication()).getTracker(Frax.TrackerName.APP_TRACKER);

        return view;
    }
    @Override
    public void onStop() {
        super.onStop();
        //GoogleAnalytics.getInstance(this).reportActivityStop(this);
        tracker.setScreenName(null);
    }
    private void Load_data() {

        lGroups = new ArrayList<>();
        dataGroups = new HashMap<String, List<String>>();

        lGroups.add("Anatomía");
        lGroups.add("Patología");
        lGroups.add("Herramientas");
       // lGroups.add("Ticagrelor");
        // lGroups.add("Información para pacientes");

        List<String> hijos_grupo1 = new ArrayList<String>();
        hijos_grupo1.add("El hueso");
        //hijos_grupo1.add("El corazón");
        //hijos_grupo1.add("Ciclo cardiaco");

        List<String> hijos_grupo2 = new ArrayList<String>();
        hijos_grupo2.add("Osteoporosis");
        hijos_grupo2.add("Factores de riesgo");
        hijos_grupo2.add("Tratamiento");
        //hijos_grupo2.add("Angina de pecho");
        //hijos_grupo2.add("Infarto agudo del miocardio (IAMCEST, IAMSEST)");

        List<String> hijos_grupo3 = new ArrayList<String>();
        hijos_grupo3.add("Calculadora de riesgo FRAX");
        hijos_grupo3.add("Cálculo de requerimientos de calcio");

        /*List<String> hijos_grupo4 = new ArrayList<String>();
        hijos_grupo4.add("Mecanismo de acción");
        hijos_grupo4.add("IPP");*/

/*
        List<String> hijos_grupo5 = new ArrayList<String>();
     */

        dataGroups.put(lGroups.get(0), hijos_grupo1);
        dataGroups.put(lGroups.get(1), hijos_grupo2);
        dataGroups.put(lGroups.get(2), hijos_grupo3);
        //dataGroups.put(lGroups.get(3), hijos_grupo4);
        //dataGroups.put(lGroups.get(4), hijos_grupo5);

        adapter = new My_Adapter(activity, lGroups, dataGroups);
        expandableListView.setAdapter(adapter);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void displayView(String sSelection, int position) {
        Fragment fragment = null;
        //Toast.makeText(activity,"Seleccion :"+sSelection,Toast.LENGTH_LONG).show();
        try {
            //Toast.makeText(activity,"you Selection"+sSelection,Toast.LENGTH_LONG).show();
            loadAnalytics(sNameSection);
            container_fragment = new Container_fragment();
            container_fragment.sSelection = sSelection;
            fragment = container_fragment.Load_fragment();

            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.frameLayoutDetail, fragment).commit();
            if(sSelection.equals("references")){

                viewMenu(1,false);
            }
            else{

                viewMenu(1,true);
            }
            if(sSelection.equals("pathology")){

                MainActivity.btn_Information.setVisibility(View.VISIBLE);
            }
            else{

                MainActivity.btn_Information.setVisibility(View.INVISIBLE);
            }


            General.deleteCache(context);
            if(position<11){
            expandableListView.setItemChecked(position, true);
            expandableListView.setSelection(position);
            }

            //setTitle(grupos.get(position));
            //getSupportActionBar().setTitle(mDrawerTitle);
            //getSupportActionBar().setSubtitle(mTitle);
            //mDrawerLayout.closeDrawer(expandableListView);


        } catch (Exception e) {
            Toast.makeText(activity, "Se ha generado  un  inconveniente    ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnHome:
                General.Reboot_Page();
                sNameSection="Home ";
                break;
            case R.id.btnClose:
                General.closePage();

                break;
            case R.id.btnReferences:
                displayView("references",12);
                sNameSection="Referencias ";
                break;



        }
        loadAnalytics(sNameSection);
    }
    public void loadAnalytics(String sScreen){

        tracker.setScreenName(sScreen);

        // Send a screen view.
        tracker.send(new HitBuilders.AppViewBuilder().build());

    }
    public  void viewMenu(int iSelection,boolean validate){
        Animation_Selection animation_selection= new Animation_Selection();
        Animation animation=null;
        ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(activity, R.animator.flip_3d);
        anim.cancel();
        MainActivity.linear_Menu.clearAnimation();
        if(validate){
            MainActivity.btn_blackBoart.setVisibility(View.VISIBLE);
            MainActivity.btn_Mail.setVisibility(View.VISIBLE);

        }

        else{

            MainActivity.btn_blackBoart.setVisibility(View.INVISIBLE);
            MainActivity.btn_Mail.setVisibility(View.INVISIBLE);
        }
        switch(iSelection){

            case 1:

                animation= animation_selection.Animation_selec(10);
                MainActivity.btn_Menu.setImageResource(R.drawable.icon_menu);
                MainActivity.btn_Menu.startAnimation(animation);

                anim.setTarget(MainActivity.linear_Menu);
                anim.setDuration(1400);
                anim.start();
                animation= animation_selection.Animation_selec(3);
                MainActivity.linear_Menu.startAnimation(animation);
                MainActivity.linear_Menu.setVisibility(View.GONE);
                animation = animation_selection.Animation_selec(1);
                MainActivity.frameLayoutDetail.startAnimation(animation);
                if(MainActivity.frameLayoutDetail.getVisibility()!=View.VISIBLE) {


                    MainActivity.frameLayoutDetail.setVisibility(View.VISIBLE);

                }



                break;

        }



    }

}
