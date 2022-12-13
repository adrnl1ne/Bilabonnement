package com.eksamen.Repository;

import com.eksamen.Model.Bil.Bil;
import com.eksamen.Model.Bil.BilModel;
import com.eksamen.Model.Bil.Biltilstand;
import com.eksamen.utilities.DCM;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BilRepository {

    private CallableStatement cstmt;
    private ResultSet rs;
    private final Connection conn = DCM.getConn();


    // Jakob
    // Returner et Bil objekt ud fra en primær nøgle der passer med en række bil tabellen i vores database
    public Bil viewBil(String Stelnummer) {
        try {
            String Model_QUERY = "SELECT * FROM Bil WHERE Stelnummer=?";
            PreparedStatement preparedStatement = conn.prepareStatement(Model_QUERY);
            preparedStatement.setString(1, Stelnummer);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String stelnummer = resultSet.getString("Stelnummer");
                int Tilstands_ID = resultSet.getInt("Tilstands_ID");
                Biltilstand tilstand = Biltilstand.getEnum(Tilstands_ID);
                int Model_ID = resultSet.getInt("Model_ID");
                double KmKørt = resultSet.getDouble("Km_Kørt");

                Bil bil = new Bil(stelnummer);
                bil.setBiltilstand(tilstand);
                bil.setKmKort(KmKørt);
                BilModel bilModel = new BilModelRepository().viewBilmodel(Model_ID);
                bil.setBilModel(bilModel);

                return bil;

               /*
                if (resultSet1.next()) {
                    String Tilstand_QUERY = "SELECT Biltilstand FROM biltilstand WHERE TilStands_ID = ?";
                PreparedStatement preparedStatement1 = conn.prepareStatement(Tilstand_QUERY);
                preparedStatement1.setInt(1, Tilstands_ID);
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                }*/
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ikke muligt at se bil med stelnummeret: " + Stelnummer);
            throw new RuntimeException(e);
        }
        return null;
    }

    // Jakob
    // Returner en liste af alle Bil objekter der er i bil tabellen i vores database
    public List<Bil> viewAlleBiler() {
        List<Bil> alleBiler = new ArrayList<>();

        try {
            String QUERY = "SELECT Stelnummer FROM bil";
            PreparedStatement preparedStatement = conn.prepareStatement(QUERY);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String stelnummer = resultSet.getString("Stelnummer");
                alleBiler.add(viewBil(stelnummer));
            }
        } catch (SQLException e) {
            System.err.println("Fejl, Kan ikke se alle biler");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return alleBiler;
    }

    // Jakob
    // Returner en liste af alle de biler som har tilstanden Klar fra vores database
    public List<Bil> viewKlarBil() {
        List<Bil> udlejedeBiler = new ArrayList<>();

        try {

            //Laver Callable statement
            cstmt = conn.prepareCall("{call viewInfo(1)}");
            cstmt.execute();

            rs = cstmt.getResultSet();
            while (rs.next()) {
                String stelnummer = rs.getString("Stelnummer");
                udlejedeBiler.add(viewBil(stelnummer));
            }


        } catch (SQLException e) {
            System.err.println("Fejl, kan ikke hente biler");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return udlejedeBiler;
    }

    public List<Bil> viewUdlejetBiler() {
        List<Bil> udlejedeBiler = new ArrayList<>();

        try {

            //Laver Callable statement
            cstmt = conn.prepareCall("{call viewInfo(2)}");
            cstmt.execute();

            rs = cstmt.getResultSet();
            while (rs.next()) {
                String stelnummer = rs.getString("Stelnummer");
                udlejedeBiler.add(viewBil(stelnummer));
            }


        } catch (SQLException e) {
            System.err.println("Fejl, kan ikke hente biler");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return udlejedeBiler;
    }


    public List<Bil> viewCheckUpBiler() {
        List<Bil> udlejedeBiler = new ArrayList<>();

        try {

            //Laver Callable statement
            cstmt = conn.prepareCall("{call viewInfo(3)}");
            cstmt.execute();

            rs = cstmt.getResultSet();
            while (rs.next()) {
                String stelnummer = rs.getString("Stelnummer");
                udlejedeBiler.add(viewBil(stelnummer));
            }


        } catch (SQLException e) {
            System.err.println("Fejl, kan ikke hente biler");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return udlejedeBiler;
    }

    public List<Bil> viewSkadedeBiler() {
        List<Bil> udlejedeBiler = new ArrayList<>();

        try {

            //Laver Callable statement
            cstmt = conn.prepareCall("{call viewInfo(4)}");
            cstmt.execute();

            rs = cstmt.getResultSet();
            while (rs.next()) {
                String stelnummer = rs.getString("Stelnummer");
                udlejedeBiler.add(viewBil(stelnummer));
            }


        } catch (SQLException e) {
            System.err.println("Fejl, kan ikke hente biler");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return udlejedeBiler;
    }

    // Jakob
    // Dette updater en allerede eksisterende bil i vores database med f.eks. en anden Tilstand eller Km_Kørt
    public void updateBil(Bil bil) {
        viewBil(bil.getStelnummer());
        try {
            String Stelnummer = bil.getStelnummer();
            Biltilstand tilstand = bil.getBiltilstand();
            int Tilstands_ID = tilstand.getId();
            double Km_Kørt = bil.getKmKort();
            String QUERY = "UPDATE bil SET Tilstands_ID =?, Km_Kørt =? WHERE Stelnummer=?";
            PreparedStatement preparedStatement = conn.prepareStatement(QUERY);
            preparedStatement.setInt(1, Tilstands_ID);
            preparedStatement.setDouble(2, Km_Kørt);
            preparedStatement.setString(3, Stelnummer);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Kan ikke opdatere " + bil);
            throw new RuntimeException(e);
        }
    }





}