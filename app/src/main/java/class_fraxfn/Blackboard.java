package class_fraxfn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by DIEGO  CASALLAS on 30/04/2015.
 */
public class Blackboard extends View {

    public String sColor;
    public  int  iWidth;
    private  float fX;
    private float fY;
    private String sAccion;
    private Path path;
    private boolean bValidateDown=false;

    public Blackboard(Context context){
        super(context);
        this.fX=50;
        this.fY=50;
        this.sAccion="accion";
        this.path=new Path();


    }

    public  void onDraw(Canvas canvas){
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);//Style  type  line
        paint.setStrokeWidth(iWidth);//Width
        paint.setColor(Color.parseColor(sColor));//Color line

        if(sAccion.equals("down") ){


                path.moveTo(fX,fY);


        }
        if(sAccion.equals("move")){
            if( bValidateDown){

                path.lineTo(fX, fY);
            }

        }
        canvas.drawPath(path, paint);//metodo  para pintar
        //Toast.makeText(getContext(),"X"+fX+" Y"+fY, Toast.LENGTH_LONG).show();



    }

    public  boolean onTouchEvent(MotionEvent e){

        fX=e.getX();
        fY=e.getY();

        int axion=e.getAction();
        if(axion== MotionEvent.ACTION_DOWN){

            sAccion="down";
            bValidateDown=true;
        }
        if(axion== MotionEvent.ACTION_MOVE){

            sAccion="move";

        }
        //Toast.makeText(getContext(),"Selecciono"+sAccion,Toast.LENGTH_LONG).show();


        invalidate();

        return true;
    }







}


