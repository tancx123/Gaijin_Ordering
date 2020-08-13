package com.example.orderandinventorysystem.Model;

import java.io.Serializable;

public class Invoice implements Serializable {

    private String invID, salesID, invCustName, invDate, invStatus, invDueDate;
    private double invPrice;

    public Invoice(String invID, String salesID, String invCustName, String invDate, String invStatus, double invPrice, String invDueDate) {
        this.invID = invID;
        this.salesID = salesID;
        this.invCustName = invCustName;
        this.invDate = invDate;
        this.invStatus = invStatus;
        this.invPrice = invPrice;
        this.invDueDate = invDueDate;
    }

    public String getInvDueDate() {
        return invDueDate;
    }

    public void setInvDueDate(String invDueDate) {
        this.invDueDate = invDueDate;
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
