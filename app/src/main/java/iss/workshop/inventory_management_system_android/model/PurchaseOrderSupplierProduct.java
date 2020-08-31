package iss.workshop.inventory_management_system_android.model;

public class PurchaseOrderSupplierProduct {
    public int id;
    public SupplierProduct supplierProduct;
    public PurchaseOrder purchaseOrder;
    public float pOUnitPrice;
    public int pOQuantityRequested;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SupplierProduct getSupplierProduct() {
        return supplierProduct;
    }

    public void setSupplierProduct(SupplierProduct supplierProduct) {
        this.supplierProduct = supplierProduct;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public float getpOUnitPrice() {
        return pOUnitPrice;
    }

    public void setpOUnitPrice(float pOUnitPrice) {
        this.pOUnitPrice = pOUnitPrice;
    }

    public int getpOQuantityRequested() {
        return pOQuantityRequested;
    }

    public void setpOQuantityRequested(int pOQuantityRequested) {
        this.pOQuantityRequested = pOQuantityRequested;
    }
/*    public void setIsSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean getIsSelected() {return isSelected;}*/
}
