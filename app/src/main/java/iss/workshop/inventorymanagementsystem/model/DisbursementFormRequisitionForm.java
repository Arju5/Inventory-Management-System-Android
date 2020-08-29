package iss.workshop.inventorymanagementsystem.model;

public class DisbursementFormRequisitionForm {
    public int id;
    public DisbursementForm disbursementForm;
    public RequisitionForm requisitionForm;
    public int dfrfStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DisbursementForm getDisbursementForm() {
        return disbursementForm;
    }

    public void setDisbursementForm(DisbursementForm disbursementForm) {
        this.disbursementForm = disbursementForm;
    }

    public RequisitionForm getRequisitionForm() {
        return requisitionForm;
    }

    public void setRequisitionForm(RequisitionForm requisitionForm) {
        this.requisitionForm = requisitionForm;
    }

    public int getDfrfStatus() {
        return dfrfStatus;
    }

    public void setDfrfStatus(int dfrfStatus) {
        this.dfrfStatus = dfrfStatus;
    }
}
