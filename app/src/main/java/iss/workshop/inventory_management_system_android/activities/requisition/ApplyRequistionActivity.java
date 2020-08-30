package iss.workshop.inventory_management_system_android.activities.requisition;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.adapters.requisition.ProductlistAdapter;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.model.Employee;
import iss.workshop.inventory_management_system_android.model.Product;
import iss.workshop.inventory_management_system_android.viewmodel.RequisitionViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplyRequistionActivity extends AppCompatActivity
        implements  View.OnClickListener {

    private static final String TAG = "ApplyrfActivity";
    private ListView listview;
    public static ArrayList<Product> prodModelList, cacheList;
    private ProductlistAdapter productlistAdapter;
    private EditText editsearch,add_rf_cmt;
    private Button  btnnext,btnsearch;
    private ServiceHelper.ApiService service;
    private RequisitionViewModel rfViewmodel;
    SharePreferenceHelper sharePreferenceHelper;
    String rfcomment, searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_requistion);

        //get login userid
        sharePreferenceHelper = new SharePreferenceHelper(this);
        service = ServiceHelper.getClient(this);

        //get view
        listview = (ListView) findViewById(R.id.prodlist);
        prodModelList = new ArrayList<>();
        productlistAdapter = new ProductlistAdapter(this, R.layout.product_item,prodModelList);

        //call apply api method
        ApplyRF();

        //editsearch.setOnQueryTextListener(this);
        btnsearch = (Button)findViewById(R.id.search_btn);
        btnsearch.setOnClickListener(this);

        add_rf_cmt = (EditText)findViewById(R.id.add_rf_comment) ;

        btnnext = (Button) findViewById(R.id.next);
        btnnext.setOnClickListener(this);
    }

    private void ApplyRF() {
        productlistAdapter.clear();
        Call<RequisitionViewModel> callrfdetails = service.ApplyRF();
        callrfdetails.enqueue(new Callback<RequisitionViewModel>() {
            @Override
            public void onResponse(Call<RequisitionViewModel> call, Response<RequisitionViewModel> response) {
                if (response.isSuccessful()) {

                    RequisitionViewModel rfvm = response.body();

                    prodModelList = new ArrayList<>();
                    prodModelList.addAll(rfvm.productList);
                    productlistAdapter = new ProductlistAdapter(getApplicationContext(),R.layout.product_item,prodModelList);
                    if(listview != null)
                    {
                        listview.setAdapter(productlistAdapter);
                    }else {
                        Log.d(TAG, "product list view is null");
                    }
                }
            }
            @Override
            public void onFailure(Call<RequisitionViewModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    private void SaveRf(RequisitionViewModel rfViewmodel) {
        Call<RequisitionViewModel> callsaveRF = service.SaveRf(rfViewmodel);
        callsaveRF.enqueue(new Callback<RequisitionViewModel>() {
            @Override
            public void onResponse(Call<RequisitionViewModel> call, Response<RequisitionViewModel> response) {

                if (response.isSuccessful()) {
                    RequisitionViewModel rfViewmodel = response.body();
                    if(rfViewmodel != null){
                        Toast.makeText(getApplicationContext(), "Apply Requisition successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ApplyRequistionActivity.this, RequisitionLandingActivity.class);
                        intent.putExtra("empType",sharePreferenceHelper.getUserRole());
                        startActivity(intent);
                    }
                } else {
                    Log.e(TAG, "onResponse Save: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<RequisitionViewModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public void onClick(View view) {
        int btnid = view.getId();
        if(btnid == R.id.next){
            List<Product> reqProdlist = new ArrayList<>();
            rfViewmodel = new RequisitionViewModel();
            // rfViewmodel.requisitionForm = new RequisitionForm();
            //search selected product list
            for (int i = 0; i < productlistAdapter.arraylist.size(); i++){
                Log.d(TAG, "onClick adapter arraylist : "+ productlistAdapter.arraylist.size());
                Log.d(TAG, "onclick next/submit btn getselected: "+i+"   "+productlistAdapter.arraylist.get(i).getSelected());
                if(productlistAdapter.arraylist.get(i).getSelected()) {
                    reqProdlist.add( productlistAdapter.arraylist.get(i));
                }
            }
            if(add_rf_cmt != null){
                rfcomment = add_rf_cmt.getText().toString();
                Log.d(TAG,"add_rf_cmt :: "+rfcomment);
                if(!rfcomment.isEmpty()){
                    rfViewmodel.setComment(rfcomment);
                }
                else{
                    Log.d(TAG,"rfcomment is empty");
                }
            }

            rfViewmodel.setProductList(reqProdlist);  //set the selected ones into rf viewmodel

            Employee emp = new Employee();
            emp.setId(sharePreferenceHelper.getuserId());
            rfViewmodel.setEmployee(emp);  // send the emp id

            SaveRf(rfViewmodel);
        }
        else if(btnid == R.id.search_btn){
            //locate the search text view
            editsearch = (EditText) findViewById(R.id.search);
            searchText = editsearch.getText().toString();
            Log.d(TAG, "search text in onCreate: "+searchText);
            productlistAdapter.filter(searchText);

        }
    }
}