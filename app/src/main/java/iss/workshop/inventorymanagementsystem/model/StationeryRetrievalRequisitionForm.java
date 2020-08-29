package iss.workshop.inventorymanagementsystem.model;

import java.io.Serializable;

import iss.workshop.inventorymanagementsystem.helper.Pageable;

public class StationeryRetrievalRequisitionForm implements Serializable, Pageable {
    public int id;
    public StationeryRetrieval stationeryRetrieval;
    public RequisitionForm requisitionForm;
    public int srrfStatus;
    private boolean isSelected;

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

    public RequisitionForm getRequisitionForm() {
        return requisitionForm;
    }

    public void setRequisitionForm(RequisitionForm requisitionForm) {
        this.requisitionForm = requisitionForm;
    }

    public int getSrrfStatus() {
        return srrfStatus;
    }

    public void setSrrfStatus(int srrfStatus) {
        this.srrfStatus = srrfStatus;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean selected) {
        isSelected = selected;
    }
    public boolean getIsSelected() {return isSelected;}
}
