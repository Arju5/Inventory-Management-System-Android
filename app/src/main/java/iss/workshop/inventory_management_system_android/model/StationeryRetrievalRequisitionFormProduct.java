package iss.workshop.inventory_management_system_android.model;

import java.io.Serializable;

import iss.workshop.inventory_management_system_android.helper.Pageable;

public class StationeryRetrievalRequisitionFormProduct implements Serializable, Pageable {
    public int id;
    public int productAssigned;
    public StationeryRetrieval sr;
    public RequisitionFormsProduct rfp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductAssigned() {
        return productAssigned;
    }

    public void setProductAssigned(int productAssigned) {
        this.productAssigned = productAssigned;
    }

    public StationeryRetrieval getSr() {
        return sr;
    }

    public void setSr(StationeryRetrieval sr) {
        this.sr = sr;
    }

    public RequisitionFormsProduct getRfp() {
        return rfp;
    }

    public void setRfp(RequisitionFormsProduct rfp) {
        this.rfp = rfp;
    }
}
