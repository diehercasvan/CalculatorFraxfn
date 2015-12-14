package com.edibca.fraxfn;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import class_fraxfn.General;


/**
 * Created by DIEGO CASALLAS on 11/09/2015.
 */
public class Requirement_calculator extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemClickListener,MyCallback {
    private View view;
    private Activity activity;
    private Context context;

    private RadioGroup groupSex;
    private RadioGroup groupPregnancy;
    private Spinner spinner;
    private Spinner spinnerGroup;
    private Button btn_calculater;
    private int selectedId[] = new int[2];
    private TextView txt_Calcium;
    private TextView txt_Vitamin;
    private int iCalcium = 0;
    private boolean bValidateSex;
    private String sCalcium = "";
    private String sVitamin = "";
    private TextView txt_info_pregnancy;
    private TextView txt_CalciumData;
    private ProgressBar progressBar;
    private TextView txt_InfoProgressBar;
    private String[] sDescription;
    private TableLayout table_GroupFood;
    private TableRow tableRow;
    private GenerateData generateData;
    private DialogPerson dialogPerson;
    private boolean bValidateLoad = false;
    private double dTotal;
    private int portion = 0;
    private int dataValue = 0;
    private boolean bValidate = true;
    private boolean bValidateTable = true;
    private boolean validateCancel = true;

    private String sDateTextTotal="0";

    public Requirement_calculator(){

        context=General.CONTEXT;
        activity=General.ACTIVITY;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.requirement_calculator,container,false);

        loadPage();

