package iss.workshop.inventory_management_system_android.activities.disbursement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;

public class DisbursementSummaryStatusSelectionActivity extends BaseActivity {

    private static final String TAG = "DisburSumStatusSelecion";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_disbursement_summary_status_selection, frameLayout);
        txt_menuTitle.setText("Disbursement Summary");
        Button createdDF = (Button)rootView.findViewById(R.id.df_CREATED);
        Button pendingDeliveryDF = (Button)rootView.findViewById(R.id.df_PENDING_DELIVERY);
        Button pendingAssignedDF = (Button)rootView.findViewById(R.id.df_PENDING_ASSIGNED);
        Button completedDF = (Button)rootView.findViewById(R.id.df_COMPLETED);
        final Intent intent = new Intent(DisbursementSummaryStatusSelectionActivity.this, DisbursementSummaryActivity.class);

        createdDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onResponse: Disbursement Summary Status Selection = OPEN");
                intent.putExtra("Status", "OPEN");
                startActivity(intent);
            }
        });

        pendingDeliveryDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onResponse: Disbursement Summary Status Selection = PENDING DELIVERY");
                Intent intent = new Intent(DisbursementSummaryStatusSelectionActivity.this, DisbursementSummaryActivity.class);
                intent.putExtra("Status", "PENDING_DELIVERY");
                startActivity(intent);
            }
        });

        pendingAssignedDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onResponse: Disbursement Summary Status Selection = PENDING_ASSIGNMENT");
                Intent intent = new Intent(DisbursementSummaryStatusSelectionActivity.this, DisbursementSummaryActivity.class);
                intent.putExtra("Status", "PENDING_ASSIGNMENT");
                startActivity(intent);
            }
        });

        completedDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onResponse: Disbursement Summary Status Selection = COMPLETED");
                Intent intent = new Intent(DisbursementSummaryStatusSelectionActivity.this, DisbursementSummaryActivity.class);
                intent.putExtra("Status", "COMPLETED");
                startActivity(intent);
            }
        });

    }
}