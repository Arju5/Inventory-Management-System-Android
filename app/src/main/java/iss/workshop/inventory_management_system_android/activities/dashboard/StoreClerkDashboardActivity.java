package iss.workshop.inventory_management_system_android.activities.dashboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.activities.disbursement.DisbursementSummaryActivity;

public class StoreClerkDashboardActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = getLayoutInflater().inflate(R.layout.activity_store_clerk_dashboard, frameLayout);
        txt_menuTitle.setText("DASHBOARD");

        Button mCLERK_TOTAL_DF_PENDING_APPROVAL = (Button)rootView.findViewById(R.id.CLERK_TOTAL_DF_PENDING_APPROVAL);
        Button mCLERK_TOTAL_DF_PENDING_DELIVERY = (Button)rootView.findViewById(R.id.CLERK_TOTAL_DF_PENDING_DELIVERY);
        Button mCLERK_TOTAL_DF_PENDING_ASSIGN = (Button)rootView.findViewById(R.id.CLERK_TOTAL_DF_PENDING_ASSIGN);
        Button mCLERK_TOTAL_DF_COMPLETED = (Button)rootView.findViewById(R.id.CLERK_TOTAL_DF_COMPLETED);

        mCLERK_TOTAL_DF_PENDING_APPROVAL.setOnClickListener(this);
        mCLERK_TOTAL_DF_PENDING_DELIVERY.setOnClickListener(this);
        mCLERK_TOTAL_DF_PENDING_ASSIGN.setOnClickListener(this);
        mCLERK_TOTAL_DF_COMPLETED.setOnClickListener(this);

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