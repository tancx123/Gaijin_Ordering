package com.example.orderandinventorysystem.Model;

import java.io.Serializable;

public class ItemOrder implements Serializable {

    private String orderID, itemID, itemName;
    private double sellPrice, total, discount;
    private int quantity;

    public ItemOrder(String orderID, String itemID, String itemName, double sellPrice, int quantity, double discount) {
        this.orderID = orderID;
        this.itemID = itemID;
        this.itemName = itemName;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
        total = sellPrice * quantity;
        this.discount = discount;
    }

    public ItemOrder(String orderID, String itemID, String itemName, double sellPrice, double total, int quantity, double discount) {
        this.orderID = orderID;
        this.itemID = itemID;
        this.itemName = itemName;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
        this.total = total;
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
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
