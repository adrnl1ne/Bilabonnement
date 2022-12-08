package com.eksamen.Model.Lejeaftale;

import com.eksamen.Model.Abonnement.AbonnementLejeaftale;
import com.eksamen.Model.Bil.Bil;
import com.eksamen.Model.Kunde.Kunde;
import com.eksamen.Model.Skader.Skaderapport;

import java.time.LocalDate;

public class LejeAftale {

    private int lejeAftale_ID;
    private Kunde kunde;
    private Bil bil;
    private LocalDate startDato;
    private String nummerPlade;
    private AbonnementLejeaftale abonnementLejeaftale;

    private LeveringsType Type;
    private double kørselDistanceInden;

    private Skaderapport skaderapport;

    private String leveringsadresse;
    private String afleveringsadresse;
    private double transportTillæg;


    // Constructors
    public LejeAftale(int lejeAftale_ID) {
        this.lejeAftale_ID = lejeAftale_ID;
    }

    public LejeAftale(Bil bil, Kunde kunde) {
        this.bil = bil;
        this.kunde = kunde;
    }


    // Custom made Metoder

    // Marcus
    public double calculatePrice() {
        int abnmtLængde = abonnementLejeaftale.getAbonnementLængde();
        double prisPrMåned = abonnementLejeaftale.getPricePrMonth();
        double farveprisPrMåned = abonnementLejeaftale.getExtraColorPrice();
        double udbetaling = abonnementLejeaftale.getUdbetaling();
        double sum = (prisPrMåned + farveprisPrMåned) * abnmtLængde + udbetaling;
        return sum;
    }


    // Jakob
    // Udregner den sidste date en udlejet bil skal afleveres tilbage
    public LocalDate calcAfleveringsDato() {
        return getStartDato().plusDays(abonnementLejeaftale.getAbonnementLængde() * 30L);
    }




    // Getters, Setters og toString

    public int getLejeAftale_ID() {
        return lejeAftale_ID;
    }

    public void setLejeAftale_ID(int lejeAftale_ID) {
        this.lejeAftale_ID = lejeAftale_ID;
    }

    public Bil getBil() {
        return bil;
    }

    public void setBil(Bil bil) {
        this.bil = bil;
    }


    public LocalDate getStartDato() {
        return startDato;
    }

    public void setStartDato(LocalDate startDato) {
        this.startDato = startDato;
    }



    public double getKørselDistanceInden() {
        return kørselDistanceInden;
    }

    public void setKørselDistanceInden(double kørselDistanceInden) {
        this.kørselDistanceInden = kørselDistanceInden;
    }

    public String getNummerPlade() {
        return nummerPlade;
    }

    public void setNummerPlade(String nummerPlade) {
        this.nummerPlade = nummerPlade;
    }

    public AbonnementLejeaftale getAbonnement() {
        return abonnementLejeaftale;
    }

    public void setAbonnement(AbonnementLejeaftale abonnementLejeaftale) {
        this.abonnementLejeaftale = abonnementLejeaftale;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public Skaderapport getSkaderapport() {
        return skaderapport;
    }

    public void setSkaderapport(Skaderapport skaderapport) {
        this.skaderapport = skaderapport;
    }

    public LeveringsType getType() {
        return Type;
    }

    public void setType(LeveringsType type) {
        Type = type;
    }

    public String getLeveringsadresse() {
        return leveringsadresse;
    }

    public void setLeveringsadresse(String leveringsadresse) {
        this.leveringsadresse = leveringsadresse;
    }

    public String getAfleveringsadresse() {
        return afleveringsadresse;
    }

    public void setAfleveringsadresse(String afleveringsadresse) {
        this.afleveringsadresse = afleveringsadresse;
    }

    public double getTransportTillæg() {
        return transportTillæg;
    }

    public void setTransportTillæg(double transportTillæg) {
        this.transportTillæg = transportTillæg;
    }

    @Override
    public String toString() {
        return "LejeAftale{" +
                "lejeAftale_ID=" + lejeAftale_ID +
                ", kunde=" + kunde +
                ", bil=" + bil +
                ", startDato=" + startDato +
                ", nummerPlade='" + nummerPlade + '\'' +
                ", abonnementLejeaftale=" + abonnementLejeaftale +
                ", Type=" + Type +
                ", KørselDistanceInden=" + kørselDistanceInden +
                ", skaderapport=" + skaderapport +
                ", Leveringsadresse='" + leveringsadresse + '\'' +
                ", Afleveringsadress='" + afleveringsadresse + '\'' +
                ", TransportTillæg=" + transportTillæg +
                '}';
    }
}
