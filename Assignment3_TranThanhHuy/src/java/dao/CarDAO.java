/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.Myconnection;
import dto.Car;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class CarDAO {

    public int getTotalCar() throws SQLException {
        int result = 0;
        Connection cn = Myconnection.makeConnection();
        if (cn != null) {
            String sql = "SELECT COUNT(CarId) AS NumberOfCar FROM dbo.Car";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt("NumberOfCar");
            }
            rs.close();
            pst.close();
            cn.close();
        }
        return result;
    }

    public ArrayList<Car> getCar(int page, int pageSize) throws SQLException {
        ArrayList<Car> result = new ArrayList<>();
        Connection cn = Myconnection.makeConnection();
        if (cn != null) {
            String sql = "SELECT CarId,Name,Bard,CarType,Color,YearManufacture,Price,Amount,Description,Img,Status,CreateDate "
                    + "FROM dbo.Car ORDER BY CreateDate DESC "
                    + "OFFSET ? ROWS FETCH NEXT ? ROW ONLY ";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, page);
            pst.setInt(2, pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("CarId");
                String name = rs.getString("Name");
                float price = rs.getFloat("Price");
                int amount = rs.getInt("Amount");
                String description = rs.getString("Description");
                String bard = rs.getString("Bard");
                String createDate = rs.getString("CreateDate");
                String status = rs.getString("Status");
                String img = rs.getString("Img");
                String type = rs.getString("CarType");
                String color = rs.getString("Color");
                String yearManufacture = rs.getString("YearManufacture");
                Car newCar = new Car(id, name, bard, type, color, yearManufacture, description, img, createDate, status, price, amount);
                result.add(newCar);
            }
            rs.close();
            pst.close();
            cn.close();
        }
        return result;
    }

    public int getTotalCar(String name, String catelogy, String rentalDate, String returnDate, int amount) throws SQLException {
        int result = 0;
        Connection cn = Myconnection.makeConnection();
        if (cn != null) {
            String sql = "SELECT COUNT(Car.CarId) AS NumberOfCar  "
                    + "	FROM dbo.Car FULL OUTER JOIN ( "
                    + "	SELECT CarId,AmounrRenting  "
                    + "	FROM dbo.CarIsRenting  WHERE  (RentDate <= ? AND ReturnDate >= ? OR  "
                    + "	RentDate <= ? AND ReturnDate >= ?) AND  Status <> N'Inactive' ) AS CarRenting "
                    + "	ON CarRenting.CarId = Car.CarId "
                    + "	WHERE ISNULL(Amount-CarRenting.AmounrRenting,Amount) >0 "
                    + "	AND Name LIKE ? AND CarType LIKE ? AND ISNULL(Amount-CarRenting.AmounrRenting,Amount) >= ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, rentalDate);
            pst.setString(2, rentalDate);
            pst.setString(3, returnDate);
            pst.setString(4, returnDate);
            pst.setString(5, "%" + name + "%");
            pst.setString(6, "%" + catelogy + "%");
            pst.setInt(7, amount);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result = rs.getInt("NumberOfCar");
            }
            rs.close();
            pst.close();
            cn.close();
        }
        return result;
    }

    public ArrayList<Car> getCarSearch(int page, int pageSize, String name, String catelogy, String rentalDate, String returnDate, int amount) throws SQLException {
        ArrayList<Car> result = new ArrayList<>();
        Connection cn = Myconnection.makeConnection();
        if (cn != null) {
            String sql = "SELECT Car.CarId,Name,Bard,CarType,Color,YearManufacture,Price, "
                    + "	ISNULL(Amount-CarRenting.AmounrRenting,Amount) AS RealAmount, "
                    + "	Description,Img,Status,CreateDate "
                    + "	FROM dbo.Car FULL OUTER JOIN ( "
                    + "	SELECT CarId,AmounrRenting "
                    + "	FROM dbo.CarIsRenting  WHERE  (RentDate <= ? AND ReturnDate >= ? OR  "
                    + "	RentDate <= ? AND ReturnDate >=?) AND  Status <> N'Inactive' ) AS CarRenting "
                    + "	ON CarRenting.CarId = Car.CarId "
                    + "	WHERE ISNULL(Amount-CarRenting.AmounrRenting,Amount) >0 "
                    + "	AND Name LIKE ? AND CarType LIKE ? AND ISNULL(Amount-CarRenting.AmounrRenting,Amount) >= ? "
                    + "	ORDER BY YearManufacture DESC "
                    + "	OFFSET ?  ROWS FETCH NEXT ? ROW ONLY";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, rentalDate);
            pst.setString(2, rentalDate);
            pst.setString(3, returnDate);
            pst.setString(4, returnDate);
            pst.setString(5, "%" + name + "%");
            pst.setString(6, "%" + catelogy + "%");
            pst.setInt(7, amount);
            pst.setInt(8, page);
            pst.setInt(9, pageSize);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("CarId");
                String nameCar = rs.getString("Name");
                float price = rs.getFloat("Price");
                int amountCar = rs.getInt("RealAmount");
                String description = rs.getString("Description");
                String bard = rs.getString("Bard");
                String createDate = rs.getString("CreateDate");
                String status = rs.getString("Status");
                String img = rs.getString("Img");
                String type = rs.getString("CarType");
                String color = rs.getString("Color");
                String yearManufacture = rs.getString("YearManufacture");
                Car newCar = new Car(id, nameCar, bard, type, color, yearManufacture, description, img, createDate, status, price, amountCar);
                result.add(newCar);
            }
            rs.close();
            pst.close();
            cn.close();
        }
        return result;
    }

}
