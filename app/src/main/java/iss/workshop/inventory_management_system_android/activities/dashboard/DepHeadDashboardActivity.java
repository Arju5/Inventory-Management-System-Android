package iss.workshop.inventory_management_system_android.activities.dashboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.viewmodel.DashboardViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepHeadDashboardActivity extends BaseActivity {

    View rootView;
    private static final String TAG = "StoreClerkDashboard";
    SharePreferenceHelper sharePreferenceHelper;
    private ServiceHelper.ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().inflate(R.layout.activity_dashboard_dept_head, frameLayout);
        txt_menuTitle.setText("DASHBOARD");
        sharePreferenceHelper = new SharePreferenceHelper(this);
        service = ServiceHelper.getClient(this);
        getDashBoardViewModel();

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
                        Button DEPTHEAD_TOTAL_RF_PENDING = (Button) findViewById(R.id.DEPTHEAD_TOTAL_RF_PENDING);
                        Button DEPTHEAD_TOTAL_RF_APPROVED = (Button) findViewById(R.id.DEPTHEAD_TOTAL_RF_APPROVED);
                        Button DEPTHEAD_TOTAL_RF_NOT_COMPLETED = (Button) findViewById(R.id.DEPTHEAD_TOTAL_RF_NOT_COMPLETED);
                        Button DEPTHEAD_TOTAL_SR_COMPLETED = (Button)findViewById(R.id.DEPTHEAD_TOTAL_SR_COMPLETED);

                        DEPTHEAD_TOTAL_RF_PENDING.append(" : " + String.valueOf(dashboardViewModel.totalRFSubmitted));
                        DEPTHEAD_TOTAL_RF_APPROVED.append(" : " + String.valueOf(dashboardViewModel.totalRFApproved));
                        DEPTHEAD_TOTAL_RF_NOT_COMPLETED.append(" : " + String.valueOf(dashboardViewModel.totalRFNotCompleted));
                        DEPTHEAD_TOTAL_SR_COMPLETED.append(" : " + String.valueOf(dashboardViewModel.totalRFCompleted));

                    } else {
                        Log.e(TAG, " SRRF List is null");
                        Toast.makeText(DepHeadDashboardActivity.this, "OOPS! Dashboard API is down Please try again later", Toast.LENGTH_SHORT).show();
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