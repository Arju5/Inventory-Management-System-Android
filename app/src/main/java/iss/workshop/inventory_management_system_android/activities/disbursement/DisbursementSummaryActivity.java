package iss.workshop.inventory_management_system_android.activities.disbursement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.activities.DashboardActivity;
import iss.workshop.inventory_management_system_android.adapters.DisbursementSummaryAdapter;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.model.DisbursementForm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisbursementSummaryActivity extends BaseActivity implements AdapterView.OnItemClickListener{

    private static final String TAG = "DisburseSumActivity";

    private ServiceHelper.ApiService service;
    private DisbursementSummaryAdapter dfSummaryAdapter;
    public static String status;
    View rootView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().inflate(R.layout.activity_disbursement_summary, frameLayout);
        txt_menuTitle.setText("DISBURSEMENT SUMMARY");
        service = ServiceHelper.getClient(this);
        dfSummaryAdapter = new DisbursementSummaryAdapter(DisbursementSummaryActivity.this, R.layout.disbursement_summary_row);
        Intent intent = getIntent();
        status = intent.getStringExtra("Status");

        getDisbursementsList(status);
    }

    private void getDisbursementsList(String status) {

        dfSummaryAdapter.clear();
        final ListView disbursementListView = (ListView) rootView.findViewById(R.id.disbursementsummary);
        Call<ArrayList<DisbursementForm>> serviceDFAPICall = null;
        if(status.equals("OPEN"))
            serviceDFAPICall = service.getCreatedDisbursementList();
        else if (status.equals("PENDING_DELIVERY"))
            serviceDFAPICall = service.getPendingDeliveryDisbursementList();
        else if (status.equals("PENDING_ASSIGNMENT"))
            serviceDFAPICall = service.getPendingAssignmentDisbursementList();
        else if (status.equals("COMPLETED"))
            serviceDFAPICall = service.getCompleteDisbursementList();
        serviceDFAPICall.enqueue(new Callback<ArrayList<DisbursementForm>>() {
            @Override
            public void onResponse(Call<ArrayList<DisbursementForm>> call, Response<ArrayList<DisbursementForm>> response) {
                if(response.isSuccessful()) {

                    ArrayList<DisbursementForm> disbursementList = response.body();
                    dfSummaryAdapter.setDisbursementFormList(disbursementList);
                    disbursementListView.setAdapter(dfSummaryAdapter);
                    disbursementListView.setOnItemClickListener(DisbursementSummaryActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<DisbursementForm>> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> av,
                            View v, int pos, long id) {

        TextView textView = v.findViewById(R.id.dfsummary_DisbursementCode);
        String expr = textView.getText().toString();
        Intent intent = new Intent(DisbursementSummaryActivity.this, DisbursementFormDetailsActivity.class);
        intent.putExtra("DFCode", expr);
        intent.putExtra("CurrentStatus", status);
        startActivity(intent);
    }

}