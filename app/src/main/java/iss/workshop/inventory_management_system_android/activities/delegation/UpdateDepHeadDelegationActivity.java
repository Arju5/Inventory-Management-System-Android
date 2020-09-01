package iss.workshop.inventory_management_system_android.activities.delegation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.adapters.DelegationSummaryAdapter;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.model.DelegationForm;
import iss.workshop.inventory_management_system_android.viewmodel.DelegationViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDepHeadDelegationActivity extends BaseActivity {

    private static final String TAG = "Update Delegation";
    private ServiceHelper.ApiService service;
    private int count = 1;
    private DelegationForm delegationForm;
    View rootView;
    SharePreferenceHelper sharePreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().inflate(R.layout.activity_update_dep_head_delegation, frameLayout);
        txt_menuTitle.setText("UPDATE DELEGATION");

        Intent intent = getIntent();
        sharePreferenceHelper = new SharePreferenceHelper(this);
        Log.d(TAG, "Delegation Summary Activity - Details");
        service = ServiceHelper.getClient(this);
        Call<DelegationForm> serviceDVMAPICall = null;
        serviceDVMAPICall = service.ViewDLFormById(intent.getIntExtra("delegationId", 999));
        serviceDVMAPICall.enqueue(new Callback<DelegationForm>() {
            @Override
            public void onResponse(Call<DelegationForm> call, Response<DelegationForm> response) {
                Log.d(TAG, "Response Code - " + response.code());
                Log.d(TAG, "Response Code - " + response.message());
                Log.d(TAG, "Response Code - " + response.toString());

                if (response.isSuccessful()) {
                    delegationForm = response.body();
                    Log.d(TAG, "onResponse : Success - " + delegationForm.departmentHead.username);
                    putDataToLayout(delegationForm);

                }
            }
            @Override
            public void onFailure(Call<DelegationForm> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
            }
        });

    }

    private void putDataToLayout(DelegationForm delegationForm) {

        TextView delegatee = rootView.findViewById(R.id.dephead_delegatee);
        delegatee.setText(delegationForm.delegatee.firstname + " " + delegationForm.delegatee.lastname);
        TextView assignedDate = rootView.findViewById(R.id.dephead_delegateAssignedDate);
        assignedDate.setText(DelegationSummaryAdapter.removeTfromTimeStamp(delegationForm.dlAssignedDate));
        TextView startDate = rootView.findViewById(R.id.dephead_delegateStartDate);
        startDate.setText(DelegationSummaryAdapter.removeTfromTimeStamp(delegationForm.startDate));
        TextView endDate = rootView.findViewById(R.id.dephead_delegateEndDate);
        endDate.setText(DelegationSummaryAdapter.removeTfromTimeStamp(delegationForm.endDate));
        TextView delegateComments = rootView.findViewById(R.id.dephead_delegateComments);
        delegateComments.setText(delegationForm.delegateComment);
        TextView delegationType = rootView.findViewById(R.id.dephead_delegateType);
        delegationType.setTextColor(Color.BLUE);
        delegationType.setText(delegationForm.delegatedType.employeeTypeName);
        TextView delegationid = rootView.findViewById(R.id.dephead_delegationid);
        delegationid.setText(String.valueOf(delegationForm.delegatee.id));
    }


}