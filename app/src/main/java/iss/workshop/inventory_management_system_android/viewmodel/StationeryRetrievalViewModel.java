package iss.workshop.inventory_management_system_android.viewmodel;

import java.io.Serializable;
import java.util.List;

import iss.workshop.inventory_management_system_android.helper.Pageable;
import iss.workshop.inventory_management_system_android.model.Employee;
import iss.workshop.inventory_management_system_android.model.RequisitionForm;
import iss.workshop.inventory_management_system_android.model.StationeryRetrieval;
import iss.workshop.inventory_management_system_android.model.StationeryRetrievalProduct;
import iss.workshop.inventory_management_system_android.model.StationeryRetrievalRequisitionForm;
import iss.workshop.inventory_management_system_android.model.StationeryRetrievalRequisitionFormProduct;

public class StationeryRetrievalViewModel implements Serializable, Pageable {
    public Employee employee;
    public StationeryRetrieval stationeryRetrieval;
    public List<RequisitionForm> retrievalRequisitions;
    public List<StationeryRetrievalProduct> retrievalProducts;
    public List<LoginViewModel> users;
    public List<StationeryRetrievalRequisitionFormProduct> sRRFPList;
    public Employee warehousepacker;
    public Employee storeclerk;
    public List<StationeryRetrievalRequisitionForm> srrfList;

    public List<StationeryRetrievalRequisitionForm> getSrrfList() {
        return srrfList;
    }

    public void setSrrfList(List<StationeryRetrievalRequisitionForm> srrfList) {
        this.srrfList = srrfList;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public StationeryRetrieval getStationeryRetrieval() {
        return stationeryRetrieval;
    }

    public void setStationeryRetrieval(StationeryRetrieval stationeryRetrieval) {
        this.stationeryRetrieval = stationeryRetrieval;
    }

    public List<RequisitionForm> getRetrievalRequisitions() {
        return retrievalRequisitions;
    }

    public void setRetrievalRequisitions(List<RequisitionForm> retrievalRequisitions) {
        this.retrievalRequisitions = retrievalRequisitions;
    }

    public List<StationeryRetrievalProduct> getRetrievalProducts() {
        return retrievalProducts;
    }

    public void setRetrievalProducts(List<StationeryRetrievalProduct> retrievalProducts) {
        this.retrievalProducts = retrievalProducts;
    }

    public List<LoginViewModel> getUsers() {
        return users;
    }

    public void setUsers(List<LoginViewModel> users) {
        this.users = users;
    }

    public List<StationeryRetrievalRequisitionFormProduct> getsRRFPList() {
        return sRRFPList;
    }

    public void setsRRFPList(List<StationeryRetrievalRequisitionFormProduct> sRRFPList) {
        this.sRRFPList = sRRFPList;
    }

    public Employee getWarehousepacker() {
        return warehousepacker;
    }

    public void setWarehousepacker(Employee warehousepacker) {
        this.warehousepacker = warehousepacker;
    }

    public Employee getStoreclerk() {
        return storeclerk;
    }

    public void setStoreclerk(Employee storeclerk) {
        this.storeclerk = storeclerk;
    }
}
