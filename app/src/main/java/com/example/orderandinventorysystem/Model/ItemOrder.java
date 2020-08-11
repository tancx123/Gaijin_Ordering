package com.example.orderandinventorysystem.Model;

import java.io.Serializable;

public class ItemOrder implements Serializable {

    private String itemID, itemName;
    private double sellPrice, total;
    private int quantity;

    public ItemOrder(String itemID, String itemName, double sellPrice, int quantity) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
        total = sellPrice * quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
