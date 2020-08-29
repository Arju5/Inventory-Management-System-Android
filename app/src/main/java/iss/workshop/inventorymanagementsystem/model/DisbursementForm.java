package iss.workshop.inventorymanagementsystem.model;

public class DisbursementForm {
    public int id;
    public Employee storeClerk;
    public Employee deptRep;
    public int dfStatus;
    public String dfHandoverDate;
    public String dfDeliveryDate;
    public String dfDate;
    public String dfComments;
    public CollectionPoint collectionPoint;
    public String dfCode;
    public String dfTransAssignDate;
    public Employee dfAssignedBy;

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

    public Employee getDeptRep() {
        return deptRep;
    }

    public void setDeptRep(Employee deptRep) {
        this.deptRep = deptRep;
    }

    public int getDfStatus() {
        return dfStatus;
    }

    public void setDfStatus(int dfStatus) {
        this.dfStatus = dfStatus;
    }

    public String getDfHandoverDate() {
        return dfHandoverDate;
    }

    public void setDfHandoverDate(String dfHandoverDate) {
        this.dfHandoverDate = dfHandoverDate;
    }

    public String getDfDeliveryDate() {
        return dfDeliveryDate;
    }

    public void setDfDeliveryDate(String dfDeliveryDate) {
        this.dfDeliveryDate = dfDeliveryDate;
    }

    public String getDfDate() {
        return dfDate;
    }

    public void setDfDate(String dfDate) {
        this.dfDate = dfDate;
    }

    public String getDfComments() {
        return dfComments;
    }

    public void setDfComments(String dfComments) {
        this.dfComments = dfComments;
    }

    public CollectionPoint getCollectionPoint() {
        return collectionPoint;
    }

    public void setCollectionPoint(CollectionPoint collectionPoint) {
        this.collectionPoint = collectionPoint;
    }

    public String getDfCode() {
        return dfCode;
    }

    public void setDfCode(String dfCode) {
        this.dfCode = dfCode;
    }

    public String getDfTransAssignDate() {
        return dfTransAssignDate;
    }

    public void setDfTransAssignDate(String dfTransAssignDate) {
        this.dfTransAssignDate = dfTransAssignDate;
    }

    public Employee getDfAssignedBy() {
        return dfAssignedBy;
    }

    public void setDfAssignedBy(Employee dfAssignedBy) {
        this.dfAssignedBy = dfAssignedBy;
    }
}
