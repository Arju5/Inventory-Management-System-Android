package iss.workshop.inventory_management_system_android.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.SupplierSummaryActivity;
import iss.workshop.inventory_management_system_android.model.Supplier;

public class SupplierSummaryAdapter extends ArrayAdapter {

    private Context context;
    private static final String TAG = "SupplierSummaryAdapter";
    List<Supplier> supplierList;

    public SupplierSummaryAdapter(Context context, int resId) {
        super(context, resId);
        this.context = context;
        Log.d(TAG, "context = " + context);
        Log.d(TAG, "resId = " + resId);
    }

    public void addSupplier (List<Supplier> supplierList) {
        this.supplierList = supplierList;
        for(Supplier supplier : supplierList)
            add(supplier);
    }

    @NotNull
    @Override
    public View getView(int position, @Nullable View convertView, @NotNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                SupplierSummaryActivity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.supplierlist_row, null);

        TextView suppliername = view.findViewById(R.id.suppliername);
        suppliername.setText(supplierList.get(position).supplierName);

        return view;
    }
}
