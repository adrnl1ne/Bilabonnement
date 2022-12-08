package com.eksamen.Model.Kunde;

import com.eksamen.Model.Lejeaftale.LejeAftale;

import java.util.List;

public class Kunde {
    private List<LejeAftale> lejeaftaler;
    private Kontaktinfo info;
    private String cprnumber;
    private String RegNum;
    private String KontoNum;

    public Kunde(Kontaktinfo kontaktinfo, String CPR) {
        info = kontaktinfo;
        cprnumber = CPR;
    }

    public Kunde (String CPR) {
        this.cprnumber = CPR;
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

    public void setNyesteInfo(Kontaktinfo nyestelinfo) {
        info = nyestelinfo;
    }

    public String getCprnumber() {
        return cprnumber;
    }

    public void setCprnumber(String cprnumber) {
        this.cprnumber = cprnumber;
    }

    public String getRegNum() {
        return RegNum;
    }

    public void setRegNum(String regNum) {
        RegNum = regNum;
    }

    public String getKontoNum() {
        return KontoNum;
    }

    public void setKontoNum(String kontoNum) {
        KontoNum = kontoNum;
    }

    @Override
    public String toString() {
        return "Kunde{" +
                "RegNum=" + RegNum +
                ", KontoNum=" + KontoNum +
                '}';
    }
}
