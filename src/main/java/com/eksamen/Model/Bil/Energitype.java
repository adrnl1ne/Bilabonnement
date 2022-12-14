package com.eksamen.Model.Bil;

public enum Energitype {
    DIESEL(1),
    ELEKTRISK(2),
    BENZIN(3);

    private final int Id;

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
            case 1 -> {
                return Energitype.DIESEL;
            }
            case 2 -> {
                return Energitype.ELEKTRISK;
            }
            case 3 -> {
                return Energitype.BENZIN;
            }
            default -> {
                System.err.println("Det var ikke muligt at få en enum Energitype med ID'et: " + EnergiType_ID);
                throw new RuntimeException();
            }
        }
    }
    //Jakob
    public int getInt() {
        return switch (this) {
            case DIESEL -> 1;
            case ELEKTRISK -> 2;
            case BENZIN -> 3;
            };
    }
    //Marcus
    public String displayEnergiType() {
        switch (this.getId()) {
            case 1 -> {
                return "Diesel";
            }
            case 2 -> {
                return "Elektrisk";
            }
            case 3 -> {
                return "Benzin";
            }
            default -> {
                System.err.println("Det var ikke muligt at display en Energitype, ud efter ID'et: " + this.getId());
                throw new RuntimeException();
            }
        }
    }
}
