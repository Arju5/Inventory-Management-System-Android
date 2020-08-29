package iss.workshop.inventory_management_system_android.activities.requisition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.activities.DashboardActivity;
import iss.workshop.inventory_management_system_android.adapters.ReqSummaryAdapter;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.viewmodel.RequisitionSummaryViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequisitionSummaryActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private static final String TAG = "ReqSummaryActivity";
    private ServiceHelper.ApiService service;
    SharePreferenceHelper sharePreferenceHelper;
    private ReqSummaryAdapter rfsummaryadapter;
    ListView rfsummaryListView;
    int btnId;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_requisition_summary, frameLayout);
        txt_menuTitle.setText("REQUISITION SUMMARY");

        //Get UserName by sharePreference
        sharePreferenceHelper = new SharePreferenceHelper(this);
        username = sharePreferenceHelper.getUserName();

        service = ServiceHelper.getClient(this);
        rfsummaryadapter = new ReqSummaryAdapter(RequisitionSummaryActivity.this,R.layout.reqsummary_row);
        rfsummaryListView = findViewById(R.id.reqSummarylistView);

        Intent intent = getIntent();
        btnId = intent.getIntExtra("btnId",0);
        //Log.d(TAG, "btnId ::"+ btnId);
        getReqSummary();
    }

    private void getReqSummary() {
        rfsummaryadapter.clear();

        //final ListView rfsummaryListView = findViewById(R.id.reqSummarylistView);
        Call<RequisitionSummaryViewModel> callrfsummary = service.getReqSummary(username);
        callrfsummary.enqueue(new Callback<RequisitionSummaryViewModel>() {
            @Override
            public void onResponse(Call<RequisitionSummaryViewModel> call, Response<RequisitionSummaryViewModel> response) {
                if(response.isSuccessful()) {

                    RequisitionSummaryViewModel rfsummary = response.body();

                    //check if the btn clicked is pending list btn or processed list btn
                    if(btnId == R.id.emp_Requisitions){
                        rfsummaryadapter.setrfSummaryList(rfsummary.rfListPending);

                    }
                    else if(btnId == R.id.emp_Requisitions){
                        rfsummaryadapter.setrfSummaryList(rfsummary.rfListProcessed);
                    }
                    Log.d(TAG, "employee:: "+rfsummary.employee.firstname);
                    if(rfsummaryListView!=null)
                    {
                        rfsummaryListView.setAdapter(rfsummaryadapter);
                        rfsummaryListView.setOnItemClickListener(RequisitionSummaryActivity.this);
                    }
                    else
                    { Log.d(TAG, "list view is null"); }

                }
            }

            @Override
            public void onFailure(Call<RequisitionSummaryViewModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> av,
                            View v, int pos, long id) {

        TextView req_id = v.findViewById(R.id.reqId);

        Log.d(TAG, "from summary to form req-code sending :" + req_id.getText().toString());

        Intent intent = new Intent(this, DashboardActivity.class);

        intent.putExtra("reqId", req_id.getText().toString());
        startActivity(intent);

    }

}