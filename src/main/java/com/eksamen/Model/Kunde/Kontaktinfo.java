package com.eksamen.Model.Kunde;

public class Kontaktinfo {
    private String CPR;
    private String firstName;
    private String lastName;
    private String address;
    private int postnr;
    private String city;
    private String email;
    private int mobilNumber;


    // Constructors

    public Kontaktinfo(String CPR) {
        this.CPR = CPR;
    }


    // Custom made Metoder






    // Getters, Setters og toString
    public String getCPR() {
        return CPR;
    }

    public void setCPR(String CPR) {
        this.CPR = CPR;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostnr() {
        return postnr;
    }

    public void setPostnr(int postnr) {
        this.postnr = postnr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String City) {
        this.city = City;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobilNumber() {
        return mobilNumber;
    }

    public void setMobilNumber(int mobilNumber) {
        this.mobilNumber = mobilNumber;
    }


    @Override
    public String toString() {
        return "Kontaktinfo{" +
            "CPR='" + CPR + '\'' +
            ", FirstName='" + firstName + '\'' +
            ", LastName='" + lastName + '\'' +
            ", Address='" + address + '\'' +
            ", Postnr=" + postnr +
            ", City='" + city + '\'' +
            ", Email='" + email + '\'' +
            ", MobilNumber=" + mobilNumber +
            '}';
    }

}
