package iss.workshop.inventory_management_system_android.model;

import androidx.core.util.Supplier;

import java.util.Date;


public class DeliveryOrder {
    public int id;
    public String deliveryOrderNo;
    public Supplier supplier;
    public PurchaseOrder purchaseOrder;
    public String dOComments;
    public Date dOReceivedDate;
    public Date dOActualTime;
    public Employee receivedBy;
    public String dOCode;
    public int dOStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeliveryOrderNo() {
        return deliveryOrderNo;
    }

    public void setDeliveryOrderNo(String deliveryOrderNo) {
        this.deliveryOrderNo = deliveryOrderNo;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getdOComments() {
        return dOComments;
    }

    public void setdOComments(String dOComments) {
        this.dOComments = dOComments;
    }

    public Date getdOReceivedDate() {
        return dOReceivedDate;
    }

    public void setdOReceivedDate(Date dOReceivedDate) {
        this.dOReceivedDate = dOReceivedDate;
    }

    public Date getdOActualTime() {
        return dOActualTime;
    }

    public void setdOActualTime(Date dOActualTime) {
        this.dOActualTime = dOActualTime;
    }

    public Employee getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(Employee receivedBy) {
        this.receivedBy = receivedBy;
    }

    public String getdOCode() {
        return dOCode;
    }

    public void setdOCode(String dOCode) {
        this.dOCode = dOCode;
    }

    public int getdOStatus() {
        return dOStatus;
    }

    public void setdOStatus(int dOStatus) {
        this.dOStatus = dOStatus;
    }
}
