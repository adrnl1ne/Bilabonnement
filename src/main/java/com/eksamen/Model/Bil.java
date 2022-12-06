package com.eksamen.Model;

import java.util.List;

public class Bil {

    private String Stelnummer;
    private int Model_ID;
    private String Bilmodel;
    private int Biltilstand;
    private List<String> Skadeapport;


    public Bil(String stelnummer, int model_ID, String bilmodel, int biltilstand, List<String> skadeapport) {
        Stelnummer = stelnummer;
        Model_ID = model_ID;
        Bilmodel = bilmodel;
        Biltilstand = biltilstand;
        Skadeapport = skadeapport;
    }

    public int getBiltilstand() {
        return Biltilstand;
    }

    public void setBiltilstand(int biltilstand) {
        Biltilstand = biltilstand;
    }

    public String getStelnummer() {
        return Stelnummer;
    }

    public void setStelnummer(String stelnummer) {
        Stelnummer = stelnummer;
    }

    public int getModel_ID() {
        return Model_ID;
    }

    public void setModel_ID(int model_ID) {
        Model_ID = model_ID;
    }

    public String getBilmodel() {
        return Bilmodel;
    }

    public void setBilmodel(String bilmodel) {
        Bilmodel = bilmodel;
    }

    public List<String> getSkadeapport() {
        return Skadeapport;
    }

    public void setSkadeapport(List<String> skadeapport) {
        Skadeapport = skadeapport;
    }
}
