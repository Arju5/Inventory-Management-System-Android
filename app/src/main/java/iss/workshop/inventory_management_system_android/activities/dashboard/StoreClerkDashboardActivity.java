package iss.workshop.inventory_management_system_android.activities.dashboard;

import androidx.appcompat.app.AlertDialog;
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

import java.util.ArrayList;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.activities.disbursement.DisbursementFormActivity;
import iss.workshop.inventory_management_system_android.activities.disbursement.DisbursementSummaryActivity;
import iss.workshop.inventory_management_system_android.adapters.DisbursementFormAdapter;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.model.StationeryRetrievalRequisitionForm;
import iss.workshop.inventory_management_system_android.viewmodel.DashboardViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoreClerkDashboardActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG = "StoreClerkDashboard";
    SharePreferenceHelper sharePreferenceHelper;
    private ServiceHelper.ApiService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = getLayoutInflater().inflate(R.layout.activity_store_clerk_dashboard, frameLayout);
        txt_menuTitle.setText("DASHBOARD");
        sharePreferenceHelper = new SharePreferenceHelper(this);
        service = ServiceHelper.getClient(this);
        getDashBoardViewModel();

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
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


    }

    public void getDashBoardViewModel() {
        Call<DashboardViewModel> callDashboardViewModel = service.getCountDash(sharePreferenceHelper.getuserId());
        callDashboardViewModel.enqueue(new Callback<DashboardViewModel>() {
            @Override
            public void onResponse( Call<DashboardViewModel> call, Response<DashboardViewModel> response) {
                if(response.isSuccessful()) {
                    DashboardViewModel dashboardViewModel = response.body();
                    Log.d(TAG, "Dashboard Store Clerk- " + dashboardViewModel.emp.username);
                    if (dashboardViewModel != null) {
                        Button mCLERK_TOTAL_RF_APPROVED = (Button) findViewById(R.id.CLERK_TOTAL_RF_APPROVED);
                        Button mCLERK_TOTAL_RF_NOT_COMPLETED = (Button) findViewById(R.id.CLERK_TOTAL_RF_NOT_COMPLETED);
                        Button mCCLERK_TOTAL_SR_CREATED = (Button) findViewById(R.id.CLERK_TOTAL_SR_CREATED);
                        Button mCLERK_TOTAL_SR_PENDING = (Button)findViewById(R.id.CLERK_TOTAL_SR_PENDING);
                        Button mCLERK_TOTAL_SR_COMPLETED = (Button)findViewById(R.id.CLERK_TOTAL_SR_COMPLETED);
                        Button mCLERK_TOTAL_DF_PENDING_APPROVAL = (Button)findViewById(R.id.CLERK_TOTAL_DF_PENDING_APPROVAL);
                        Button mCLERK_TOTAL_DF_PENDING_DELIVERY = (Button)findViewById(R.id.CLERK_TOTAL_DF_PENDING_DELIVERY);
                        Button mCLERK_TOTAL_DF_PENDING_ASSIGN = (Button)findViewById(R.id.CLERK_TOTAL_DF_PENDING_ASSIGN);
                        Button mCLERK_TOTAL_DF_COMPLETED = (Button)findViewById(R.id.CLERK_TOTAL_DF_COMPLETED);

                        mCLERK_TOTAL_RF_APPROVED.append(" ( " + String.valueOf(dashboardViewModel.totalRFApproved) + " )");
                        mCLERK_TOTAL_RF_NOT_COMPLETED.append(" ( " + String.valueOf(dashboardViewModel.totalRFNotCompleted) + " )");
                        mCCLERK_TOTAL_SR_CREATED.append(" ( " + String.valueOf(dashboardViewModel.totalSROpen) + " )");
                        mCLERK_TOTAL_SR_PENDING.append(" ( " + String.valueOf(dashboardViewModel.totalSRPendingAssignment) + " )");
                        mCLERK_TOTAL_SR_COMPLETED.append(" ( " + String.valueOf(dashboardViewModel.totalSRAssigned) + " )");
                        mCLERK_TOTAL_DF_PENDING_APPROVAL.append(" ( " + String.valueOf(dashboardViewModel.totalDFCreated) + " )");
                        mCLERK_TOTAL_DF_PENDING_DELIVERY.append(" ( " + String.valueOf(dashboardViewModel.totalDFPendingDelivery) + " )");
                        mCLERK_TOTAL_DF_PENDING_ASSIGN.append(" ( " + String.valueOf(dashboardViewModel.totalDFPendingAssignment) + " )");
                        mCLERK_TOTAL_DF_COMPLETED.append(" ( " + String.valueOf(dashboardViewModel.totalDFCompleted) + " )");

                        mCLERK_TOTAL_DF_PENDING_APPROVAL.setOnClickListener(StoreClerkDashboardActivity.this);
                        mCLERK_TOTAL_DF_PENDING_DELIVERY.setOnClickListener(StoreClerkDashboardActivity.this);
                        mCLERK_TOTAL_DF_PENDING_ASSIGN.setOnClickListener(StoreClerkDashboardActivity.this);
                        mCLERK_TOTAL_DF_COMPLETED.setOnClickListener(StoreClerkDashboardActivity.this);
                    } else {
                        Log.e(TAG, " SRRF List is null");
                        Toast.makeText(StoreClerkDashboardActivity.this, "OOPS! Dashboard API is down Please try again later", Toast.LENGTH_SHORT).show();
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