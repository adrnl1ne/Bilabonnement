package com.eksamen.utilities;

public class FixingNoneDamagedCarException extends Exception {

  //Lavet af Marcus
  public FixingNoneDamagedCarException(String message) {
    super(message);
  }

  @Override
  public String toString() {
    return "Det er ikke tilladt at sætte en bil, der ikke er i tilstanden SKADET!, til at være klar. " +
        "Der blev forsøgt at reparer Bilen:\n" + super.toString();
  }
}
