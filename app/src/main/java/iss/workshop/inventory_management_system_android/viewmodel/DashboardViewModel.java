package iss.workshop.inventory_management_system_android.viewmodel;

import java.util.List;

import iss.workshop.inventory_management_system_android.model.CollectionPoint;
import iss.workshop.inventory_management_system_android.model.Department;
import iss.workshop.inventory_management_system_android.model.Employee;

public class DashboardViewModel {

    public Employee emp;
    public Department dept;
    public Employee deptRep;

    public List<CollectionPoint> cpList;
    public CollectionPoint cpUpdated;

    //By Dept Head - Total Req For this Month
    public int rFsThisMonth;
    //By Dept Head - Total RF Yet to Approve
    public int pendingRFApproval;
    public int pendingRFDelivery;
    public int totalReqSubmitted;
    public int pendingApprovedRFByEmployee;
    public int pendingRFToDeliver;
    public int pendingIAApprovals;
    public int pendingRFToAssignToSR;
    public int pendingRFsForCollection;

    public int totalRFSubmitted;
    public int totalRFApproved;
    public int totalRFRejected;
    public int totalRFNotCompleted;
    public int totalRFCompleted;
    public int totalRFOngoing;

    public int totalRFSubmittedDept;
    public int totalRFApprovedDept;
    public int totalRFRejectedDept;
    public int totalRFNotCompletedDept;
    public int totalRFCompletedDept;
    public int totalRFOngoingDept;
    public int totalSROpen;
    public int totalSRPendingAssignment;
    public int totalSRAssigned;

    public int totalDFCreated;
    public int totalDFPendingDelivery;
    public int totalDFPendingAssignment;
    public int totalDFCompleted;


    public int totalPOIssued;
    public int totalPONotCompleted;
    public int totalPOCompleted;

    public int totalITPendingApproval;
    public int totalITApproved;

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public Employee getDeptRep() {
        return deptRep;
    }

    public void setDeptRep(Employee deptRep) {
        this.deptRep = deptRep;
    }

    public List<CollectionPoint> getCpList() {
        return cpList;
    }

    public void setCpList(List<CollectionPoint> cpList) {
        this.cpList = cpList;
    }

    public CollectionPoint getCpUpdated() {
        return cpUpdated;
    }

    public void setCpUpdated(CollectionPoint cpUpdated) {
        this.cpUpdated = cpUpdated;
    }

    public int getrFsThisMonth() {
        return rFsThisMonth;
    }

    public void setrFsThisMonth(int rFsThisMonth) {
        this.rFsThisMonth = rFsThisMonth;
    }

    public int getPendingRFApproval() {
        return pendingRFApproval;
    }

    public void setPendingRFApproval(int pendingRFApproval) {
        this.pendingRFApproval = pendingRFApproval;
    }

    public int getPendingRFDelivery() {
        return pendingRFDelivery;
    }

    public void setPendingRFDelivery(int pendingRFDelivery) {
        this.pendingRFDelivery = pendingRFDelivery;
    }

    public int getTotalReqSubmitted() {
        return totalReqSubmitted;
    }

    public void setTotalReqSubmitted(int totalReqSubmitted) {
        this.totalReqSubmitted = totalReqSubmitted;
    }

    public int getPendingApprovedRFByEmployee() {
        return pendingApprovedRFByEmployee;
    }

    public void setPendingApprovedRFByEmployee(int pendingApprovedRFByEmployee) {
        this.pendingApprovedRFByEmployee = pendingApprovedRFByEmployee;
    }

    public int getPendingRFToDeliver() {
        return pendingRFToDeliver;
    }

    public void setPendingRFToDeliver(int pendingRFToDeliver) {
        this.pendingRFToDeliver = pendingRFToDeliver;
    }

    public int getPendingIAApprovals() {
        return pendingIAApprovals;
    }

    public void setPendingIAApprovals(int pendingIAApprovals) {
        this.pendingIAApprovals = pendingIAApprovals;
    }

    public int getPendingRFToAssignToSR() {
        return pendingRFToAssignToSR;
    }

    public void setPendingRFToAssignToSR(int pendingRFToAssignToSR) {
        this.pendingRFToAssignToSR = pendingRFToAssignToSR;
    }

    public int getPendingRFsForCollection() {
        return pendingRFsForCollection;
    }

    public void setPendingRFsForCollection(int pendingRFsForCollection) {
        this.pendingRFsForCollection = pendingRFsForCollection;
    }

    public int getTotalRFSubmitted() {
        return totalRFSubmitted;
    }

    public void setTotalRFSubmitted(int totalRFSubmitted) {
        this.totalRFSubmitted = totalRFSubmitted;
    }

    public int getTotalRFApproved() {
        return totalRFApproved;
    }

    public void setTotalRFApproved(int totalRFApproved) {
        this.totalRFApproved = totalRFApproved;
    }

