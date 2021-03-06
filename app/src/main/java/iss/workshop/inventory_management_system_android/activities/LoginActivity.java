package iss.workshop.inventory_management_system_android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.dashboard.DepHeadDashboardActivity;
import iss.workshop.inventory_management_system_android.activities.dashboard.DeptRepDashboardActivity;
import iss.workshop.inventory_management_system_android.activities.dashboard.StoreClerkDashboardActivity;
import iss.workshop.inventory_management_system_android.activities.dashboard.StoreManagerDashboardActivity;
import iss.workshop.inventory_management_system_android.activities.department.DepHeadApproveDisbursementActivity;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.model.Employee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Login Activity";
    SharePreferenceHelper sharePreferenceHelper;

    EditText editUsername, editPassword;
    Button btnSignIn, btnRegister;
    private ServiceHelper.ApiService service;
    String emptype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharePreferenceHelper = new SharePreferenceHelper(this);
        editUsername = (EditText)findViewById(R.id.editUsername);
        editPassword=(EditText)findViewById(R.id.editPassword);

        btnSignIn=(Button)findViewById(R.id.btnSignIn);
        btnRegister=(Button)findViewById(R.id.btnRegister);

        service = ServiceHelper.getClient(this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Employee> callemp = service.getEmpObj(editUsername.getText().toString(), editPassword.getText().toString());
                callemp.enqueue(new Callback<Employee>() {
                    @Override
                    public void onResponse(Call<Employee> call, Response<Employee> response) {
                        if (response.isSuccessful()) {
                            Employee employee = response.body();
                            if(employee != null){
                                Log.e(TAG, "onResponse: employee_name : "+employee.firstname);
                                Log.e(TAG, "onResponse: employee : "+employee.employeeType.employeeTypeName);
                                if (employee.tempDeptHeadType != null ) {
                                    Log.e(TAG, "onResponse: employee : "+employee.tempDeptHeadType.employeeTypeName);
                                    sharePreferenceHelper.setLogin(employee.getUsername(),employee.getId(),employee.tempDeptHeadType.employeeTypeName);
                                    emptype = employee.tempDeptHeadType.employeeTypeName;
                                } else {
                                    sharePreferenceHelper.setLogin(employee.getUsername(),employee.getId(),employee.employeeType.employeeTypeName);
                                    emptype = employee.employeeType.employeeTypeName;
                                }

                                //add username to shared preferences

                                if (emptype.equals("Store Clerk")) {
                                    Intent intent = new Intent(LoginActivity.this, StoreClerkDashboardActivity.class);
                                    intent.putExtra("Status", emptype);
                                    startActivity(intent);
                                } else if (emptype.equals("Employee")) {
                                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                    intent.putExtra("Status", emptype);
                                    startActivity(intent);
                                } else if (emptype.equals("Department Head")) {
                                    Intent intent = new Intent(LoginActivity.this, DepHeadDashboardActivity.class);
                                    intent.putExtra("Status", emptype);
                                    startActivity(intent);
                                } else if (emptype.equals("Department Representative")) {
                                    Intent intent = new Intent(LoginActivity.this, DeptRepDashboardActivity.class);
                                    intent.putExtra("Status", emptype);
                                    startActivity(intent);
                                } else if (emptype.equals("Store Manager")) {
                                    Intent intent = new Intent(LoginActivity.this, StoreManagerDashboardActivity.class);
                                    intent.putExtra("Status", emptype);
                                    startActivity(intent);
                                }

                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Login Failed. Please report to Logic University IT Care", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Employee> call, Throwable t) {
                        Log.e(TAG, "onFailure: ",t );
                    }
                });
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(LoginActivity.this, "Please Email to Logic University", Toast.LENGTH_SHORT).show();
            }
        });

    }

}