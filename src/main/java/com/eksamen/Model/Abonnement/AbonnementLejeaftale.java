package com.eksamen.Model.Abonnement;

public class AbonnementLejeaftale {

    private int lejeaftale_ID;
    private boolean isUnlimited;
    private int kmPrMd;
    private int abonnementLængde;
    private double afleveringPrice;
    private double pricePrMonth;
    private double udbetaling;
    private double extraColorPrice;
    private double priceForOverDrive;


    // Constructors
    public AbonnementLejeaftale() {
    }

    public AbonnementLejeaftale(int lejeaftale_ID) {
        this.lejeaftale_ID = lejeaftale_ID;
    }


    // Custom made Metoder
    public String displayAbonnementType() {
        if (isUnlimited) {
            return "Unlimited";
        } else {
            return "Limited";
        }
    }



    // getters, setters and toString.
    public int getLejeaftale_ID() {
        return lejeaftale_ID;
    }

    public void setLejeaftale_ID(int lejeaftale_ID) {
        this.lejeaftale_ID = lejeaftale_ID;
    }

    public boolean isUnlimited() {
        return isUnlimited;
    }

    public void setUnlimited(boolean unlimited) {
        isUnlimited = unlimited;
    }

    public int getKmPrMd() {
        return kmPrMd;
    }

    public void setKmPrMd(int kmPrMd) {
        this.kmPrMd = kmPrMd;
    }

    public int getAbonnementLængde() {
        return abonnementLængde;
    }

    public void setAbonnementLængde(int abonnementLængde) {
        this.abonnementLængde = abonnementLængde;
    }

    public double getAfleveringPrice() {
        return afleveringPrice;
    }

    public void setAfleveringPrice(double afleveringPrice) {
        this.afleveringPrice = afleveringPrice;
    }

    public double getPricePrMonth() {
        return pricePrMonth;
    }

    public void setPricePrMonth(double pricePrMonth) {
        this.pricePrMonth = pricePrMonth;
    }

    public double getUdbetaling() {
        return udbetaling;
    }

    public void setUdbetaling(double udbetaling) {
        this.udbetaling = udbetaling;
    }

    public double getExtraColorPrice() {
        return extraColorPrice;
    }

    public void setExtraColorPrice(double extraColorPrice) {
        this.extraColorPrice = extraColorPrice;
    }

    public double getPriceForOverDrive() {
        return priceForOverDrive;
    }

    public void setPriceForOverDrive(double priceForOverDrive) {
        this.priceForOverDrive = priceForOverDrive;
    }


    @Override
    public String toString() {
        return "AbonnementLejeaftale{" +
                "lejeaftale_ID=" + lejeaftale_ID +
                ", isUnlimited=" + isUnlimited +
                ", kmPrMd=" + kmPrMd +
                ", AbonnementLængde=" + abonnementLængde +
                ", AfleveringPrice=" + afleveringPrice +
                ", PricePrMonth=" + pricePrMonth +
                ", Udbetaling=" + udbetaling +
                ", ExtraColorPrice=" + extraColorPrice +
                ", PriceForOverDrive=" + priceForOverDrive +
                '}';
    }


}
