package iss.workshop.inventory_management_system_android.viewmodel;

import java.util.List;

import iss.workshop.inventory_management_system_android.model.DisbursementFormRequisitionFormProduct;
import iss.workshop.inventory_management_system_android.model.Employee;
import iss.workshop.inventory_management_system_android.model.Product;
import iss.workshop.inventory_management_system_android.model.RequisitionForm;
import iss.workshop.inventory_management_system_android.model.RequisitionFormsProduct;

public class RequisitionViewModel {
    public Employee employee;
    public List<Product> productList;

    public RequisitionForm requisitionForm;
    public List<DisbursementFormRequisitionFormProduct> dfrfpList;
    public List<RequisitionFormsProduct> rfpList;

    public String comment;
    public String rfHeadReply;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public RequisitionForm getRequisitionForm() {
        return requisitionForm;
    }

    public void setRequisitionForm(RequisitionForm requisitionForm) {
        this.requisitionForm = requisitionForm;
    }

    public List<DisbursementFormRequisitionFormProduct> getDfrfpList() {
        return dfrfpList;
    }

    public void setDfrfpList(List<DisbursementFormRequisitionFormProduct> dfrfpList) {
        this.dfrfpList = dfrfpList;
    }

    public List<RequisitionFormsProduct> getRfpList() {
        return rfpList;
    }

    public void setRfpList(List<RequisitionFormsProduct> rfpList) {
        this.rfpList = rfpList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRfHeadReply() {
        return rfHeadReply;
    }

    public void setRfHeadReply(String rfHeadReply) {
        this.rfHeadReply = rfHeadReply;
    }
}
