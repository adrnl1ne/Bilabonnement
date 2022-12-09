package com.eksamen.Model.Kunde;

import com.eksamen.Model.Lejeaftale.LejeAftale;

import java.util.List;

public class Kunde {

    private String CPR;
    private Kontaktinfo info;
    private String regNum;
    private String kontoNum;
    private List<LejeAftale> lejeaftaler;

    // Constructors
    public Kunde(Kontaktinfo kontaktinfo, String CPR) {
        info = kontaktinfo;
        this.CPR = CPR;
    }

    public Kunde (String CPR) {
        this.CPR = CPR;
    }



    // Custom made Metoder

    public String displayNavn() {
        return info.getFirstName() + ' ' + info.getLastName();
    }




    // Getters, Setters and toString()

    public List<LejeAftale> getLejeaftaler() {
        return lejeaftaler;
    }

    public void setLejeaftaler(List<LejeAftale> lejeaftaler) {
        this.lejeaftaler = lejeaftaler;
    }

    public Kontaktinfo getInfo() {
        return info;
    }

    public void setKontaktInfo(Kontaktinfo nyestelinfo) {
        info = nyestelinfo;
    }

    public String getCPR() {
        return CPR;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public String getKontoNum() {
        return kontoNum;
    }

    public void setKontoNum(String kontoNum) {
        this.kontoNum = kontoNum;
    }

    @Override
    public String toString() {
        return "Kunde{" +
                "lejeaftaler=" + lejeaftaler +
                ", info=" + info +
                ", cprnumber='" + CPR + '\'' +
                ", RegNum='" + regNum + '\'' +
                ", KontoNum='" + kontoNum + '\'' +
                '}';
    }
}
