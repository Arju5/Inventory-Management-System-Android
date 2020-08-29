package iss.workshop.inventorymanagementsystem.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import iss.workshop.inventorymanagementsystem.R;
import iss.workshop.inventorymanagementsystem.viewmodel.StationeryProductViewModel;

public class SF_ProductListAdapter extends BaseAdapter {

    private static final String TAG = "SF_ProductListAdapter";
    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sfitem_productlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).bindProduct((StationeryProductViewModel) getItemsList().get(position));
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
        TextView warehouseqty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productname = itemView.findViewById(R.id.desname);
            requestedqty = itemView.findViewById(R.id.productqty);
            warehouseqty = itemView.findViewById(R.id.warehouseqty);

        }

        public void bindProduct(final StationeryProductViewModel model){
            productname.setText(model.getProductname());
            requestedqty.setText(String.valueOf(model.getProductcount()));
            warehouseqty.setText(String.valueOf(model.getWarehousecount()));
        }
    }

}


