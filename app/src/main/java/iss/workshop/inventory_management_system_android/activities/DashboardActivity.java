package iss.workshop.inventory_management_system_android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;


import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.dashboard.StoreClerkDashboardActivity;
import iss.workshop.inventory_management_system_android.activities.disbursement.DisbursementSummaryActivity;
import iss.workshop.inventory_management_system_android.activities.requisition.ApplyRequistionActivity;
import iss.workshop.inventory_management_system_android.activities.requisition.RequisitionSummaryActivity;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.viewmodel.DashboardViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends BaseActivity {

    View rootView;
    private static final String TAG = "EmployeeDashboard";
    SharePreferenceHelper sharePreferenceHelper;
    private ServiceHelper.ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharePreferenceHelper = new SharePreferenceHelper(this);
        if (sharePreferenceHelper.getUserRole().equals("Employee")) {
            rootView = getLayoutInflater().inflate(R.layout.activity_dashboard, frameLayout);
            txt_menuTitle.setText("DASHBOARD");
            service = ServiceHelper.getClient(this);
            getDashBoardViewModel();



           /* Button memp_PendingRequisitions = (Button) rootView.findViewById(R.id.emp_CreateRequisition);
            memp_PendingRequisitions.setOnClickListener(this);
            Button memp_ApprovedRequisitions = (Button) rootView.findViewById(R.id.emp_Requisitions);
            memp_ApprovedRequisitions.setOnClickListener(this);
            Button memp_Disbursements = (Button) rootView.findViewById(R.id.emp_Disbursements);
            memp_Disbursements.setOnClickListener(this);


        } else if (sharePreferenceHelper.getUserRole().equals("Store Clerk")) {


        } else if (sharePreferenceHelper.getUserRole().equals("Department Head")) {
            View rootView = getLayoutInflater().inflate(R.layout.activity_dashboard_dept_head, frameLayout);
            txt_menuTitle.setText("DASHBOARD");
        } else if (sharePreferenceHelper.getUserRole().equals("Department Representative")) {
            View rootView = getLayoutInflater().inflate(R.layout.activity_dashboard_dept_rep, frameLayout);
            txt_menuTitle.setText("DASHBOARD");
        } else if (sharePreferenceHelper.getUserRole().equals("Temporary Department Head")) {
            View rootView = getLayoutInflater().inflate(R.layout.activity_dashboard_temp_dept_head, frameLayout);
            txt_menuTitle.setText("DASHBOARD");
        } else if (sharePreferenceHelper.getUserRole().equals("Store Manager")) {
            View rootView = getLayoutInflater().inflate(R.layout.activity_dashboard_store_manager, frameLayout);
            txt_menuTitle.setText("DASHBOARD");
        } else if (sharePreferenceHelper.getUserRole().equals("Store Supervisor")) {
            View rootView = getLayoutInflater().inflate(R.layout.activity_dashboard_store_supervisor, frameLayout);
            txt_menuTitle.setText("DASHBOARD");
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.CLERK_TOTAL_DF_PENDING_APPROVAL) {
            Intent intent = new Intent(this, DisbursementSummaryActivity.class);
            intent.putExtra("Status", "PENDING_APPROVAL");
            startActivity(intent);
        } else if (id == R.id.CLERK_TOTAL_DF_PENDING_DELIVERY) {
            Intent intent = new Intent(this, DisbursementSummaryActivity.class);
            intent.putExtra("Status", "PENDING_DELIVERY");
            startActivity(intent);
        } else if (id == R.id.CLERK_TOTAL_DF_PENDING_ASSIGN) {
            Intent intent = new Intent(this, DisbursementSummaryActivity.class);
            intent.putExtra("Status", "PENDING_DELIVERY");
            startActivity(intent);
        } else if (id == R.id.CLERK_TOTAL_DF_COMPLETED) {
            Intent intent = new Intent(this, DisbursementSummaryActivity.class);
            intent.putExtra("Status", "COMPLETED");
            startActivity(intent);
        }
        */
        }

    }

    public void getDashBoardViewModel() {
        Call<DashboardViewModel> callDashboardViewModel = service.getCountDash(sharePreferenceHelper.getuserId());
        callDashboardViewModel.enqueue(new Callback<DashboardViewModel>() {
            @Override
            public void onResponse(Call<DashboardViewModel> call, Response<DashboardViewModel> response) {
                if (response.isSuccessful()) {
                    DashboardViewModel dashboardViewModel = response.body();
                    Log.d(TAG, "Dashboard Store Clerk- " + dashboardViewModel.emp.username);
                    if (dashboardViewModel != null) {
                        Button EMPLOYEE_TOTAL_RF_SUBMITTED = (Button) findViewById(R.id.EMPLOYEE_TOTAL_RF_SUBMITTED);
                        Button EMPLOYEE_TOTAL_RF_APPROVED = (Button) findViewById(R.id.EMPLOYEE_TOTAL_RF_APPROVED);
                        Button EMPLOYEE_TOTAL_RF_NOT_COMPLETED = (Button) findViewById(R.id.EMPLOYEE_TOTAL_RF_NOT_COMPLETED);
                        Button EMPLOYEE_TOTAL_SR_COMPLETED = (Button) findViewById(R.id.EMPLOYEE_TOTAL_SR_COMPLETED);

                        EMPLOYEE_TOTAL_RF_SUBMITTED.append(" : " + String.valueOf(dashboardViewModel.totalRFSubmitted));
                        EMPLOYEE_TOTAL_RF_APPROVED.append(" : " + String.valueOf(dashboardViewModel.totalRFApproved));
                        EMPLOYEE_TOTAL_RF_NOT_COMPLETED.append(" : " + String.valueOf(dashboardViewModel.totalRFNotCompleted));
                        EMPLOYEE_TOTAL_SR_COMPLETED.append(" : " + String.valueOf(dashboardViewModel.totalRFCompleted));
                    } else {
                        Log.e(TAG, " SRRF List is null");
                        Toast.makeText(DashboardActivity.this, "OOPS! Dashboard API is down Please try again later", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DashboardViewModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            ExitApp();
        }
    }

    private void ExitApp() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Logic University Inventory");
        builder.setMessage("Do You Want To Exit?");
        builder.setIcon(R.drawable.ic_key);
        //final AlertDialog dialog = builder.create();
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finish();

            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}