package iss.workshop.inventory_management_system_android.activities.stationery;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.adapters.SF_PARequisitionListAdapter;
import iss.workshop.inventory_management_system_android.helper.Pageable;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.model.RequisitionForm;
import iss.workshop.inventory_management_system_android.model.StationeryRetrievalProduct;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SF_SRFActivity extends BaseActivity {

    RecyclerView rcv;
    private ServiceHelper.ApiService service;
    SharePreferenceHelper sharePreferenceHelper;
    private SF_PARequisitionListAdapter reqadapter;
    private static final String TAG = "SF_StationeryRetrievalF";

    Button btnselected;
    List<Integer> selectedRequisition;

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
        //setContentView(R.layout.activity_sf_requisitionlist);

        View rootView = getLayoutInflater().inflate(R.layout.activity_sf_requisitionlist, frameLayout);
        txt_menuTitle.setText("Create SRF");

        reqadapter = new SF_PARequisitionListAdapter();
        btnselected = (Button) rootView.findViewById(R.id.btnchoosereq);
        selectedRequisition = new ArrayList<>();

        //for Back Button
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get UserName by sharePreference
        sharePreferenceHelper = new SharePreferenceHelper(this);
        String username = sharePreferenceHelper.getUserName();
        Toast.makeText(getApplicationContext(),username,Toast.LENGTH_LONG).show();

        rcv = rootView.findViewById(R.id.req_recycler);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rcv.setAdapter(reqadapter);

        service = ServiceHelper.getClient(this);
        getRequisitionList();

        btnselected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Pageable model:reqadapter.getItemsList()){
                    RequisitionForm requisitionForm = (RequisitionForm) model;
                    Log.e(TAG, "onClick: "+requisitionForm.isSelected + ":" + requisitionForm.id);
                    if(requisitionForm.isSelected){
                        selectedRequisition.add(requisitionForm.id);
                        Log.e(TAG, "onClick: Selected Requisition: "+selectedRequisition);
                    }
                }
                getProductsBySelectedRequisition();
            }

        });
    }

    private void getRequisitionList() {

        reqadapter.clear();//to clear adapter list

        Call<ArrayList<RequisitionForm>> callrequisition = service.getRequisitionList();
        callrequisition.enqueue(new Callback<ArrayList<RequisitionForm>>() {
            @Override
            public void onResponse(Call<ArrayList<RequisitionForm>> call, Response<ArrayList<RequisitionForm>> response) {

                if(response.isSuccessful()) {
                    ArrayList<RequisitionForm> requisitionlist = response.body();
                    for(int i=0;i<requisitionlist.size();i++){
                        Log.d(TAG, "onResponse: Requisition number = "+requisitionlist.get(i).rfCode);
                        reqadapter.add(requisitionlist.get(i));
                    }
                } else {
                    Log.e(TAG, "onResponse: "+ response.message());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RequisitionForm>> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
            }
        });
    }

    private void getProductsBySelectedRequisition(){
        Call<List<StationeryRetrievalProduct>> callproducts = service.postProductsBySelectedRequisition(selectedRequisition);
        callproducts.enqueue(new Callback<List<StationeryRetrievalProduct>>() {
            @Override
            public void onResponse(Call<List<StationeryRetrievalProduct>> call, Response<List<StationeryRetrievalProduct>> response) {

                if(response.isSuccessful()) {
                    List<StationeryRetrievalProduct> productlist = response.body();
                    if(productlist != null){
                        Log.e(TAG, "onResponse: Product : "+productlist.size() );

                        startActivity(SF_SRFProductActivity.getSRFProductIntent(SF_SRFActivity.this,productlist,selectedRequisition));
                    }


                } else {
                    Log.e(TAG, "onResponse: "+ response.message());
                }
            }

            @Override
            public void onFailure(Call<List<StationeryRetrievalProduct>> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
            }
        });
    }
}