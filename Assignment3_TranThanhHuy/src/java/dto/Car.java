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
public class Car {
    private int idCar;
    private String name;
    private String bard;   
    private String type;   
    private String color;
    private String yearManufacture;
    private String desc;
    private String img;
    private String createDate;
    private String status;
    private float price;
    private int amount;

    public Car(int idCar, String name, String bard, String type, String color, String yearManufacture, String desc, String img, String createDate,String status, float price, int amount) {
        this.idCar = idCar;
        this.name = name;
        this.bard = bard;
        this.type = type;
        this.color = color;
        this.yearManufacture = yearManufacture;
        this.desc = desc;
        this.img = img;
        this.createDate = createDate;
        this.price = price;
        this.amount = amount;
        this.status = status;
    }

    /**
     * @return the idCar
     */
    public int getIdCar() {
        return idCar;
    }

    /**
     * @param idCar the idCar to set
     */
    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the bard
     */
    public String getBard() {
        return bard;
    }

    /**
     * @param bard the bard to set
     */
    public void setBard(String bard) {
        this.bard = bard;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the yearManufacture
     */
    public String getYearManufacture() {
        return yearManufacture;
    }

    /**
     * @param yearManufacture the yearManufacture to set
     */
    public void setYearManufacture(String yearManufacture) {
        this.yearManufacture = yearManufacture;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the img
     */
    public String getImg() {
        return img;
    }

    /**
     * @param img the img to set
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * @return the createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
}
