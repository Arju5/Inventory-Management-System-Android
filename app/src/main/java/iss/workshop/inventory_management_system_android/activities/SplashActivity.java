package iss.workshop.inventory_management_system_android.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import iss.workshop.inventory_management_system_android.R;
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

                    /*switch (sharePreferenceHelper.getUserRole()){
                        case "Store Clerk":
                            Intent intent = new Intent(SplashActivity.this, SF_StationeryRetrievalSummaryActivity.class);
                            startActivity(intent);
                            break;
                        case "Employee": case "Department Representative": case "Department Head":
                            Intent empintent = new Intent(SplashActivity.this, RequisitionLandingActivity.class);
                            empintent.putExtra("empType",sharePreferenceHelper.getUserRole());
                            startActivity(empintent);
                            break;
                    }*/
                    Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                    //To be the last activity for back button(need to delete the login activity)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK );//(need to delete the login activity)
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        }.start();
    }
}