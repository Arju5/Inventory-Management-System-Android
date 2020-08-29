package iss.workshop.inventorymanagementsystem.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import iss.workshop.inventorymanagementsystem.R;
import iss.workshop.inventorymanagementsystem.model.StationeryRetrievalRequisitionForm;

public class SF_CompletedSRFReqAdapter extends BaseAdapter {

    private static final String TAG = "SF_CompletedSRFReqAdapt";
    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sfitem_completesrf_req,parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).bindProduct((StationeryRetrievalRequisitionForm) getItemsList().get(position),position);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindCustomHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView req_number;
        TextView req_date;
        TextView reqstatus;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            req_number = itemView.findViewById(R.id.req_number);
            req_date = itemView.findViewById(R.id.req_date);
            reqstatus = itemView.findViewById(R.id.reqstatus);

        }

        public void bindProduct(final StationeryRetrievalRequisitionForm model,final int position){
            req_number.setText(model.getRequisitionForm().getRfCode());
            //Log.e(TAG, "bindProduct: "+  model.getProduct().getProductName());
            /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss a");
            String rfDate = simpleDateFormat.format(model.getRequisitionForm().getRfDate());*/
            req_date.setText(model.getRequisitionForm().getRfDate());
            //Log.e(TAG, "bindProduct: "+model.getProductRequestedTotal());
            if(model.getRequisitionForm().getRfStatus() == 0){
                reqstatus.setText("Submitted");
            }
            else if(model.getRequisitionForm().getRfStatus() == 6){
                reqstatus.setText("Ongoing");
            }


            //Log.e(TAG, "bindProduct: "+model.getProductReceivedTotal());
        }
    }

}


