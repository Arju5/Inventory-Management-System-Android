package iss.workshop.inventory_management_system_android.activities.dashboard;

import android.os.Bundle;
import android.view.View;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;

public class DeptRepDashboardActivity extends BaseActivity {

    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().inflate(R.layout.activity_dashboard_dept_rep, frameLayout);
        txt_menuTitle.setText("DASHBOARD");
    }
}
