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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.model.RequisitionForm;

public class ReqSummaryAdapter extends ArrayAdapter {

    private Context context;
    private static final String TAG = "ReqSummaryAdapter";
    List<RequisitionForm> reqlist ;

    public ReqSummaryAdapter(Context context, int resId){
        super(context, resId);
        this.context = context;
        Log.d(TAG,"context = "+context);
    }
    public void setrfSummaryList (List<RequisitionForm> reqlist) {

        this.reqlist = reqlist;
        for (RequisitionForm reqForm : reqlist)
            add(reqForm);
    }

    @NonNull
    @Override
    public View getView(int pos, View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.reqsummary_row, null);

       /* for(RequisitionForm requisitionForm : this.reqlist){
            Log.d(TAG, "onResponse: RequisitionForm Status = "+requisitionForm.rfStatus);
            Log.d(TAG, "onResponse: RequisitionForm Code = "+requisitionForm.rfCode);
            Log.d(TAG, "onResponse: RequisitionForm date = "+requisitionForm.rfDate);
        }*/
        TextView reqId = view.findViewById(R.id.reqId);
        Log.d(TAG, "getpos id = " + reqlist.get(pos).id);
        reqId.setText(String.valueOf(reqlist.get(pos).id));


        TextView reqCode = view.findViewById(R.id.reqCode);
        reqCode.setText(reqlist.get(pos).rfCode);

        /*TextView reqStatus = view.findViewById(R.id.reqStatus);
        Log.d(TAG,"getpos of status = "+reqlist.get(pos).rfStatus);
        reqStatus.setText(reqlist.get(pos).rfStatus.toString());*/

        TextView reqDate = view.findViewById(R.id.reqDate);
        Date testDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            testDate = sdf.parse(reqlist.get(pos).rfDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh-mm-ss a");
        String rfDate = simpleDateFormat.format(testDate);
        reqDate.setText(rfDate);
        return view;
    }

}
