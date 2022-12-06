package com.eksamen.Model;

import java.util.List;

public class Bil {

    private String Stelnummer;
    private String Bilmodel;
    private int Biltilstand;
    //private List<String> Skaderapport;


    public Bil(String stelnummer, String bilmodel, int biltilstand/*, List<String> skaderapport*/) {
        Stelnummer = stelnummer;

        Bilmodel = bilmodel;
        Biltilstand = biltilstand;
        //Skaderapport = skaderapport;
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



    public String getBilmodel() {
        return Bilmodel;
    }

    public void setBilmodel(String bilmodel) {
        Bilmodel = bilmodel;
    }

}
