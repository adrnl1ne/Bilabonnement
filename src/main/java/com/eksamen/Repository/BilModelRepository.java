package com.eksamen.Repository;

import com.eksamen.Model.BilModel;
import com.eksamen.Model.Energitype;
import com.eksamen.utilities.DCM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BilModelRepository {

    private static final Connection conn = DCM.getConn();
    public BilModel viewBilmodel(int Model_ID) {
        try {
            String Model_ID_QUERY = "SELECT * FROM bilmodel WHERE Model_ID=?";
            PreparedStatement preparedStatement = conn.prepareStatement(Model_ID_QUERY);
            preparedStatement.setInt(1, Model_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int model_ID = resultSet.getInt("Model_ID");
                int Energitype_ID = resultSet.getInt("Energitype_ID");
                String Model = resultSet.getString("Model");
                boolean isGearManuel = resultSet.getBoolean("isGearManuel");
                double CO2_Udslip = resultSet.getDouble("CO2_Udslip");
                double Stålpris = resultSet.getDouble("Stålpris");
                double KmPrX = resultSet.getDouble("KmPrX");

                String EnergiType_QUERY = "SELECT Energi FROM energitype WHERE Energitype_ID=?";
                PreparedStatement preparedStatement1 = conn.prepareStatement(EnergiType_QUERY);
                preparedStatement1.setInt(1, Energitype_ID);
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                if (resultSet1.next()) {
                    Energitype type = Energitype.getEnum(resultSet1.getInt(Energitype_ID));
                    BilModel bilModel = new BilModel(model_ID);
                    bilModel.setEnergitype(type);
                    bilModel.setModel(Model);
                    bilModel.setGearManuel(isGearManuel);
                    bilModel.setStålpris(Stålpris);
                    bilModel.setCO2_Udslip(CO2_Udslip);
                    bilModel.setKmPrX(KmPrX);
                    return bilModel;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Kan ikke ikke se bil med model_id" + Model_ID);
            throw new RuntimeException(e);
        }
        return null;
    }
}
