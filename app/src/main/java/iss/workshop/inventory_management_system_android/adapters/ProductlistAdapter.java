package iss.workshop.inventory_management_system_android.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.model.Product;

public class ProductlistAdapter extends BaseAdapter  {

    private static final String TAG = "ProductList Adapter";
    private Context context;
    public static List<Product> productArrayList = new ArrayList<>();
    LayoutInflater inflater;
    int requested_qty = 0;

    public ProductlistAdapter(Context context, List<Product> productArrayList) {

        this.context = context;
        inflater = LayoutInflater.from(context);
        this.productArrayList = productArrayList;

    }

    public static void setProductArrayList(List<Product> productArrayList) {
        ProductlistAdapter.productArrayList = productArrayList;
    }

    public static List<Product> getProductArrayList() {
        return productArrayList;
    }

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
        return productArrayList.size();
    }

    @Override
    public Product getItem(int position) {
        return productArrayList.get(position);
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
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.product_item, null, true);

            holder.checkBox = (CheckBox) view.findViewById(R.id.cb);
            holder.product = (TextView) view.findViewById(R.id.prod);
            holder.qty = (EditText)view.findViewById(R.id.prod_qty);

            view.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)view.getTag();
        }

        holder.product.setText(productArrayList.get(position).getProductName());
        holder.checkBox.setChecked(productArrayList.get(position).getSelected());

        //get the qty the user entered
        holder.qty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                requested_qty = Integer.parseInt(holder.qty.getText().toString());
                productArrayList.get(position).setProductRequested(requested_qty);
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
                Integer pos = (Integer)  holder.checkBox.getTag();
                Toast.makeText(context, (pos+1)+" is clicked!", Toast.LENGTH_SHORT).show();

                if(productArrayList.get(pos).getSelected()){
                    productArrayList.get(pos).setSelected(false);
                }else {
                    productArrayList.get(pos).setSelected(true);
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
        List<Product> selectedlist= new ArrayList<>() ;
        selectedlist.clear();

        Log.d(TAG,"product array list in filter "+productArrayList.size());
        if (charText.length() == 0) {
            //productArrayList.addAll(arraylist);
            setProductArrayList(productArrayList);
        } else {
            for (Product prod : productArrayList) {
                if (prod.getProductName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    selectedlist.add(prod);
                    setProductArrayList(selectedlist);
                }
            }
        }
        notifyDataSetChanged();
    }
}