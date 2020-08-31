package iss.workshop.inventory_management_system_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import iss.workshop.inventory_management_system_android.adapters.DepartmentSummaryAdapter;
import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.model.Department;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepartmentSummaryActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private static final String TAG = "DeptSummaryActivity";

    private ServiceHelper.ApiService service;
    private DepartmentSummaryAdapter deptSummaryAdapter;
    View rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().inflate(R.layout.activity_department, frameLayout);
        txt_menuTitle.setText("DEPARTMENT SUMMARY");
        service = ServiceHelper.getClient(this);
        deptSummaryAdapter = new DepartmentSummaryAdapter(DepartmentSummaryActivity.this, R.layout.deptlist_row);
        getDepartmentList();
    }

    private void getDepartmentList() {
        deptSummaryAdapter.clear();

        final ListView departmentListView = (ListView) findViewById(R.id.deptlist);
        Call<ArrayList<Department>> calldepartmentlist = service.getDepartmentList();
        calldepartmentlist.enqueue(new Callback<ArrayList<Department>>() {
            @Override
            public void onResponse(Call<ArrayList<Department>> call, Response<ArrayList<Department>> response) {
                if(response.isSuccessful()) {
                    ArrayList<Department> departmentList = response.body();
                    deptSummaryAdapter.addDept(departmentList);
                    departmentListView.setAdapter(deptSummaryAdapter);
                    departmentListView.setOnItemClickListener(DepartmentSummaryActivity.this);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Department>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> av,
                            View v, int pos, long id) {

        TextView textView = v.findViewById(R.id.deptname);
        String deptname = textView.getText().toString();
        Intent intent = new Intent(DepartmentSummaryActivity.this, DepartmentDetailedActivity.class);
        intent.putExtra("departmentname", deptname);
        startActivity(intent);
    }
}