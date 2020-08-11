package com.example.orderandinventorysystem.Model;

public class Purchase {

    private String venName, deliverType, pOrder, dDate, eDate, amount;

    public Purchase(String venName, String deliverType, String pOrder, String dDate, String eDate, String amount) {
        this.venName = venName;
        this.deliverType = deliverType;
        this.pOrder = pOrder;
        this.dDate = dDate;
        this.eDate = eDate;
        this.amount = amount;
    }

    public String getVenName() {
        return venName;
    }

    public void setVenName(String venName) {
        this.venName = venName;
    }

    public String getDeliverType() {
        return deliverType;
    }

    public void setDeliverType(String companyName) {
        this.deliverType = deliverType;
    }

    public String getpOrder() {
        return pOrder;
    }

    public void setpOrder(String pOrder) {
        this.pOrder = pOrder;
    }

    public String getdDate() {
        return dDate;
    }

    public void setdDate(String phone) {
        this.dDate = dDate;
    }

    public String geteDate() { return eDate; }

    public void seteDate(String mobile) {
        this.eDate = eDate;
    }

    public String getAmount() { return amount; }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
