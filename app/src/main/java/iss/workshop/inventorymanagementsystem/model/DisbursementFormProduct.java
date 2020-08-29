package iss.workshop.inventorymanagementsystem.model;

public class DisbursementFormProduct {
    public int id;

    public DisbursementForm disbursementForm;
    public Product product;
    public int productToDeliverTotal;
    public int productDeliveredTotal;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductToDeliverTotal() {
        return productToDeliverTotal;
    }

    public void setProductToDeliverTotal(int productToDeliverTotal) {
        this.productToDeliverTotal = productToDeliverTotal;
    }

    public int getProductDeliveredTotal() {
        return productDeliveredTotal;
    }

    public void setProductDeliveredTotal(int productDeliveredTotal) {
        this.productDeliveredTotal = productDeliveredTotal;
    }

}
