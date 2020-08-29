package iss.workshop.inventorymanagementsystem.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import iss.workshop.inventorymanagementsystem.R;
import iss.workshop.inventorymanagementsystem.model.StationeryRetrievalProduct;

public class SF_CompletedSRFWarehouseAdapter extends BaseAdapter {

    private static final String TAG = "SF_SRFPendingAdapter";
    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sfitem_completesrf_warehouse,parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).bindProduct((StationeryRetrievalProduct) getItemsList().get(position),position);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindCustomHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView description;
        TextView requestedqty;
        TextView receivedqty;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.description);
            requestedqty = itemView.findViewById(R.id.requestedqty);
            receivedqty = itemView.findViewById(R.id.receivedqty);

        }

        public void bindProduct(final StationeryRetrievalProduct model,final int position){
            description.setText(model.getProduct().getProductName());
            Log.e(TAG, "bindProduct: "+  model.getProduct().getProductName());
            requestedqty.setText(String.valueOf(model.getProductRequestedTotal()));
            Log.e(TAG, "bindProduct: "+model.getProductRequestedTotal());
            receivedqty.setText(String.valueOf(model.getProductReceivedTotal()));
            Log.e(TAG, "bindProduct: "+model.getProductReceivedTotal());
        }
    }

}


