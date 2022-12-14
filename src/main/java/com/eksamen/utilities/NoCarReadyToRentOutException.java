package com.eksamen.utilities;

public class NoCarReadyToRentOutException extends Exception {

  //Lavet af Marcus
  public NoCarReadyToRentOutException(String message) {
    super(message);
  }

  @Override
  public String toString() {
    return "Der er ikke flere biler med Tilstanden KLAR. " + super.toString();
  }
}
