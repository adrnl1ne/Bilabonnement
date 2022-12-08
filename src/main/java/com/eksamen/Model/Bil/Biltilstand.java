package com.eksamen.Model.Bil;

public enum Biltilstand {

    KLAR(1),
    UDLEJET(2),
    CHECKUP(3),
    SKADET(4);
    private final int Id;


    // Constructor
    Biltilstand (int Id) {
        this.Id = Id;
    }

    // Getter
    public int getId() {
        return Id;
    }


    // Custom made Metoder

    //Jakob
    public static Biltilstand getEnum(int Tilstands_ID) {
        switch (Tilstands_ID) {
            case 1:
                return Biltilstand.KLAR;
            case 2:
                return Biltilstand.UDLEJET;
            case 3:
                return Biltilstand.CHECKUP;
            case 4:
                return Biltilstand.SKADET;
            default:
                System.err.println("Det var ikke muligt at finde enum Biltilstand, ud efter ID'et: " + Tilstands_ID);
                throw new RuntimeException();
        }
    }
}
