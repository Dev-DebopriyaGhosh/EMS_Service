
package com.management.model;


public class Employee {

    protected String employeeFirstName;

    protected String employeeLastName;
    protected int employeePhone;
    protected String employeeEmail;
    protected Address address;

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String value) {
        this.employeeFirstName = value;
    }
    public String getEmployeeLastName() {
        return employeeLastName;
    }
    public void setEmployeeLastName(String value) {
        this.employeeLastName = value;
    }
    public int getEmployeePhone() {
        return employeePhone;
    }
    public void setEmployeePhone(int value) {
        this.employeePhone = value;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String value) {
        this.employeeEmail = value;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address value) {
        this.address = value;
    }

}
