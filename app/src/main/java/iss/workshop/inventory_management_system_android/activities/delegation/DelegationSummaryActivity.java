package iss.workshop.inventory_management_system_android.activities.delegation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.activities.dashboard.DepHeadDashboardActivity;
import iss.workshop.inventory_management_system_android.activities.disbursement.DibursementHandOverActivity;
import iss.workshop.inventory_management_system_android.activities.disbursement.DisbursementAssignment;
import iss.workshop.inventory_management_system_android.activities.disbursement.DisbursementFormDetailsActivity;
import iss.workshop.inventory_management_system_android.activities.disbursement.DisbursementSummaryActivity;
import iss.workshop.inventory_management_system_android.adapters.DelegationSummaryAdapter;
import iss.workshop.inventory_management_system_android.adapters.DisbursementSummaryAdapter;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.viewmodel.DelegationViewModel;
import iss.workshop.inventory_management_system_android.viewmodel.DisbursementViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DelegationSummaryActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "DisburseDetailActivity";
    private ServiceHelper.ApiService service;
    private int count = 1;
    private DelegationViewModel delegationViewModel;
    View rootView;
    private DelegationSummaryAdapter delegationSummaryAdapter;
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
                    delegationSummaryAdapter = new DelegationSummaryAdapter(DelegationSummaryActivity.this, R.layout.delegate_summary_row);
                    ListView delegationListView = (ListView) rootView.findViewById(R.id.dephead_delegate_summary);
                    delegationListView.setAdapter(delegationSummaryAdapter);
                    delegationSummaryAdapter.setDelegationList(delegationViewModel);
                    delegationListView.setOnItemClickListener((AdapterView.OnItemClickListener) DelegationSummaryActivity.this);
                }
            }
            @Override
            public void onFailure(Call<DelegationViewModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, "Manage Delegation soon Arriving. Please keep subscribed", Toast.LENGTH_SHORT).show();
        /*TextView textView = view.findViewById(R.id.dephead_delegationid);
        String expr = textView.getText().toString();
        Intent intent = new Intent(DelegationSummaryActivity.this, UpdateDepHeadDelegationActivity.class);
        intent.putExtra("delegationId", textView.getText());
        startActivity(intent);*/
    }
}