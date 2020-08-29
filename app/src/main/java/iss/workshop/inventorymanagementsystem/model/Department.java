package iss.workshop.inventorymanagementsystem.model;

public class Department {
    public int id;
    public String departmentName;
    public int depHeadId;
    public String phoneNumber;
    public String email;
    public CollectionPoint collectionPoint;
    public String deptCode;
    public int deptRepId;
    public Employee deptHead;
    public Employee deptRep;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getDepHeadId() {
        return depHeadId;
    }

    public void setDepHeadId(int depHeadId) {
        this.depHeadId = depHeadId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CollectionPoint getCollectionPoint() {
        return collectionPoint;
    }

    public void setCollectionPoint(CollectionPoint collectionPoint) {
        this.collectionPoint = collectionPoint;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public int getDeptRepId() {
        return deptRepId;
    }

    public void setDeptRepId(int deptRepId) {
        this.deptRepId = deptRepId;
    }

    public Employee getDeptHead() {
        return deptHead;
    }

    public void setDeptHead(Employee deptHead) {
        this.deptHead = deptHead;
    }

    public Employee getDeptRep() {
        return deptRep;
    }

    public void setDeptRep(Employee deptRep) {
        this.deptRep = deptRep;
    }
}
