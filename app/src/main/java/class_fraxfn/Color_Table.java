package class_fraxfn;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * Created by DIEGO  CASALLAS on 02/06/2015.
 */
public class Color_Table {

    private TableRow.LayoutParams layoutParams;
    private TableLayout tableLayout;
    private int MAX_ROWS=2;
    private Button BtnId;
    private Activity activity=General.ACTIVITY;
//Method for create table
    public  void   createTable(TableLayout table){
        tableLayout=table;

        layoutParams = new TableRow.LayoutParams(40,40);

        layoutParams.bottomMargin=5;
        layoutParams.leftMargin=5;
        addRow();




    }
//method for  create  row
    public void addRow(){

        String[]colors=new String[]{"#F00A0A","#0A25F0","#0AF016","#E5F00A","#F0790A","#000000","#0A9CF0","#090909","#808000","#800080","#00FFFF","#FF0000","#FF00FF"};
        TableRow fila=null;
        int cont=0;
        for(int i = 0;i<MAX_ROWS;i++){

            fila = new TableRow(activity);
            for(int j=0;j<3;j++){

                BtnId = new Button(activity);
                BtnId.setGravity(Gravity.CENTER_HORIZONTAL);
                BtnId.setBackgroundColor(Color.parseColor(colors[cont]));
                BtnId.setLayoutParams(layoutParams);
                BtnId.setId(cont);
                BtnId.setOnClickListener((View.OnClickListener) activity);
                fila.addView(BtnId);
                if(cont==2)
                {
                    cont = 2;

                }
                else if(cont==5)

                {
                    cont = 5;
                }

                cont++;
            }
            tableLayout.addView(fila);

        }

    }
}
