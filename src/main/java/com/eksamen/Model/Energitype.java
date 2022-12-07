package com.eksamen.Model;

public enum Energitype {
    DIESEL(1),
    ELEKTRISK(2),
    BENZIN(3);

    private int Id;

    Energitype(int id){
        Id = id;
    }

    public int getId() {
        return Id;
    }

    //Jakob
    public static Energitype getEnum(int EnergiType_ID) {
        switch (EnergiType_ID) {
            case 1:
                return Energitype.DIESEL;
            case 2:
                return Energitype.ELEKTRISK;
            default:
                return Energitype.BENZIN;
        }
    }
    public int getInt() {
        switch (this) {
            case DIESEL:
                return 1;
            case ELEKTRISK:
                return 2;
            default:
                return 3;
        }
    }
}
