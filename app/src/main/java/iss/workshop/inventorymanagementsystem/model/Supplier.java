package iss.workshop.inventorymanagementsystem.model;

public class Supplier {
    public int id;
    public String supplierCode;
    public String supplierName;
    public String contactName;
    public String phoneNumber;
    public String faxNumber;
    public String address;
    public String gSTregistrationNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getgSTregistrationNumber() {
        return gSTregistrationNumber;
    }

    public void setgSTregistrationNumber(String gSTregistrationNumber) {
        this.gSTregistrationNumber = gSTregistrationNumber;
    }
}
