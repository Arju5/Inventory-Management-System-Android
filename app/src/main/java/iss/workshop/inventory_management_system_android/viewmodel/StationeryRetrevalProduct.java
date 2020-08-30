package iss.workshop.inventory_management_system_android.viewmodel;

import java.io.Serializable;

import iss.workshop.inventory_management_system_android.helper.Pageable;

public class StationeryRetrevalProduct implements Serializable, Pageable {

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
