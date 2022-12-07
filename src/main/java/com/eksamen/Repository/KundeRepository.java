package com.eksamen.Repository;

import com.eksamen.Model.Kunde.Kontaktinfo;
import com.eksamen.Model.Kunde.Kunde;
import com.eksamen.utilities.DCM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KundeRepository {

    private final Connection conn = DCM.getConn();

    // Marcus
    public void createKunde(Kunde kunde) {
        String CPR = kunde.getCprnumber();
        String regNum = kunde.getRegNum();
        String kontoNum = kunde.getKontoNum();
        boolean erNyKunde = true;

        try {
            String findKundeQUERY = "SELECT * FROM kunde WHERE CPR = ?";
            PreparedStatement prepStatement = conn.prepareStatement(findKundeQUERY);
            prepStatement.setString(1, CPR);
            ResultSet resultSet = prepStatement.executeQuery();
            if (resultSet.next()) {
                erNyKunde = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at view, altså Selecte Kunden: " + kunde);
            throw new RuntimeException();
        }

        if (erNyKunde) {
            try {
                String QUERY = "INSERT INTO kunde (CPR, RegNum, KontoNum) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(QUERY);
                preparedStatement.setString(1, CPR);
                preparedStatement.setString(2, regNum);
                preparedStatement.setString(3, kontoNum);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Not possible to create, to Insert, a Kunde: " + kunde);
                throw new RuntimeException(e);
            }
        } else {
            this.updateKunde(kunde);
        }

    }

    // Marcus
    private Kontaktinfo viewKontaktInfo(Kunde kunde) {
        // Finder kundens primær nøgle, CPR
        String CPR = kunde.getCprnumber();

        try {
            String KontaktInfoQUERY = "SELECT * FROM kontaktinfo WHERE CPR = ? AND Counter = (SELECT MAX(counter) FROM kontaktinfo WHERE CPR = ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(KontaktInfoQUERY);
            preparedStatement.setString(1, CPR);
            preparedStatement.setString(2,CPR);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Kontaktinfo kontaktInfo = new Kontaktinfo();
                int lejeAftale_ID = resultSet.getInt("Lejeaftale_ID");
                String fornavn = resultSet.getString("Fornavn");
                kontaktInfo.setFirstName(fornavn);
                String efternavn = resultSet.getString("Efternavn");
                kontaktInfo.setLastName(efternavn);
                String adresse = resultSet.getString("Adresse");
                kontaktInfo.setAddress(adresse);
                int postnr = resultSet.getInt("Postnr");
                kontaktInfo.setPostnr(postnr);
                String by = resultSet.getString("By");
                kontaktInfo.setCity(by);
                String email = resultSet.getString("Mail");
                kontaktInfo.setEmail(email);
                int mobil = resultSet.getInt("Mobil");
                kontaktInfo.setMobilNumber(mobil);
                int counter = resultSet.getInt("Counter");
                kontaktInfo.setCounter(counter);
                kunde.setNyesteInfo(kontaktInfo);
                return kontaktInfo;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at view nyeste kontaktinfo for Kunden: " + kunde);
            throw new RuntimeException();
        }
    }

    // Marcus
    public Kunde viewKunde(String CPR) {
        try {
            String KundeQUERY = "SELECT * FROM kunde WHERE CPR = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(KundeQUERY);
            preparedStatement.setString(1, CPR);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String CPRNum = resultSet.getString("CPR");
                String regNum = resultSet.getString("RegNum");
                String kontoNum = resultSet.getString("KontoNum");
                Kunde kunde = new Kunde(CPRNum);
                kunde.setRegNum(regNum);
                kunde.setKontoNum(kontoNum);

                // Finder den nyeste kontaktinfo for denne kunde med det CPR som blev givet og giver det til vores kunde
                Kontaktinfo nyesteKontaktInfo = this.viewKontaktInfo(kunde);
                kunde.setNyesteInfo(nyesteKontaktInfo);


                // Til sidst bringes en kunde over, der mangler sine LejeAftaler, men disse kan hentes fra LejeaftaleRepo
                return kunde;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Not possible to view a Kunde med CPR:" + CPR);
            throw new RuntimeException(e);
        }
    }

    // Marcus
    public List<Kunde> viewAllKunder() {
        List<Kunde> alleKunder = new ArrayList<>();

        try {
            String QUERY = "SELECT CPR FROM kunde";
            PreparedStatement preparedStatement = conn.prepareStatement(QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String CPR = resultSet.getString(1);
                Kunde kunde = viewKunde(CPR);
                alleKunder.add(kunde);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Var ikke muligt at viewAllKunder");
            throw new RuntimeException(e);
        }

        return alleKunder;
    }

    // Marcus
    public void updateKunde(Kunde kunde) {
        try {
            String CPR = kunde.getCprnumber();
            String regNum = kunde.getRegNum();
            String kontoNum = kunde.getKontoNum();
            String QUERY = "UPDATE kunde SET RegNum = ?, KontoNum = ? WHERE CPR = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setString(1, regNum);
            preparedStatement.setString(2, kontoNum);
            preparedStatement.setString(3, CPR);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at opdatere kunden: " + kunde);
            throw new RuntimeException();
        }
    }

    public void deleteKunde(Kunde kunde) {
        try {
            String CPR = kunde.getCprnumber();
            String QUERY = "DELETE FROM kunde WHERE CPR = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setString(1, CPR);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at delete kunden: " + kunde);
            throw new RuntimeException(e);
        }
    }
}
