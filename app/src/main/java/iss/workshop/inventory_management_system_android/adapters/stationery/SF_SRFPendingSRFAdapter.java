package iss.workshop.inventory_management_system_android.adapters.stationery;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.adapters.BaseAdapter;
import iss.workshop.inventory_management_system_android.model.StationeryRetrievalProduct;

public class SF_SRFPendingSRFAdapter extends BaseAdapter {

    private static final String TAG = "SF_SRFPendingAdapter";
    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sfitem_pendingsrf,parent,false);
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

        TextView productname;
        TextView requestedqty;

        TextView receivedqty;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productname = itemView.findViewById(R.id.descriptionPName);
            requestedqty = itemView.findViewById(R.id.productPQty);
            receivedqty = itemView.findViewById(R.id.receivedPQty);

        }

        public void bindProduct(final StationeryRetrievalProduct model,final int position){
            productname.setText(model.getProduct().getProductName());
            Log.e(TAG, "bindProduct: "+  model.getProduct().getProductName());
            requestedqty.setText(String.valueOf(model.getProductRequestedTotal()));
            Log.e(TAG, "bindProduct: "+model.getProductRequestedTotal());
            receivedqty.setText(String.valueOf(model.getProductReceivedTotal()));
            Log.e(TAG, "bindProduct: "+model.getProductReceivedTotal());
        }
    }

}


