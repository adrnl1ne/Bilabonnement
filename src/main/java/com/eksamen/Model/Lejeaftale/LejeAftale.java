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
    private LocalDate afleveringsDato;
    private String nummerPlade;
    private AbonnementLejeaftale abonnementLejeaftale;

    private LeveringsType Type;
    private double KørselDistanceInden;

    private Skaderapport skaderapport;

    private String Leveringsadresse;
    private String Afleveringsadress;
    private double TransportTillæg;

    public LejeAftale() {
    }

    public LejeAftale(int lejeAftale_ID) {
        this.lejeAftale_ID = lejeAftale_ID;
    }



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

    public LocalDate getDefaultAfleveringsdato() {
        return getStartDato().plusMonths(abonnementLejeaftale.getAbonnementLængde());

    }

    public void setAfleveringsDato(LocalDate afleveringsDato) {
        this.afleveringsDato = afleveringsDato;
    }

    public double getKørselDistanceInden() {
        return KørselDistanceInden;
    }

    public void setKørselDistanceInden(double kørselDistanceInden) {
        KørselDistanceInden = kørselDistanceInden;
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
        return Leveringsadresse;
    }

    public void setLeveringsadresse(String leveringsadresse) {
        Leveringsadresse = leveringsadresse;
    }

    public String getAfleveringsadress() {
        return Afleveringsadress;
    }

    public void setAfleveringsadress(String afleveringsadress) {
        Afleveringsadress = afleveringsadress;
    }

    public double getTransportTillæg() {
        return TransportTillæg;
    }

    public void setTransportTillæg(double transportTillæg) {
        TransportTillæg = transportTillæg;
    }


    @Override
    public String toString() {
        return "LejeAftale{" +
                "kunde=" + kunde +
                ", abonnement=" + abonnementLejeaftale +
                ", lejeAftale_ID=" + lejeAftale_ID +
                ", nummerPlade='" + nummerPlade + '\'' +
                ", bil=" + bil +
                ", KørselDistanceInden=" + KørselDistanceInden +
                ", startDato=" + startDato +
                ", afleveringsDato=" + afleveringsDato +
                ", skaderapport=" + skaderapport +
                ", Type=" + Type +
                ", Leveringsadresse='" + Leveringsadresse + '\'' +
                ", Afleveringsadress='" + Afleveringsadress + '\'' +
                ", TransportTillæg=" + TransportTillæg +
                '}';
    }
}