        return view;

    }

    public void loadPage() {
        bValidateSex = false;
        btn_calculater = (Button) view.findViewById(R.id.btnCalculater);
        btn_calculater.setOnClickListener(this);
        groupSex = (RadioGroup) view.findViewById(R.id.radioSex);
        groupSex.setOnCheckedChangeListener(this);
        groupPregnancy = (RadioGroup) view.findViewById(R.id.radioPregnancy);
        txt_info_pregnancy = (TextView) view.findViewById(R.id.txtInfoPregnancy);
        txt_Calcium = (TextView) view.findViewById(R.id.txtCalcium);
        txt_Vitamin = (TextView) view.findViewById(R.id.txtVitamin);

        spinnerGroup = (Spinner) view.findViewById(R.id.spinGroup);

        spinner = (Spinner) view.findViewById(R.id.spinner);
        loadSppiner(0);


        txt_Calcium = (TextView) view.findViewById(R.id.txtCalcium);
        txt_Vitamin = (TextView) view.findViewById(R.id.txtVitamin);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        txt_CalciumData = (TextView) view.findViewById(R.id.txtCalciumData);
        txt_InfoProgressBar = (TextView) view.findViewById(R.id.txtInfoProgressBar);


        sDescription = getResources().getStringArray(R.array.foodGroup);

        table_GroupFood = (TableLayout) view.findViewById(R.id.tableGroupFood);

        generateData = new GenerateData(getActivity());


    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void viewDialog(String data) {
        if (bValidateTable) {
            dialogPerson = new DialogPerson();
            dialogPerson.myCallback=this;
            dialogPerson.sData=data;
            FragmentManager fragmentManager = getFragmentManager();
            dialogPerson.show(fragmentManager, "Calculadora");
        }
    }

    public void createRow(final String data, int position) {

        try {

            if (bValidateTable) {
                validateCancel = true;
                tableRow = new TableRow(getActivity());
                tableRow.setPadding(5, 5, 5, 5);
                tableRow.setGravity(Gravity.CENTER);
                final TextView txtData = new TextView(getActivity());
                txtData.setText(data);
                txtData.setWidth(450);
                txtData.setPadding(8, 8, 8, 8);
                txtData.setGravity(Gravity.LEFT);
                txtData.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));


                final TextView txtPortion = new TextView(getActivity());
                txtPortion.setText("Porción :" + position);
                txtPortion.setWidth(150);
                txtPortion.setPadding(8, 8, 8, 8);
                txtPortion.setBackgroundColor(getResources().getColor(R.color.page));
                txtPortion.setGravity(Gravity.CENTER);
                txtPortion.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));


                final ImageView imageView = new ImageView(getActivity());

                imageView.setImageResource(R.drawable.close32);
                imageView.setPadding(8, 8, 8, 8);


                imageView.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        final TableRow t = (TableRow) v.getParent();
                        final TextView firstTextView = (TextView) t.getChildAt(0);
                        final TextView firstTextPortion = (TextView) t.getChildAt(1);
                        final String firstText = firstTextView.getText().toString();
                        final String firstPortion = firstTextPortion.getText().toString();
                        final TableRow parent = (TableRow) v.getParent();

                        //Toast.makeText(getApplication(), "Selecciono" + generateData.searchArray(firstText), Toast.LENGTH_LONG).show();
                        //Toast.makeText(getApplication(), "Selecciono" + firstPortion.replace("Porsion :","").trim(), Toast.LENGTH_LONG).show();
                        dataValue = Integer.parseInt(generateData.searchArray(firstText)) * -1;
                        portion = Integer.parseInt(firstPortion.replace("Porción :", "").trim());
                        bValidate = true;
                        calculater();
                        table_GroupFood.removeView(parent);
                        validateCancel = false;

                    }
                });
                if (validateCancel) {
                    tableRow.addView(txtData);
                    tableRow.addView(txtPortion);
                    tableRow.addView(imageView);

                    table_GroupFood.addView(tableRow);
                    portion = position;
                    dataValue = Integer.parseInt(generateData.searchArray(data));
                    bValidate = true;
                    calculater();
                }
            }
        } catch (Exception e) {

            Toast.makeText(getActivity(), "Se ha presentado un inconveniente intente de nuevo ", Toast.LENGTH_LONG).show();
        }


    }


    public void loadSppiner(int data) {
        try {
            if (data == 0) {
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.age, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                spinner.setOnItemSelectedListener(this);
            } else {
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.foodGroup, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerGroup.setAdapter(adapter);
                spinnerGroup.setOnItemSelectedListener(this);


            }
        } catch (Exception e) {

            Toast.makeText(getActivity(), "Se ha presentado un inconveniente intente de nuevo ", Toast.LENGTH_LONG).show();
        }


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

    public void calculater() {
        try {


            if (dataValue != 0) {
                double dCalculate = 0;

                if (bValidate) {

                    dTotal = dTotal + dataValue;
                }
                dataValue = dataValue * portion;

                dCalculate = (dTotal / iCalcium) * 100;
                DecimalFormat df = new DecimalFormat("#.##");

                String sDateText = "%" + df.format(dCalculate);
                sDateTextTotal=String.valueOf(dTotal).substring(0,String.valueOf(dTotal).length()-2);
                if(sDateTextTotal.length()>3){
                    sDateTextTotal=sDateTextTotal.substring(0,1)+"."+sDateTextTotal.substring(1,sDateTextTotal.length());
                }

                txt_CalciumData.setText(sDateTextTotal +" / "+sCalcium);

                int iProgress = (int) dCalculate;
                progressBar.setProgress(iProgress);
                txt_InfoProgressBar.setText(sDateText);
                if (iProgress <= 100) {


                    bValidateTable = true;

                } else {
                    txt_InfoProgressBar.setText("%100");
                    bValidateTable = false;
                    Toast.makeText(getActivity(), "Nivel de Calcio en  100%", Toast.LENGTH_LONG).show();
                }
            }
        } catch (Exception e) {

            Toast.makeText(getActivity(), "Se ha presentado un inconveniente intente de nuevo ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {

        selectedId[0] = groupSex.getCheckedRadioButtonId();


        if (selectedId[0] == -1) {
            Toast.makeText(getActivity(), "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
        } else {


            if (bValidateSex) {
                selectedId[1] = groupPregnancy.getCheckedRadioButtonId();

                if (selectedId[1] == -1) {
                    Toast.makeText(getActivity(), "Por favor escoja una opción ", Toast.LENGTH_SHORT).show();
                } else {

                    calculate();
                }

            } else {
                calculate();
            }


        }
        bValidateLoad = false;
        bValidate = false;
        calculater();


    }

    public void calculate() {

        txt_Calcium.setText(sCalcium);
        txt_CalciumData.setText(sDateTextTotal+" / "+sCalcium);
        txt_Vitamin.setText(sVitamin);
        loadSppiner(1);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sSelection = parent.getItemAtPosition(position).toString();

        if (sSelection.equals("1 a 3 años")) {

            sCalcium = "700 mg";
            sVitamin = "600";
            iCalcium = 700;
        } else if (sSelection.equals("4 a 8 años")) {
            sCalcium = "1.000 mg";
            sVitamin = "600 UI";
            iCalcium = 1000;

        } else if (sSelection.equals("9 a 13 años")) {
            sCalcium = "1.300 mg";
            sVitamin = "600 UI";
            iCalcium = 1300;

        } else if (sSelection.equals("14 a 18 años")) {
            sCalcium = "1.300 mg";
            sVitamin = "600 UI";
            iCalcium = 1300;

        } else if (sSelection.equals("19 a 50 años")) {
            sCalcium = "1.000 mg";
            sVitamin = "600 UI";
            iCalcium = 1000;

        } else if (sSelection.equals("51 a 70 años")) {
            sCalcium = "1.200 mg";
            sVitamin = "600 UI";
            iCalcium = 1200;

        } else if (sSelection.equals("Más de 71 años")) {
            sCalcium = "1.200 mg";
            sVitamin = "800 UI";
            iCalcium = 1200;

        } else {
            //Toast.makeText(this,"Esta es la seleccion"+position,Toast.LENGTH_LONG).show();

            ///createRow(sSelection);
            if (bValidateLoad) {
                viewDialog(sSelection);
            } else {
                bValidateLoad = true;
            }

        }


        // Toast.makeText(this, "Selecciono esto" + sSelection, Toast.LENGTH_LONG).show();


    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int childCount = group.getChildCount();

        for (int x = 0; x < childCount; x++) {

            RadioButton btn = (RadioButton) group.getChildAt(x);

            if (btn.getId() == checkedId) {

                btn_calculater.setEnabled(true);
                txt_Calcium.setText("0 mg");
                txt_Vitamin.setText("0 UI");
                txt_CalciumData.setText("0 mg");
                if (btn.getText().toString().equals("Hombre")) {
                    bValidateSex = false;
                    txt_info_pregnancy.setVisibility(View.GONE);
                    groupPregnancy.setVisibility(View.GONE);


                } else {

                    bValidateSex = true;
                    txt_info_pregnancy.setVisibility(View.VISIBLE);
                    groupPregnancy.setVisibility(View.VISIBLE);

                }


            }

        }

    }

    @Override
    public void onArticleSelected(int position, String sData) {
        //Toast.makeText(getApplication(), "Selecciono" + position + "Selecciono tipo" + sData, Toast.LENGTH_LONG).show();
        createRow(sData, position);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name = parent.getItemAtPosition(position).toString();
        //Toast.makeText(getApplication(), "Selecciono" + name, Toast.LENGTH_LONG).show();
    }


}
