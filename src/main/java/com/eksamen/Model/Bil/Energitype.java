package com.eksamen.Model.Bil;

public enum Energitype {
    DIESEL(1),
    ELEKTRISK(2),
    BENZIN(3);

    private int Id;

    Energitype(int id){
        Id = id;
    }

    // Getter
    public int getId() {
        return Id;
    }



    // Custom made Metoder

    //Jakob
    public static Energitype getEnum(int EnergiType_ID) {
        switch (EnergiType_ID) {
            case 1:
                return Energitype.DIESEL;
            case 2:
                return Energitype.ELEKTRISK;
            case 3:
                return Energitype.BENZIN;
            default:
                System.err.println("Det var ikke muligt at få en enum Energitype med ID'et: " + EnergiType_ID);
                throw new RuntimeException();
        }
    }
    public int getInt() {
        switch (this) {
            case DIESEL:
                return 1;
            case ELEKTRISK:
                return 2;
            case BENZIN:
                return 3;
            default:
                throw new RuntimeException("Fejl i energitype");
        }
    }

    public String displayEnergiType() {
        switch (this.getId()) {
            case 1:
                return "Diesel";
            case 2:
                return "Elektrisk";
            case 3:
                return "Benzin";
            default:
                System.err.println("Det var ikke muligt at display en Energitype, ud efter ID'et: " + this.getId());
                throw new RuntimeException();
        }
    }
}
