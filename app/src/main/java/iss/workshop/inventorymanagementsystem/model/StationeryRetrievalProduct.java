package iss.workshop.inventorymanagementsystem.model;

import java.io.Serializable;

import iss.workshop.inventorymanagementsystem.helper.Pageable;

public class StationeryRetrievalProduct implements Serializable, Pageable {
    public int id;
    public StationeryRetrieval stationeryRetrieval;
    public Product product;
    public int productRequestedTotal;
    public int productReceivedTotal;
    public int productCount;
    public int productAssigned;

    public int getProductAssigned() {
        return productAssigned;
    }

    public void setProductAssigned(int productAssigned) {
        this.productAssigned = productAssigned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StationeryRetrieval getStationeryRetrieval() {
        return stationeryRetrieval;
    }

    public void setStationeryRetrieval(StationeryRetrieval stationeryRetrieval) {
        this.stationeryRetrieval = stationeryRetrieval;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductRequestedTotal() {
        return productRequestedTotal;
    }

    public void setProductRequestedTotal(int productRequestedTotal) {
        this.productRequestedTotal = productRequestedTotal;
    }

    public int getProductReceivedTotal() {
        return productReceivedTotal;
    }

    public void setProductReceivedTotal(int productReceivedTotal) {
        this.productReceivedTotal = productReceivedTotal;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }
}
