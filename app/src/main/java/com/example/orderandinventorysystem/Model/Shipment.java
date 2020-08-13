package com.example.orderandinventorysystem.Model;

public class Shipment {

    private String packID, shipID, shipDate, carrier;

    public Shipment(String shipID, String packID, String shipDate, String carrier) {
        this.packID = packID;
        this.shipID = shipID;
        this.shipDate = shipDate;
        this.carrier = carrier;
    }

    public String getPackID() {
        return packID;
    }

    public void setPackID(String packID) {
        this.packID = packID;
    }

    public String getShipID() {
        return shipID;
    }

    public void setShipID(String shipID) {
        this.shipID = shipID;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }
}
