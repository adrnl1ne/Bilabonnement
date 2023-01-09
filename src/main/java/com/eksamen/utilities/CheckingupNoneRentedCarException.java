package com.eksamen.utilities;

public class CheckingupNoneRentedCarException extends Exception {

  //Lavet af Marcus
  public CheckingupNoneRentedCarException(String message) {
    super(message);
  }

  @Override
  public String toString() {
    return "Det er ikke tilladt at sætte en bil til checkup der ikke er i tilstanden UDLEJET! " +
        "Der blev forsøgt at check Bilen:\n" + super.toString();
  }
}
