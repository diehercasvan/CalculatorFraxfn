package com.edibca.fraxfnGTRD;
import android.app.Activity;
import android.app.Fragment;


/**
 * Created by DIEGO   CASALLAS on 02/06/2015.
 */
public class Container_fragment {
    private Fragment fragment;
    public String sSelection;
    //Object

    private Bone bone;
    private Osteoporosis osteoporosis;
    private FactoresRiesgo factoresRiesgo;
    private Tratamiento tratamiento;
    private Calculadora calculadora;
    private Requirement_calculator calculadoraRequerimiento;
    private References references;


    public Container_fragment() {

        sSelection = "";
        bone = new Bone();
        osteoporosis = new Osteoporosis();
        factoresRiesgo = new FactoresRiesgo();
        tratamiento = new Tratamiento();
        calculadora = new Calculadora();
        calculadoraRequerimiento = new Requirement_calculator();
        references = new References();

    }

    public Fragment Load_fragment() {

        switch (sSelection) {

            case "anatomy":
                fragment = bone;
                break;

            case "pathology":
                fragment = osteoporosis;
                break;
            case "pathologyOne":
                fragment = factoresRiesgo;
                break;
            case "pathologyTwo":
                fragment = tratamiento;
                break;

            case "treatment":
                fragment = calculadora;
                break;
            case "treatmentOne":
                fragment = calculadoraRequerimiento;
                break;

            case "references":
                fragment = references;

                break;

        }


        return fragment;
    }


}
