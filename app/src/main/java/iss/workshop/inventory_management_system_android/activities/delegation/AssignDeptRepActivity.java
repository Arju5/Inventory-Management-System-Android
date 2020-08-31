package iss.workshop.inventory_management_system_android.activities.delegation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.helper.MyDateFormat;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.model.Employee;
import iss.workshop.inventory_management_system_android.viewmodel.DelegationViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignDeptRepActivity extends BaseActivity implements AdapterView.OnItemSelectedListener{

    View rootView;

    private ServiceHelper.ApiService service;
    SharePreferenceHelper sharePreferenceHelper;
    MyDateFormat dateFormat;

    Integer user_id;
    private static final String TAG = "Delegate Dep Rep";

    TextView deptRepName;
    Spinner tempRepName_spinner;
    TextView comment;

    Button delegate_btn;
    Button cancel_btn;

    List<Employee> emplist_cache;

    Employee deprep_delegatee;
    DelegationViewModel dvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().inflate(R.layout.activity_assign_dept_rep, frameLayout);
        txt_menuTitle.setText("DELEGATE DEPARTMENT REP");

        deptRepName = rootView.findViewById(R.id.deprep_deptHeadName);
        comment = rootView.findViewById(R.id.deprep_comment);
        delegate_btn = rootView.findViewById(R.id.deprep_delegate);
        cancel_btn = rootView.findViewById(R.id.deprep_cancel);

        tempRepName_spinner = rootView.findViewById(R.id.deprep_deptRepName);

        delegate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DelegationViewModel delegationViewModel = new DelegationViewModel();
                delegationViewModel.setEmployee(dvm.getEmployee());
                delegationViewModel.setDelegatee(deprep_delegatee);
                delegationViewModel.setDelegateComment(comment.getText().toString());
                SaveEmpDepRep(delegationViewModel);
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DelegationSummaryActivity.class);
                startActivity(intent);
            }
        });

        //Get UserName by sharePreference
        sharePreferenceHelper = new SharePreferenceHelper(this);
        user_id = sharePreferenceHelper.getuserId();

        service = ServiceHelper.getClient(this);
        getEmployeeListForDelegateDepRep(user_id);

    }

    public void getEmployeeListForDelegateDepRep(int user_id) {

        emplist_cache = new ArrayList<>();

        Call<DelegationViewModel> callsrform = service.getEmployeeListForDelegate(user_id);
        callsrform.enqueue(new Callback<DelegationViewModel>() {
            @Override
            public void onResponse(Call<DelegationViewModel> call, Response<DelegationViewModel> response) {

                if (response.isSuccessful()) {
                    dvm = response.body();
                    if (dvm != null) {
                        deptRepName.setText(dvm.getEmployee().getFirstname() + " " + dvm.getEmployee().getLastname());

                        emplist_cache.addAll(dvm.getDeptEmployeeList());

                        Log.e(TAG, "onResponse: Emp Size" + dvm.getDeptEmployeeList().size());

                        setupSpinner();
                    }
                } else {
                    Log.e(TAG, "onResponse: " + response.message());
                    Toast.makeText(getApplicationContext(), "Check Assigned Quantities", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DelegationViewModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setupSpinner(){
        List<String> empname = new ArrayList<>();
        for (Employee emp : emplist_cache) {
            empname.add(emp.getFirstname() + " " + emp.getLastname());
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(AssignDeptRepActivity.this, R.layout.item_spinner, empname);
        tempRepName_spinner.setAdapter(arrayAdapter);

        tempRepName_spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        deprep_delegatee = emplist_cache.get(i);
        Log.e(TAG, "onItemSelected: "+ deprep_delegatee.getUsername());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void SaveEmpDepRep(DelegationViewModel dvm) {
        Call<DelegationViewModel> callsrform = service.SaveEmpDepRep(dvm);
        callsrform.enqueue(new Callback<DelegationViewModel>() {
            @Override
            public void onResponse(Call<DelegationViewModel> call, Response<DelegationViewModel> response) {

                if (response.isSuccessful()) {
                    DelegationViewModel dvmresult = response.body();
                    if(dvmresult != null){
                        Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.e(TAG, "onResponse: " + response.message());
                    Toast.makeText(getApplicationContext(), "Check Assigned Date", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DelegationViewModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

}