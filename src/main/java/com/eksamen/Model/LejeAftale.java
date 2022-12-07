package com.eksamen.Model;

import java.time.LocalDate;

public class LejeAftale {
    private int LejeAftale_ID;
    private Bil bil;
    private double PrisPrMåned;
    private int AbonnementLængde;
    private LocalDate StartDato;
    private LocalDate AfleveringsDato;


    public LejeAftale(int lejeAftale_ID, Bil bil, double abonnementPris, int abonnementLængde,
                      LocalDate startDato, LocalDate afleveringsDato) {
        LejeAftale_ID = lejeAftale_ID;
        this.bil = bil;
        PrisPrMåned = abonnementPris;
        AbonnementLængde = abonnementLængde;
        StartDato = startDato;
        AfleveringsDato = afleveringsDato;
    }

    public double calculateTotalPrice() {
        double sum = getPrisPrMåned() * getAbonnementLængde();
        return sum;
    }

    public int getLejeAftale_ID() {
        return LejeAftale_ID;
    }

    public void setLejeAftale_ID(int lejeAftale_ID) {
        LejeAftale_ID = lejeAftale_ID;
    }

    public Bil getBil() {
        return bil;
    }

    public void setBil(Bil bil) {
        this.bil = bil;
    }

    public double getPrisPrMåned() {
        return PrisPrMåned;
    }

    public void setPrisPrMåned(double prisPrMåned) {
        PrisPrMåned = prisPrMåned;
    }

    public int getAbonnementLængde() {
        return AbonnementLængde;
    }

    public void setAbonnementLængde(int abonnementLængde) {
        AbonnementLængde = abonnementLængde;
    }

    public LocalDate getStartDato() {
        return StartDato;
    }

    public void setStartDato(LocalDate startDato) {
        StartDato = startDato;
    }

    public LocalDate getAfleveringsDato() {
        AfleveringsDato = getStartDato().plusMonths(getAbonnementLængde());
        return AfleveringsDato;
    }

    public void setAfleveringsDato(LocalDate afleveringsDato) {
        AfleveringsDato = afleveringsDato;
    }
}
