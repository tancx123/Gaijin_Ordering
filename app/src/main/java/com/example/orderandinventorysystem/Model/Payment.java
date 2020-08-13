package com.example.orderandinventorysystem.Model;

import java.io.Serializable;

public class Payment implements Serializable {

    String payID, invID, payCust, payDate;
    double payAmount;

    public Payment(String payID, String invID, String payCust, String payDate, double payAmount) {
        this.payID = payID;
        this.invID = invID;
        this.payCust = payCust;
        this.payDate = payDate;
        this.payAmount = payAmount;
    }

    public String getPayID() {
        return payID;
    }

    public void setPayID(String payID) {
        this.payID = payID;
    }

    public String getInvID() {
        return invID;
    }

    public void setInvID(String invID) {
        this.invID = invID;
    }

    public String getPayCust() {
        return payCust;
    }

    public void setPayCust(String payCust) {
        this.payCust = payCust;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }
}
