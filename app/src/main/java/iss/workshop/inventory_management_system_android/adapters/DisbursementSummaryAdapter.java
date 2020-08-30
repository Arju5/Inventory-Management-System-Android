package iss.workshop.inventory_management_system_android.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.model.DisbursementForm;

public class DisbursementSummaryAdapter extends ArrayAdapter {
    private Context context;
    private static final String TAG = "DisburseSummaryAdapter";
    List<DisbursementForm> disbursementFormList ;

    public DisbursementSummaryAdapter(Context context, int resId) {
        super(context, resId);
        this.context = context;
        Log.d(TAG,"context = "+context);
        Log.d(TAG,"resId = "+resId);
    }

    public void setDisbursementFormList (List<DisbursementForm> disbursementFormList) {

        this.disbursementFormList = disbursementFormList;
        for (DisbursementForm disbursementForm : disbursementFormList)
            add(disbursementForm);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.disbursement_summary_row, null);

        TextView dfcode = view.findViewById(R.id.dfsummary_DisbursementCode);
        dfcode.setText(disbursementFormList.get(position).dfCode);
        TextView storeClerk = view.findViewById(R.id.dfsummary_StoreClerk);
        storeClerk.setText(disbursementFormList.get(position).storeClerk.firstname + " " + disbursementFormList.get(position).storeClerk.lastname);
        TextView departmentName = view.findViewById(R.id.dfsummary_Department);
        departmentName.setText(disbursementFormList.get(position).deptRep.department.departmentName);
        /*TextView departmentRep = view.findViewById(R.id.dfsummary_DepartmentRep);
        departmentRep.setText(disbursementFormList.get(position).deptRep.firstname + " " + disbursementFormList.get(position).deptRep.lastname);*/
        TextView deliveryTime = view.findViewById(R.id.dfsummary_DeliveryDate);
        deliveryTime.setText(disbursementFormList.get(position).dfDeliveryDate);
        TextView collectionPoint = view.findViewById(R.id.dfsummary_collectionPoint);
        collectionPoint.setText(disbursementFormList.get(position).collectionPoint.collectionName);

        return view;
    }
}
