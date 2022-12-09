package com.eksamen.Model.Skader;

public enum SkadeType {
    STENSLAG(1, 1),
    FLERE_STENSLAG(2, 0),
    LAKFELT(3, 100),
    RIDSET_ALUFÆLG(4, 4),
    NY_FORRUDE(5, 1),
    MANGLER_NØGLE(6, 2);



    private int Id; // Måden vi finder ud af hvilken type en skade er når vi skal view eller create en skade
    private int timesTypeCanBeReported; // Den mængde gange en skade kan være på en rapport, som der er kun 1 forrude

    // Constructor
     SkadeType(int id, int timesTypeCanBeReported) {
        Id = id;
        this.timesTypeCanBeReported = timesTypeCanBeReported;
    }

    // Custom made Metoder
    public String displaySkadeType() {
         switch (this.getId()) {
             case 1:
                 return "Stenslag";
             case 2:
                 return "Flere Stenslag";
             case 3:
                 return "Lakfelt";
             case 4:
                 return "Ridset Alufælg";
             case 5:
                 return "Ny Forrude";
             case 6:
                 return "Mangler Nøgle";
             default:
                 System.err.println("Det var ikke muligt at display en Type Skade, ud efter ID'et: " + this.getId());
                 throw new RuntimeException();
         }
    }

    // Getters
    public int getId() {
        return Id;
    }

    public int getTimesTypeCanBeReported() {
         return timesTypeCanBeReported;
    }

    public static SkadeType getEnum(int Skadetype_ID) {
        switch (Skadetype_ID) {
            case 1:
                return SkadeType.STENSLAG;
            case 2:
                return SkadeType.FLERE_STENSLAG;
            case 3:
                return SkadeType.LAKFELT;
            case 4:
                return SkadeType.RIDSET_ALUFÆLG;
            case 5:
                return SkadeType.NY_FORRUDE;
            case 6:
                return SkadeType.MANGLER_NØGLE;
            default:
                System.err.println("Det var ikke muligt at udføre metoden getEnum for en SkadeType med ID'et: " + Skadetype_ID);
                throw new RuntimeException();
        }
    }
}

