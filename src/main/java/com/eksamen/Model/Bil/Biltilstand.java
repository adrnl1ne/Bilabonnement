package com.eksamen.Model.Bil;

public enum Biltilstand {

    KLAR(1),
    UDLEJET(2),
    CHECKUP(3),
    SKADET(4);
    private int Id;

    Biltilstand(int id) {
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public int getInt() {
        switch (this) {
            case KLAR:
                return 1;
            case UDLEJET:
                return 2;
            case CHECKUP:
                return 3;
            default:
                return 4;
        }
    }

    //Jakob
    public static Biltilstand getEnum(int Tilstands_ID) {
        switch (Tilstands_ID) {
            case 1:
                return Biltilstand.KLAR;
            case 2:
                return Biltilstand.UDLEJET;
            case 3:
                return Biltilstand.CHECKUP;
            default:
                return Biltilstand.SKADET;
        }
    }
}
