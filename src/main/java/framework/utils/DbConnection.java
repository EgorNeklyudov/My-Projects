package framework.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/union_reporting?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection connectMySqlLocalServer() {

            try{
                return DriverManager.getConnection(URL,USERNAME,PASSWORD);
            }catch(Exception ex){
                System.out.println(ex.getMessage());
                System.out.println("Connection failed...");
                throw new RuntimeException(ex);
            }
        }
    }
