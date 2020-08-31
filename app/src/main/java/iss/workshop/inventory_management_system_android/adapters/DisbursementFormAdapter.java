package iss.workshop.inventory_management_system_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.helper.MyDateFormat;
import iss.workshop.inventory_management_system_android.model.StationeryRetrievalRequisitionForm;
import iss.workshop.inventory_management_system_android.viewmodel.DisbursementViewModel;

public class DisbursementFormAdapter extends BaseAdapter {
    private Context context;
    private static final String TAG = "DisbursementFormAdapter";
    DisbursementViewModel disbursementViewModel;
    public static List<StationeryRetrievalRequisitionForm> SRRFList;
    LayoutInflater inflater;

    public DisbursementFormAdapter(Context context, List<StationeryRetrievalRequisitionForm> SRRFList) {

        this.context = context;
        inflater = LayoutInflater.from(context);
        this.SRRFList = SRRFList;

    }

    public static void setSRRFList(List<StationeryRetrievalRequisitionForm> SRRFList) {
        DisbursementFormAdapter.SRRFList = SRRFList;
    }

    public static List<StationeryRetrievalRequisitionForm> getSRRFList() {
        return SRRFList;
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
        return SRRFList.size();
    }

    @Override
    public StationeryRetrievalRequisitionForm getItem(int position) {
        return SRRFList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.disbursement_create_row, null, true);

            holder.checkBox = (CheckBox) view.findViewById(R.id.dfcreaterow_CheckBox);
            holder.stationeryRetrival = (TextView) view.findViewById(R.id.dfcreaterow_SRCode);
            holder.requisitionNumber = (TextView) view.findViewById(R.id.dfcreaterow_requisitionNumber);
            holder.departmentName = (TextView) view.findViewById(R.id.dfcreaterow_departmentName);
            holder.approvedDate = (TextView) view.findViewById(R.id.dfcreaterow_DateApproved);
            view.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)view.getTag();
        }
        holder.departmentName.setText(SRRFList.get(position).requisitionForm.employee.department.departmentName);
        holder.stationeryRetrival.setText(SRRFList.get(position).stationeryRetrieval.srCode);
        holder.requisitionNumber.setText(SRRFList.get(position).requisitionForm.rfCode);
        try {
            MyDateFormat dateFormat = new MyDateFormat();
            Date date = dateFormat.DATE_FORMAT_YMD_HMS.parse(dateFormat.removeTfromServerDate(SRRFList.get(position).requisitionForm.getRfApprovalDate()));
            holder.approvedDate.setText(dateFormat.DATE_FORMAT_DMY_HMS_AAA.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            holder.approvedDate.setText(SRRFList.get(position).requisitionForm.getRfApprovalDate());
        }
        holder.checkBox.setChecked(SRRFList.get(position).getIsSelected());
        holder.checkBox.setTag(R.integer.btnplusview, view);
        holder.checkBox.setTag( position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View tempview = (View) holder.checkBox.getTag(R.integer.btnplusview);
                TextView tv = (TextView) tempview.findViewById(R.id.prod);
                Integer pos = (Integer)  holder.checkBox.getTag();

                if(SRRFList.get(pos).getIsSelected()){
                    SRRFList.get(pos).setIsSelected(false);
                }else {
                    SRRFList.get(pos).setIsSelected(true);
                }

            }
        });

        return view;
    }
    private class ViewHolder {
        protected CheckBox checkBox;
        private TextView stationeryRetrival;
        private TextView requisitionNumber;
        private TextView departmentName;
        private TextView approvedDate;
    }
}
