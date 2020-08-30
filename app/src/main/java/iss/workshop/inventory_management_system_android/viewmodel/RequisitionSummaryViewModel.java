package iss.workshop.inventory_management_system_android.viewmodel;

import java.util.List;

import iss.workshop.inventory_management_system_android.model.Employee;
import iss.workshop.inventory_management_system_android.model.RequisitionForm;

public class RequisitionSummaryViewModel {

    public List<RequisitionForm> rfListPending;
    public List<RequisitionForm> rfListProcessed;
    public Employee employee;

    public List<RequisitionForm> getRfListPending() {
        return rfListPending;
    }

    public void setRfListPending(List<RequisitionForm> rfListPending) {
        this.rfListPending = rfListPending;
    }

    public List<RequisitionForm> getRfListProcessed() {
        return rfListProcessed;
    }

    public void setRfListProcessed(List<RequisitionForm> rfListProcessed) {
        this.rfListProcessed = rfListProcessed;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
