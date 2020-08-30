package iss.workshop.inventory_management_system_android.model;

import java.io.Serializable;

import iss.workshop.inventory_management_system_android.helper.Pageable;

public class ProductCategory implements Serializable, Pageable {
    public int id;
    public String productCategoryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }
}
