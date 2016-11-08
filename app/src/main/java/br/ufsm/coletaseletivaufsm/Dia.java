package br.ufsm.coletaseletivaufsm;

import android.app.Application;

import java.util.List;

/**
 * Criado por Emilio Hoffmann em 11/2016
 *
 */

public class Dia extends Application{

    private String Dias;
    private List<String> Locais;

    public String getDias() {
        return Dias;
    }

    public List<String> getLocais() {
        return Locais;
    }

    public void setDias(String dias) {
        Dias = dias;
    }


    public void setLocais(List<String> locais) {
        Locais = locais;
    }

    public Dia(String dias, List<String> locais) {
        Dias = dias;
        Locais = locais;
    }

    public Dia() {
    }
}
