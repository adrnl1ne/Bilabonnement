package com.eksamen.Repository;

import com.eksamen.Model.Abonnement.AbonnementBilModel;
import com.eksamen.Model.Bil.BilModel;
import com.eksamen.Model.Bil.Energitype;
import com.eksamen.utilities.DCM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BilModelRepository {

    private static final Connection conn = DCM.getConn();

    private String viewBilmærke(int Bilmærke_ID) {
        try {
            String selectQUERY = "SELECT Mærket FROM mærke WHERE Bilmærke_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(selectQUERY);
            preparedStatement.setInt(1, Bilmærke_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
            throw new SQLException();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at skabe et Bilmærke, altså view, et Bilmærke med ID'et: " + Bilmærke_ID);
            throw new RuntimeException();
        }
    }

    private String viewUdstyr (int Udstyr_ID) {
        try {
            String SelectQUERY = "SELECT Udstyr FROM udstyr WHERE Udstyr_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(SelectQUERY);
            preparedStatement.setInt(1, Udstyr_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
            throw new SQLException();
        } catch (SQLException e){
            e.printStackTrace();
            System.err.println("Det var ikke muligt at view et udstyr med ID'et: " + Udstyr_ID);
            throw new RuntimeException();
        }
    }

    private List<String> viewAltUdstyrForEnBilModel(int BilModel_ID) {
        List<String> udstyrEnBilModelHar = new ArrayList<>();
        try {
            String selectQUERY = "SELECT Udstyr_ID FROM modelharudstyr WHERE Model_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(selectQUERY);
            preparedStatement.setInt(1, BilModel_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int etUdstyrsID = resultSet.getInt(1);
                String etUdstyr = this.viewUdstyr(etUdstyrsID);
                udstyrEnBilModelHar.add(etUdstyr);
            }
            return udstyrEnBilModelHar;
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Der var ikke muligt at view alt det udstyr der er for en BilModel med ID'et: " + BilModel_ID);
            throw new RuntimeException();
        }
    }

    private AbonnementBilModel viewAbonnementBilmodel(int Model_ID) {
        try {
            String selectQUERY = "SELECT * FROM abnmtpris WHERE Model_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(selectQUERY);
            preparedStatement.setInt(1, Model_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                boolean isUnlimited = resultSet.getBoolean("isUnlimited");
                double md3Pris = resultSet.getDouble("Md3Pris");
                double md6Pris = resultSet.getDouble("Md6Pris");
                double md12Pris = resultSet.getDouble("Md12Pris");
                double md24Pris = resultSet.getDouble("Md24Pris");
                double md36Pris = resultSet.getDouble("Md36Pris");
                double farveValgPris = resultSet.getDouble("FarvePris");
                double udbetaling = resultSet.getDouble("Udbetaling");

                AbonnementBilModel abnmtBilmodel = new AbonnementBilModel();
                abnmtBilmodel.setBilModel(Model_ID);
                abnmtBilmodel.setUnlimited(isUnlimited);
                abnmtBilmodel.setThreeMonthsPris(md3Pris);
                abnmtBilmodel.setSiXMonthsPris(md6Pris);
                abnmtBilmodel.setTwelveMonthsPrice(md12Pris);
                abnmtBilmodel.setTwentyFourMonthsPrice(md24Pris);
                abnmtBilmodel.setThirtysixMonthsPrice(md36Pris);
                abnmtBilmodel.setPriceForColorChoice(farveValgPris);
                abnmtBilmodel.setStartUdbetaling(udbetaling);
                return abnmtBilmodel;
            }
            throw new SQLException();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Det var ikke muligt at view en AbonnementsPriser med ID'et: " + Model_ID);
            throw new RuntimeException();
        }
    }


    public BilModel viewBilmodel(int Model_ID) {
        try {
            String Model_ID_QUERY = "SELECT * FROM bilmodel WHERE Model_ID=?";
            PreparedStatement preparedStatement = conn.prepareStatement(Model_ID_QUERY);
            preparedStatement.setInt(1, Model_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int Bilmærke_ID = resultSet.getInt("Bilmærke_ID");
                String bilmærke = this.viewBilmærke(Bilmærke_ID);
                int model_ID = resultSet.getInt("Model_ID");
                int Energitype_ID = resultSet.getInt("Energitype_ID");
                String Model = resultSet.getString("Model");
                boolean isGearManuel = resultSet.getBoolean("isGearManuel");
                double CO2_Udslip = resultSet.getDouble("CO2_Udslip");
                double Stålpris = resultSet.getDouble("Stålpris");
                double KmPrX = resultSet.getDouble("KmPrX");
                List<String> udstyr = this.viewAltUdstyrForEnBilModel(Model_ID);
                Energitype type = Energitype.getEnum(resultSet.getInt(Energitype_ID));

                BilModel bilModel = new BilModel(model_ID);
                bilModel.setMærke(bilmærke);
                bilModel.setEnergitype(type);
                bilModel.setModel(Model);
                bilModel.setGearManuel(isGearManuel);
                bilModel.setStålpris(Stålpris);
                bilModel.setCO2_Udslip(CO2_Udslip);
                bilModel.setKmPrX(KmPrX);
                bilModel.setUdstyr(udstyr);
                AbonnementBilModel abonnementBilModel = viewAbonnementBilmodel(Model_ID);
                bilModel.setAbopris(abonnementBilModel);
                return bilModel;

            }
            throw new SQLException();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Kan ikke ikke se bil med model_id" + Model_ID);
            throw new RuntimeException(e);
        }

    }




}
