package iss.workshop.inventorymanagementsystem.viewmodel;

import iss.workshop.inventorymanagementsystem.model.Employee;

public class SRProductViewModel {
    public Employee employee;
    public String productname;
    public int productqty;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getProductqty() {
        return productqty;
    }

    public void setProductqty(int productqty) {
        this.productqty = productqty;
    }
}
