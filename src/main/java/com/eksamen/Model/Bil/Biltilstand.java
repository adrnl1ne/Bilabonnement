package com.eksamen.Model.Bil;

public enum Biltilstand {

    KLAR(1),
    UDLEJET(2),
    CHECKUP(3),
    SKADET(4);
    private final int Id;


    private Biltilstand (int Id) {
        this.Id = Id;
    }


    public int getId() {
        return Id;
    }

    /*Biltilstand(int id) {
        Id = id;
    }*/



    public int getEnumToInt() {
        Biltilstand biltilstand;
        switch (this) {
            case KLAR:
                biltilstand = Biltilstand.KLAR;
                return biltilstand.getId();
            case UDLEJET:
                biltilstand = Biltilstand.UDLEJET;
                return biltilstand.getId();
            case CHECKUP:
                biltilstand = Biltilstand.CHECKUP;
                return biltilstand.getId();
            case SKADET:
                biltilstand = Biltilstand.SKADET;
                return biltilstand.getId();
            default:
                throw new RuntimeException("Fejl i biltilstand");
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
