package iss.workshop.inventory_management_system_android.activities.delegation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.helper.MyDateFormat;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.model.Employee;
import iss.workshop.inventory_management_system_android.viewmodel.DelegationViewModel;
import iss.workshop.inventory_management_system_android.viewmodel.StationeryRetrievalViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DelegateDepHeadActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    View rootView;

    private ServiceHelper.ApiService service;
    SharePreferenceHelper sharePreferenceHelper;
    MyDateFormat dateFormat;

    Integer user_id;
    private static final String TAG = "DelegateDepHeadActivity";

    TextView deptHeadName;
    Spinner tempHeadName_spinner;
    Button btn_start_date;
    TextView start_date;
    Button btn_end_date;
    TextView end_date;
    TextView comment;

    Button delegate_btn;

    List<Employee> emplist_cache;

    Employee employee_delegatee;
    DelegationViewModel dvm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().inflate(R.layout.activity_delegate_dep_head, frameLayout);
        txt_menuTitle.setText("Delegate Form for Dept Head");

        deptHeadName = rootView.findViewById(R.id.deptHeadName);
        btn_start_date = rootView.findViewById(R.id.btn_start_date);
        btn_end_date = rootView.findViewById(R.id.btn_end_date);
        start_date = rootView.findViewById(R.id.start_date);
        end_date = rootView.findViewById(R.id.end_date);
        comment = rootView.findViewById(R.id.comment);
        delegate_btn = rootView.findViewById(R.id.delegate);

        tempHeadName_spinner = rootView.findViewById(R.id.tempHeadName);


        btn_start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id = 1;
                showDatePickerDialog(id);
            }
        });

        btn_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int id = 2;
                showDatePickerDialog(id);
            }
        });

        delegate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DelegationViewModel delegationViewModel = new DelegationViewModel();
                delegationViewModel.setEmployee(dvm.getEmployee());
                delegationViewModel.setDelegatee(employee_delegatee);
                delegationViewModel.setDelegateComment(comment.getText().toString());
                delegationViewModel.setStartDate(start_date.getText().toString());
                delegationViewModel.setEndDate(end_date.getText().toString());

                SaveEmpDepHead(delegationViewModel);
            }
        });


        //Get UserName by sharePreference
        sharePreferenceHelper = new SharePreferenceHelper(this);
        user_id = sharePreferenceHelper.getuserId();

        service = ServiceHelper.getClient(this);
        getEmployeeListForDelegate(user_id);


    }

    private void showDatePickerDialog(final int id) {

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        if (id == 1) {
                            String date = i1+1 + "/" + i2 + "/" + i;
                            start_date.setText(date);
                        } else {
                            String date = i1+1 + "/" + i2 + "/" + i;
                            end_date.setText(date);
                        }
                    }
                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        //Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("GMT+08"));
        datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
        datePickerDialog.show();
    }

    public void getEmployeeListForDelegate(int user_id) {

        emplist_cache = new ArrayList<>();

        Call<DelegationViewModel> callsrform = service.getEmployeeListForDelegate(user_id);
        callsrform.enqueue(new Callback<DelegationViewModel>() {
            @Override
            public void onResponse(Call<DelegationViewModel> call, Response<DelegationViewModel> response) {

                if (response.isSuccessful()) {
                    dvm = response.body();
                    if (dvm != null) {
                        deptHeadName.setText(dvm.getEmployee().getFirstname() + " " + dvm.getEmployee().getLastname());

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

        ArrayAdapter arrayAdapter = new ArrayAdapter(DelegateDepHeadActivity.this, R.layout.item_spinner, empname);
        tempHeadName_spinner.setAdapter(arrayAdapter);

        tempHeadName_spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        employee_delegatee = emplist_cache.get(i);
        Log.e(TAG, "onItemSelected: "+ employee_delegatee.getUsername());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void SaveEmpDepHead(DelegationViewModel dvm) {
        Call<DelegationViewModel> callsrform = service.SaveEmpDepHead(dvm);
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
}