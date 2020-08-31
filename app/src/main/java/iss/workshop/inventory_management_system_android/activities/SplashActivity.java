package iss.workshop.inventory_management_system_android.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.dashboard.DepHeadDashboardActivity;
import iss.workshop.inventory_management_system_android.activities.dashboard.DeptRepDashboardActivity;
import iss.workshop.inventory_management_system_android.activities.dashboard.StoreClerkDashboardActivity;
import iss.workshop.inventory_management_system_android.activities.dashboard.StoreManagerDashboardActivity;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;

public class SplashActivity extends AppCompatActivity {
    SharePreferenceHelper sharePreferenceHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharePreferenceHelper = new SharePreferenceHelper(this);

        setContentView(R.layout.activity_splash);

        new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                if(sharePreferenceHelper.isLogin()){

                   if (sharePreferenceHelper.getUserRole().equals("Store Clerk")) {
                       Intent intent = new Intent(SplashActivity.this, StoreClerkDashboardActivity.class);
                       //To be the last activity for back button(need to delete the login activity)
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK );//(need to delete the login activity)
                       startActivity(intent);
                   } else if (sharePreferenceHelper.getUserRole().equals("Employee")) {
                       Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                       //To be the last activity for back button(need to delete the login activity)
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK );//(need to delete the login activity)
                       startActivity(intent);
                   } else if (sharePreferenceHelper.getUserRole().equals("Store Manager")) {
                       Intent intent = new Intent(SplashActivity.this, StoreManagerDashboardActivity.class);
                       //To be the last activity for back button(need to delete the login activity)
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK );//(need to delete the login activity)
                       startActivity(intent);
                   } else if (sharePreferenceHelper.getUserRole().equals("Department Representative")) {
                       Intent intent = new Intent(SplashActivity.this, DeptRepDashboardActivity.class);
                       //To be the last activity for back button(need to delete the login activity)
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK );//(need to delete the login activity)
                       startActivity(intent);
                   } else if (sharePreferenceHelper.getUserRole().equals("Department Head")) {
                       Intent intent = new Intent(SplashActivity.this, DepHeadDashboardActivity.class);
                       //To be the last activity for back button(need to delete the login activity)
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK );//(need to delete the login activity)
                       startActivity(intent);
                   }

                }
                else{
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        }.start();
    }
}