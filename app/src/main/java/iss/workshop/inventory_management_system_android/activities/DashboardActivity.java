package iss.workshop.inventory_management_system_android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;

public class DashboardActivity extends BaseActivity {

    SharePreferenceHelper sharePreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharePreferenceHelper = new SharePreferenceHelper(this);
        if (sharePreferenceHelper.getUserRole().equals("Employee")) {
            View rootView = getLayoutInflater().inflate(R.layout.activity_dashboard, frameLayout);
            txt_menuTitle.setText("DASHBOARD");
        } else if (sharePreferenceHelper.getUserRole().equals("Store Clerk")) {
            View rootView = getLayoutInflater().inflate(R.layout.activity_dashboard_store_clerk, frameLayout);
            txt_menuTitle.setText("DASHBOARD");
        } else if (sharePreferenceHelper.getUserRole().equals("Department Head")) {
            View rootView = getLayoutInflater().inflate(R.layout.activity_dashboard_dept_head, frameLayout);
            txt_menuTitle.setText("DASHBOARD");
        }
    }
}