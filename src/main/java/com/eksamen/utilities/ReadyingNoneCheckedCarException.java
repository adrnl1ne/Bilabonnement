package com.eksamen.utilities;

public class ReadyingNoneCheckedCarException extends Exception {

  //Lavet af Marcus
  public ReadyingNoneCheckedCarException(String message) {
    super(message);
  }

  @Override
  public String toString() {
    return "Det er ikke tilladt at sætte en bil, der ikke er i tilstanden CHECKUP!, til at være klar. " +
        "Der blev forsøgt at klargøre Bilen:\n" + super.toString();
  }
}
