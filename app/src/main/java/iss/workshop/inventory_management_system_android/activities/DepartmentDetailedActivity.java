package iss.workshop.inventory_management_system_android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.model.Department;
import iss.workshop.inventory_management_system_android.model.Employee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepartmentDetailedActivity extends AppCompatActivity {
    private static final String TAG = "DeptDetailedActivity";
    private Employee employee;
    private ServiceHelper.ApiService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_detailed);
        Intent intent = getIntent();
        service = ServiceHelper.getClient(this);
        getDepartmentInfo(intent.getStringExtra("departmentname"));
    }

    private void getDepartmentInfo(String deptname) {

        Call<Department> departmentCall = service.getDepartmentInfo(deptname);
        departmentCall.enqueue(new Callback<Department>() {
            @Override
            public void onResponse(Call<Department> call, Response<Department> response) {
                if(response.isSuccessful()) {
                    Department department = response.body();

                    TextView deptCode = findViewById(R.id.departmentCode);
                    deptCode.setText(department.getDeptCode());
                    TextView deptname = findViewById(R.id.departmentName);
                    deptname.setText(department.getDepartmentName());
                    TextView depthead = findViewById(R.id.departmentHead);
                    depthead.setText(String.valueOf(department.getDeptHead().username));
                    TextView deptrep = findViewById(R.id.departmentRepresentative);
                    deptrep.setText(String.valueOf(department.getDeptRep()));
                    TextView contact = findViewById(R.id.contactNo);
                    contact.setText(department.getPhoneNumber());
                    TextView email = findViewById(R.id.email);
                    email.setText(department.getEmail());
                    TextView collectionpt = findViewById(R.id.collectionPoint);
                    collectionpt.setText(department.getCollectionPoint().collectionName);

                }
            }

            @Override
            public void onFailure(Call<Department> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });

    }

    /*public void getEmployeeById(int id) {
        Call<Employee> employeeCall = service.getEmployeeById(id);
        employeeCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "Employee Found by Id");


                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {

            }
        });
    }*/

}