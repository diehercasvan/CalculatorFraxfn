package com.edibca.fraxfnGTRD;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.*;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import com.edibca.fraxfn.R;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import class_fraxfn.Animation_Selection;
import class_fraxfn.General;
import class_fraxfn.Xml;

/**
 * Created by DIEGO CASALLAS on 11/09/2015.
 */
public class Calculadora extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener  {
    private View view;
    private Context context;
    private Activity activity;

    private Button btn_send;
    private Animation animation;
    private Animation_Selection animation_selection;
    private Xml xml;
    private Spinner spinner;
    private RadioGroup radioGroup1;
    private RadioButton[] radioSex = new RadioButton[8];
    private RadioGroup radioGroup2;
    private RadioGroup radioGroup3;
    private RadioGroup radioGroup4;
    private RadioGroup radioGroup5;
    private RadioGroup radioGroup6;
    private RadioGroup radioGroup7;
    private RadioGroup radioGroup8;
    private int selectedId[] = new int[8];
    private EditText txt_Weight;
    private EditText txt_Height;
    private String sYear = "";
    private int[] idXML;
    private TextView[] listResult = new TextView[5];
    //Load object
    private General Obj_General;
    public Calculadora(){

        context=General.CONTEXT;
        activity=General.ACTIVITY;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.risk_calculator,container,false);

