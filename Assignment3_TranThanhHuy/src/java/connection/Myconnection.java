/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author admin
 */
public class Myconnection {
     public static Connection makeConnection() {
        Connection connected = null;
        try {
            // nap driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // tao ket noi
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Assignment3_TranThanhHuy";
            connected = DriverManager.getConnection(url, "sa", "123456");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return connected;
    }
}
