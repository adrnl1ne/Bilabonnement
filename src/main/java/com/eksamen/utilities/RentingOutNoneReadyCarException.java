package com.eksamen.utilities;

public class RentingOutNoneReadyCarException extends Exception {

  //Lavet af Marcus
  public RentingOutNoneReadyCarException(String message) {
    super(message);
  }

  @Override
  public String toString() {
    return "Det er ikke tilladt at udleje en bil der ikke er i tilstanden KLAR! " +
            "Der blev forsøgt at udleje Bilen: " + super.toString();
  }
}
