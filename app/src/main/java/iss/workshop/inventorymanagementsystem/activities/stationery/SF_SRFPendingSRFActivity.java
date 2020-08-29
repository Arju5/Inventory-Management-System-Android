package iss.workshop.inventorymanagementsystem.activities.stationery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iss.workshop.inventorymanagementsystem.R;
import iss.workshop.inventorymanagementsystem.adapter.SF_SRFOpenDelegate;
import iss.workshop.inventorymanagementsystem.adapter.SF_SRFPendingAssignedAdapter;
import iss.workshop.inventorymanagementsystem.adapter.SF_SRFPendingSRFAdapter;
import iss.workshop.inventorymanagementsystem.helper.ServiceHelper;
import iss.workshop.inventorymanagementsystem.helper.SharePreferenceHelper;
import iss.workshop.inventorymanagementsystem.model.StationeryRetrievalRequisitionFormProduct;
import iss.workshop.inventorymanagementsystem.viewmodel.StationeryRetrievalViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SF_SRFPendingSRFActivity extends AppCompatActivity implements SF_SRFOpenDelegate {

    private static final String TAG = "SF_SRFPendingSRFActivit";
    Integer selected_sfId;
    String username;
    TextView warehousepacker;
    TextView warehouseName;
    TextView createDate;
    TextView retrievalId;
    TextView status;
    Button assignButton;

    String cName;
    String wName;
    String cDate;
    String rId;

    RecyclerView rcv_assigned;
    RecyclerView rcv_requisition;
    private ServiceHelper.ApiService service;
    SharePreferenceHelper sharePreferenceHelper;

    private SF_SRFPendingSRFAdapter sfSrfPendingAdapter;
    private SF_SRFPendingAssignedAdapter pendingAssignedAdapter;
    StationeryRetrievalViewModel srform;
    List<StationeryRetrievalRequisitionFormProduct> cachelist;

    //for Back Button
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
        setContentView(R.layout.activity_sf_pendingsrf);

        //for Back Button
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        service = ServiceHelper.getClient(this);

        //Get UserName by sharePreference
        sharePreferenceHelper = new SharePreferenceHelper(this);
        username = sharePreferenceHelper.getUserName();

        Intent intent = getIntent();
        selected_sfId = Integer.parseInt(intent.getStringExtra("SFId"));

        Log.e(TAG, "onCreate: sfId" + selected_sfId );

        warehousepacker = findViewById(R.id.sclerkname);
        //warehouseName = findViewById(R.id.whousename);
        createDate = findViewById(R.id.crdate);
        retrievalId = findViewById(R.id.retrieval_name);
        status = findViewById(R.id.status_name);
        assignButton = findViewById(R.id.btnAssignProductToRequ);

        cachelist = new ArrayList<>();

        final List<StationeryRetrievalRequisitionFormProduct> srrfplist = new ArrayList<>();
        assignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(StationeryRetrievalRequisitionFormProduct model:cachelist){
                    //Log.e(TAG, "onClick: Id" + model.getId() );
                    //Log.e(TAG, "onClick: Qty " + model.getProductReceivedTotal() );
                    StationeryRetrievalRequisitionFormProduct srrfp = new StationeryRetrievalRequisitionFormProduct();
                    srrfp.setId(model.getId());
                    srrfp.setProductAssigned(model.getProductAssigned());
                    srrfplist.add(srrfp);
                }
                Log.e(TAG, "onClick: SRRFP"+srrfplist.size() );
                srform.setsRRFPList(srrfplist);
                srform.setRetrievalProducts(srform.getRetrievalProducts());
                srform.setStationeryRetrieval(srform.getStationeryRetrieval());

                int srId = srform.getStationeryRetrieval().getId();
                saveAssignedProductsInPendingSR(srId);
            }
        });


        sfSrfPendingAdapter = new SF_SRFPendingSRFAdapter();
        pendingAssignedAdapter = new SF_SRFPendingAssignedAdapter(this);
        rcv_assigned = findViewById(R.id.pdassigned_recycler);
        rcv_assigned.setHasFixedSize(true);
        rcv_assigned.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rcv_assigned.setAdapter(sfSrfPendingAdapter);

        rcv_requisition = findViewById(R.id.srrf_recycler);
        rcv_requisition.setHasFixedSize(true);
        rcv_requisition.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rcv_requisition.setAdapter(pendingAssignedAdapter);


        getSRPendingFormBySelectedId();
    }

    public void getSRPendingFormBySelectedId() {

        Call<StationeryRetrievalViewModel> callsrform = service.getSRPendingFormBySelectedId(selected_sfId);
        callsrform.enqueue(new Callback<StationeryRetrievalViewModel>() {
            @Override
            public void onResponse(Call<StationeryRetrievalViewModel> call, Response<StationeryRetrievalViewModel> response) {

                if(response.isSuccessful()) {
                    srform = response.body();
                    Log.e(TAG, "onResponse: Product List : " + srform.retrievalProducts.size() );

                    cName = srform.stationeryRetrieval.warehousePacker.getFirstname() + "" + srform.stationeryRetrieval.storeClerk.getLastname();
                    warehousepacker.setText(cName);

                    createDate.setText(srform.stationeryRetrieval.getSrDate());

                    retrievalId.setText(srform.stationeryRetrieval.getSrCode());

                    status.setText("Pending");
                    for(int i=0;i<srform.retrievalProducts.size();i++){
                        Log.e(TAG, "onResponse: Requisition number = "+srform.retrievalProducts.get(i).getProduct().getProductName());
                        sfSrfPendingAdapter.add(srform.retrievalProducts.get(i));
                    }

                    Log.e(TAG, "onResponse: srform : "+srform.getsRRFPList().size() );
                    /*for(int i = 0;i<srform.getsRRFPList().size();i++){
                        Log.e(TAG, "onResponse: RQ : "+srform.getsRRFPList().get(i).getRfp().getRequisitionForm().getRfCode());
                        Log.e(TAG, "onResponse: Product : "+srform.getsRRFPList().get(i).getRfp().getProduct().getProductName() );
                        Log.e(TAG, "onResponse: Product Approve : "+srform.getsRRFPList().get(i).getRfp().getProductApproved() );
                    }*/

                    for(int i=0;i<srform.getsRRFPList().size();i++){
                        //Log.e(TAG, "onResponse: Requisition number = "+srform.sRRFPList.size());
                        //Log.e(TAG, "onResponse: Resulttttt = "+srform.sRRFPList.get(i));
                        pendingAssignedAdapter.add(srform.getsRRFPList().get(i));
                        cachelist.add(srform.sRRFPList.get(i));
                    }


                    rcv_assigned.setVisibility(View.GONE);
                    rcv_assigned.setVisibility(View.VISIBLE);

                    rcv_requisition.setVisibility(View.GONE);
                    rcv_requisition.setVisibility(View.VISIBLE);


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

    public void saveAssignedProductsInPendingSR(int srId){
        Call<StationeryRetrievalViewModel> callsaveReceivedQty = service.saveAssignedProductsInPendingSR(srId, srform);
        callsaveReceivedQty.enqueue(new Callback<StationeryRetrievalViewModel>() {
            @Override
            public void onResponse(Call<StationeryRetrievalViewModel> call, Response<StationeryRetrievalViewModel> response) {

                if (response.isSuccessful()) {
                    StationeryRetrievalViewModel srvm = response.body();
                    if(srvm != null){
                        Intent intent = new Intent(SF_SRFPendingSRFActivity.this,SF_StationeryRetrievalSummaryActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Log.e(TAG, "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<StationeryRetrievalViewModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public void onChangeQty(int qty, int position) {
        cachelist.get(position).setProductAssigned(qty);
    }
}
