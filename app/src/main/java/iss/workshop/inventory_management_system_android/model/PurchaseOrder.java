package iss.workshop.inventory_management_system_android.model;

import java.util.Date;
import java.util.function.Supplier;


public class PurchaseOrder {
    public int id;
    public Supplier supplier;
    public String deliverTo;
    public Date expectedDeliveryDate;
    public Employee issuedBy;
    public int pOStatus;
    public String pOComments;
    public Date pOIssueDate;
    public String pOCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getDeliverTo() {
        return deliverTo;
    }

    public void setDeliverTo(String deliverTo) {
        this.deliverTo = deliverTo;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public Employee getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(Employee issuedBy) {
        this.issuedBy = issuedBy;
    }

    public int getpOStatus() {
        return pOStatus;
    }

    public void setpOStatus(int pOStatus) {
        this.pOStatus = pOStatus;
    }

    public String getpOComments() {
        return pOComments;
    }

    public void setpOComments(String pOComments) {
        this.pOComments = pOComments;
    }

    public Date getpOIssueDate() {
        return pOIssueDate;
    }

    public void setpOIssueDate(Date pOIssueDate) {
        this.pOIssueDate = pOIssueDate;
    }

    public String getpOCode() {
        return pOCode;
    }

    public void setpOCode(String pOCode) {
        this.pOCode = pOCode;
    }
}
