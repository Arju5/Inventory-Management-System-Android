package iss.workshop.inventory_management_system_android.adapters.requisition;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.model.RequisitionFormsProduct;

public class ReqProductAdapter extends ArrayAdapter {
    private Context context;
    private static final String TAG = "ReqProductAdapter";
    public static List<RequisitionFormsProduct> reqproductlist = new ArrayList<>();
    public String rfStatus ;
    SharePreferenceHelper sharePreferenceHelper;

    public ReqProductAdapter(Context context,int resId, List<RequisitionFormsProduct> reqproductlist) {

        super(context, resId);
        this.context = context;
        this.reqproductlist = reqproductlist;
        Log.d(TAG, "context = " + context);
        sharePreferenceHelper = new SharePreferenceHelper(context);
    }


    public void setrfProductList(List<RequisitionFormsProduct> reqproductlist) {

        //this.reqproductlist = reqproductlist;
        ReqProductAdapter.reqproductlist = reqproductlist;
        for (RequisitionFormsProduct reqProduct : reqproductlist)
            add(reqProduct);
    }
    public void setrfStatus(String rfStatus){
        this.rfStatus = rfStatus;
        Log.d(TAG,"rfstatus inside reqproduct adapter::: "+rfStatus);
    }
    @NonNull
    @Override
    public View getView(final int pos, View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.reqform_row, null);


        TextView prodName = view.findViewById(R.id.req_product_name);
        prodName.setText(reqproductlist.get(pos).product.productName);

        TextView prodUom = view.findViewById(R.id.product_uom_value);
        prodUom.setText(reqproductlist.get(pos).product.units);

        TextView requestedQty = view.findViewById(R.id.qty_requested_value);
        String request_qty = String.valueOf(reqproductlist.get(pos).productRequested);
        requestedQty.setText(request_qty);

        final EditText toapproveQty = view.findViewById(R.id.qty_approve_value);
        TextView approvedQty = view.findViewById(R.id.qty_approved);

        if(sharePreferenceHelper.getUserRole().equals("Employee") || rfStatus!="Submitted"){
            //toapproveQty.setEnabled(false);
            toapproveQty.setVisibility(View.GONE);
            approvedQty.setText(String.valueOf(reqproductlist.get(pos).productApproved));
        }
        else if(sharePreferenceHelper.getUserRole().equals("Department Head")&& rfStatus=="Submitted")
        { approvedQty.setVisibility(View.GONE);}

        toapproveQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().isEmpty()){
                    int qty_value = Integer.parseInt(editable.toString());
                    reqproductlist.get(pos).setProductApproved(qty_value);
                    //rf_approve.onChangeQty(Integer.parseInt(editable.toString()),pos);
                }
                if(rfStatus!="Submitted"){
                    Log.d(TAG,"product approved inside adapter  "+ reqproductlist.get(pos).productApproved);
                    toapproveQty.setText(reqproductlist.get(pos).productApproved);
                }

            }
        });
        return view;
    }

}

