package com.eksamen.Model.Kunde;

public class Kontaktinfo {
    private Kunde kunde;
    private String CPR;
    private String FirstName;
    private String LastName;
    private String Address;
    private int Postnr;
    private String City;
    private String Email;
    private int MobilNumber;

    public Kontaktinfo() {
    }

    public Kontaktinfo(String CPR) {
        this.CPR = CPR;
    }

    // Getters and Setters


    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getPostnr() {
        return Postnr;
    }

    public void setPostnr(int postnr) {
        this.Postnr = postnr;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getMobilNumber() {
        return MobilNumber;
    }

    public void setMobilNumber(int mobilNumber) {
        MobilNumber = mobilNumber;
    }


    @Override
    public String toString() {
        return "KontaktInfo{" +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Address='" + Address + '\'' +
                ", Email='" + Email + '\'' +
                ", Mobilnumber=" + MobilNumber +
                '}';
    }

    public String getCPR() {
        return CPR;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }
}
