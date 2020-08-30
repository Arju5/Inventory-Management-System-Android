package iss.workshop.inventory_management_system_android.activities.stationery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.adapters.stationery.SF_ProductListAdapter;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.model.StationeryRetrievalProduct;
import iss.workshop.inventory_management_system_android.viewmodel.StationeryRequisitionProductViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SF_SRFProductActivity extends BaseActivity {
    private static final String TAG = "SF_SRFProductActivity";

    public static final String IE_list = "list";
    public static final String RE_list = "reqlist";

    public static Intent getSRFProductIntent(Context context, List<StationeryRetrievalProduct> modellist, List<Integer> selectedRequisition) {
        Intent intent = new Intent(context, SF_SRFProductActivity.class);
        intent.putExtra(IE_list, (Serializable) modellist);
        intent.putExtra(RE_list, (Serializable) selectedRequisition);
        return intent;
    }

    RecyclerView rcv;
    private ServiceHelper.ApiService service;
    SharePreferenceHelper sharePreferenceHelper;
    private SF_ProductListAdapter sf_productListAdapter;
    StationeryRequisitionProductViewModel srvm;
    Button btncreate;
    EditText edicomment;
    List<StationeryRetrievalProduct> productlist;
    List<Integer> requisitionIdlist;
    String comment;
    String username;

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
        //setContentView(R.layout.activity_sf_productlist);
        View rootView = getLayoutInflater().inflate(R.layout.activity_sf_productlist, frameLayout);
        txt_menuTitle.setText("Create SRF");

        //for Back Button
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        service = ServiceHelper.getClient(this);

        //Get UserName by sharePreference
        sharePreferenceHelper = new SharePreferenceHelper(this);
        username = sharePreferenceHelper.getUserName();

        btncreate = rootView.findViewById(R.id.createsrf);
        edicomment = (EditText)rootView.findViewById(R.id.textArea_comment);

        sf_productListAdapter = new SF_ProductListAdapter();

        productlist = (List<StationeryRetrievalProduct>) getIntent().getSerializableExtra(IE_list);
        requisitionIdlist = (List<Integer>) getIntent().getSerializableExtra(RE_list);

        if (productlist.size() == 0) {
            btncreate.setVisibility(View.GONE);
        }

        rcv = findViewById(R.id.product_recycler);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rcv.setAdapter(sf_productListAdapter);

        for (int i = 0; i < productlist.size(); i++) {
            /*Toast.makeText(getApplicationContext(), productlist.get(i).getProductname().toString(), Toast.LENGTH_LONG);
            Log.e(TAG, "onCreate: ProductName : " + productlist.get(i).getProductname());*/
            sf_productListAdapter.add(productlist.get(i));
        }

        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comment = edicomment.getText().toString();

                //Add to StationeryRequistionViewModel
                srvm = new StationeryRequisitionProductViewModel();
                srvm.setUsername(username);
                srvm.setComment(comment);
                srvm.setRequisitionIdList(requisitionIdlist);
                srvm.setSpvm(productlist);

                /*Log.e(TAG, "onClick: name : "+srvm.getUsername().toUpperCase().toString() );
                Log.e(TAG, "onClick: comment : "+srvm.getComment());
                Log.e(TAG, "onClick: requ : "+srvm.requisitionIdList.size() );
                Log.e(TAG, "onClick: product : "+srvm.spvm.size() );*/


                Call<StationeryRequisitionProductViewModel> callcreatesrform = service.createSRForm(srvm);
                callcreatesrform.enqueue(new Callback<StationeryRequisitionProductViewModel>() {
                    @Override
                    public void onResponse(Call<StationeryRequisitionProductViewModel> call, Response<StationeryRequisitionProductViewModel> response) {

                        if (response.isSuccessful()) {
                            StationeryRequisitionProductViewModel srpvm = response.body();
                            if(srpvm != null){
                                Intent intent = new Intent(SF_SRFProductActivity.this,SF_StationeryRetrievalSummaryActivity.class);
                                startActivity(intent);
                            }
                        } else {
                            Log.e(TAG, "onResponse: " + response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<StationeryRequisitionProductViewModel> call, Throwable t) {
                        Log.e(TAG, "onFailure: ", t);
                    }
                });

            }
        });
    }

}