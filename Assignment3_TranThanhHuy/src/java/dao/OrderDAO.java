/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.Myconnection;
import dto.CarIsRenting;
import dto.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class OrderDAO {

    public float getDiscount(String disocunt, String today) throws SQLException {
        float result = 0;
        Connection cn = Myconnection.makeConnection();
        if (cn != null) {
            String sql = "SELECT Value FROM dbo.Discount WHERE DiscountCode = ? AND EndDate > ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, disocunt);
            pst.setString(2, today);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getFloat("Value");
            }
            rs.close();
            pst.close();
            cn.close();
        }
        return result;
    }

    public int confirmOrder(int idOrder, String emailUser, String payment, String discountName, float total) throws SQLException {
        Connection cn = Myconnection.makeConnection();
        int result = -1;
        if (cn != null) {
            String sql = "INSERT INTO dbo.Ordertbl "
                    + "	(IdOrder,EmailUser,Total,PaymentMenthod,DateCreate,Status,DiscountCode) "
                    + "	VALUES "
                    + "	( ?,?,?,?,GETDATE(), N'Active',?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idOrder);
            pst.setString(2, emailUser);
            pst.setFloat(3, total);
            pst.setString(4, payment);
            pst.setString(5, discountName);
            result = pst.executeUpdate();
            cn.close();
        }
        return result;
    }

    public int confirmCarRenting(int idOrder, int carId, int amountRenting, String rentDate, String returnDate, float price) throws SQLException {
        Connection cn = Myconnection.makeConnection();
        int result = -1;
        if (cn != null) {
            String sql = "INSERT INTO dbo.CarIsRenting "
                    + "	(OderId,CarId,AmounrRenting,RentDate,ReturnDate,Price,Status) "
                    + "	VALUES "
                    + "	(?,?,?,?,?,?,N'Active')";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idOrder);
            pst.setInt(2, carId);
            pst.setInt(3, amountRenting);
            pst.setString(4, rentDate);
            pst.setString(5, returnDate);
            pst.setFloat(6, price);
            result = pst.executeUpdate();
            cn.close();
        }
        return result;
    }

    public ArrayList<Order> getOderHistory(String emailUser, String nameCar, String dateCreate) throws SQLException {
        ArrayList<Order> result = new ArrayList<>();
        Connection cn = Myconnection.makeConnection();
        if (cn != null) {
            String sql = "SELECT DISTINCT IdOrder,Total,PaymentMenthod,DateCreate,Ordertbl.Status "
                    + "	FROM dbo.Ordertbl JOIN dbo.CarIsRenting "
                    + "	ON CarIsRenting.OderId = Ordertbl.IdOrder "
                    + "	JOIN dbo.Car ON Car.CarId = CarIsRenting.CarId "
                    + "	WHERE "
                    + "	Ordertbl.EmailUser = ? "
                    + "	AND "
                    + "	Car.Name LIKE ? "
                    + "	OR  DateCreate = ? ORDER BY DateCreate DESC ";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, emailUser);
            pst.setString(2, "%" + nameCar + "%");
            pst.setString(3, dateCreate);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int idOrder = rs.getInt("IdOrder");
                float total = rs.getFloat("Total");
                String payment = rs.getString("PaymentMenthod");
                String date = rs.getString("DateCreate");
                String status = rs.getString("Status");
                Order newOrder = new Order(idOrder, total, payment, date, status);
                result.add(newOrder);
            }
            rs.close();
            pst.close();
            cn.close();
        }
        return result;
    }

    public ArrayList<CarIsRenting> getDetailHistory(int idOrder) throws SQLException {
        ArrayList<CarIsRenting> result = new ArrayList<>();
        Connection cn = Myconnection.makeConnection();
        if (cn != null) {
            String sql = "	SELECT IdRenting,OderId,CarIsRenting.CarId,AmounrRenting,Car.Name, "
                    + "	FORMAT(RentDate,'yyyy-MM-dd') AS RentDate,"
                    + "    FORMAT(ReturnDate,'yyyy-MM-dd') AS ReturnDate,"
                    + "    CarIsRenting.Price,Rate,CarIsRenting.Status "
                    + "	FROM dbo.CarIsRenting JOIN dbo.Car ON Car.CarId = CarIsRenting.CarId "
                    + "	WHERE OderId = ? ";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idOrder);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int idRent = rs.getInt("IdRenting");
                int orderId = rs.getInt("OderId");
                int carId = rs.getInt("CarId");
                int amount = rs.getInt("AmounrRenting");
                String rentDate = rs.getString("RentDate");
                String returnDate = rs.getString("ReturnDate");
                float price = rs.getFloat("Price");
                float rate = rs.getFloat("Rate");
                String status = rs.getString("Status");
                String name = rs.getString("Name");
                CarIsRenting newItem = new CarIsRenting(idRent, orderId, carId, amount, name, rentDate, returnDate, price, rate, status);
                result.add(newItem);
            }
            rs.close();
            pst.close();
            cn.close();
        }
        return result;
    }

    public int setStatusOrder(int idOrder) throws SQLException {
        Connection cn = Myconnection.makeConnection();
        int result = 0;
        if (cn != null) {
            String sql = "UPDATE dbo.Ordertbl SET Status = N'Inactive' WHERE IdOrder = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idOrder);
            result = pst.executeUpdate();
            pst.close();
        }
        cn.close();
        return result;

    }

    public int setStatusOrderDetail(int idOrder) throws SQLException {
        Connection cn = Myconnection.makeConnection();
        int result = 0;
        if (cn != null) {
            String sql = "UPDATE dbo.CarIsRenting SET Status = N'Inactive' WHERE OderId = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idOrder);
            result = pst.executeUpdate();
            pst.close();
        }
        cn.close();
        return result;

    }

    public int setRate(int idRenting, float rate) throws SQLException {
        Connection cn = Myconnection.makeConnection();
        int result = 0;
        if (cn != null) {
            String sql = "UPDATE dbo.CarIsRenting SET Rate = ? WHERE IdRenting = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setFloat(1, rate);
            pst.setInt(2, idRenting);
            result = pst.executeUpdate();
            pst.close();
        }
        cn.close();
        return result;

    }
}
