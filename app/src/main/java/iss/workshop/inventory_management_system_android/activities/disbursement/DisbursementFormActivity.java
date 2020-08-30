/*
package iss.workshop.inventorymanagementsystem.activities.disbursement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.activities.DashboardActivity;
import iss.workshop.inventory_management_system_android.adapters.DisbursementFormAdapter;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.model.DisbursementForm;
import iss.workshop.inventory_management_system_android.model.Employee;
import iss.workshop.inventory_management_system_android.model.StationeryRetrievalRequisitionForm;
import iss.workshop.inventory_management_system_android.viewmodel.DisbursementViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisbursementFormActivity extends BaseActivity {

    private static final String TAG = "DisburseFormActivity";
    private ListView listViewSRRF;
    public List<StationeryRetrievalRequisitionForm> SRRFList;
    private TextView comments;
    private Button btnSubmit;
    private ServiceHelper.ApiService service;
    private DisbursementFormAdapter dfFormAdapter;
    private DisbursementViewModel dfViewModel;
    SharePreferenceHelper sharePreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_disbursement_form, frameLayout);
        txt_menuTitle.setText("CREATE DISBURSEMENT");
        listViewSRRF = (ListView) findViewById(R.id.createDisbursementForm);
        comments = (TextView) findViewById(R.id.createDisbursementForm_Comments);
        btnSubmit = (Button) findViewById(R.id.createDisbursementForm_Submit);
        sharePreferenceHelper = new SharePreferenceHelper(this);
        service = ServiceHelper.getClient(this);
        getSRRFList();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<StationeryRetrievalRequisitionForm> reqSRRFList = new ArrayList<>();
                for (StationeryRetrievalRequisitionForm stationeryRetrievalRequisitionForm : DisbursementFormAdapter.SRRFList) {
                    if (stationeryRetrievalRequisitionForm.getIsSelected())
                        reqSRRFList.add(stationeryRetrievalRequisitionForm);
                }
                dfViewModel = new DisbursementViewModel();
                dfViewModel.setSrrfAssignedList(reqSRRFList);
                dfViewModel.setComment(comments.getText().toString());
                Employee emp = new Employee();
                emp.setId(sharePreferenceHelper.getuserId());
                dfViewModel.setEmployee(emp);  // send the emp id
                for (StationeryRetrievalRequisitionForm stationeryRetrievalRequisitionForm : dfViewModel.srrfAssignedList)
                    Log.d(TAG, "Req SRRF List - " + stationeryRetrievalRequisitionForm.requisitionForm.rfCode);
                SaveDF(dfViewModel);
            }
        });
    }

    private void getSRRFList() {
        Call<ArrayList<StationeryRetrievalRequisitionForm>> callSRRFList = service.getSRRFAssignedFormsToCreateDisbursement();
        callSRRFList.enqueue(new Callback<ArrayList<StationeryRetrievalRequisitionForm>>() {
            @Override
            public void onResponse( Call<ArrayList<StationeryRetrievalRequisitionForm>> call, Response<ArrayList<StationeryRetrievalRequisitionForm>> response) {
                if(response.isSuccessful()) {

                    SRRFList = response.body();
                    Log.d(TAG, "SRRF List Size - " + SRRFList.size());
                    dfFormAdapter = new DisbursementFormAdapter(DisbursementFormActivity.this, SRRFList);
                    dfFormAdapter.setSRRFList(SRRFList);
                    if (listViewSRRF != null && SRRFList.size() > 0) {
                        listViewSRRF.setAdapter(dfFormAdapter);
                    }else {
                        Log.e(TAG, " SRRF List is null");
                        Toast.makeText(DisbursementFormActivity.this, "There are no Assigned Stationery Requisitions", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<StationeryRetrievalRequisitionForm>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    private void SaveDF(DisbursementViewModel dfViewModel) {
        Call<DisbursementViewModel> callsaveRF = service.SaveDF(dfViewModel);
        callsaveRF.enqueue(new Callback<DisbursementViewModel>() {
            @Override
            public void onResponse(Call<DisbursementViewModel> call, Response<DisbursementViewModel> response) {
                Log.d(TAG, "Response Code - " + response.code());
                Log.d(TAG, "Response Code - " + response.message());
                Log.d(TAG, "Response Code - " + response.toString());
                if (response.isSuccessful()) {
                    DisbursementViewModel dfViewModel = response.body();
                    if (dfViewModel != null) {
                        Toast.makeText(getApplicationContext(), "Create Disbursement is successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DisbursementFormActivity.this, DisbursementSummaryStatusSelectionActivity.class);
                        intent.putExtra("empType", sharePreferenceHelper.getUserRole());
                        startActivity(intent);
                    }
                } else {
                    Log.e(TAG, "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<DisbursementViewModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

}
*/
