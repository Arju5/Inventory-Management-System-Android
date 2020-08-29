package iss.workshop.inventorymanagementsystem.viewmodel;

import java.util.List;

public class StationeryRequisitionProductViewModel {
    public String username;
    public String comment;
    public List<StationeryProductViewModel> spvm;
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

    public List<StationeryProductViewModel> getSpvm() {
        return spvm;
    }

    public void setSpvm(List<StationeryProductViewModel> spvm) {
        this.spvm = spvm;
    }

    public List<Integer> getRequisitionIdList() {
        return requisitionIdList;
    }

    public void setRequisitionIdList(List<Integer> requisitionIdList) {
        this.requisitionIdList = requisitionIdList;
    }
}