        xml = new Xml(activity, getResources());
        loadPage();
        return view;

    }
    public void loadPage() {

        btn_send = (Button) view.findViewById(R.id.btnCalculater);
        btn_send.setOnClickListener(this);


        spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity, R.array.edad, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        radioGroup1 = (RadioGroup) view.findViewById(R.id.radioGroup1);
        radioGroup2 = (RadioGroup) view.findViewById(R.id.radioGroup2);
        radioGroup3 = (RadioGroup) view.findViewById(R.id.radioGroup3);
        radioGroup4 = (RadioGroup) view.findViewById(R.id.radioGroup4);
        radioGroup5 = (RadioGroup) view.findViewById(R.id.radioGroup5);
        radioGroup6 = (RadioGroup) view.findViewById(R.id.radioGroup6);
        radioGroup7 = (RadioGroup) view.findViewById(R.id.radioGroup7);
        radioGroup8 = (RadioGroup) view.findViewById(R.id.radioGroup8);

        txt_Weight = (EditText) view.findViewById(R.id.txtWeight);
        txt_Height = (EditText) view.findViewById(R.id.txtHeight);

        listResult[0] = (TextView) view.findViewById(R.id.txtIMC);
        listResult[1] = (TextView) view.findViewById(R.id.txtClasificacion);
        listResult[2] = (TextView) view.findViewById(R.id.txtOst);
        listResult[3] = (TextView) view.findViewById(R.id.txtCad);
        listResult[4] = (TextView) view.findViewById(R.id.txtData);
        animation_selection = new Animation_Selection();

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
        String sDataCalculate = "";
        selectedId[0] = radioGroup1.getCheckedRadioButtonId();
        selectedId[1] = radioGroup2.getCheckedRadioButtonId();
        selectedId[2] = radioGroup3.getCheckedRadioButtonId();
        selectedId[3] = radioGroup4.getCheckedRadioButtonId();
        selectedId[4] = radioGroup5.getCheckedRadioButtonId();
        selectedId[5] = radioGroup6.getCheckedRadioButtonId();
        selectedId[6] = radioGroup7.getCheckedRadioButtonId();
        selectedId[7] = radioGroup8.getCheckedRadioButtonId();
        if (selectedId[0] == -1 || validateText()) {
            General.messageToast(R.string.validateBox);
        } else {
            radioSex[0] = (RadioButton) view.findViewById(selectedId[0]);
            radioSex[1] = (RadioButton) view.findViewById(selectedId[1]);
            radioSex[2] = (RadioButton) view.findViewById(selectedId[2]);
            radioSex[3] = (RadioButton) view.findViewById(selectedId[3]);
            radioSex[4] = (RadioButton) view.findViewById(selectedId[4]);
            radioSex[5] = (RadioButton) view.findViewById(selectedId[5]);
            radioSex[6] = (RadioButton) view.findViewById(selectedId[6]);
            radioSex[7] = (RadioButton) view.findViewById(selectedId[7]);

            sDataCalculate = radioSex[0].getText() + "," + radioSex[1].getText() + "," + radioSex[2].getText() + "," + radioSex[3].getText() + "," + radioSex[4].getText() + "," + radioSex[5].getText() + "," + radioSex[6].getText() + "," + radioSex[7].getText();

            calculate(sDataCalculate);

        }


    }

    public void calculate(String data) {
        String sData[] = data.split(",");
        animation = null;
        listResult[4].clearAnimation();
        int iContSelection = 0;
        String sIMC = validateIMC();
        if (sIMC.equals("0")) {
            errorAplication();
        } else {
            String sSexo = "";
            String sContSelecction;
            String sC = "";
            String sO = "";

            for (int i = 0; i < sData.length; i++) {

                if (sData[i].equals(activity.getResources().getString(R.string.textRequerement11))) {

                    sSexo = "h";
                } else if (sData[i].equals(activity.getResources().getString(R.string.textRequerement12))) {

                    sSexo = "m";
                } else {
                    if (!sData[i].equals(activity.getResources().getString(R.string.no))) {
                        iContSelection++;

                    }
                }

            }
            if (iContSelection == 7) {

                iContSelection = 6;
            }
            if (iContSelection > 0 && iContSelection < 6) {

                animation = animation_selection.Animation_selec(6);
                listResult[4].startAnimation(animation);
                listResult[4].setVisibility(View.VISIBLE);
            } else {


                listResult[4].setVisibility(View.GONE);

            }

            sContSelecction = String.valueOf(iContSelection);
            sC = "c_" + sSexo + "_" + sYear;
            sO = "o_" + sSexo + "_" + sYear;

            String sdata = "";
            try {
                animation = animation_selection.Animation_selec(6);
                if (sSexo.equals("h")) {
                    sdata = xml.getEventsFromAnXMLFile(sIMC, sContSelecction, sO, idXML[1]).replace(sIMC, "");
                    if (sdata.indexOf(sO) != -1) {
                        sdata = sdata.replace(sO, "");
                    }
                    listResult[2].startAnimation(animation);
                    listResult[2].setText(sdata);
                    sdata = xml.getEventsFromAnXMLFile(sIMC, sContSelecction, sC, idXML[0]).replace(sIMC, "");
                    if (sdata.indexOf(sC) != -1) {
                        sdata = sdata.replace(sC, "");
                    }
                    listResult[3].startAnimation(animation);
                    listResult[3].setText(sdata);

                } else {
                    sdata = xml.getEventsFromAnXMLFile(sIMC, sContSelecction, sO, idXML[3]).replace(sIMC, "");
                    if (sdata.indexOf(sO) != -1) {
                        sdata = sdata.replace(sO, "");
                    }
                    listResult[2].startAnimation(animation);
                    listResult[2].setText(sdata);
                    sdata = xml.getEventsFromAnXMLFile(sIMC, sContSelecction, sC, idXML[2]).replace(sIMC, "");
                    if (sdata.indexOf(sC) != -1) {
                        sdata = sdata.replace(sC, "");
                    }
                    listResult[3].startAnimation(animation);
                    listResult[3].setText(sdata);

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }

    }


    public boolean validateText() {

        if ((txt_Weight.getText().equals("") || txt_Weight.getText().length() == 0) || (txt_Height.getText().equals("") || txt_Height.getText().length() == 0)) {

            return true;
        }

        return false;


    }

    public String validateIMC() {
        String IMC = "";
        animation = null;
        try {
            animation = animation_selection.Animation_selec(6);
            double dWeight = Double.parseDouble(String.valueOf(txt_Weight.getText()));
            double dHeight = Double.parseDouble(String.valueOf(txt_Height.getText()));
            if (dWeight == 0.0 || dHeight == 0.0) {

                IMC = "0";
            } else {

                dHeight = dHeight / 100;
                double dIMC = dWeight / (dHeight * dHeight);
                IMC = "" + dIMC;
                listResult[0].startAnimation(animation);
                listResult[0].setText(IMC.substring(0, 4));
                listResult[1].startAnimation(animation);
                if (dIMC <= 18) {

                    listResult[1].setText(activity.getResources().getString(R.string.menssageCalculater));
                } else if (dIMC > 18 && dIMC < 25) {

                    listResult[1].setText(activity.getResources().getString(R.string.menssageCalculater1));
                } else if (dIMC >= 25 && dIMC < 30) {

                    listResult[1].setText(activity.getResources().getString(R.string.menssageCalculater2));
                } else if (dIMC >= 30 && dIMC < 35) {
                    listResult[1].setText(activity.getResources().getString(R.string.menssageCalculater3));
                } else if (dIMC >= 35 && dIMC < 40) {
                    listResult[1].setText(activity.getResources().getString(R.string.menssageCalculater4));
                } else {
                    listResult[1].setText(activity.getResources().getString(R.string.menssageCalculater5));

                }
                long tmp = Math.round(dIMC);
                if (dIMC <= 15) {

                    IMC = "IMC_15";
                } else if (dIMC > 15 && dIMC <= 20) {

                    IMC = "IMC_20";
                } else if (dIMC > 20 && dIMC <= 25) {

                    IMC = "IMC_25";
                } else if (dIMC > 25 && dIMC <= 30) {

                    IMC = "IMC_30";
                } else if (dIMC > 30 && dIMC <= 35) {

                    IMC = "IMC_35";
                } else if (dIMC > 35 && dIMC <= 40) {

                    IMC = "IMC_40";
                } else {

                    IMC = "IMC_45";

                }

            }
        } catch (Exception e) {


            IMC = "0";
        }
        return IMC;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        try {
            idXML = new int[4];
            switch (position) {
                case 0:
                    sYear = "50";
                    idXML[0] = R.xml.c_h_50;
                    idXML[1] = R.xml.o_h_50;
                    idXML[2] = R.xml.c_m_50;
                    idXML[3] = R.xml.o_m_50;

                    break;
                case 1:
                    sYear = "55";
                    idXML[0] = R.xml.c_h_55;
                    idXML[1] = R.xml.o_h_55;
                    idXML[2] = R.xml.c_m_55;
                    idXML[3] = R.xml.o_m_55;

                    break;
                case 2:
                    sYear = "60";
                    idXML[0] = R.xml.c_h_60;
                    idXML[1] = R.xml.o_h_60;
                    idXML[2] = R.xml.c_m_60;
                    idXML[3] = R.xml.o_m_60;
                    break;
                case 3:
                    sYear = "65";
                    idXML[0] = R.xml.c_h_65;
                    idXML[1] = R.xml.o_h_65;
                    idXML[2] = R.xml.c_m_65;
                    idXML[3] = R.xml.o_m_65;
                    break;

                case 4:
                    sYear = "70";
                    idXML[0] = R.xml.c_h_70;
                    idXML[1] = R.xml.o_h_70;
                    idXML[2] = R.xml.c_m_70;
                    idXML[3] = R.xml.o_m_70;
                    break;
                case 5:
                    sYear = "75";
                    idXML[0] = R.xml.c_h_75;
                    idXML[1] = R.xml.o_h_75;
                    idXML[2] = R.xml.c_m_75;
                    idXML[3] = R.xml.o_m_75;
                    break;
                case 6:
                    sYear = "80";
                    idXML[0] = R.xml.c_h_80;
                    idXML[1] = R.xml.o_h_80;
                    idXML[2] = R.xml.c_m_80;
                    idXML[3] = R.xml.o_m_80;
                    break;
                case 7:
                    sYear = "85";
                    idXML[0] = R.xml.c_h_85;
                    idXML[1] = R.xml.o_h_85;
                    idXML[2] = R.xml.c_m_85;
                    idXML[3] = R.xml.o_m_85;
                    break;
                case 8:
                    sYear = "90";
                    idXML[0] = R.xml.c_h_90;
                    idXML[1] = R.xml.o_h_90;
                    idXML[2] = R.xml.c_m_90;
                    idXML[3] = R.xml.o_m_90;
                    break;


            }
        } catch (Exception e) {
            errorAplication ();

        }

    }
    public void errorAplication (){
        listResult[4].setVisibility(View.GONE);
        General.messageToast(R.string.error);
        General.messageToast(R.string.validateBox1);
        for(int i=0;i<listResult.length-1;i++){

            listResult[i].setText("");
        }



    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
