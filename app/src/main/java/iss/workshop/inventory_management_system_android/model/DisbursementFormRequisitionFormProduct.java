package iss.workshop.inventory_management_system_android.model;

public class DisbursementFormRequisitionFormProduct {
    public int id;
    public DisbursementForm disbursementForm;
    public RequisitionFormsProduct requisitionFormsProduct;
    public int productCollected;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DisbursementForm getDisbursementForm() {
        return disbursementForm;
    }

    public void setDisbursementForm(DisbursementForm disbursementForm) {
        this.disbursementForm = disbursementForm;
    }

    public RequisitionFormsProduct getRequisitionFormsProduct() {
        return requisitionFormsProduct;
    }

    public void setRequisitionFormsProduct(RequisitionFormsProduct requisitionFormsProduct) {
        this.requisitionFormsProduct = requisitionFormsProduct;
    }

    public int getProductCollected() {
        return productCollected;
    }

    public void setProductCollected(int productCollected) {
        this.productCollected = productCollected;
    }
}
