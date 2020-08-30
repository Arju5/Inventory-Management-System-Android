package iss.workshop.inventory_management_system_android.viewmodel;

import java.util.List;

import iss.workshop.inventory_management_system_android.model.Employee;
import iss.workshop.inventory_management_system_android.model.StationeryRetrieval;

public class StationeryRetrievalSummaryViewModel {
    public Employee employee;
    public List<StationeryRetrieval> pendingSROpens;
    public List<StationeryRetrieval> pendingSRAssignments;
    public List<StationeryRetrieval> completedSRs;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<StationeryRetrieval> getPendingSROpens() {
        return pendingSROpens;
    }

    public void setPendingSROpens(List<StationeryRetrieval> pendingSROpens) {
        this.pendingSROpens = pendingSROpens;
    }

    public List<StationeryRetrieval> getPendingSRAssignments() {
        return pendingSRAssignments;
    }

    public void setPendingSRAssignments(List<StationeryRetrieval> pendingSRAssignments) {
        this.pendingSRAssignments = pendingSRAssignments;
    }

    public List<StationeryRetrieval> getCompletedSRs() {
        return completedSRs;
    }

    public void setCompletedSRs(List<StationeryRetrieval> completedSRs) {
        this.completedSRs = completedSRs;
    }
}
