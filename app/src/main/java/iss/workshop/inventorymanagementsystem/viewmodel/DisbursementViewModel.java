package iss.workshop.inventorymanagementsystem.viewmodel;

import java.util.List;

import iss.workshop.inventorymanagementsystem.model.DisbursementForm;
import iss.workshop.inventorymanagementsystem.model.DisbursementFormProduct;
import iss.workshop.inventorymanagementsystem.model.DisbursementFormRequisitionForm;
import iss.workshop.inventorymanagementsystem.model.DisbursementFormRequisitionFormProduct;
import iss.workshop.inventorymanagementsystem.model.Employee;
import iss.workshop.inventorymanagementsystem.model.Product;
import iss.workshop.inventorymanagementsystem.model.StationeryRetrievalRequisitionForm;

public class DisbursementViewModel {
    public Employee employee;
    public List<Product> productList;
    public DisbursementForm disbursementForm;
    public List<DisbursementFormRequisitionForm> disbursementFormRequisitionForms;
    public List<DisbursementFormProduct> disbursementFormProducts;
    public List<DisbursementFormRequisitionFormProduct> disbursementFormRequisitionFormProducts;

    public List<DisbursementForm> dfCreatedList;
    public List<DisbursementForm> dfPendingDeliveryList;
    public List<DisbursementForm> dfPendingAssignList;

    public List<DisbursementForm> dfCompletedList;
    public List<StationeryRetrievalRequisitionForm> srrfAssignedList;
    public String comment;
    public Employee deptrep;
    public Employee storeclerk;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public DisbursementForm getDisbursementForm() {
        return disbursementForm;
    }

    public void setDisbursementForm(DisbursementForm disbursementForm) {
        this.disbursementForm = disbursementForm;
    }

    public List<DisbursementFormRequisitionForm> getDisbursementFormRequisitionForms() {
        return disbursementFormRequisitionForms;
    }

    public void setDisbursementFormRequisitionForms(List<DisbursementFormRequisitionForm> disbursementFormRequisitionForms) {
        this.disbursementFormRequisitionForms = disbursementFormRequisitionForms;
    }

    public List<DisbursementFormProduct> getDisbursementFormProducts() {
        return disbursementFormProducts;
    }

    public void setDisbursementFormProducts(List<DisbursementFormProduct> disbursementFormProducts) {
        this.disbursementFormProducts = disbursementFormProducts;
    }

    public List<DisbursementFormRequisitionFormProduct> getDisbursementFormRequisitionFormProducts() {
        return disbursementFormRequisitionFormProducts;
    }

    public void setDisbursementFormRequisitionFormProducts(List<DisbursementFormRequisitionFormProduct> disbursementFormRequisitionFormProducts) {
        this.disbursementFormRequisitionFormProducts = disbursementFormRequisitionFormProducts;
    }

    public List<DisbursementForm> getDfCreatedList() {
        return dfCreatedList;
    }

    public void setDfCreatedList(List<DisbursementForm> dfCreatedList) {
        this.dfCreatedList = dfCreatedList;
    }

    public List<DisbursementForm> getDfPendingDeliveryList() {
        return dfPendingDeliveryList;
    }

    public void setDfPendingDeliveryList(List<DisbursementForm> dfPendingDeliveryList) {
        this.dfPendingDeliveryList = dfPendingDeliveryList;
    }

    public List<DisbursementForm> getDfPendingAssignList() {
        return dfPendingAssignList;
    }

    public void setDfPendingAssignList(List<DisbursementForm> dfPendingAssignList) {
        this.dfPendingAssignList = dfPendingAssignList;
    }

    public List<DisbursementForm> getDfCompletedList() {
        return dfCompletedList;
    }

    public void setDfCompletedList(List<DisbursementForm> dfCompletedList) {
        this.dfCompletedList = dfCompletedList;
    }

    public List<StationeryRetrievalRequisitionForm> getSrrfAssignedList() {
        return srrfAssignedList;
    }

    public void setSrrfAssignedList(List<StationeryRetrievalRequisitionForm> srrfAssignedList) {
        this.srrfAssignedList = srrfAssignedList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Employee getDeptrep() {
        return deptrep;
    }

    public void setDeptrep(Employee deptrep) {
        this.deptrep = deptrep;
    }

    public Employee getStoreclerk() {
        return storeclerk;
    }

    public void setStoreclerk(Employee storeclerk) {
        this.storeclerk = storeclerk;
    }
}
