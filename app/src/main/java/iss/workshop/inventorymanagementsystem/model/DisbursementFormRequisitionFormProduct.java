package iss.workshop.inventorymanagementsystem.model;

public class DisbursementFormRequisitionFormProduct {
    public int Id;
    public DisbursementForm DisbursementForm;
    public RequisitionFormsProduct RequisitionFormsProduct;
    public int ProductCollected;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public iss.workshop.inventorymanagementsystem.model.DisbursementForm getDisbursementForm() {
        return DisbursementForm;
    }

    public void setDisbursementForm(iss.workshop.inventorymanagementsystem.model.DisbursementForm disbursementForm) {
        DisbursementForm = disbursementForm;
    }

    public iss.workshop.inventorymanagementsystem.model.RequisitionFormsProduct getRequisitionFormsProduct() {
        return RequisitionFormsProduct;
    }

    public void setRequisitionFormsProduct(iss.workshop.inventorymanagementsystem.model.RequisitionFormsProduct requisitionFormsProduct) {
        RequisitionFormsProduct = requisitionFormsProduct;
    }

    public int getProductCollected() {
        return ProductCollected;
    }

    public void setProductCollected(int productCollected) {
        ProductCollected = productCollected;
    }
}
