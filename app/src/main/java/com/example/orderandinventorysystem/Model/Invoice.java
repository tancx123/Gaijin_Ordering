package com.example.orderandinventorysystem.Model;

public class Invoice {

    private String invID, salesID, invCustName, invDate, invStatus;
    private double invPrice;

    public Invoice(String invID, String salesID, String invCustName, String invDate, String invStatus, double invPrice) {
        this.invID = invID;
        this.salesID = salesID;
        this.invCustName = invCustName;
        this.invDate = invDate;
        this.invStatus = invStatus;
        this.invPrice = invPrice;
    }

    public String getInvID() {
        return invID;
    }

    public void setInvID(String invID) {
        this.invID = invID;
    }

    public String getSalesID() {
        return salesID;
    }

    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public String getInvCustName() {
        return invCustName;
    }

    public void setInvCustName(String invCustName) {
        this.invCustName = invCustName;
    }

    public String getInvDate() {
        return invDate;
    }

    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    public String getInvStatus() {
        return invStatus;
    }

    public void setInvStatus(String invStatus) {
        this.invStatus = invStatus;
    }

    public double getInvPrice() {
        return invPrice;
    }

    public void setInvPrice(double invPrice) {
        this.invPrice = invPrice;
    }
}
