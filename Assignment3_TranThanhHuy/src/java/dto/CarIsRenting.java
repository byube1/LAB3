/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author admin
 */
public class CarIsRenting {
    private int idRenting;
    private int orderID;
    private int carId;
    private int amountRenting;
    private String nameCar;
    private String rentDate;
    private String returnDate;
    private float price;
    private float rate;
    private String status;
  

    public CarIsRenting(int idRenting, int orderID, int carId, int amountRenting,String name, String rentDate, String returnDate, float price, float rate,String status) {
        this.idRenting = idRenting;
        this.orderID = orderID;
        this.carId = carId;
        this.amountRenting = amountRenting;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.price = price;
        this.rate = rate;
        this.status = status;
        this.nameCar = name;
    }
    
    public CarIsRenting(int idRenting, int orderID, int carId, int amountRenting, String rentDate, String returnDate, float price) {
        this.idRenting = idRenting;
        this.orderID = orderID;
        this.carId = carId;
        this.amountRenting = amountRenting;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.price = price;     
    }

    public CarIsRenting(String nameCar,int carId, int amountRenting, float price) {
        this.carId = carId;
        this.amountRenting = amountRenting;
        this.price = price;
        this.nameCar = nameCar;
    }
    
     public CarIsRenting(String nameCar,int carId, int amountRenting, float price, String rentDate, String returnDate) {
        this.carId = carId;
        this.amountRenting = amountRenting;
        this.price = price;
        this.nameCar = nameCar;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }
    
    

    /**
     * @return the idRenting
     */
    public int getIdRenting() {
        return idRenting;
    }

    /**
     * @param idRenting the idRenting to set
     */
    public void setIdRenting(int idRenting) {
        this.idRenting = idRenting;
    }

    /**
     * @return the orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the carId
     */
    public int getCarId() {
        return carId;
    }

    /**
     * @param carId the carId to set
     */
    public void setCarId(int carId) {
        this.carId = carId;
    }

    /**
     * @return the amountRenting
     */
    public int getAmountRenting() {
        return amountRenting;
    }

    /**
     * @param amountRenting the amountRenting to set
     */
    public void setAmountRenting(int amountRenting) {
        this.amountRenting = amountRenting;
    }

    /**
     * @return the rentDate
     */
    public String getRentDate() {
        return rentDate;
    }

    /**
     * @param rentDate the rentDate to set
     */
    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    /**
     * @return the returnDate
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    /**
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * @return the nameCar
     */
    public String getNameCar() {
        return nameCar;
    }

    /**
     * @param nameCar the nameCar to set
     */
    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    /**
     * @return the total
     */
    public float getTotal() {
        return this.price*this.amountRenting;
    }

    /**
     * @return the rate
     */
    public float getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(float rate) {
        this.rate = rate;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @param total the total to set
     */
   
    
    
    
    
}
