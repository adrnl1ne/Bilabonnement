package com.eksamen.Repository;

import com.eksamen.Model.Abonnement.AbonnementLejeaftale;
import com.eksamen.Model.Bil.Bil;
import com.eksamen.Model.Bil.Biltilstand;
import com.eksamen.Model.Kunde.Kunde;
import com.eksamen.Model.Lejeaftale.LejeAftale;
import com.eksamen.Model.Lejeaftale.LeveringsType;
import com.eksamen.utilities.DCM;
import com.eksamen.utilities.RentingOutNoneReadyCarException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class LejeAftaleRepository {

    private final Connection conn = DCM.getConn();



    private int findNyesteLejeAftale_ID() {
        try {
            String newestLejeaftaleQUERY = "SELECT MAX(Lejeaftale_ID) AS Lejeaftale_ID FROM lejeaftale";
            PreparedStatement preparedStatement1 = conn.prepareStatement(newestLejeaftaleQUERY);
            ResultSet resultSet = preparedStatement1.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("Lejeaftale_ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at finde, altså Selecte, det højeste ID, " +
                    "altså ID'et til den nyeste Lejeaftale der er i tabellen.");
            throw new RuntimeException(e);
        }
        return -1;
    }

    private void createAbonnement(AbonnementLejeaftale abonnementLejeaftale) {
        int lejeaftalens_ID = abonnementLejeaftale.getLejeaftale_ID();
        boolean isUnlimited = abonnementLejeaftale.isUnlimited();
        int kmPrMd = abonnementLejeaftale.getKmPrMd();
        int abonnementLængde = abonnementLejeaftale.getAbonnementLaengde();
        double overAflPris = abonnementLejeaftale.getAfleveringPrice();
        double prisPrMd = abonnementLejeaftale.getPricePrMonth();
        double udbetaling = abonnementLejeaftale.getUdbetaling();
        double farvePrisPrMd = abonnementLejeaftale.getExtraColorPrice();
        double prisPrKmOver = abonnementLejeaftale.getPriceForOverDrive();

        try {

            String abonnementQUERY = "INSERT INTO abnmtlejeaftale (Lejeaftale_ID, isUnlimited, KmPrMd, AbnmtLængde, " +
                    "OverAflPris, PrisPrMåned, Udbetaling, " +
                    "FarvePrisPrMåned, PrisPrKmOver) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement2 = conn.prepareStatement(abonnementQUERY);
            preparedStatement2.setInt(1, lejeaftalens_ID);
            preparedStatement2.setBoolean(2, isUnlimited);
            preparedStatement2.setInt(3, kmPrMd);
            preparedStatement2.setInt(4, abonnementLængde);
            preparedStatement2.setDouble(5, overAflPris);
            preparedStatement2.setDouble(6, prisPrMd);
            preparedStatement2.setDouble(7, udbetaling);
            preparedStatement2.setDouble(8, farvePrisPrMd);
            preparedStatement2.setDouble(9, prisPrKmOver);
            preparedStatement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at create, altså inserte i tabellen, Abonnementet: " + abonnementLejeaftale);
            throw new RuntimeException();
        }
    }

    private void createLevering(LejeAftale lejeAftale) {
        int lejeaftalens_ID = lejeAftale.getLejeAftale_ID();
        int leveringsType_ID = lejeAftale.getType().getId();
        String leveringsAdresse = lejeAftale.getLeveringsadresse();
        String afleveringsAdresse = lejeAftale.getAfleveringsadresse();
        double kørtDistanceFørUdlejning = lejeAftale.getBil().getKmKort();
        double transporttillæg = lejeAftale.getTransportTillaeg();

        try {
            String leveringQUERY = "INSERT INTO levering (Lejeaftale_ID, LeveringsType_ID, Leveringsadresse, " +
                    "Afleveringsadresse, Km_Kørt, TransportTillæg) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement3 = conn.prepareStatement(leveringQUERY);
            preparedStatement3.setInt(1, lejeaftalens_ID);
            preparedStatement3.setInt(2, leveringsType_ID);
            preparedStatement3.setString(3, leveringsAdresse);
            preparedStatement3.setString(4, afleveringsAdresse);
            preparedStatement3.setDouble(5, kørtDistanceFørUdlejning);
            preparedStatement3.setDouble(6, transporttillæg);
            preparedStatement3.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at create, altså Inserte i tabellen, Leveringen: " + lejeAftale);
            throw new RuntimeException();
        }
    }

    private void updateAbonnement(AbonnementLejeaftale abonnementLejeaftale) {
        // Finder id'et til hvor i tabellen dette Abonnement skal updates
        int lejeaftale_ID = abonnementLejeaftale.getLejeaftale_ID();

        // Finder alle de værdier der er i en lejeaftales abonnement, som så skal updates
        boolean isUnlimited = abonnementLejeaftale.isUnlimited();
        int kmPrMd = abonnementLejeaftale.getKmPrMd();
        int abonnementLængde = abonnementLejeaftale.getAbonnementLaengde();
        double overAflPris = abonnementLejeaftale.getAfleveringPrice();
        double prisPrMd = abonnementLejeaftale.getPricePrMonth();
        double udbetaling = abonnementLejeaftale.getUdbetaling();
        double farvePrisPrMd = abonnementLejeaftale.getExtraColorPrice();
        double prisPrKmOver = abonnementLejeaftale.getPriceForOverDrive();

        // Updater de fundne værdier med dem i tabellen
        try {
            String abonnementQUERY = "UPDATE abnmtlejeaftale SET isUnlimited = ?, KmPrMd = ?, AbnmtLængde = ?, " +
                    "OverAflPris = ?, PrisPrMåned = ?, Udbetaling = ?, FarvePrisPrMåned = ?, " +
                    "PrisPrKmOver = ? WHERE Lejeaftale_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(abonnementQUERY);
            preparedStatement.setBoolean(1, isUnlimited);
            preparedStatement.setInt(2, kmPrMd);
            preparedStatement.setInt(3, abonnementLængde);
            preparedStatement.setDouble(4, overAflPris);
            preparedStatement.setDouble(5, prisPrMd);
            preparedStatement.setDouble(6, udbetaling);
            preparedStatement.setDouble(7, farvePrisPrMd);
            preparedStatement.setDouble(8, prisPrKmOver);
            preparedStatement.setInt(9, lejeaftale_ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at update Abonnementet: " + abonnementLejeaftale);
            throw new RuntimeException(e);
        }
    }

    private void updateLevering(LejeAftale lejeAftale) {
        // Finder id'et til hvor i tabellen denne Levering skal updates
        int lejeAftale_ID = lejeAftale.getLejeAftale_ID();

        // Finder alle de værdier der er i en lejeaftales levering, som så skal updates
        int LeveringsType_ID = lejeAftale.getType().getId();
        String leveringsAdresse = lejeAftale.getLeveringsadresse();
        String afleveringsAdresse = lejeAftale.getAfleveringsadresse();
        double kmKørtFørUdlej = lejeAftale.getKorselDistanceInden();
        double transporttillæg = lejeAftale.getTransportTillaeg();

        // Updater tabellen med de fundne værdier
        try {
            String leveringQUERY = "UPDATE levering SET LeveringsType_ID = ?, Leveringsadresse = ?," +
                    " Afleveringsadresse = ?, Km_Kørt = ?, TransportTillæg = ? WHERE Lejeaftale_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(leveringQUERY);
            preparedStatement.setInt(1, LeveringsType_ID);
            preparedStatement.setString(2, leveringsAdresse);
            preparedStatement.setString(3, afleveringsAdresse);
            preparedStatement.setDouble(4, kmKørtFørUdlej);
            preparedStatement.setDouble(5, transporttillæg);
            preparedStatement.setInt(6, lejeAftale_ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at Update Leveringen: " + lejeAftale);
            throw new RuntimeException(e);
        }

    }

    private LejeAftale viewLevering(LejeAftale lejeAftale) {

        int lejeAftale_ID = lejeAftale.getLejeAftale_ID();

        try {
            String leveringQUERY = "SELECT * FROM levering WHERE levering.Lejeaftale_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(leveringQUERY);
            preparedStatement.setInt(1, lejeAftale_ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int leveringsType_ID = resultSet.getInt("LeveringsType_ID");
                LeveringsType leveringsType = LeveringsType.getType(leveringsType_ID);
                lejeAftale.setType(leveringsType);

                String leveringsAdresse = resultSet.getString("Leveringsadresse");
                lejeAftale.setLeveringsadresse(leveringsAdresse);

                String afleveringsAdresse = resultSet.getString("Afleveringsadresse");
                lejeAftale.setAfleveringsadresse(afleveringsAdresse);

                double kmKørt = resultSet.getDouble("Km_Kørt");
                lejeAftale.setKorselDistanceInden(kmKørt);

                double transporttillæg = resultSet.getDouble("Transporttillæg");
                lejeAftale.setTransportTillaeg(transporttillæg);

                return lejeAftale;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at view, altså Select, Leveringen: " + lejeAftale);
            throw new RuntimeException();
        }
    }

    private AbonnementLejeaftale viewAbonnement(LejeAftale lejeAftale) {
        int lejeAftale_ID = lejeAftale.getLejeAftale_ID();
        AbonnementLejeaftale lejeAftalesAbo = new AbonnementLejeaftale(lejeAftale_ID);

        try {
            String abonnementQUERY = "SELECT * FROM abnmtlejeaftale WHERE abnmtlejeaftale.Lejeaftale_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(abonnementQUERY);
            preparedStatement.setInt(1, lejeAftale_ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                boolean isUnlimited = resultSet.getBoolean("isUnlimited");
                lejeAftalesAbo.setUnlimited(isUnlimited);

                int kmPrMd = resultSet.getInt("KmPrMd");
                lejeAftalesAbo.setKmPrMd(kmPrMd);

                int aboLængde = resultSet.getInt("AbnmtLængde");
                lejeAftalesAbo.setAbonnementLaengde(aboLængde);

                double overAflPris = resultSet.getDouble("OverAflPris");
                lejeAftalesAbo.setAfleveringPrice(overAflPris);

                double prisPrMd = resultSet.getDouble("PrisPrMåned");
                lejeAftalesAbo.setPricePrMonth(prisPrMd);

                double udbetaling = resultSet.getDouble("Udbetaling");
                lejeAftalesAbo.setUdbetaling(udbetaling);

                double farvePrisPrMd = resultSet.getDouble("FarvePrisPrMåned");
                lejeAftalesAbo.setExtraColorPrice(farvePrisPrMd);

                double prisPrKmOver = resultSet.getDouble("PrisPrKmOver");
                lejeAftalesAbo.setPriceForOverDrive(prisPrKmOver);

                return lejeAftalesAbo;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt view, altså Select, Abonnementet: " + lejeAftalesAbo);
            throw new RuntimeException();
        }

    }

    public void deleteLejeaftale(LejeAftale lejeAftale) {
        try {
            int LejeAftale_ID = lejeAftale.getLejeAftale_ID();
            String QUERY = "DELETE FROM lejeaftale WHERE Lejeaftale_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setInt(1, LejeAftale_ID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at delete Lejeaftalen: " + lejeAftale);
            throw new RuntimeException(e);
        }

    }

    public void createLejeaftale(LejeAftale lejeAftale) throws RentingOutNoneReadyCarException {
        // Som det første creates kunden, hvis kunden er ny, for ellers kan lejeaftalen ikke blive created, da den har en foreign key der henviser til en kunde i databasen
        new KundeRepository().createKunde(lejeAftale.getKunde());

        int lejeaftalens_ID;

        Bil potentielBilTilUdlejning = lejeAftale.getBil();
        Biltilstand lejeBilensTilstand = potentielBilTilUdlejning.getBiltilstand();

        //note: Checker om bilen er klar til udlejning
        if (lejeBilensTilstand != Biltilstand.KLAR) {
            System.err.println("En LejeAftale forsøgte at udleje en bil med Tilstanden: " + lejeBilensTilstand +
                    ", LejeAftalen der blev modtaget af metoden, createLejeAftale, var: " + lejeAftale);
            throw new RentingOutNoneReadyCarException(potentielBilTilUdlejning.toString());
        }


        potentielBilTilUdlejning.setBiltilstand(Biltilstand.UDLEJET);
        new BilRepository().updateBil(potentielBilTilUdlejning);
        AbonnementLejeaftale abonnementLejeaftale = lejeAftale.getAbonnement();
        String CPR = lejeAftale.getKunde().getCPR();


        String stelnummer = potentielBilTilUdlejning.getStelnummer();
        LocalDate startDato = lejeAftale.getStartDato();
        if (startDato == null) {
            startDato = LocalDate.now();
        }
        String nummerplade = lejeAftale.getNummerPlade();

        try {
            String QUERY = "INSERT INTO lejeaftale (CPR, LAStelnummer, StartDato, Nummerplade) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setString(1, CPR);
            preparedStatement.setString(2, stelnummer);
            preparedStatement.setDate(3, java.sql.Date.valueOf(startDato));
            preparedStatement.setString(4, nummerplade);
            preparedStatement.executeUpdate();

            lejeaftalens_ID = this.findNyesteLejeAftale_ID();
            lejeAftale.setLejeAftale_ID(lejeaftalens_ID);
            lejeAftale.getAbonnement().setLejeaftale_ID(lejeaftalens_ID);


            if (lejeaftalens_ID > 0) {
                this.createAbonnement(abonnementLejeaftale);
                this.createLevering(lejeAftale);
            } else {
                this.deleteLejeaftale(lejeAftale);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at create, altså inserte i tabellen, LejeAftalen: " + lejeAftale);
            throw new RuntimeException(e);
        }

    }

    public LejeAftale viewLejeaftale(int Lejeaftale_ID) {
        try {
            LejeAftale lejeAftalen;
            AbonnementLejeaftale abmnt;
            Kunde kunden;
            // Creating the LejeAftale object from the table
            String lejeaftaleQUERY = "SELECT * FROM lejeaftale WHERE lejeaftale.Lejeaftale_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(lejeaftaleQUERY);
            preparedStatement.setInt(1, Lejeaftale_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int leje_ID = resultSet.getInt("Lejeaftale_ID");
                lejeAftalen = new LejeAftale(leje_ID);

                String CPR = resultSet.getString("CPR");
                kunden = new KundeRepository().viewKunde(CPR);
                lejeAftalen.setKunde(kunden);

                String stelnummer = resultSet.getString("Stelnummer");
                Bil udlejetBil = new BilRepository().viewBil(stelnummer);
                lejeAftalen.setBil(udlejetBil);

                LocalDate startDato = resultSet.getDate("StartDato").toLocalDate();
                lejeAftalen.setStartDato(startDato);
                String nummerplade = resultSet.getString("Nummerplade");
                lejeAftalen.setNummerPlade(nummerplade);

                // Creating the Abonnement object from the table
                abmnt = this.viewAbonnement(lejeAftalen);
                lejeAftalen.setAbonnement(abmnt);

                // Creating the Levering objekt from the table
                lejeAftalen = this.viewLevering(lejeAftalen);

                // Der sendes over en LejeAftale, der ikke har den SkadesRapport,
                // som er tilknyttet til den, hvis der er en
                return lejeAftalen;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at view, alstå få Lejeaftalen fra tabellen, " +
                    "ved brug af Lejeaftale_ID: " + Lejeaftale_ID);
            throw new RuntimeException();
        }
        return null;
    }


    public void updateLejeaftale(LejeAftale lejeAftale) {

        // Finder de værdier i en lejeAftale, som skal gemmes i LejeAftale Tabellen
        int lejeAftale_ID = lejeAftale.getLejeAftale_ID();
        String CPR_Number = lejeAftale.getKunde().getCPR();
        String stelnummer = lejeAftale.getBil().getStelnummer();
        LocalDate startDato = lejeAftale.getStartDato();
        String nummerplade = lejeAftale.getNummerPlade();

        // updater værdierne der lige er blevet fundet
        try {
            String lejeAftaleQUERY = "UPDATE lejeaftale SET CPR = ?, LAStelnummer = ?, StartDato = ?," +
                    " Nummerplade = ? WHERE Lejeaftale_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(lejeAftaleQUERY);
            preparedStatement.setString(1, CPR_Number);
            preparedStatement.setString(2, stelnummer);
            preparedStatement.setDate(3, java.sql.Date.valueOf(startDato));
            preparedStatement.setString(4, nummerplade);
            preparedStatement.setInt(5, lejeAftale_ID);
            preparedStatement.executeUpdate();

            // Updater de individuelle objekter inde i en lejeAftale
            this.updateAbonnement(lejeAftale.getAbonnement());
            this.updateLevering(lejeAftale);

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at update LejeAftalen: " + lejeAftale);
            throw new RuntimeException(e);
        }

    }



}
