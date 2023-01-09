package com.eksamen.utilities;

public class DamagingNoneCheckedCarException extends Exception {

  //Lavet af Marcus
  public DamagingNoneCheckedCarException(String message) {
    super(message);
  }

  @Override
  public String toString() {
    return "Det er ikke tilladt at sætte en bil, der ikke er i tilstanden CHECKUP!, til at være skadet. " +
        "Der blev forsøgt at skade Bilen:\n" + super.toString();
  }
}
