package com.eksamen.Model.Lejeaftale;

public enum LeveringsType {
    STANDARD(1),
    FDM(2);

    private final int Id;

    // Constructor

    LeveringsType(int id) {
        Id = id;
    }

    // Custom made Metoder
    //Marcus
    public String displayLeveringsType() {
        switch (this.getId()) {
            case 1 -> {
                return "Standard levering";
            }
            case 2 -> {
                return "FDM levering";
            }
            default -> {
                System.err.println("Det var ikke muligt at display en Type levering, ud efter ID'et: " + this.getId());
                throw new RuntimeException();
            }
        }
    }


    // Getters
    public int getId() {
        return Id;
    }

    //Jakob
    public static LeveringsType getType(int id) {
        switch (id) {
            case 1 -> {
                return STANDARD;
            }
            case 2 -> {
                return FDM;
            }
        }
        System.err.println("Der blev forsøgt at getType for en leveringstype, men talet/ID'et der blev brugt: " + id + " er ikke valid!");
            throw new RuntimeException();
    }


    @Override
    public String toString() {
        return "LeveringsType{" +
                "Id=" + Id +
                '}';
    }
}
