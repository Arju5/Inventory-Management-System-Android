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

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.disbursement.DisbursementSummaryActivity;
import iss.workshop.inventory_management_system_android.helper.MyDateFormat;
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

        TextView DF_DATE = (TextView) view.findViewById(R.id.DF_DATE);
        if (DisbursementSummaryActivity.status.equals("OPEN")) {
            DF_DATE.setText("Expected Delivery");
        } else if (DisbursementSummaryActivity.status.equals("PENDING_DELIVERY")) {
            DF_DATE.setText("Confirmed Delivery");
        } else if (DisbursementSummaryActivity.status.equals("PENDING_ASSIGNMENT")) {
            DF_DATE.setText("HandOver Date");
        } else if (DisbursementSummaryActivity.status.equals("COMPLETED")) {
            DF_DATE.setText("Completion Date");
        }

        TextView dfcode = view.findViewById(R.id.dfsummary_DisbursementCode);
        dfcode.setText(disbursementFormList.get(position).dfCode);
        TextView storeClerk = view.findViewById(R.id.dfsummary_StoreClerk);
        storeClerk.setText(disbursementFormList.get(position).storeClerk.firstname + " " + disbursementFormList.get(position).storeClerk.lastname);
        TextView departmentName = view.findViewById(R.id.dfsummary_Department);
        departmentName.setText(disbursementFormList.get(position).deptRep.department.departmentName);
        /*TextView departmentRep = view.findViewById(R.id.dfsummary_DepartmentRep);
        departmentRep.setText(disbursementFormList.get(position).deptRep.firstname + " " + disbursementFormList.get(position).deptRep.lastname);*/
        TextView deliveryTime = view.findViewById(R.id.dfsummary_DeliveryDate);

        TextView collectionPoint = view.findViewById(R.id.dfsummary_collectionPoint);
        collectionPoint.setText(disbursementFormList.get(position).collectionPoint.collectionName);
        try {
            MyDateFormat dateFormat = new MyDateFormat();
            Date date = dateFormat.DATE_FORMAT_YMD_HMS.parse(dateFormat.removeTfromServerDate(disbursementFormList.get(position).dfDeliveryDate));
            deliveryTime.setText(dateFormat.DATE_FORMAT_DMY_HMS_AAA.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            deliveryTime.setText(disbursementFormList.get(position).dfDeliveryDate);
        }
        return view;
    }
}
