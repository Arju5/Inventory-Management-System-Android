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
}
