package iss.workshop.inventory_management_system_android.model;

public class RequisitionFormsProduct {
    public int id;
    public RequisitionForm requisitionForm;
    public Product product;
    public int productRequested;
    public int productApproved;
    public int productCollectedTotal;
    public int productBalanceForSR;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RequisitionForm getRequisitionForm() {
        return requisitionForm;
    }

    public void setRequisitionForm(RequisitionForm requisitionForm) {
        this.requisitionForm = requisitionForm;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductRequested() {
        return productRequested;
    }

    public void setProductRequested(int productRequested) {
        this.productRequested = productRequested;
    }

    public int getProductApproved() {
        return productApproved;
    }

    public void setProductApproved(int productApproved) {
        this.productApproved = productApproved;
    }

    public int getProductCollectedTotal() {
        return productCollectedTotal;
    }

    public void setProductCollectedTotal(int productCollectedTotal) {
        this.productCollectedTotal = productCollectedTotal;
    }

    public int getProductBalanceForSR() {
        return productBalanceForSR;
    }

    public void setProductBalanceForSR(int productBalanceForSR) {
        this.productBalanceForSR = productBalanceForSR;
    }
}
