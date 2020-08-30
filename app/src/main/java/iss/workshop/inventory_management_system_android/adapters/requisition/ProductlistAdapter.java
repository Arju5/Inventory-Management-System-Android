package iss.workshop.inventory_management_system_android.adapters.requisition;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.requisition.ApplyRequistionActivity;
import iss.workshop.inventory_management_system_android.model.Product;

public class ProductlistAdapter extends ArrayAdapter {

    private static final String TAG = "ProductList Adapter";
    private Context context;
    //public static List<Product> productArrayList = new ArrayList<>();
    //public static List<Product> cacheArrayList = new ArrayList<>();
    LayoutInflater inflater;
    public ArrayList<Product> arraylist = new ArrayList<>();

    int requested_qty = 0;

    public ProductlistAdapter(Context context, int resId,List<Product>productList) {

        super(context, resId);
        this.context = context;
        inflater = LayoutInflater.from(context);
        //this.arraylist = new ArrayList<>();
        this.arraylist.addAll(ApplyRequistionActivity.prodModelList);
        //this.productArrayList.addAll(prodModellist);
    }


    /*public static void setProductArrayList(List<Product> productArrayList) {
        ProductlistAdapter.productArrayList = productArrayList;
        Log.e(TAG, "setProductArrayList: Sizeeee    "  + ProductlistAdapter.productArrayList.size() );
    }
    public static void setcacheArrayList(List<Product> cacheArrayList) {
        ProductlistAdapter.cacheArrayList = cacheArrayList;
        Log.d(TAG, "setcacheArrayList: sizeeee  "+ProductlistAdapter.cacheArrayList.size());
    }*/
    /*public static List<Product> getProductArrayList() {
        return productArrayList;
    }*/

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return ApplyRequistionActivity.prodModelList.size();
    }

    @Override
    public Product getItem(int position) {
        return ApplyRequistionActivity.prodModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            /*inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);*/
            view = inflater.inflate(R.layout.product_item, null, true);

            holder.checkBox = (CheckBox) view.findViewById(R.id.cb);
            holder.product = (TextView) view.findViewById(R.id.prod);
            holder.qty = (EditText)view.findViewById(R.id.prod_qty);

            view.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)view.getTag();
        }

        holder.product.setText(ApplyRequistionActivity.prodModelList.get(position).getProductName());
        holder.checkBox.setChecked(ApplyRequistionActivity.prodModelList.get(position).getSelected());

        //get the qty the user entered
        holder.qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                requested_qty = Integer.parseInt(holder.qty.getText().toString());
                ApplyRequistionActivity.prodModelList.get(position).setProductRequested(requested_qty);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        holder.checkBox.setTag(R.integer.btnplusview, view);
        holder.checkBox.setTag( position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View tempview = (View) holder.checkBox.getTag(R.integer.btnplusview);
                TextView tv = (TextView) tempview.findViewById(R.id.prod);
                //Integer prodPos = (Integer)holder.product.getTag();
                Integer pos = (Integer)  holder.checkBox.getTag();
                Log.e(TAG, "onClick: posiyion in selected list "+position );

                Toast.makeText(context, (position+1)+" is clicked!", Toast.LENGTH_SHORT).show();

                if(arraylist.get(position).getSelected()){
                    arraylist.get(position).setSelected(false);
                }else {
                    arraylist.get(position).setSelected(true);
                }

            }
        });

        return view;
    }
    private class ViewHolder {
        protected CheckBox checkBox;
        private TextView product;
        private EditText qty;
    }
    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        ApplyRequistionActivity.prodModelList.clear();
        /*List<Product> selectedlist= new ArrayList<>() ;
        List<Product> cachelist= new ArrayList<>() ;
        //cacheArrayList.clear();
        selectedlist.clear();
        productArrayList.clear();*/
        Log.d(TAG,"product array list in filter "+ApplyRequistionActivity.prodModelList.size());
        Log.d(TAG,"cacheArrayList in filter "+arraylist.size());
        Log.d(TAG, "search text in filter : "+ charText);
        if (charText.length() == 0) {
            ApplyRequistionActivity.prodModelList.addAll(arraylist);
            //productArrayList.addAll(arraylist);
            /*setcacheArrayList(productArrayList);
            cachelist = productArrayList;*/
        } else {
            for (Product prod : arraylist) {
                if (prod.getProductName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    ApplyRequistionActivity.prodModelList.add(prod);
                    /*selectedlist.add(prod);
                    setcacheArrayList(selectedlist);
                    cachelist = selectedlist;*/
                }
            }
        }

        notifyDataSetChanged();
        //return cachelist;
    }
}