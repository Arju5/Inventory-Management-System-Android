package iss.workshop.inventory_management_system_android.model;

public class DeliveryOrderSupplierProduct {
    public int id;
    public SupplierProduct supplierProduct;
    public DeliveryOrder deliveryOrder;
    public int dOQuantityReceived;
    public PurchaseOrderSupplierProduct purchaseOrderSupplierProduct;

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

    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public int getdOQuantityReceived() {
        return dOQuantityReceived;
    }

    public void setdOQuantityReceived(int dOQuantityReceived) {
        this.dOQuantityReceived = dOQuantityReceived;
    }

    public PurchaseOrderSupplierProduct getPurchaseOrderSupplierProduct() {
        return purchaseOrderSupplierProduct;
    }

    public void setPurchaseOrderSupplierProduct(PurchaseOrderSupplierProduct purchaseOrderSupplierProduct) {
        this.purchaseOrderSupplierProduct = purchaseOrderSupplierProduct;
    }
}
