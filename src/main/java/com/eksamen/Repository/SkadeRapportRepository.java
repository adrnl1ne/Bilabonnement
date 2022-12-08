package com.eksamen.Repository;

import com.eksamen.Model.Skader.Skade;
import com.eksamen.Model.Skader.SkadeType;
import com.eksamen.Model.Skader.Skaderapport;
import com.eksamen.utilities.DCM;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SkadeRapportRepository {

    private static final Connection conn = DCM.getConn();

    private double findSkadePris(int skadetype_id) {
        // vil finde den pris, som er blevet sat til en skadetype i tabellen
        try {
            String skadeTypeQUERY = "SELECT Pris FROM skadetype WHERE Skadetype_Id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(skadeTypeQUERY);
            preparedStatement.setInt(1, skadetype_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("Pris");
            }
            return -1;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at finde prisen for en SkadeType med dette ID: " + skadetype_id);
            throw new RuntimeException();
        }
    }
    private void createSkade(Skade skaden) {

        // Finder de værdier en Skade har, når den for første gang skal sættes/insertes ind i tabellen
        int SkadesRapport_ID = skaden.getSkaderapport_ID();
        int Skadetype_ID = skaden.getType().getId();
        double pris;

        // Finder ud af om en helt ny skade har fået indsat en pris, skadens pris er 0,
        // fordi den ikke er sat, og så finder vi en pris inde i skadetype tabellen
        if (skaden.getPrice() == 0.0) {
            pris = this.findSkadePris(Skadetype_ID);
        } else {
            pris = skaden.getPrice();
        }

        // og hvis Skadetype_ID'et ikke er mellem de typer af skader,
        // så vil prisen være mere end nul og skaden vil blive oprettet på tabellen
        if (pris > 0) {
            try {
                // Inserter de fundne værdier
                String InsertQUERY = "INSERT INTO skade (Skaderapport_ID, Skadetype_ID, Pris) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(InsertQUERY);
                preparedStatement.setInt(1, SkadesRapport_ID);
                preparedStatement.setInt(2, Skadetype_ID);
                preparedStatement.setDouble(3, pris);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Det var ikke muligt at create Skaden: " + skaden);
                throw new RuntimeException();
            }
        }
    }

    private Skade viewSkade(int Skade_ID) {
        try {
            String skadeQUERY = "SELECT * FROM skade WHERE Skade_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(skadeQUERY);
            preparedStatement.setInt(1, Skade_ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Skade skade = new Skade(Skade_ID);

                int SkadesRapport_ID = resultSet.getInt("Skadesrapport_ID"); // Kan ikke bruge denne værdi, da hvis jeg laver en SkadesRapport med den, så vil den køre i selvsving

                int SkadeType_ID = resultSet.getInt("Skadetype_ID");
                SkadeType skadeType = SkadeType.getEnum(SkadeType_ID);
                skade.setType(skadeType);

                double pris = resultSet.getInt("Pris");
                skade.setPrice(pris);

                return skade;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at view en Skade med ID'et: " + Skade_ID);
            throw new RuntimeException();
        }
    }

    private List<Skade> viewAlleSkader(Skaderapport skadesRapport) {
        List<Skade> skader = new ArrayList<>();
        int skadesRapport_ID = skadesRapport.getSkaderapport_ID();
        try {
            String skadeQUERY = "SELECT Skade_ID FROM skade WHERE Skaderapport_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(skadeQUERY);
            preparedStatement.setInt(1, skadesRapport_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int Skade_ID = resultSet.getInt(1);
                Skade skade = this.viewSkade(Skade_ID);
                if (skade != null) {
                    skade.setSkaderapport_ID(skadesRapport_ID);
                    skader.add(skade);
                }
            }
            return skader;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at view alle skader for SkadeRapporten: " + skadesRapport);
            throw new RuntimeException();
        }
    }
    public void createSkadesRapport(Skaderapport skadeRapport) {
        try {
            // Finder de værdier som skal Insertes i tabellen for Skadesrapporter
            int Lejeaftale_ID = skadeRapport.getLejeaftale().getLejeAftale_ID();
            String Stelnummer = skadeRapport.getBil().getStelnummer();
            LocalDate afleveringsdate = skadeRapport.getAfleveringsdate();
            double kørselsdistance = skadeRapport.getKørselsdistance();
            skadeRapport.getBil().setKmKørt(kørselsdistance);

            // Inserter de fundne værdier for skadesrapporten

            String InsertQUERY = "INSERT INTO skadesrapport (Lejeaftale_ID, Stelnummer, Afleveringsdato," +
                    " Kørselsdistance) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement1 = conn.prepareStatement(InsertQUERY);
            preparedStatement1.setInt(1, Lejeaftale_ID);
            preparedStatement1.setString(2, Stelnummer);
            preparedStatement1.setDate(3, Date.valueOf(afleveringsdate));
            preparedStatement1.setDouble(4, kørselsdistance);
            preparedStatement1.executeUpdate();

            // Finder den nyeste SkadesRapport, som lige er blevet lagt ind i tabellen, og finder dens ID
            String SelectQUERY = "SELECT MAX(Skaderapport_ID) FROM skadesrapport";
            PreparedStatement preparedStatement2 = conn.prepareStatement(SelectQUERY);
            ResultSet resultSet = preparedStatement2.executeQuery();
            if (resultSet.next()) {
                int SkadesRapport_ID = resultSet.getInt("Skaderapport_ID");
                skadeRapport.setSkaderapport_ID(SkadesRapport_ID);
                List<Skade> skader = skadeRapport.getSkader();
                // Finder hver skade i en liste af skader og Inserter dem ind i tabellen for skader
                for (Skade skade : skader) {
                    this.createSkade(skade);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at create, altså Inserte, SkadesRapporten: " + skadeRapport);
            throw new RuntimeException();
        }
    }

    /*
    public Skaderapport viewSkadesRapport(int Skaderapport_ID) {

        try {
            String skadesRapportQUERY = "SELECT * FROM skadesrapport WHERE Skaderapport_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(skadesRapportQUERY);
            preparedStatement.setInt(1, Skaderapport_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Skaderapport skadesRapport = new Skaderapport(Skaderapport_ID);
                int lejeAftale_ID = resultSet.getInt("Lejeaftale_ID");
                LejeAftale lejeAftale = new LejeAftaleRepository().viewLejeaftale(lejeAftale_ID);
                skadesRapport.setLejeaftale(lejeAftale);

                String stelnummer = resultSet.getString("Stelnummer");
                Bil bil = new BilRepository().viewBil(stelnummer);
                skadesRapport.setBil(bil);

                LocalDate afleveringsdato = resultSet.getDate("Afleveringsdato").toLocalDate();
                skadesRapport.setAfleveringsdate(afleveringsdato);

                double kørselsdistance = resultSet.getDouble("Kørselsdistance");
                skadesRapport.setKørselsdistance(kørselsdistance);

                List<Skade> rapportensSkader = this.viewAlleSkader(skadesRapport);
                skadesRapport.setSkade(rapportensSkader);

                return skadesRapport;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at view, altså Select, en SkadesRapport med ID'et: " + Skaderapport_ID);
            throw new RuntimeException();
        }
    }
*/

}
