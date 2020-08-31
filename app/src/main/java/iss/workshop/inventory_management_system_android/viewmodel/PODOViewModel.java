package iss.workshop.inventory_management_system_android.viewmodel;

import androidx.core.util.Supplier;

import java.util.List;

import iss.workshop.inventory_management_system_android.model.DeliveryOrder;
import iss.workshop.inventory_management_system_android.model.DeliveryOrderSupplierProduct;
import iss.workshop.inventory_management_system_android.model.Employee;
import iss.workshop.inventory_management_system_android.model.Product;
import iss.workshop.inventory_management_system_android.model.PurchaseOrder;
import iss.workshop.inventory_management_system_android.model.PurchaseOrderSupplierProduct;
import iss.workshop.inventory_management_system_android.model.StationeryRetrievalRequisitionForm;
import iss.workshop.inventory_management_system_android.model.SupplierProduct;

public class PODOViewModel {

    public Employee employee;
    public PurchaseOrder pO;
    public DeliveryOrder dO;

    public PurchaseOrderSupplierProduct purchaseOrderSupplierProduct;

    public List<PurchaseOrder> poListIssued;
    public List<PurchaseOrder> poListNotCompleted;
    public List<PurchaseOrder> poListCompleted;
    public List<PurchaseOrderSupplierProduct> posList;

    public List<DeliveryOrder> doList;
    public List<DeliveryOrder> doListNotCompleted;
    public List<DeliveryOrder> doListCompleted;

    public List<Supplier> supplierList;
    public List<SupplierProduct> sPList;
    public List<Product> productList;
    public String comment;
    public List<DeliveryOrderSupplierProduct> dOSPList;


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public PurchaseOrder getpO() {
        return pO;
    }

    public void setpO(PurchaseOrder pO) {
        this.pO = pO;
    }

    public DeliveryOrder getdO() {
        return dO;
    }

    public void setdO(DeliveryOrder dO) {
        this.dO = dO;
    }

    public PurchaseOrderSupplierProduct getPurchaseOrderSupplierProduct() {
        return purchaseOrderSupplierProduct;
    }

    public void setPurchaseOrderSupplierProduct(PurchaseOrderSupplierProduct purchaseOrderSupplierProduct) {
        this.purchaseOrderSupplierProduct = purchaseOrderSupplierProduct;
    }

    public List<PurchaseOrder> getPoListIssued() {
        return poListIssued;
    }

    public void setPoListIssued(List<PurchaseOrder> poListIssued) {
        this.poListIssued = poListIssued;
    }

    public List<PurchaseOrder> getPoListNotCompleted() {
        return poListNotCompleted;
    }

    public void setPoListNotCompleted(List<PurchaseOrder> poListNotCompleted) {
        this.poListNotCompleted = poListNotCompleted;
    }

    public List<PurchaseOrder> getPoListCompleted() {
        return poListCompleted;
    }

    public void setPoListCompleted(List<PurchaseOrder> poListCompleted) {
        this.poListCompleted = poListCompleted;
    }

    public List<PurchaseOrderSupplierProduct> getPosList() {
        return posList;
    }

    public void setPosList(List<PurchaseOrderSupplierProduct> posList) {
        this.posList = posList;
    }

    public List<DeliveryOrder> getDoList() {
        return doList;
    }

    public void setDoList(List<DeliveryOrder> doList) {
        this.doList = doList;
    }

    public List<DeliveryOrder> getDoListNotCompleted() {
        return doListNotCompleted;
    }

    public void setDoListNotCompleted(List<DeliveryOrder> doListNotCompleted) {
        this.doListNotCompleted = doListNotCompleted;
    }

    public List<DeliveryOrder> getDoListCompleted() {
        return doListCompleted;
    }

    public void setDoListCompleted(List<DeliveryOrder> doListCompleted) {
        this.doListCompleted = doListCompleted;
    }

    public List<Supplier> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<Supplier> supplierList) {
        this.supplierList = supplierList;
    }

    public List<SupplierProduct> getsPList() {
        return sPList;
    }

    public void setsPList(List<SupplierProduct> sPList) {
        this.sPList = sPList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<DeliveryOrderSupplierProduct> getdOSPList() {
        return dOSPList;
    }

    public void setdOSPList(List<DeliveryOrderSupplierProduct> dOSPList) {
        this.dOSPList = dOSPList;
    }
}