    public int getTotalRFRejected() {
        return totalRFRejected;
    }

    public void setTotalRFRejected(int totalRFRejected) {
        this.totalRFRejected = totalRFRejected;
    }

    public int getTotalRFNotCompleted() {
        return totalRFNotCompleted;
    }

    public void setTotalRFNotCompleted(int totalRFNotCompleted) {
        this.totalRFNotCompleted = totalRFNotCompleted;
    }

    public int getTotalRFCompleted() {
        return totalRFCompleted;
    }

    public void setTotalRFCompleted(int totalRFCompleted) {
        this.totalRFCompleted = totalRFCompleted;
    }

    public int getTotalRFOngoing() {
        return totalRFOngoing;
    }

    public void setTotalRFOngoing(int totalRFOngoing) {
        this.totalRFOngoing = totalRFOngoing;
    }

    public int getTotalRFSubmittedDept() {
        return totalRFSubmittedDept;
    }

    public void setTotalRFSubmittedDept(int totalRFSubmittedDept) {
        this.totalRFSubmittedDept = totalRFSubmittedDept;
    }

    public int getTotalRFApprovedDept() {
        return totalRFApprovedDept;
    }

    public void setTotalRFApprovedDept(int totalRFApprovedDept) {
        this.totalRFApprovedDept = totalRFApprovedDept;
    }

    public int getTotalRFRejectedDept() {
        return totalRFRejectedDept;
    }

    public void setTotalRFRejectedDept(int totalRFRejectedDept) {
        this.totalRFRejectedDept = totalRFRejectedDept;
    }

    public int getTotalRFNotCompletedDept() {
        return totalRFNotCompletedDept;
    }

    public void setTotalRFNotCompletedDept(int totalRFNotCompletedDept) {
        this.totalRFNotCompletedDept = totalRFNotCompletedDept;
    }

    public int getTotalRFCompletedDept() {
        return totalRFCompletedDept;
    }

    public void setTotalRFCompletedDept(int totalRFCompletedDept) {
        this.totalRFCompletedDept = totalRFCompletedDept;
    }

    public int getTotalRFOngoingDept() {
        return totalRFOngoingDept;
    }

    public void setTotalRFOngoingDept(int totalRFOngoingDept) {
        this.totalRFOngoingDept = totalRFOngoingDept;
    }

    public int getTotalSROpen() {
        return totalSROpen;
    }

    public void setTotalSROpen(int totalSROpen) {
        this.totalSROpen = totalSROpen;
    }

    public int getTotalSRPendingAssignment() {
        return totalSRPendingAssignment;
    }

    public void setTotalSRPendingAssignment(int totalSRPendingAssignment) {
        this.totalSRPendingAssignment = totalSRPendingAssignment;
    }

    public int getTotalSRAssigned() {
        return totalSRAssigned;
    }

    public void setTotalSRAssigned(int totalSRAssigned) {
        this.totalSRAssigned = totalSRAssigned;
    }

    public int getTotalDFCreated() {
        return totalDFCreated;
    }

    public void setTotalDFCreated(int totalDFCreated) {
        this.totalDFCreated = totalDFCreated;
    }

    public int getTotalDFPendingDelivery() {
        return totalDFPendingDelivery;
    }

    public void setTotalDFPendingDelivery(int totalDFPendingDelivery) {
        this.totalDFPendingDelivery = totalDFPendingDelivery;
    }

    public int getTotalDFPendingAssignment() {
        return totalDFPendingAssignment;
    }

    public void setTotalDFPendingAssignment(int totalDFPendingAssignment) {
        this.totalDFPendingAssignment = totalDFPendingAssignment;
    }

    public int getTotalDFCompleted() {
        return totalDFCompleted;
    }

    public void setTotalDFCompleted(int totalDFCompleted) {
        this.totalDFCompleted = totalDFCompleted;
    }

    public int getTotalPOIssued() {
        return totalPOIssued;
    }

    public void setTotalPOIssued(int totalPOIssued) {
        this.totalPOIssued = totalPOIssued;
    }

    public int getTotalPONotCompleted() {
        return totalPONotCompleted;
    }

    public void setTotalPONotCompleted(int totalPONotCompleted) {
        this.totalPONotCompleted = totalPONotCompleted;
    }

    public int getTotalPOCompleted() {
        return totalPOCompleted;
    }

    public void setTotalPOCompleted(int totalPOCompleted) {
        this.totalPOCompleted = totalPOCompleted;
    }

    public int getTotalITPendingApproval() {
        return totalITPendingApproval;
    }

    public void setTotalITPendingApproval(int totalITPendingApproval) {
        this.totalITPendingApproval = totalITPendingApproval;
    }

    public int getTotalITApproved() {
        return totalITApproved;
    }

    public void setTotalITApproved(int totalITApproved) {
        this.totalITApproved = totalITApproved;
    }
}
