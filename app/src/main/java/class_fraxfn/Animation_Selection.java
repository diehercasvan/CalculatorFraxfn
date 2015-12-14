package class_fraxfn;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.edibca.fraxfn.R;

/**
 * Created by DIEGO CASALLAS  on 09/09/2015.
 */
public class Animation_Selection {
    private Animation animation;

    public Animation_Selection(){
        animation=null;

    }
    public Animation Animation_selec(int selection){

        switch(selection)
        {

            case 1:
                animation = AnimationUtils.loadAnimation(General.ACTIVITY, R.anim.frame_animation);
                break;
            case 2:
                animation = AnimationUtils.loadAnimation(General.ACTIVITY, R.anim.menu_animation_open);
                break;
            case 3:
                animation = AnimationUtils.loadAnimation(General.ACTIVITY, R.anim.menu_animation_close);
                break;
            case 4:
                animation = AnimationUtils.loadAnimation(General.ACTIVITY, R.anim.push_down_in);
                break;
            case 5:
                animation = AnimationUtils.loadAnimation(General.ACTIVITY, R.anim.push_down_out);
                break;
            case 6:
                animation = AnimationUtils.loadAnimation(General.ACTIVITY, R.anim.push_up_in);
                break;
            case 7:
                animation = AnimationUtils.loadAnimation(General.ACTIVITY, R.anim.push_up_out);
                break;
            case 8:
                animation = AnimationUtils.loadAnimation(General.ACTIVITY, R.anim.slide_in_left);
                break;
            case 9:
                animation = AnimationUtils.loadAnimation(General.ACTIVITY, R.anim.slide_in_right);
                break;
            case 10:
                animation = AnimationUtils.loadAnimation(General.ACTIVITY, R.anim.rotate);
            break;
            case 11:
                animation = AnimationUtils.loadAnimation(General.ACTIVITY, R.anim.fade_in);
                break;
            case 12:
                animation = AnimationUtils.loadAnimation(General.ACTIVITY, R.anim.fade_out);
                break;


        }

        return animation;
    }
}
