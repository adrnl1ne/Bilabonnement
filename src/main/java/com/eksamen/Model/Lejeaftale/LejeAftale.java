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
    private double korselDistanceInden;

    private Skaderapport skaderapport;

    private String leveringsadresse;
    private String afleveringsadresse;
    private double transportTillaeg;


    // Constructors
    public LejeAftale(int lejeAftale_ID) {
        this.lejeAftale_ID = lejeAftale_ID;
    }

    public LejeAftale(Bil bil, Kunde kunde) {
        this.bil = bil;
        this.kunde = kunde;
        this.korselDistanceInden = bil.getKmKort();
    }


    // Custom made Metoder

    // Marcus
    public double calculatePrice() {
        int abnmtLængde = abonnementLejeaftale.getAbonnementLaengde();
        double prisPrMåned = abonnementLejeaftale.getPricePrMonth();
        double farveprisPrMåned = abonnementLejeaftale.getExtraColorPrice();
        double udbetaling = abonnementLejeaftale.getUdbetaling();
        double sum = (prisPrMåned + farveprisPrMåned) * abnmtLængde + udbetaling;
        return sum;
    }

    //Marcus
    public String displayCalculatedPrice() {
        return this.calculatePrice() + " kr. over " + abonnementLejeaftale.displayAbonnementLaengde();
    }


    // Jakob
    // Udregner den sidste date en udlejet bil skal afleveres tilbage, hvor en måned er 30 dage,
    //idet at Bilabbonnement A/S benytter sig af et 30 dages system
    public LocalDate calcAfleveringsDato() {
        return getStartDato().plusDays(abonnementLejeaftale.getAbonnementLaengde() * 30L);
    }

    // Jakob
    // Udregner den sidste date en udlejet bil skal afleveres tilbage, ud fra månedssystem
    /*public LocalDate calcAfleveringsDato() {
        return getStartDato().plusMonths(abonnementLejeaftale.getAbonnementLaengde());
    }*/
    //Jakob
    public boolean forSenAflevering() {

        if (LocalDate.now().isAfter(calcAfleveringsDato())) {
            return true;
        } else {
            return false;
        }
    }

    public String displayOmOverskredetLevering() {
        if (forSenAflevering()) {
            return "Lejeaftalen er overskredet";
        } else {
            return "";
        }
    }

    //Marcus
    public String displayTransportTillaeg() {
        return transportTillaeg + " kr.";
    }

    //Marcus
    public String displayKorselDistanceInden() {
        return korselDistanceInden + " kilometer";
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


    public double getKorselDistanceInden() {
        return korselDistanceInden;
    }

    public void setKorselDistanceInden(double korselDistanceInden) {
        this.korselDistanceInden = korselDistanceInden;
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

    public double getTransportTillaeg() {
        return transportTillaeg;
    }

    public void setTransportTillaeg(double transportTillaeg) {
        this.transportTillaeg = transportTillaeg;
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
                ", KørselDistanceInden=" + korselDistanceInden +
                ", skaderapport=" + skaderapport +
                ", Leveringsadresse='" + leveringsadresse + '\'' +
                ", Afleveringsadress='" + afleveringsadresse + '\'' +
                ", TransportTillæg=" + transportTillaeg +
                '}';
    }
}
