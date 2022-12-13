package com.eksamen.Repository;

import com.eksamen.Model.Bil.Bil;
import com.eksamen.Model.Lejeaftale.LejeAftale;
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


    // Marcus
    // Indsætter en helt ny SkadeRapport ind i vores database og får en metode til at indsætte dens skader
    public void createSkadesRapport(Skaderapport skadeRapport) {
        // Finder de værdier som skal Insertes i tabellen for Skadesrapporter
        int Lejeaftale_ID = skadeRapport.getLejeaftale().getLejeAftale_ID();
        String Stelnummer = skadeRapport.getBil().getStelnummer();
        LocalDate afleveringsdate = skadeRapport.getAfleveringsdate();
        double kørselsdistance = skadeRapport.getKorselsdistance();
        skadeRapport.getBil().setKmKort(kørselsdistance);

        // Inserter de fundne værdier for skadesrapporten
        try {
            String InsertQUERY = "INSERT INTO skadesrapport (Lejeaftale_ID, Stelnummer, Afleveringsdato," +
                " Kørselsdistance) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement1 = conn.prepareStatement(InsertQUERY);
            preparedStatement1.setInt(1, Lejeaftale_ID);
            preparedStatement1.setString(2, Stelnummer);
            preparedStatement1.setDate(3, Date.valueOf(afleveringsdate));
            preparedStatement1.setDouble(4, kørselsdistance);
            preparedStatement1.executeUpdate();

            // Finder SkadesRapportens, som lige er blevet inserted i tabellen, auto genererede ID/primær nøgle ved at søge efter dets unikke Lejeaftale
            String SelectQUERY = "SELECT Skaderapport_ID FROM skadesrapport WHERE Lejeaftale_ID = ?";
            PreparedStatement preparedStatement2 = conn.prepareStatement(SelectQUERY);
            preparedStatement2.setInt(1, Lejeaftale_ID);
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

    // Marcus
    // Indsætter en ny skade for en skaderapport ind i vores database, samt sætter en pris for den skade
    private void createSkade(Skade skaden) {

        // Finder de værdier en Skade har, når den for første gang skal sættes/insertes ind i tabellen
        int SkadesRapport_ID = skaden.getSkaderapport_ID();
        int Skadetype_ID = skaden.getType().getId();
        double pris;

        // Finder ud af om en helt ny skade har fået indsat en pris, skadens pris er 0,
        // fordi den ikke er sat, og så finder vi en pris inde i skadetype tabellen
        if (skaden.getPrice() == 0.0) {
            pris = this.findSkadePris(Skadetype_ID);
            skaden.setPrice(pris);
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

    // Marcus
    // Finder en pris for en skade ud efter det skadetype_id den er blevet givet, fra prisliste tabellen, ellers bringes -1
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

    // Marcus
    // Selecter, altså returner et skaderapport objekt ud efter et ID/primær nøgle, ellers bringes null
    public Skaderapport viewSkadesRapport(int skaderapport_ID) {

        try {
            String skadesRapportQUERY = "SELECT * FROM skadesrapport WHERE Skaderapport_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(skadesRapportQUERY);
            preparedStatement.setInt(1, skaderapport_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Skaderapport skaderapport = new Skaderapport(skaderapport_ID);
                int lejeAftale_ID = resultSet.getInt("Lejeaftale_ID");
                LejeAftale lejeAftale = new LejeAftaleRepository().viewLejeaftale(lejeAftale_ID);
                skaderapport.setLejeaftale(lejeAftale);

                String stelnummer = resultSet.getString("Stelnummer");
                Bil bil = new BilRepository().viewBil(stelnummer);
                skaderapport.setBil(bil);

                LocalDate afleveringsdato = resultSet.getDate("Afleveringsdato").toLocalDate();
                skaderapport.setAfleveringsdate(afleveringsdato);

                double kørselsdistance = resultSet.getDouble("Kørselsdistance");
                skaderapport.setKorselsdistance(kørselsdistance);

                List<Skade> rapportensSkader = this.viewAlleSkader(skaderapport);
                skaderapport.setSkader(rapportensSkader);

                return skaderapport;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at view, altså Select, en SkadesRapport med ID'et: " + skaderapport_ID);
            throw new RuntimeException();
        }
    }

    // Marcus
    // Selecter, altså returner et skaderapport objekt, ud efter en lejeaftale, hvis den skaderapport har en lejeaftale ellers returnes null
    public Skaderapport viewSkadesRapport(LejeAftale lejeAftale) {
        try {
            String selectQUERY = "SELECT Skaderapport_ID FROM skadesrapport WHERE Lejeaftale_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(selectQUERY);
            preparedStatement.setInt(1, lejeAftale.getLejeAftale_ID());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int skaderapport_ID = resultSet.getInt(1);
                return this.viewSkadesRapport(skaderapport_ID);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var en fejl ved at view en Skaderapport for LejeAftalen: " + lejeAftale);
            throw new RuntimeException();
        }
    }

    // Selecter, altså returner et Skade objekt ud efter dets ID/primær nøgle
    private Skade viewSkade(int Skade_ID) {
        try {
            String skadeQUERY = "SELECT * FROM skade WHERE Skade_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(skadeQUERY);
            preparedStatement.setInt(1, Skade_ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Skade skade = new Skade(Skade_ID);

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


    // Marcus
    // Selecter, altså returner en liste af alle skaderapporter der er i databasen
    public List<Skaderapport> viewAlleSkadeRapporter() {
        List<Skaderapport> skadesRapporter = new ArrayList<>();
        try {
            String alleRapporterQUERY = "SELECT Skaderapport_ID FROM skadesrapport";
            PreparedStatement preparedStatement = conn.prepareStatement(alleRapporterQUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int SkadesRapport_ID = resultSet.getInt(1);
                Skaderapport rapport = this.viewSkadesRapport(SkadesRapport_ID);
                if (rapport != null) {
                    skadesRapporter.add(rapport);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at view, altså Select, alle SkadesRapporter.");
        }
        return skadesRapporter;
    }

    // Marcus
    // Selecter, altså returner en liste af alle skader der er i databasen for en specifik skaderapport
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

    // Marcus
    // Selecter, altså returner en liste af alle de skadetyper der i databasen
    public List<SkadeType> viewAlleSkadeTyper() {
        List<SkadeType> skadeTyper = new ArrayList<>();
        try {
            String selectQUERY = "SELECT Skadetype_Id FROM skadetype";
            PreparedStatement preparedStatement = conn.prepareStatement(selectQUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int skadetype_ID = resultSet.getInt("Skadetype_Id");
                SkadeType type = SkadeType.getEnum(skadetype_ID);
                skadeTyper.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at view, altså Select, alle SkadeTyper");
            throw new RuntimeException();
        }
        return skadeTyper;
    }

    // Marcus
    // Selecter, altså returner en liste af alle de skadetyper, der er tilladte at vælge til en specifik skaderapport
    public List<SkadeType> viewAlleSkadeTyper(Skaderapport rapport) {
        // Finder de SkadeTyper, som kun må rapporteres efter at en anden er blevet rapporteret
        SkadeType stenslag = SkadeType.STENSLAG;
        SkadeType flereStenslag = SkadeType.FLERE_STENSLAG;


        List<SkadeType> skadeTyper = this.viewAlleSkadeTyper();
        List<Skade> rapportensSkader = rapport.getSkader();
        List<SkadeType> valideSkadeTyper = new ArrayList<>();


        for (SkadeType skadeType : skadeTyper) {
            int antalEnSkadeTypeKanRapportes = skadeType.getTimesTypeCanBeReported();

            for (Skade skade : rapportensSkader) {
                SkadeType rapportensSkadesType = skade.getType();
                if (rapportensSkadesType == skadeType) {
                    antalEnSkadeTypeKanRapportes--;
                    if (skadeType == stenslag) {
                        valideSkadeTyper.add(flereStenslag);
                    }
                }
            }
            if (antalEnSkadeTypeKanRapportes > 0) {
                valideSkadeTyper.add(skadeType);
            }
        }

        return valideSkadeTyper;
    }


    // Marcus
    // Updater en allerede eksisterende skaderapport med de attributter der er i et Skaderapport objekt, samt de skader den indeholder
    public void updateSkadesRapport(Skaderapport skaderapport) {
        // Finder de værdier i en SkadeRapport, som skal gemmes i SkadesRapport Tabellen
        int skadesRapport_ID = skaderapport.getSkaderapport_ID();
        int lejeAftale_ID = skaderapport.getLejeaftale().getLejeAftale_ID();
        String stelnummer = skaderapport.getBil().getStelnummer();
        LocalDate afleveringsDato = skaderapport.getAfleveringsdate();
        double kørselsdistance = skaderapport.getKorselsdistance();

        // updater værdierne der lige er blevet fundet
        try {
            String updateQUERY = "UPDATE skadesrapport Set Lejeaftale_ID = ?, Stelnummer = ?, Afleveringsdato = ?, Kørselsdistance = ? WHERE Skaderapport_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(updateQUERY);
            preparedStatement.setInt(1, lejeAftale_ID);
            preparedStatement.setString(2, stelnummer);
            preparedStatement.setDate(3, java.sql.Date.valueOf(afleveringsDato));
            preparedStatement.setDouble(4, kørselsdistance);
            preparedStatement.setInt(5, skadesRapport_ID);
            preparedStatement.executeUpdate();

            // Finder bilen, som skaderapporten er udfærdiget på, og opdater bilens km kørt til det der står i rapporten
            if (kørselsdistance > 0) {
                Bil bil = new BilRepository().viewBil(stelnummer);
                bil.setKmKort(kørselsdistance);
                new BilRepository().updateBil(bil);
            }
            // Finder alle de skader en SkadeRapport har og updater dem, samt creater dem som er nye
            List<Skade> skader = skaderapport.getSkader();
            for (Skade skade : skader) {
                updateSkade(skade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at Update SkadeRapporten: " + skaderapport);
            throw new RuntimeException();
        }


    }

    // Marcus
    // Updater en allerede eksisterende skade med det attribut felt der er for dens pris der er i et Skade objekt eller creater skaden hvis den ikke har et ID
    private void updateSkade(Skade skade) {
        // Finder de værdier en skade har til at blive updated, sat ind i tabellen i stedet for hvad den skades værdi har i øjeblikket
        int skade_ID = skade.getSkade_ID();
        double pris = skade.getPrice();

        // Hvis en skades ID er 0 så må den være ny den skade vil blive Created
        if (skade_ID <= 0) {
            this.createSkade(skade);
        } // Ellers updates skaden bare, som dens nye pris er, ikke muligt at skifte en skades type eller skaderapport
        else {
            try {
                String updateQUERY = "UPDATE skade SET Pris = ? WHERE Skade_ID = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(updateQUERY);
                preparedStatement.setDouble(1, pris);
                preparedStatement.setInt(2, skade_ID);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Det var ikke muligt at update Skaden: " + skade);
                throw new RuntimeException();
            }
        }
    }



    // Marcus
    // Sletter en skaderapport fra vores database, samt alle de skader der tilknyttet til den
    public void deleteSkadesRapport(Skaderapport skaderapport) {
        try {
            String deleteQUERY = "DELETE FROM skadesrapport WHERE Skaderapport_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(deleteQUERY);
            preparedStatement.setInt(1, skaderapport.getSkaderapport_ID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at fjerne, altså Delete, SkadesRapporten: " + skaderapport);
            throw new RuntimeException();
        }
    }

    // Marcus
    // Sletter en specifik skade, i tilfælde af at rapporten skal blive, men en skade i rapporten ikke skal være der mere
    public void deleteSkade(Skade skade) {
        try {
            String deleteQUERY = "DELETE FROM skade WHERE Skade_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(deleteQUERY);
            preparedStatement.setInt(1, skade.getSkade_ID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at fjerne, altså Delete, Skaden: " + skade);
            throw new RuntimeException();
        }
    }


}
