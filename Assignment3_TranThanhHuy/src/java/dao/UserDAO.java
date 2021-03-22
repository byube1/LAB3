/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.Myconnection;
import dto.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class UserDAO {

    public User login(String email, String pass) throws SQLException {
        User cus = null;
        Connection cn = Myconnection.makeConnection();
        if (cn != null) {
            String sql = "SELECT Email,Password,UserName,Role,Status,CreateDate "
                    + "FROM   dbo.UserInfo "
                    + "WHERE UserID = ? AND Password = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String emailUser = rs.getString("Email");
                String passUser = rs.getString("Password");
                String userName = rs.getString("UserName");
                String role = rs.getString("Role");
                String status = rs.getString("Status");
                String createDate = rs.getString("CreateDate");
                cus = new User(emailUser, passUser, userName, role, status, createDate);
            }
            rs.close();
            cn.close();
        }
        return cus;
    }

    public int registrationUser(String userID,String email, String pass, String userName, String phone, String address,int userCode) throws SQLException {
        Connection cn = Myconnection.makeConnection();
        int result = -1;
        if (cn != null) {
            String sql = "INSERT INTO dbo.UserInfo "
                    + "( Email,UserID,Password,UserName,Phone,Address,Role,Status,Code,CreateDate ) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?, ?,N'User',N'New',?,GETDATE() )";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, userID);
            pst.setString(3, pass);
            pst.setString(4, userName);
            pst.setString(5, phone);
            pst.setString(6, address);
            pst.setInt(7, userCode);
            result = pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    
     public int verifyUser(String email) throws SQLException {
        Connection cn = Myconnection.makeConnection();
        int result = 0;
        if (cn != null) {
            String sql = "UPDATE dbo.UserInfo SET Status = N'Active' WHERE Email = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);        
            result = pst.executeUpdate();
            pst.close();
        }
        cn.close();
        return result;
    }
     
      public int getCode(String email) throws SQLException {
        int code = 0;
        Connection cn = Myconnection.makeConnection();
        if (cn != null) {
            String sql = "SELECT Code FROM dbo.UserInfo WHERE Email = ? ";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
               code = rs.getInt("Code");
            }
            rs.close();
            cn.close();
        }
        return code;
    }

}
