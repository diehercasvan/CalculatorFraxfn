package com.edibca.fraxfnGTRD;

import android.app.Activity;

import com.edibca.fraxfn.R;

/**
 * Created by DIEGO CASALLAS on 28/09/2015.
 */
public class GenerateData {
    private String [][] sData;
    private String [] sArray;
    private String [] sArrayNumber;
    public  GenerateData(Activity activity) {
        sArray = activity.getResources().getStringArray(R.array.foodGroup);
        sArrayNumber=activity.getResources().getStringArray(R.array.numberGroup);
        sData = new String[sArray.length][2];
        for (int i = 0; i < sData.length; i++) {

            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    sData[i][j] = sArray[i];
                } else {
                    sData[i][j] = sArrayNumber[i];
                }
            }

        }
    }
        public  String searchArray (String data) {

            int i=0;
            boolean validate=true;
            String sReturnData="";
            while (validate) {

                if(sData[i][0].equals(data)){

                    validate=false;
                    sReturnData=sData[i][1];
                }
                i++;
            }

            return sReturnData;

    }
}
