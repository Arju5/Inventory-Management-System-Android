package iss.workshop.inventory_management_system_android.adapters;

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

import iss.workshop.inventory_management_system_android.activities.DepartmentSummaryActivity;
import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.model.Department;

public class DepartmentSummaryAdapter extends ArrayAdapter {

    private Context context;
    private static final String TAG = "DeptSummaryAdapter";
    List<Department> departmentList;

    public DepartmentSummaryAdapter(Context context, int resId) {
        super(context, resId);
        this.context = context;
        Log.d(TAG,"context = " + context);
        Log.d(TAG,"resId = " + resId);
    }

    public void addDept (List<Department> departmentList) {
        this.departmentList = departmentList;
        for(Department department : departmentList)
            add(department);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                DepartmentSummaryActivity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.deptlist_row, null);

        TextView deptname = view.findViewById(R.id.deptname);
        deptname.setText(departmentList.get(position).departmentName);

        return view;
    }
}
