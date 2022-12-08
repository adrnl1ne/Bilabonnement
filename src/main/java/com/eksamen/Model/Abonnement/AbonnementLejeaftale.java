package com.eksamen.Model.Abonnement;

public class AbonnementLejeaftale {

    private int lejeaftale_ID;
    private boolean isUnlimited;
    private int kmprMd;
    private int AbonnementLængde;
    private double AfleveringPrice;
    private double Priceprmonth;
    private double Udbetaling;
    private double XtraColorprice;
    private double PriceForOverDrive;

    public AbonnementLejeaftale() {
    }

    public AbonnementLejeaftale(int lejeaftale_ID) {
        this.lejeaftale_ID = lejeaftale_ID;
    }

    // getters, setters and tostring.

    public boolean isUnlimited() {
        return isUnlimited;
    }

    public void setUnlimited(boolean unlimited) {
        isUnlimited = unlimited;
    }

    public int getKmprMd() {
        return kmprMd;
    }

    public void setKmprMd(int kmprMd) {
        this.kmprMd = kmprMd;
    }

    public int getAbonnementLængde() {
        return AbonnementLængde;
    }

    public void setAbonnementLængde(int abonnementLængde) {
        AbonnementLængde = abonnementLængde;
    }

    public double getAfleveringPrice() {
        return AfleveringPrice;
    }

    public void setAfleveringPrice(double afleveringPrice) {
        AfleveringPrice = afleveringPrice;
    }

    public double getPriceprmonth() {
        return Priceprmonth;
    }

    public void setPriceprmonth(double priceprmonth) {
        Priceprmonth = priceprmonth;
    }

    public double getUdbetaling() {
        return Udbetaling;
    }

    public void setUdbetaling(double udbetaling) {
        Udbetaling = udbetaling;
    }

    public double getXtraColorprice() {
        return XtraColorprice;
    }

    public void setXtraColorprice(double xtraColorprice) {
        XtraColorprice = xtraColorprice;
    }

    public double getPriceForOverDrive() {
        return PriceForOverDrive;
    }

    public void setPriceForOverDrive(double priceForOverDrive) {
        PriceForOverDrive = priceForOverDrive;
    }


    @Override
    public String toString() {
        return "AbonnementLejeaftale{" +
                "lejeaftale_ID=" + lejeaftale_ID +
                ", isUnlimited=" + isUnlimited +
                ", kmprMd=" + kmprMd +
                ", AbonnementLængde=" + AbonnementLængde +
                ", AfleveringPrice=" + AfleveringPrice +
                ", Priceprmonth=" + Priceprmonth +
                ", Udbetaling=" + Udbetaling +
                ", XtraColorprice=" + XtraColorprice +
                ", PriceForOverDrive=" + PriceForOverDrive +
                '}';
    }

    public int getLejeaftale_ID() {
        return lejeaftale_ID;
    }

    public void setLejeaftale_ID(int lejeaftale_ID) {
        this.lejeaftale_ID = lejeaftale_ID;
    }
}
