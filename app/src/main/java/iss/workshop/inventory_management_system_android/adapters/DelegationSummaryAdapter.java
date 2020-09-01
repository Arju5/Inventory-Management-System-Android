package iss.workshop.inventory_management_system_android.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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
import iss.workshop.inventory_management_system_android.model.DelegationForm;
import iss.workshop.inventory_management_system_android.model.DisbursementForm;
import iss.workshop.inventory_management_system_android.viewmodel.DelegationViewModel;

public class DelegationSummaryAdapter extends ArrayAdapter {
    private Context context;
    private static final String TAG = "DelegationSummaryAdapter";
    List<DelegationForm> delegationList ;

    public DelegationSummaryAdapter(Context context, int resId) {
        super(context, resId);
        this.context = context;
        Log.d(TAG,"context = "+context);
        Log.d(TAG,"resId = "+resId);
    }

    public void setDelegationList (DelegationViewModel delegationViewModel) {

        this.delegationList = delegationViewModel.delegationList;
        for (DelegationForm delegationForm : delegationViewModel.delegationList)
            add(delegationForm);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.delegate_summary_row, null);

        TextView delegatee = view.findViewById(R.id.dephead_delegatee);
        delegatee.setText(delegationList.get(position).delegatee.firstname + " " + delegationList.get(position).delegatee.lastname);
        TextView assignedDate = view.findViewById(R.id.dephead_delegateAssignedDate);
        assignedDate.setText(removeTfromTimeStamp(delegationList.get(position).dlAssignedDate));
        TextView startDate = view.findViewById(R.id.dephead_delegateStartDate);
        startDate.setText(removeTfromTimeStamp(delegationList.get(position).startDate));
        TextView endDate = view.findViewById(R.id.dephead_delegateEndDate);
        endDate.setText(removeTfromTimeStamp(delegationList.get(position).endDate));
        TextView delegateComments = view.findViewById(R.id.dephead_delegateComments);
        delegateComments.setText(delegationList.get(position).delegateComment);
        TextView delegationType = view.findViewById(R.id.dephead_delegateType);
        delegationType.setTextColor(Color.BLUE);
        delegationType.setText(delegationList.get(position).delegatedType.employeeTypeName);
        TextView delegationid = view.findViewById(R.id.dephead_delegationid);
        delegationid.setText(String.valueOf(delegationList.get(position).id));
        return view;
    }

    public static String removeTfromTimeStamp(String datetime) {
        try {
            MyDateFormat dateFormat = new MyDateFormat();
            Date date = dateFormat.DATE_FORMAT_YMD_HMS.parse(dateFormat.removeTfromServerDate(datetime));
            return dateFormat.DATE_FORMAT_DMY_HMS_AAA.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return datetime;
        }
    }
}
