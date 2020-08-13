package com.example.orderandinventorysystem.Model;

import java.io.Serializable;

public class Pack implements Serializable {

    private String packID, packDate, salesID, packStatus;

    public Pack(String packID, String packDate, String salesID, String packStatus) {
        this.packID = packID;
        this.packDate = packDate;
        this.salesID = salesID;
        this.packStatus = packStatus;
    }

    public String getPackStatus() {
        return packStatus;
    }

    public void setPackStatus(String packStatus) {
        this.packStatus = packStatus;
    }

    public String getPackID() {
        return packID;
    }

    public void setPackID(String packID) {
        this.packID = packID;
    }

    public String getSalesID() {
        return salesID;
    }

    public void setSalesID(String salesID) {
        this.salesID = salesID;
    }

    public String getPackDate() {
        return packDate;
    }

    public void setPackDate(String packDate) {
        this.packDate = packDate;
    }
}
