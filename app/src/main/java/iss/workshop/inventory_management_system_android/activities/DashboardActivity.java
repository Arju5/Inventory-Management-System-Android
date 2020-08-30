package iss.workshop.inventory_management_system_android.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;


import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.dashboard.StoreClerkDashboard;
import iss.workshop.inventory_management_system_android.activities.disbursement.DisbursementSummaryActivity;
import iss.workshop.inventory_management_system_android.activities.requisition.ApplyRequistionActivity;
import iss.workshop.inventory_management_system_android.activities.requisition.RequisitionSummaryActivity;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;

public class DashboardActivity extends BaseActivity implements View.OnClickListener{

    SharePreferenceHelper sharePreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharePreferenceHelper = new SharePreferenceHelper(this);
        if (sharePreferenceHelper.getUserRole().equals("Employee")) {
            View rootView = getLayoutInflater().inflate(R.layout.activity_dashboard, frameLayout);
            txt_menuTitle.setText("DASHBOARD");

           /* Button memp_PendingRequisitions = (Button) rootView.findViewById(R.id.emp_CreateRequisition);
            memp_PendingRequisitions.setOnClickListener(this);
            Button memp_ApprovedRequisitions = (Button) rootView.findViewById(R.id.emp_Requisitions);
            memp_ApprovedRequisitions.setOnClickListener(this);
            Button memp_Disbursements = (Button) rootView.findViewById(R.id.emp_Disbursements);
            memp_Disbursements.setOnClickListener(this);
*/

        } else if (sharePreferenceHelper.getUserRole().equals("Store Clerk")) {
            Intent intent = new Intent(this, StoreClerkDashboard.class);
            startActivity(intent);

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

        /*if(id == R.id.emp_CreateRequisition) {
            Intent intent = new Intent(this, ApplyRequistionActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.emp_Requisitions)
        {
            Intent intent = new Intent(this, RequisitionSummaryActivity.class);
            intent.putExtra("btnId", id);
            startActivity(intent);
        }*/

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