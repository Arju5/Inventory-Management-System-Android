package iss.workshop.inventory_management_system_android.activities.stationery;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.adapters.SF_SRFOpenDelegate;
import iss.workshop.inventory_management_system_android.adapters.SF_SRFOpenSRFAdapter;
import iss.workshop.inventory_management_system_android.helper.MyDateFormat;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.model.Employee;
import iss.workshop.inventory_management_system_android.model.StationeryRetrievalProduct;
import iss.workshop.inventory_management_system_android.viewmodel.StationeryRetrievalViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SF_SRFOpenSRFActivity extends BaseActivity implements SF_SRFOpenDelegate {

    private static final String TAG = "SF_SRFOpenSRFActivity";
    Integer selected_sfId;
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

    Button btn_openDialog_clerk;
    Button btn_openDialog_wh;
    Button btn_assign;
    TextView pswclerk;
    TextView pswwarehouse;

    RecyclerView rcv;
    private ServiceHelper.ApiService service;
    SharePreferenceHelper sharePreferenceHelper;
    MyDateFormat dateFormat;
    private SF_SRFOpenSRFAdapter productadapter;
    StationeryRetrievalViewModel srform;
    List<StationeryRetrievalProduct> cachelist;

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
        //setContentView(R.layout.activity_sf_opensrf);

        View rootView = getLayoutInflater().inflate(R.layout.activity_sf_opensrf, frameLayout);
        txt_menuTitle.setText("Open SRF");

        //for Back Button
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        service = ServiceHelper.getClient(this);

        //Get UserName by sharePreference
        sharePreferenceHelper = new SharePreferenceHelper(this);
        username = sharePreferenceHelper.getUserName();

        Intent intent = getIntent();
        selected_sfId = Integer.parseInt(intent.getStringExtra("SFId"));

        Log.e(TAG, "onCreate: sfId" + selected_sfId );

        clerkName = rootView.findViewById(R.id.sclerkname);
        //warehouseName = findViewById(R.id.whousename);
        createDate = rootView.findViewById(R.id.crdate);
        retrievalId = rootView.findViewById(R.id.retrieval_name);
        status = rootView.findViewById(R.id.status_name);
        dateFormat = new MyDateFormat();
        pswclerk = rootView.findViewById(R.id.passwordclerk);
        pswwarehouse = rootView.findViewById(R.id.passwordwarehouse);

        productadapter = new SF_SRFOpenSRFAdapter(this);
        rcv = rootView.findViewById(R.id.pd_recycler);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rcv.setAdapter(productadapter);

        btn_openDialog_clerk = rootView.findViewById(R.id.signForClerk);
        btn_openDialog_wh = rootView.findViewById(R.id.signForWarehouseKeeper);
        btn_assign = rootView.findViewById(R.id.btnAssignProduct);

        btn_openDialog_clerk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog(1);
            }
        });

        btn_openDialog_wh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog(2);
            }
        });


        final List<StationeryRetrievalProduct> srplist = new ArrayList<>();

        //Assign Products
        btn_assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(StationeryRetrievalProduct model:cachelist){
                    //Log.e(TAG, "onClick: Id" + model.getId() );
                    //Log.e(TAG, "onClick: Qty " + model.getProductReceivedTotal() );
                    StationeryRetrievalProduct srpf = new StationeryRetrievalProduct();
                    srpf.setId(model.getId());
                    srpf.setProductReceivedTotal(model.getProductReceivedTotal());
                    srplist.add(srpf);
                }

                Employee empclerk = new Employee();
                empclerk.setUsername(btn_openDialog_clerk.getText().toString());
                empclerk.setPassword(pswclerk.getText().toString());

                Employee empwarehouse = new Employee();
                empwarehouse.setUsername((btn_openDialog_wh.getText().toString()));
                empwarehouse.setPassword(pswwarehouse.getText().toString());

                Log.e(TAG, "onClick: Products : " + srplist.size() );
                Log.e(TAG, "onClick: Clerk " + empclerk.getUsername() );
                Log.e(TAG, "onClick: Clerk " + empclerk.getPassword() );
                Log.e(TAG, "onClick: Warehouse " + empwarehouse.getUsername());
                Log.e(TAG, "onClick: Warehouse " + empwarehouse.getPassword() );

                srform.setRetrievalProducts(srplist);
                srform.setStoreclerk(empclerk);
                srform.setWarehousepacker(empwarehouse);
                srform.setSrrfList(srform.getSrrfList());

                Log.e(TAG, "onClick: Stationery Retrieval : " + srform.getStationeryRetrieval() );

                saveReceivedProducts();
            }
        });
        getSRFormBySelectedId();
    }

    public void showCustomDialog(final int i){
        final Dialog dialog = new Dialog(SF_SRFOpenSRFActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog);

        final EditText username = dialog.findViewById(R.id.username);
        final EditText psw = dialog.findViewById(R.id.password);
        Button submitButton = dialog.findViewById(R.id.btnsignfordialog);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String password = psw.getText().toString();

                Log.e(TAG, "onClick: Password : " + password );

                if(i == 1){
                    btn_openDialog_clerk.setText(name.toString());
                    pswclerk.setText(String.valueOf(password));

                }
                else{
                    btn_openDialog_wh.setText(name.toString());
                    pswwarehouse.setText(password.toString());
                    /*srform.getWarehousepacker().setUsername(name);
                    srform.getWarehousepacker().setPassword(password);*/
                }
                dialog.dismiss();
            }
            //dialog.dismiss();
        });
        dialog.show();
    }

    public void getSRFormBySelectedId() {

        Call<StationeryRetrievalViewModel> callsrform = service.getSRFormBySelectedId(selected_sfId);
        callsrform.enqueue(new Callback<StationeryRetrievalViewModel>() {
            @Override
            public void onResponse(Call<StationeryRetrievalViewModel> call, Response<StationeryRetrievalViewModel> response) {

                if(response.isSuccessful()) {
                    cachelist = new ArrayList<>();
                    srform = response.body();
                    //Log.e(TAG, "onResponse: Product List : " + srform.retrievalProducts.size() );

                    cName = srform.stationeryRetrieval.storeClerk.getFirstname() + "" + srform.stationeryRetrieval.storeClerk.getLastname();
                    clerkName.setText(cName);

                    createDate.setText(srform.stationeryRetrieval.getSrDate());

                    retrievalId.setText(srform.stationeryRetrieval.getSrCode());

                    status.setText("Open");
                    for(int i=0;i<srform.retrievalProducts.size();i++){
                        Log.d(TAG, "onResponse: Requisition number = "+srform.retrievalProducts.get(i).getProduct().getProductName());
                        productadapter.add(srform.retrievalProducts.get(i));
                        cachelist.add(srform.retrievalProducts.get(i));
                    }
                    rcv.setVisibility(View.GONE);
                    rcv.setVisibility(View.VISIBLE);
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

    public void saveReceivedProducts(){
        Call<StationeryRetrievalViewModel> callsaveReceivedQty = service.saveReceivedQtyForOpenSRF(srform);
        callsaveReceivedQty.enqueue(new Callback<StationeryRetrievalViewModel>() {
            @Override
            public void onResponse(Call<StationeryRetrievalViewModel> call, Response<StationeryRetrievalViewModel> response) {

                if (response.isSuccessful()) {
                    StationeryRetrievalViewModel srvm = response.body();
                    if(srvm != null){
                        Intent intent = new Intent(SF_SRFOpenSRFActivity.this,SF_StationeryRetrievalSummaryActivity.class);
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
        cachelist.get(position).setProductReceivedTotal(qty);
    }
}