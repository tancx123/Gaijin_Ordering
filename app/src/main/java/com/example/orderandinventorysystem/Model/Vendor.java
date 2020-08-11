package com.example.orderandinventorysystem.Model;

public class Vendor {

    private String venName, companyName, email, phone, mobile;

    public Vendor(String venName, String companyName, String email, String phone, String mobile) {
        this.venName = venName;
        this.companyName = companyName;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
    }

    public String getVenName() {
        return venName;
    }

    public void setVenName(String venName) {
        this.venName = venName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


}
