package com.example.orderandinventorysystem.Model;

public class Package {

    private String packID, packDate, salesID;

    public Package(String packID, String packDate, String salesID) {
        this.packID = packID;
        this.packDate = packDate;
        this.salesID = salesID;
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
