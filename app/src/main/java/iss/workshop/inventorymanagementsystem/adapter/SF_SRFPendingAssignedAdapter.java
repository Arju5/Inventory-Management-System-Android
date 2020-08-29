package iss.workshop.inventorymanagementsystem.adapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import iss.workshop.inventorymanagementsystem.R;
import iss.workshop.inventorymanagementsystem.model.StationeryRetrievalRequisitionFormProduct;

public class SF_SRFPendingAssignedAdapter extends BaseAdapter {

    private static final String TAG = "SF_SRFCreatedAdapter";
    private SF_SRFOpenDelegate delegate;

    public SF_SRFPendingAssignedAdapter(SF_SRFOpenDelegate delegate){
        this.delegate = delegate;
    }
    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sfitem_pendingreqsrf,parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).bindProduct((StationeryRetrievalRequisitionFormProduct) getItemsList().get(position),position);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindCustomHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView reqNo;
        TextView pName;
        TextView qtyApprove;
        EditText qtyAssign;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            reqNo = itemView.findViewById(R.id.reqno);
            pName = itemView.findViewById(R.id.pName);
            qtyApprove = itemView.findViewById(R.id.qtyApprove);
            qtyAssign = itemView.findViewById(R.id.QtyAssign);

        }

        public void bindProduct(final StationeryRetrievalRequisitionFormProduct model,final int position){
            Log.e(TAG, "bindProductSSSS: "+  model.getRfp().getRequisitionForm().getRfCode());
            reqNo.setText(model.getRfp().getRequisitionForm().getRfCode().toString());

            pName.setText(String.valueOf(model.getRfp().getProduct().getProductName().toString()));
            Log.e(TAG, "bindProductSSSS: "+ model.getRfp().getProduct().getProductName());
            qtyApprove.setText(String.valueOf(model.getRfp().getProductApproved()));

            qtyAssign.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if(!editable.toString().isEmpty()){
                        delegate.onChangeQty(Integer.parseInt(editable.toString()),position);
                    }
                }
            });




        }
    }

}


