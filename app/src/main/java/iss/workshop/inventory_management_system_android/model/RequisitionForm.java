package iss.workshop.inventory_management_system_android.model;

public class RequisitionForm {
    public int id;
    public Employee employee;
    public String rfCode;
    public int rfStatus;
    public String rfComments;
    public String rfDate;
    public String rfApprovalDate;
    public Employee rfApprovalBy;
    public String rfHeadReply;
    public boolean isSelected;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getRfCode() {
        return rfCode;
    }

    public void setRfCode(String rfCode) {
        this.rfCode = rfCode;
    }

    public int getRfStatus() {
        return rfStatus;
    }

    public void setRfStatus(int rfStatus) {
        this.rfStatus = rfStatus;
    }

    public String getRfComments() {
        return rfComments;
    }

    public void setRfComments(String rfComments) {
        this.rfComments = rfComments;
    }

    public String getRfDate() {
        return rfDate;
    }

    public void setRfDate(String rfDate) {
        this.rfDate = rfDate;
    }

    public String getRfApprovalDate() {
        return rfApprovalDate;
    }

    public void setRfApprovalDate(String rfApprovalDate) {
        this.rfApprovalDate = rfApprovalDate;
    }

    public Employee getRfApprovalBy() {
        return rfApprovalBy;
    }

    public void setRfApprovalBy(Employee rfApprovalBy) {
        this.rfApprovalBy = rfApprovalBy;
    }

    public String getRfHeadReply() {
        return rfHeadReply;
    }

    public void setRfHeadReply(String rfHeadReply) {
        this.rfHeadReply = rfHeadReply;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public boolean getSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
