package com.management.model;

public class Address {
	
    protected String addressLine1;
    protected String addressLine2;
    protected String district;
    protected String state;
    protected String country;
    protected int pinCode;

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String value) {
        this.addressLine1 = value;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String value) {
        this.addressLine2 = value;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String value) {
        this.district = value;
    }

    public String getState() {
        return state;
    }

    public void setState(String value) {
        this.state = value;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String value) {
        this.country = value;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int value) {
        this.pinCode = value;
    }

}
