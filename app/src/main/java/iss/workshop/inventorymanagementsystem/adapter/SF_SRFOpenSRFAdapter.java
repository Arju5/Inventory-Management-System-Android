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
import iss.workshop.inventorymanagementsystem.model.StationeryRetrievalProduct;

public class SF_SRFOpenSRFAdapter extends BaseAdapter {

    private static final String TAG = "SF_SRFCreatedAdapter";
    private SF_SRFOpenDelegate delegate;

    public SF_SRFOpenSRFAdapter(SF_SRFOpenDelegate delegate){
        this.delegate = delegate;
    }
    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sfitem_opensrf,parent,false);
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

        EditText warehouseqty;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productname = itemView.findViewById(R.id.descriptionname);
            requestedqty = itemView.findViewById(R.id.productQty);
            warehouseqty = itemView.findViewById(R.id.assignedqty);

        }

        public void bindProduct(final StationeryRetrievalProduct model,final int position){
            productname.setText(model.getProduct().getProductName());
            Log.e(TAG, "bindProduct: "+  model.getProduct().getProductName());
            requestedqty.setText(String.valueOf(model.getProductRequestedTotal()));
            Log.e(TAG, "bindProduct: "+model.getProduct().getProductRequested());
            //warehouseqty.setText(String.valueOf(model.getWarehousecount()));
            /*RxTextView.textChanges(warehouseqty)
                    .debounce(500, TimeUnit.MILLISECONDS)
                    *//*.observeOn(AndroidSchedulers.mainThread())*//*
                    .subscribe(new Consumer<CharSequence>() {
                        @Override
                        public void accept(CharSequence string) throws Throwable {
                            Log.e(TAG, "accept: " + string );
                            model.setProductAssigned(Integer.parseInt(string.toString()));
                            update(model,position);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable error) throws Throwable {
                            Log.e("ERROR", "ERROR LISTENING: " + error.getMessage());
                        }
                    });*/
            warehouseqty.addTextChangedListener(new TextWatcher() {
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


