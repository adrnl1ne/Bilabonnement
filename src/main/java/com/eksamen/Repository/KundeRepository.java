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
    // Indsætter et Kunde objekt der ikke eksisterer i databasen endnu, dog eksisterer kunden allerede, så kaldes updateKunde i stedet
    public void createKunde(Kunde kunde) {
        String CPR = kunde.getCPR();
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
                this.createKontaktinfo(kunde.getInfo());
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Not possible to create, to Insert, a Kunde: " + kunde);
                throw new RuntimeException(e);
            }
        } else {
            this.updateKunde(kunde);
        }

    }

    // Marcus og Jakob
    // Indsætter et Kontaktinfo objekt for en kunde ind i databasen, metoden bruges når en kunde bliver indsat for første gang
    private void createKontaktinfo(Kontaktinfo kontaktinfo) {
        String CPR = kontaktinfo.getCPR();
        String fornavn = kontaktinfo.getFirstName();
        String efternavn = kontaktinfo.getLastName();
        String adresse = kontaktinfo.getAddress();
        int postNr = kontaktinfo.getPostnr();
        String email = kontaktinfo.getEmail();
        int mobil = kontaktinfo.getMobilNumber();
        String city = kontaktinfo.getCity();

        try {

            String kontaktQUERY = "INSERT INTO kontaktinfo (CPR, Fornavn, Efternavn, Adresse, Postnr, Mail, Mobil, City) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement4 = conn.prepareStatement(kontaktQUERY);
            preparedStatement4.setString(1, CPR);
            preparedStatement4.setString(2, fornavn);
            preparedStatement4.setString(3, efternavn);
            preparedStatement4.setString(4, adresse);
            preparedStatement4.setInt(5, postNr);
            preparedStatement4.setString(6, email);
            preparedStatement4.setInt(7, mobil);
            preparedStatement4.setString(8, city);
            preparedStatement4.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at create, altså Inserte i tabellen, Kontaktinformationen: " + kontaktinfo);
            throw new RuntimeException(e);
        }
    }

    // Marcus
    // Returner et Kunde objekt ud fra en Primær nøgle i kunde tabellen i vores database
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

                // Finder kontaktinformationer, kontaktinfo, for kunden med det CPR som blev givet og giver det til vores kunde
                Kontaktinfo nyesteKontaktInfo = this.viewKontaktInfo(CPR);
                kunde.setKontaktInfo(nyesteKontaktInfo);


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

    // Marcus og Jakob
    // Returner et Kontaktinfo objekt til et Kunde objekt ud fra en primær/foreign key i kontaktinfo tabellen
    private Kontaktinfo viewKontaktInfo(String CPR) {

        try {
            String KontaktInfoQUERY = "SELECT * FROM kontaktinfo WHERE CPR = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(KontaktInfoQUERY);
            preparedStatement.setString(1, CPR);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Kontaktinfo kontaktInfo = new Kontaktinfo(CPR);
                String fornavn = resultSet.getString("Fornavn");
                kontaktInfo.setFirstName(fornavn);
                String efternavn = resultSet.getString("Efternavn");
                kontaktInfo.setLastName(efternavn);
                String adresse = resultSet.getString("Adresse");
                kontaktInfo.setAddress(adresse);
                int postnr = resultSet.getInt("Postnr");
                kontaktInfo.setPostnr(postnr);
                String city = resultSet.getString("City");
                kontaktInfo.setCity(city);
                String email = resultSet.getString("Mail");
                kontaktInfo.setEmail(email);
                int mobil = resultSet.getInt("Mobil");
                kontaktInfo.setMobilNumber(mobil);
                return kontaktInfo;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at view nyeste kontaktinfo for Kunden med et CPR: " + CPR);
            throw new RuntimeException();
        }
    }

    // Marcus
    // Returner en liste af alle kunder der er i kunde tabellen i vores database
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
    // Updater en kunde i databasen, som allerede eksisterer, med RegNum og KontoNum for den kunde samt kundens Kontaktinfo
    public void updateKunde(Kunde kunde) {
        try {
            String CPR = kunde.getCPR();
            String regNum = kunde.getRegNum();
            String kontoNum = kunde.getKontoNum();
            String QUERY = "UPDATE kunde SET RegNum = ?, KontoNum = ? WHERE CPR = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setString(1, regNum);
            preparedStatement.setString(2, kontoNum);
            preparedStatement.setString(3, CPR);
            preparedStatement.executeUpdate();
            this.updateKontaktinfo(kunde.getInfo());
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at opdatere kunden: " + kunde);
            throw new RuntimeException();
        }
    }

    // Marcus og Jakob
    // Updater en kontaktinfo i databasen, som allerede eksisterer, for en kunde
    private void updateKontaktinfo(Kontaktinfo kontaktinfo) {
        // Finder id'et, CPR'et, til hvor i tabellen denne Kontaktinfo skal updates
        String CPR = kontaktinfo.getCPR();

        // Finder alle de værdier der er i en lejeaftales KontaktInfo, som så skal updates
        String fornavn = kontaktinfo.getFirstName();
        String efternavn = kontaktinfo.getLastName();
        String adresse = kontaktinfo.getAddress();
        int postnr = kontaktinfo.getPostnr();
        String mail = kontaktinfo.getEmail();
        int mobil = kontaktinfo.getMobilNumber();
        String city = kontaktinfo.getCity();

        // Updater de fundne værdier med dem i tabellen
        try {

            String kontaktQUERY = "UPDATE kontaktinfo SET Fornavn = ?, Efternavn = ?, Adresse = ?, Postnr = ?, Mail = ?, Mobil = ?, City = ? WHERE CPR = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(kontaktQUERY);

            preparedStatement.setString(1, fornavn);
            preparedStatement.setString(2, efternavn);
            preparedStatement.setString(3, adresse);
            preparedStatement.setInt(4, postnr);
            preparedStatement.setString(5, mail);
            preparedStatement.setInt(6, mobil);
            preparedStatement.setString(7, city);
            preparedStatement.setString(8, CPR);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at Update KontaktInformationen: " + kontaktinfo);
            throw new RuntimeException(e);
        }
    }

    // Marcus
    // Sletter en kunde fra databasen, som så sletter den tilhørende kontaktinfo, samt alle de lejeaftaler, som den kunde havde
    public void deleteKunde(Kunde kunde) {
        try {
            String CPR = kunde.getCPR();
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
