package com.eksamen.Model.Abonnement;

public class AbonnementLejeaftale {

    private int lejeaftale_ID;
    private boolean isUnlimited;
    private int kmPrMd;
    private int abonnementLaengde;
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

    public String displayPricePrMonth() {
        return pricePrMonth + " kr. per måned";
    }

    public String displayKmPrMd() {
        return kmPrMd + " km/måned";
    }

    public String displayAbonnementLaengde() {
        return abonnementLaengde + " måneder";
    }

    public String displayAfleveringsPrice() {
        return afleveringPrice + " kr./dag";
    }

    public String displayUdbetaling() {
        return udbetaling + " kr.";
    }

    public String displayExtraColorPrice() {
        return extraColorPrice + " kr./måned";
    }

    public String displayPriceForOverDrive() {
        return priceForOverDrive + " kr./km.";
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

    public int getAbonnementLaengde() {
        return abonnementLaengde;
    }

    public void setAbonnementLaengde(int abonnementLaengde) {
        this.abonnementLaengde = abonnementLaengde;
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
                ", AbonnementLængde=" + abonnementLaengde +
                ", AfleveringPrice=" + afleveringPrice +
                ", PricePrMonth=" + pricePrMonth +
                ", Udbetaling=" + udbetaling +
                ", ExtraColorPrice=" + extraColorPrice +
                ", PriceForOverDrive=" + priceForOverDrive +
                '}';
    }


}
