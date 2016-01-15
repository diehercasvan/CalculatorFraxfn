package com.edibca.fraxfnGTRD;

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

import com.edibca.fraxfn.R;
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
    private String []sMenu;
    public Menu() {

        this.activity = General.ACTIVITY;
        this.context = General.CONTEXT;
        this.sName_Route_Download="";
        this.sMenu=activity.getResources().getStringArray(R.array.menuList);


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
                            sNameSection=sMenu[1];
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
                            sNameSection=sMenu[3];
                            break;
                        case 1:
                            sSelection = "pathologyOne";
                            position = 4;
                            sNameSection=sMenu[4];
                            break;
                        case 2:
                            sSelection = "pathologyTwo";
                            position = 5;
                            sNameSection=sMenu[5];
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
                            sNameSection=sMenu[7];
                            break;
                        case 1:
                            sSelection = "treatmentOne";
                            position = 9;
                            sNameSection=sMenu[8];
                            break;

                        default:
                            break;
                    }
                }


                displayView(sSelection, position);
                return false;
            }
        });


        tracker = ((Frax) activity.getApplication()).getTracker(Frax.TrackerName.APP_TRACKER);

        return view;
    }
    @Override
    public void onStop() {
        super.onStop();
        tracker.setScreenName(null);
    }
    private void Load_data() {

        lGroups = new ArrayList<>();
        dataGroups = new HashMap<String, List<String>>();

        lGroups.add(sMenu[0]);
        lGroups.add(sMenu[2]);
        lGroups.add(sMenu[6]);


        List<String> hijos_grupo1 = new ArrayList<String>();
        hijos_grupo1.add(sMenu[1]);

        List<String> hijos_grupo2 = new ArrayList<String>();
        hijos_grupo2.add(sMenu[3]);
        hijos_grupo2.add(sMenu[4]);
        hijos_grupo2.add(sMenu[5]);

        List<String> hijos_grupo3 = new ArrayList<String>();
        hijos_grupo3.add(sMenu[7]);
        hijos_grupo3.add(sMenu[8]);

        dataGroups.put(lGroups.get(0), hijos_grupo1);
        dataGroups.put(lGroups.get(1), hijos_grupo2);
        dataGroups.put(lGroups.get(2), hijos_grupo3);
        adapter = new My_Adapter(activity, lGroups, dataGroups);
        expandableListView.setAdapter(adapter);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void displayView(String sSelection, int position) {
        Fragment fragment = null;
        try {
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

                viewMenu(1, true);
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

        } catch (Exception e) {

            General.messageToast(R.string.error);
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
                sNameSection="Referencias";
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

