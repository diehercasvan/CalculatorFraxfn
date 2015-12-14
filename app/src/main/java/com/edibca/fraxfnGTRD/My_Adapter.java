package com.edibca.fraxfnGTRD;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edibca.fraxfn.R;

import java.util.HashMap;
import java.util.List;


/**
 * Created by Diego Casallas on 22/06/2015.
 */
public class My_Adapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> lGroups;
    private HashMap<String, List<String>> dataGroups;


    public My_Adapter(Context cont, List<String> groups, HashMap<String, List<String>> data){

        this.context=cont;
        this.lGroups=groups;
        this.dataGroups=data;

    }


    @Override
    public int getGroupCount() {
        return this.lGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.dataGroups.get(this.lGroups.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.lGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.dataGroups.get(this.lGroups.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle =(String)getGroup(groupPosition);
        ImageView imageView=null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group, null);
        }
        if (groupPosition == 0) {
           // ((ImageView) convertView.findViewById(R.id.Img_list_group)).setImageResource(R.mipmap.ic_launcher);
           // ((TextView) convertView.findViewById(R.id.Txt_description)).setText("Descripción grupo 1");
        } else if (groupPosition == 1) {
           // ((ImageView) convertView.findViewById(R.id.Img_list_group)).setImageResource(R.mipmap.ic_launcher);
            //((TextView) convertView.findViewById(R.id.Txt_description)).setText("Descripción grupo 2");
        } else if (groupPosition == 2) {
            //((ImageView) convertView.findViewById(R.id.Img_list_group)).setImageResource(R.mipmap.ic_launcher);
            //((TextView) convertView.findViewById(R.id.Txt_description)).setText("Descripción grupo 3");
        } else if (groupPosition == 3) {
            //((ImageView) convertView.findViewById(R.id.Img_list_group)).setImageResource(R.mipmap.ic_launcher);
            //((TextView) convertView.findViewById(R.id.Txt_description)).setText("Descripción grupo 4");
        }
        else if (groupPosition == 4) {
            //((ImageView) convertView.findViewById(R.id.Img_list_group)).setImageResource(R.mipmap.ic_launcher);
            //((TextView) convertView.findViewById(R.id.Txt_description)).setText("Descripción grupo 5");
        }

        TextView textView=(TextView)convertView.findViewById(R.id.Txt_group);
        int value=0;
        value=this.dataGroups.get(this.lGroups.get(groupPosition)).size();//Asignamos el nº de elementos que hay en cada grupo
        textView.setTypeface(null, Typeface.BOLD);
        //textView.setText(headerTitle + " (" + Integer.toString(value) + ")");//Método de la clase Integer
        textView.setText(headerTitle);
        imageView=(ImageView)convertView.findViewById(R.id.expandableIcon);
        int imageResourceId=isExpanded ? android.R.drawable.arrow_up_float:android.R.drawable.arrow_down_float;
        imageView.setImageResource(imageResourceId);//Cambiamos indicador flecha desplegable a la derecha, ver fila_grupo


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_group_son, null);
        }

        /*if (childPosition == 0) {
            ((ImageView) convertView.findViewById(R.id.img_hijo)).setImageResource(R.mipmap.ic_launcher);
        }*/
        int valor = 0;
        valor = this.dataGroups.get(this.lGroups.get(groupPosition)).size();
        //Asignamos a todos la misma imagen
        //for (int x = 0; x < valor; x++) {
          //  ((ImageView) convertView.findViewById(R.id.img_hijo)).setImageResource(R.mipmap.ic_launcher);
        //}


        TextView a = (TextView) convertView.findViewById(R.id.Txt_list_son_grpup);
        a.setText(childText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
