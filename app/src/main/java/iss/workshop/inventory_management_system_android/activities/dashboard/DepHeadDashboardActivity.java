package iss.workshop.inventory_management_system_android.activities.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;

public class DepHeadDashboardActivity extends BaseActivity {

    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().inflate(R.layout.activity_dashboard_dept_head, frameLayout);
        txt_menuTitle.setText("DASHBOARD");
    }
}