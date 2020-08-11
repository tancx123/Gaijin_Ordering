package com.example.orderandinventorysystem.Model;

public class Item {

    private String itemName, itemID, itemUnit, itemDesc;
    private double sellPrice, costPrice;
    private int quantity = 0;

    public Item(String itemID, String itemName, String itemUnit, String itemDesc, int quantity, double sellPrice, double costPrice) {
        this.itemName = itemName;
        this.itemID = itemID;
        this.itemUnit = itemUnit;
        this.itemDesc = itemDesc;
        this.sellPrice = sellPrice;
        this.costPrice = costPrice;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }
}
