package com.example.orderandinventorysystem.Model;

public class Sales {

    private String salesID, saleCustName, salesDate,salesStatus;
    private double salesPrice;

    public Sales(String salesID, String saleCustName, String salesDate, double salesPrice, String salesStatus) {
        this.salesID = salesID;
        this.saleCustName = saleCustName;
        this.salesDate = salesDate;
        this.salesPrice = salesPrice;
        this.salesStatus = salesStatus;
    }

    public String getSalesID() {
        return salesID;
    }

    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public String getSaleCustName() {
        return saleCustName;
    }

    public void setSaleCustName(String saleCustName) {
        this.saleCustName = saleCustName;
    }

    public String getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getSalesStatus() {
        return salesStatus;
    }

    public void setSalesStatus(String salesStatus) {
        this.salesStatus = salesStatus;
    }
}
