package iss.workshop.inventorymanagementsystem.activities.stationery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import iss.workshop.inventorymanagementsystem.R;
import iss.workshop.inventorymanagementsystem.adapter.SF_CompletedSRFReqAdapter;
import iss.workshop.inventorymanagementsystem.adapter.SF_CompletedSRFSummaryAdapter;
import iss.workshop.inventorymanagementsystem.adapter.SF_CompletedSRFWarehouseAdapter;
import iss.workshop.inventorymanagementsystem.helper.ServiceHelper;
import iss.workshop.inventorymanagementsystem.helper.SharePreferenceHelper;
import iss.workshop.inventorymanagementsystem.viewmodel.StationeryRetrievalViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SF_SRFCompletedSRFActivity extends AppCompatActivity {

    private static final String TAG = "SF_SRFCompletedSRFActiv";

    String username;
    TextView clerkName;
    TextView warehouseName;
    TextView createDate;
    TextView retrievalId;
    TextView status;

    String cName;
    String wName;
    String cDate;
    String rId;

    RecyclerView rcv_req;
    RecyclerView rcv_summary;
    RecyclerView rcv_warehouse;

    private ServiceHelper.ApiService service;
    SharePreferenceHelper sharePreferenceHelper;
    //String username;

    private SF_CompletedSRFReqAdapter completedSRFReqAdapter;
    private SF_CompletedSRFSummaryAdapter summaryAdapter;
    private SF_CompletedSRFWarehouseAdapter warehouseAdapter;

    Integer selected_sfId;

    StationeryRetrievalViewModel srform;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sf_completsrf);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        service = ServiceHelper.getClient(this);

        //Get UserName by sharePreference
        sharePreferenceHelper = new SharePreferenceHelper(this);
        username = sharePreferenceHelper.getUserName();

        Intent intent = getIntent();
        selected_sfId = Integer.parseInt(intent.getStringExtra("SFId"));

        clerkName = findViewById(R.id.sclerkname);
        //warehouseName = findViewById(R.id.whousename);
        createDate = findViewById(R.id.crdate);
        retrievalId = findViewById(R.id.retrieval_name);
        status = findViewById(R.id.status_name);

        srform = new StationeryRetrievalViewModel();

        completedSRFReqAdapter = new SF_CompletedSRFReqAdapter();
        summaryAdapter = new SF_CompletedSRFSummaryAdapter();
        warehouseAdapter = new SF_CompletedSRFWarehouseAdapter();

        rcv_req = findViewById(R.id.requ_recycler);
        rcv_req.setHasFixedSize(true);
        rcv_req.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rcv_req.setAdapter(completedSRFReqAdapter);

        rcv_warehouse = findViewById(R.id.warehouse_recycler);
        rcv_warehouse.setHasFixedSize(true);
        rcv_warehouse.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rcv_warehouse.setAdapter(warehouseAdapter);

        rcv_summary = findViewById(R.id.summary_recycler);
        rcv_summary.setHasFixedSize(true);
        rcv_summary.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rcv_summary.setAdapter(summaryAdapter);

        getCompletedSRFormBySelectedId();
    }


    public void getCompletedSRFormBySelectedId() {

        Call<StationeryRetrievalViewModel> callsrform = service.getCompletedSRFormBySelectedId(selected_sfId);
        callsrform.enqueue(new Callback<StationeryRetrievalViewModel>() {
            @Override
            public void onResponse(Call<StationeryRetrievalViewModel> call, Response<StationeryRetrievalViewModel> response) {

                if(response.isSuccessful()) {

                    srform = response.body();

                    cName = srform.stationeryRetrieval.storeClerk.getFirstname() + "" + srform.stationeryRetrieval.storeClerk.getLastname();
                    clerkName.setText(cName);

                    createDate.setText(srform.stationeryRetrieval.getSrDate());

                    retrievalId.setText(srform.stationeryRetrieval.getSrCode());

                    status.setText("Completed");

                    for(int i=0;i<srform.getSrrfList().size();i++){
                        completedSRFReqAdapter.add(srform.getSrrfList().get(i));
                    }

                    for(int i=0;i<srform.getRetrievalProducts().size();i++){
                        warehouseAdapter.add(srform.getRetrievalProducts().get(i));
                    }

                    for(int i=0;i<srform.getsRRFPList().size();i++){
                        summaryAdapter.add(srform.getsRRFPList().get(i));
                    }

                    rcv_req.setVisibility(View.GONE);
                    rcv_req.setVisibility(View.VISIBLE);

                    rcv_warehouse.setVisibility(View.GONE);
                    rcv_warehouse.setVisibility(View.VISIBLE);

                    rcv_summary.setVisibility(View.GONE);
                    rcv_summary.setVisibility(View.VISIBLE);


                } else {
                    Log.e(TAG, "onResponse: "+ response.message());
                }
            }

            @Override
            public void onFailure(Call<StationeryRetrievalViewModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
            }
        });
    }

}
