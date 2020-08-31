package iss.workshop.inventory_management_system_android.activities.delegation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.activities.disbursement.DibursementHandOverActivity;
import iss.workshop.inventory_management_system_android.activities.disbursement.DisbursementAssignment;
import iss.workshop.inventory_management_system_android.activities.disbursement.DisbursementFormDetailsActivity;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.viewmodel.DelegationViewModel;
import iss.workshop.inventory_management_system_android.viewmodel.DisbursementViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DelegationSummaryActivity extends BaseActivity {

    private static final String TAG = "DisburseDetailActivity";
    private ServiceHelper.ApiService service;
    private int count = 1;
    private DelegationViewModel delegationViewModel;
    View rootView;
    SharePreferenceHelper sharePreferenceHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().inflate(R.layout.activity_delegation_summary, frameLayout);
        txt_menuTitle.setText("DASHBOARD");
        sharePreferenceHelper = new SharePreferenceHelper(this);
        Log.d(TAG, "Delegation Summary Activity - Details");
        service = ServiceHelper.getClient(this);
        Call<DelegationViewModel> serviceDVMAPICall = null;
        serviceDVMAPICall = service.getDelegateSummary(sharePreferenceHelper.getuserId());
        serviceDVMAPICall.enqueue(new Callback<DelegationViewModel>() {
            @Override
            public void onResponse(Call<DelegationViewModel> call, Response<DelegationViewModel> response) {
                if (response.isSuccessful()) {
                    delegationViewModel = response.body();
                    Log.d(TAG, "onResponse : Success - " + delegationViewModel.departmentHead.username);
                    //putDataToLayout(delegationViewModel);
                }
            }
            @Override
            public void onFailure(Call<DelegationViewModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
            }
        });

    }
}