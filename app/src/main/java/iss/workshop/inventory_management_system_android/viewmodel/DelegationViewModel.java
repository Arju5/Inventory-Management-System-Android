package iss.workshop.inventory_management_system_android.viewmodel;

import java.util.List;

import iss.workshop.inventory_management_system_android.model.DelegationForm;
import iss.workshop.inventory_management_system_android.model.Employee;

public class DelegationViewModel {
    public List<DelegationForm> delegationList;
    public Employee employee;
    public Employee departmentHead;
    public Employee delegatee;
    public String startDate;
    public String endDate;
    public String delegateComment;
    public List<Employee> deptEmployeeList;
    public Employee assignedDeptRep;
    public DelegationForm dForm;

    public List<DelegationForm> getDelegationList() {
        return delegationList;
    }

    public void setDelegationList(List<DelegationForm> delegationList) {
        this.delegationList = delegationList;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public List<Employee> getDeptEmployeeList() {
        return deptEmployeeList;
    }

    public void setDeptEmployeeList(List<Employee> deptEmployeeList) {
        this.deptEmployeeList = deptEmployeeList;
    }

    public Employee getAssignedDeptRep() {
        return assignedDeptRep;
    }

    public void setAssignedDeptRep(Employee assignedDeptRep) {
        this.assignedDeptRep = assignedDeptRep;
    }

    public DelegationForm getdForm() {
        return dForm;
    }

    public void setdForm(DelegationForm dForm) {
        this.dForm = dForm;
    }
}
