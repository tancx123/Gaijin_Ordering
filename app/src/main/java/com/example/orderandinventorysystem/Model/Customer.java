package com.example.orderandinventorysystem.Model;

public class Customer {

    private String custName, icNo, companyName, email, phone, mobile;
    private char custType, gender;

    public Customer(String custName, String icNo, String companyName, String email, String phone, String mobile, char custType, char gender) {
        this.custName = custName;
        this.icNo = icNo;
        this.companyName = companyName;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
        this.custType = custType;
        this.gender = gender;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getIcNo() {
        return icNo;
    }

    public void setIcNo(String icNo) {
        this.icNo = icNo;
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

    public char getCustType() {
        return custType;
    }

    public void setCustType(char custType) {
        this.custType = custType;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
