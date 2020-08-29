package iss.workshop.inventorymanagementsystem.viewmodel;

import java.io.Serializable;

import iss.workshop.inventorymanagementsystem.helper.Pageable;

public class StationeryProductViewModel implements Serializable, Pageable {

    public String productname;
    public int productcount;
    public int warehousecount;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getProductcount() {
        return productcount;
    }

    public void setProductcount(int productcount) {
        this.productcount = productcount;
    }

    public int getWarehousecount() {
        return warehousecount;
    }

    public void setWarehousecount(int warehousecount) {
        this.warehousecount = warehousecount;
    }
}
