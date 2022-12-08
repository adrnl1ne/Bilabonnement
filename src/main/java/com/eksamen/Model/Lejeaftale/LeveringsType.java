package com.eksamen.Model.Lejeaftale;

public enum LeveringsType {
    STANDARD(1),
    FDM(2);

    private int Id;

    // Constructor

    LeveringsType(int id) {
        Id = id;
    }


    // Getters
    public int getId() {
        return Id;
    }


    public static LeveringsType getType(int id) {
            switch (id) {
                case 1:
                    return STANDARD;
                case 2:
                    return FDM;
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
