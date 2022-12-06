package com.eksamen.Repository;

import com.eksamen.Model.Bil;
import com.eksamen.Model.DCM;
import com.eksamen.Model.LejeAftale;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BilRepository {

    private static CallableStatement cstmt = null;
    private static ResultSet rs = null;
    private final Connection conn = DCM.getConn();


    public Bil viewBil(String Stelnummer) {
        try {
            String Model_QUERY = "SELECT * FROM Bil WHERE Stelnummer=?";
            PreparedStatement preparedStatement = conn.prepareStatement(Model_QUERY);
            preparedStatement.setString(1, Stelnummer);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String stelnummer = resultSet.getString("Stelnummer");
                String Bilmodel = resultSet.getString("Bilmodel");
                int Biltilstand = resultSet.getInt("Biltilstand");
                Bil bil = new Bil(stelnummer, Bilmodel, Biltilstand);
                bil.setStelnummer(stelnummer);
                bil.setBilmodel(Bilmodel);
                bil.setBiltilstand(Biltilstand);
                return bil;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ikke muligt at se bil med stelnummeret: " + Stelnummer);
        }
        return null;
    }



    public List<Bil> viewUdlejetBiler() {
        List<Bil> udlejedeBiler = new ArrayList<>();
        try {
            cstmt = conn.prepareCall("{call viewudlejet(2)}");
            cstmt.execute();
            rs = cstmt.getResultSet();
            String stelnummer = rs.getString("Stelnummer");
            udlejedeBiler.add(viewBil(stelnummer));

        } catch (SQLException e) {
            System.err.println("Fejl, kan ikke hente biler");
            e.printStackTrace();
        }
        return udlejedeBiler;
    }


    public List<LejeAftale> viewLejeaftelerPåUdlejetBiler() {
        List<LejeAftale> udlejetBilsLejeaftale = new ArrayList<>();
        List<Bil> udlejetBiler = viewUdlejetBiler();

        for (Bil udlejetBil: udlejetBiler) {
            try {
                String Query = "SELECT Lejeaftale_ID FROM lejeaftale WHERE Stelnummer=? " +
                        "AND Lejeaftale_ID=(SELECT MAX(Lejeaftale_ID) FROM lejeaftale WHERE Stelnummer=?)";

                PreparedStatement preparedStatement = conn.prepareStatement(Query);
                preparedStatement.setString(1, udlejetBil.getStelnummer());
                preparedStatement.setString(2, udlejetBil.getStelnummer());
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    int lejeaftale_ID = resultSet.getInt(1);
                    LejeAftale lejeAftale = new LejeAftaleRepository().viewLejeaftale(lejeaftale_ID);
                    if (lejeAftale != null) {
                        udlejetBilsLejeaftale.add(lejeAftale);
                    }
                }


            } catch (SQLException e){
                System.err.println("Kan ikke viewe udlejde bilers aftale");
                e.printStackTrace();

            }
        }

        return udlejetBilsLejeaftale;

    }

}
