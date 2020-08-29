package iss.workshop.inventory_management_system_android.model;

public class StationeryRetrieval {
    public int id;
    public Employee storeClerk;
    public Employee warehousePacker;
    public String srCode;
    public int srStatus;
    public String srComments;
    public String srDate;
    public String srRetrievalDate;
    public String srAssignedDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getStoreClerk() {
        return storeClerk;
    }

    public void setStoreClerk(Employee storeClerk) {
        this.storeClerk = storeClerk;
    }

    public Employee getWarehousePacker() {
        return warehousePacker;
    }

    public void setWarehousePacker(Employee warehousePacker) {
        this.warehousePacker = warehousePacker;
    }

    public String getSrCode() {
        return srCode;
    }

    public void setSrCode(String srCode) {
        this.srCode = srCode;
    }

    public int getSrStatus() {
        return srStatus;
    }

    public void setSrStatus(int srStatus) {
        this.srStatus = srStatus;
    }

    public String getSrComments() {
        return srComments;
    }

    public void setSrComments(String srComments) {
        this.srComments = srComments;
    }

    public String getSrDate() {
        return srDate;
    }

    public void setSrDate(String srDate) {
        this.srDate = srDate;
    }

    public String getSrRetrievalDate() {
        return srRetrievalDate;
    }

    public void setSrRetrievalDate(String srRetrievalDate) {
        this.srRetrievalDate = srRetrievalDate;
    }

    public String getSrAssignedDate() {
        return srAssignedDate;
    }

    public void setSrAssignedDate(String srAssignedDate) {
        this.srAssignedDate = srAssignedDate;
    }
}
