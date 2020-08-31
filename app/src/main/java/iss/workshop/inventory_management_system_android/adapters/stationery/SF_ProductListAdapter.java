package iss.workshop.inventory_management_system_android.adapters.stationery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.adapters.BaseAdapter;
import iss.workshop.inventory_management_system_android.model.StationeryRetrievalProduct;

public class SF_ProductListAdapter extends BaseAdapter {

    private static final String TAG = "SF_ProductListAdapter";
    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sfitem_productlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).bindProduct((StationeryRetrievalProduct) getItemsList().get(position));
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
        TextView approvedqty;
        TextView pendingqty;
        TextView warehouseqty;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productname = itemView.findViewById(R.id.desname);
            approvedqty = itemView.findViewById(R.id.productqty);
            warehouseqty = itemView.findViewById(R.id.warehouseqty);
            pendingqty = itemView.findViewById(R.id.pendingqty);

        }

        public void bindProduct(final StationeryRetrievalProduct model){
            productname.setText(model.getProduct().getProductName());
            approvedqty.setText(String.valueOf(model.getProductApprovedCount()));
            pendingqty.setText(String.valueOf(model.getProductCount()));
            warehouseqty.setText(String.valueOf(model.getProduct().getInventoryQuantity()));
        }
    }

}


