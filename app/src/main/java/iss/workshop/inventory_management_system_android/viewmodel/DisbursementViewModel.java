package iss.workshop.inventory_management_system_android.viewmodel;

import java.util.List;

import iss.workshop.inventory_management_system_android.model.DisbursementForm;
import iss.workshop.inventory_management_system_android.model.DisbursementFormProduct;
import iss.workshop.inventory_management_system_android.model.DisbursementFormRequisitionForm;
import iss.workshop.inventory_management_system_android.model.DisbursementFormRequisitionFormProduct;
import iss.workshop.inventory_management_system_android.model.Employee;
import iss.workshop.inventory_management_system_android.model.Product;
import iss.workshop.inventory_management_system_android.model.StationeryRetrievalRequisitionForm;

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
