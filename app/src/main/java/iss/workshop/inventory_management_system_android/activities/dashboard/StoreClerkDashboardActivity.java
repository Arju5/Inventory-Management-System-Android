package iss.workshop.inventory_management_system_android.activities.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.activities.disbursement.DisbursementSummaryActivity;

public class StoreClerkDashboardActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = getLayoutInflater().inflate(R.layout.activity_store_clerk_dashboard, frameLayout);
        txt_menuTitle.setText("DASHBOARD");



    }


}