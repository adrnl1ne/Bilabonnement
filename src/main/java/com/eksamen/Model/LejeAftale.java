package com.eksamen.Model;

import java.time.LocalDate;

public class LejeAftale {
    private Kunde kunde;
    private Abonnement abonnement;
    private int lejeAftale_ID;
    private String nummerPlade;
    private Bil bil;
    private double prisPrMåned;
    private double KørselDistanceInden;
    private int abonnementLængde;
    private LocalDate startDato;
    private LocalDate afleveringsDato;
    private Skaderapport skaderapport;

    private LeveringsType Type;
    private String Leveringsadresse;
    private String Afleveringsadress;
    private double TransportTillæg;


    public LejeAftale(int lejeAftale_ID) {
        this.lejeAftale_ID = lejeAftale_ID;
    }

    public double calculateTotalPrice() {
        double sum = getPrisPrMåned() * getAbonnementLængde();
        return sum;
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

    public double getPrisPrMåned() {
        return prisPrMåned;
    }

    public void setPrisPrMåned(double prisPrMåned) {
        this.prisPrMåned = prisPrMåned;
    }

    public int getAbonnementLængde() {
        return abonnementLængde;
    }

    public void setAbonnementLængde(int abonnementLængde) {
        this.abonnementLængde = abonnementLængde;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public void setStartDato(LocalDate startDato) {
        this.startDato = startDato;
    }

    public LocalDate getAfleveringsDato() {
        if (afleveringsDato.isEqual(getDefault())) {
            return afleveringsDato;
        } else {
            afleveringsDato = LocalDate.now();
        }
        return afleveringsDato;
    }

    public LocalDate getDefault() {
        afleveringsDato = getStartDato().plusMonths(getAbonnementLængde());
        return afleveringsDato;
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

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
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
                ", abonnement=" + abonnement +
                ", lejeAftale_ID=" + lejeAftale_ID +
                ", nummerPlade='" + nummerPlade + '\'' +
                ", bil=" + bil +
                ", prisPrMåned=" + prisPrMåned +
                ", KørselDistanceInden=" + KørselDistanceInden +
                ", abonnementLængde=" + abonnementLængde +
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
