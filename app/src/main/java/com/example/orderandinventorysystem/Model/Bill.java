package com.example.orderandinventorysystem.Model;

public class Bill {

    private String venName, billNo, dDate, bDate, tax;

    public Bill(String venName, String billNo, String bDate, String dDate, String tax) {
        this.venName = venName;
        this.billNo = billNo;
        this.dDate = dDate;
        this.bDate = bDate;
        this.tax = tax;
    }

    public String getVenName() {
        return venName;
    }

    public void setVenName(String venName) {
        this.venName = venName;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getdDate() {
        return dDate;
    }

    public void setdDate(String phone) {
        this.dDate = dDate;
    }

    public String getbDate() { return bDate; }

    public void setbDate(String mobile) {
        this.bDate = bDate;
    }

    public String getTax() { return tax; }

    public void setTax(String tax) {
        this.tax = tax;
    }

}
