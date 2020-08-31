package iss.workshop.inventory_management_system_android.model;

public class DelegationForm {

    public int id;
    public Employee departmentHead;
    public Employee delegatee;
    public String startDate;
    public String endDate;
    public String delegateComment;
    public String dlStatus;
    public String dlAssignedDate;
    public EmployeeType delegatedType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(Employee departmentHead) {
        this.departmentHead = departmentHead;
    }

    public Employee getDelegatee() {
        return delegatee;
    }

    public void setDelegatee(Employee delegatee) {
        this.delegatee = delegatee;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDelegateComment() {
        return delegateComment;
    }

    public void setDelegateComment(String delegateComment) {
        this.delegateComment = delegateComment;
    }

    public String getDlStatus() {
        return dlStatus;
    }

    public void setDlStatus(String dlStatus) {
        this.dlStatus = dlStatus;
    }

    public String getDlAssignedDate() {
        return dlAssignedDate;
    }

    public void setDlAssignedDate(String dlAssignedDate) {
        this.dlAssignedDate = dlAssignedDate;
    }

    public EmployeeType getDelegatedType() {
        return delegatedType;
    }

    public void setDelegatedType(EmployeeType delegatedType) {
        this.delegatedType = delegatedType;
    }
}
