package iss.workshop.inventorymanagementsystem.model;

import java.io.Serializable;

import iss.workshop.inventorymanagementsystem.helper.Pageable;

public class Product implements Serializable, Pageable {
    public int id;
    public String productName;
    public String productCode;
    public ProductCategory productCategory;
    public String productDescription;
    public String units;
    public String productImage;
    // Not Mapped
    public int productRequested;
    public int inventoryQuantity;
    public int reorderLevel;
    public int reorderQuantity;
    public String inventoryLocation;
    public int mlReorderQuantity;
    public int mlReorderLevel;
    // Not Mapped
    public int productCountCheck;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getProductRequested() {
        return productRequested;
    }

    public void setProductRequested(int productRequested) {
        this.productRequested = productRequested;
    }

    public int getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(int inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public int getReorderQuantity() {
        return reorderQuantity;
    }

    public void setReorderQuantity(int reorderQuantity) {
        this.reorderQuantity = reorderQuantity;
    }

    public String getInventoryLocation() {
        return inventoryLocation;
    }

    public void setInventoryLocation(String inventoryLocation) {
        this.inventoryLocation = inventoryLocation;
    }

    public int getMlReorderQuantity() {
        return mlReorderQuantity;
    }

    public void setMlReorderQuantity(int mlReorderQuantity) {
        this.mlReorderQuantity = mlReorderQuantity;
    }

    public int getMlReorderLevel() {
        return mlReorderLevel;
    }

    public void setMlReorderLevel(int mlReorderLevel) {
        this.mlReorderLevel = mlReorderLevel;
    }

    public int getProductCountCheck() {
        return productCountCheck;
    }

    public void setProductCountCheck(int productCountCheck) {
        this.productCountCheck = productCountCheck;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
