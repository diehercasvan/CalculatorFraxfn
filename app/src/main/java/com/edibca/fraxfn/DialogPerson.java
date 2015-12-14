package com.edibca.fraxfn;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import class_fraxfn.General;

/**
 * Created by DIEGO CASALLAS on 30/09/2015.
 */

public class DialogPerson extends DialogFragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private RadioGroup groupSeleccion;
    private View view;
    private TextView textData;
    public String sData;
    private Dialog dialog;
    public MyCallback myCallback;




    public void doWork(int data) {
        // Check the predicate, which is set elsewhere.


        // Signal the even by invoking the interface's method.
        myCallback.onArticleSelected(data, sData);

        //...
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.seleccition, container, false);

        groupSeleccion = (RadioGroup) view.findViewById(R.id.groupSelecction);
        groupSeleccion.setOnCheckedChangeListener(this);
        textData = (TextView) view.findViewById(R.id.textData);
        textData.setText(sData);


        return view;
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("Seleccion de porciones");
        return dialog;
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int childCount = group.getChildCount();
        String ans = "";
        int data = 0;
        for (int x = 0; x < childCount; x++) {

            RadioButton btn = (RadioButton) group.getChildAt(x);

            if (btn.getId() == checkedId) {

                ans = btn.getText().toString();
                //Toast.makeText(activity, "Grupo :" + ans, Toast.LENGTH_LONG).show();
                if (ans.equals("Una  porción ")) {
                    data = 1;
                } else {
                    data = 2;
                }

                dialog.dismiss();

            }

        }

        //Toast.makeText(getActivity(), "Grupo seleccionado :" + ans, Toast.LENGTH_LONG).show();
        doWork(data);
    }
}
