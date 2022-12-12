package com.eksamen.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DCM {

    private static Connection conn = createConnection();

        private static Connection createConnection() {
            String hostname = System.getenv("hostname");
            String username = System.getenv("username");
            String password = System.getenv("password");
            try {
                conn = DriverManager.getConnection(hostname, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("TASK FAILED, THROWING EXCEPTION");
                throw new RuntimeException();
            }
            System.out.println("CREATING CONNECTION");
            return conn;
        }

        public static Connection getConn() {
            return conn;
        }
    }
