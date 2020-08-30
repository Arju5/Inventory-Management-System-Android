package iss.workshop.inventory_management_system_android.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.model.StationeryRetrievalRequisitionFormProduct;

public class SF_CompletedSRFSummaryAdapter extends BaseAdapter {

    private static final String TAG = "SF_CompletedSRFSummaryA";
    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sfitem_completesrf_summary,parent,false);
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

        TextView number;
        TextView pname;
        TextView approvedqty;
        TextView collectedqty;
        TextView assignedqty;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            number = itemView.findViewById(R.id.number);
            pname = itemView.findViewById(R.id.pname);
            approvedqty = itemView.findViewById(R.id.approvedqty);
            collectedqty = itemView.findViewById(R.id.collectedqty);
            assignedqty = itemView.findViewById(R.id.assignedqty);

        }

        public void bindProduct(final StationeryRetrievalRequisitionFormProduct model,final int position){
            number.setText(model.getRfp().getRequisitionForm().getRfCode());
            //Log.e(TAG, "bindProduct: "+  model.getProduct().getProductName());
            pname.setText(model.getRfp().getProduct().getProductName());
            //Log.e(TAG, "bindProduct: "+model.getProductRequestedTotal());
            approvedqty.setText(String.valueOf(model.getRfp().getProductApproved()));
            //Log.e(TAG, "bindProduct: "+model.getProductReceivedTotal());
            collectedqty.setText(String.valueOf(model.getRfp().getProductCollectedTotal()));
            assignedqty.setText(String.valueOf(model.getProductAssigned()));
        }
    }

}


