package com.example.orderandinventorysystem.Model;

public class Item {

    private String itemName, itemSKU, itemUnit, itemDesc;
    private double sellPrice, costPrice;
    private int quantity = 0;

    public Item(String itemName, String itemSKU, String itemUnit, String itemDesc, double sellPrice, double costPrice) {
        this.itemName = itemName;
        this.itemSKU = itemSKU;
        this.itemUnit = itemUnit;
        this.itemDesc = itemDesc;
        this.sellPrice = sellPrice;
        this.costPrice = costPrice;
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

    public String getItemSKU() {
        return itemSKU;
    }

    public void setItemSKU(String itemSKU) {
        this.itemSKU = itemSKU;
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
