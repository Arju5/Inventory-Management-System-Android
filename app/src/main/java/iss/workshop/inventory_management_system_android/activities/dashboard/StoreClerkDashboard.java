package iss.workshop.inventory_management_system_android.activities.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.activities.disbursement.DisbursementSummaryActivity;

public class StoreClerkDashboard extends BaseActivity implements View.OnClickListener{

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
}