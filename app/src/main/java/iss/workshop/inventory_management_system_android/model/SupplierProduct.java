package iss.workshop.inventory_management_system_android.model;


public class SupplierProduct {
    public int id;
    public Supplier supplier;
    public Product product;
    public float productPrice;
    public int sPAvailStatus;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getsPAvailStatus() {
        return sPAvailStatus;
    }

    public void setsPAvailStatus(int sPAvailStatus) {
        this.sPAvailStatus = sPAvailStatus;
    }
}
