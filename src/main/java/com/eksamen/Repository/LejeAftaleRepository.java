package com.eksamen.Repository;

import com.eksamen.Model.Bil;
import com.eksamen.Model.DCM;
import com.eksamen.Model.LejeAftale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LejeAftaleRepository {

    private final Connection conn = DCM.getConn();

}
