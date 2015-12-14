package com.edibca.fraxfn;
import android.app.Activity;
import android.app.Fragment;


/**
 * Created by DIEGO   CASALLAS on 02/06/2015.
 */
public class Container_fragment {
    private Fragment fragment;
    public String sSelection;
    private Activity activity;
    //Object
   /* private System_heart system_heart;
    private Heart heart;
    private Cardiac_cycle cardiac_cycle;
    private Risk_factors risk_factors;
    private Syndrome syndrome;
    private Angina_chest angina_chest;
    private Heart_attack heart_attack;
    private Invasive invasive;
    private Mechanism mechanism;
    private Arteriosclerosis arteriosclerosis;
    private Not_invasive not_invasive;
    private References references;*/
    private Bone bone;
    private Osteoporosis osteoporosis;
    private FactoresRiesgo factoresRiesgo;
    private Tratamiento tratamiento;
    private Calculadora  calculadora;
    private Requirement_calculator calculadoraRequerimiento;
    private References references;


    public Container_fragment() {

        sSelection = "";
        bone=new Bone();
        osteoporosis= new Osteoporosis();
        factoresRiesgo=new FactoresRiesgo();
        tratamiento=new Tratamiento();
        calculadora=new Calculadora();
        calculadoraRequerimiento= new Requirement_calculator();
        references=new References();

      /*  system_heart=new System_heart();
        heart=new Heart();
        cardiac_cycle=new Cardiac_cycle();
        risk_factors=new Risk_factors();
        syndrome=new Syndrome();
        angina_chest=new Angina_chest();
        heart_attack=new Heart_attack();
        invasive=new Invasive();
        mechanism=new Mechanism();
        arteriosclerosis=new Arteriosclerosis();
        not_invasive=new Not_invasive();
        references=new References();*/
    }

    public Fragment Load_fragment() {

        switch(sSelection)
        {

           case "anatomy":
               fragment=bone;
            break;
            /* case "anatomyOne":
                fragment=heart;
                break;
            case "anatomyTwo":
               fragment=cardiac_cycle;
                break;*/
            case "pathology":
                fragment=osteoporosis;
                break;
            case "pathologyOne":
                fragment=factoresRiesgo;
            break;
            case "pathologyTwo":
                fragment=tratamiento;
                break;
             /*case "pathologyThree":
                fragment=angina_chest;
                break;
            case "pathologyFour":
                fragment=heart_attack;
                break;
            */case "treatment":
              fragment=calculadora;
           break;
            case "treatmentOne":
                fragment=calculadoraRequerimiento;
                break;
            /*case "mechanism":
                fragment=mechanism;

                break;
            case "ipp":


                break;*/
            case "references":
                fragment=references;

                break;

        }




        return fragment;
    }


}
