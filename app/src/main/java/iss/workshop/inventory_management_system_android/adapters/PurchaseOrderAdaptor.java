/*
package iss.workshop.inventory_management_system_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import iss.workshop.inventory_management_system_android.model.Product;
import iss.workshop.inventory_management_system_android.model.PurchaseOrder;
import iss.workshop.inventory_management_system_android.model.PurchaseOrderSupplierProduct;
import iss.workshop.inventory_management_system_android.model.StationeryRetrievalRequisitionForm;
import iss.workshop.inventory_management_system_android.viewmodel.PODOViewModel;

import static iss.workshop.inventory_management_system_android.adapters.DisbursementFormAdapter.SRRFList;

public class PurchaseOrderAdaptor extends ArrayAdapter {

    private static final String TAG = "PurchaseOrderAdaptor";
    private Context context;
    LayoutInflater inflater;
    PODOViewModel podoViewModel;
    public static List<PurchaseOrderSupplierProduct> poSPList = new ArrayList<>();
    int requested_qty = 0;

    public PurchaseOrderAdaptor(Context context, List<PurchaseOrderSupplierProduct> poSPList) {

        this.context = context;
        this.poSPList = poSPList;
        inflater = LayoutInflater.from(context);


    }

    public static void setPoSPList(List<PurchaseOrderSupplierProduct> poSPList) {
        PurchaseOrderAdaptor.poSPList = poSPList;
    }

    public static List<PurchaseOrderSupplierProduct> getPoSPList() {

        return poSPList;
    }

    @Override
    public int getCount() {
        return poSPList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
*/
