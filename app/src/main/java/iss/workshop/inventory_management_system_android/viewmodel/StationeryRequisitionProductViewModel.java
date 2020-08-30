package iss.workshop.inventory_management_system_android.viewmodel;

import java.util.List;

import iss.workshop.inventory_management_system_android.model.StationeryRetrievalProduct;

public class StationeryRequisitionProductViewModel {
    public String username;
    public String comment;
    public List<StationeryRetrievalProduct> spvm;
    public List<Integer> requisitionIdList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<StationeryRetrievalProduct> getSpvm() {
        return spvm;
    }

    public void setSpvm(List<StationeryRetrievalProduct> spvm) {
        this.spvm = spvm;
    }

    public List<Integer> getRequisitionIdList() {
        return requisitionIdList;
    }

    public void setRequisitionIdList(List<Integer> requisitionIdList) {
        this.requisitionIdList = requisitionIdList;
    }
}
