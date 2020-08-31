package iss.workshop.inventory_management_system_android.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.ProductCatalogueDetails;
import iss.workshop.inventory_management_system_android.model.Product;

public class InventorySummaryAdapter extends BaseAdapter {

    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(ViewGroup parent, int viewType) {//return xml
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inventory_summary,parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {//how to manipulate xml
        ((ViewHolder)holder).bindProduct((Product)getItemsList().get(position));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindCustomHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        //@BindView(R.id.txtproduct)
        TextView txtcategory;
        TextView txtproduct;
        TextView txtuom;
        TextView txtinventory;
        TextView txtlevel;
        TextView txtqty;
        TextView txtlocation;


        private Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();

            //ButterKnife.bind(this,itemView);//to use BindView
            txtcategory = itemView.findViewById(R.id.txtcategory);
            txtproduct = itemView.findViewById(R.id.txtproduct);
            txtuom = itemView.findViewById(R.id.txtuom);
            txtinventory = itemView.findViewById(R.id.txtinventory);
            txtlevel = itemView.findViewById(R.id.txtlevel);
            txtqty = itemView.findViewById(R.id.txtqty);
            txtlocation = itemView.findViewById(R.id.txtlocation);

        }

        public void bindProduct(Product model){
            txtcategory.setText(model.getProductCategory().getProductCategoryName());
            txtproduct.setText(model.getProductName());
            txtuom.setText(model.getUnits());
            txtinventory.setText(String.valueOf(model.getInventoryQuantity()));
            txtlevel.setText(String.valueOf(model.getReorderLevel()));
            txtqty.setText(String.valueOf(model.getReorderQuantity()));
            txtlocation.setText(String.valueOf(model.getInventoryLocation()));

            /*final int id = model.getId();
            final Product product = model;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,String.valueOf(id),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context, ProductCatalogueDetails.class);
                    intent.putExtra("productdetails",(Serializable)product);
                    context.startActivity(intent);
                }
            });*/
        }

    }
}
