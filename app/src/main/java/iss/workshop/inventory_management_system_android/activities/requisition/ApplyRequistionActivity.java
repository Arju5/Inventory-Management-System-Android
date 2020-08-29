package iss.workshop.inventory_management_system_android.activities.requisition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.activities.DashboardActivity;
import iss.workshop.inventory_management_system_android.activities.department.EmployeeRequisitionSummarySelectionActivity;
import iss.workshop.inventory_management_system_android.adapters.ProductlistAdapter;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.model.Employee;
import iss.workshop.inventory_management_system_android.model.Product;
import iss.workshop.inventory_management_system_android.viewmodel.RequisitionViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApplyRequistionActivity extends BaseActivity implements SearchView.OnQueryTextListener{

    private static final String TAG = "ApplyrfActivity";
    private ListView listview;
    private List<Product> prodModelList;
    private ProductlistAdapter productlistAdapter;
    private SearchView editsearch;
    private EditText add_rf_cmt;
    private Button btnnext;
    private ServiceHelper.ApiService service;
    private RequisitionViewModel rfViewmodel;
    SharePreferenceHelper sharePreferenceHelper;
    String rfcomment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_apply_requistion, frameLayout);
        txt_menuTitle.setText("CREATE REQUISITION");

        //get view
        listview = (ListView)rootView.findViewById(R.id.prodlist);

        btnnext = (Button)rootView.findViewById(R.id.next);
        add_rf_cmt = (EditText)findViewById(R.id.add_rf_comment) ;

        //get login userid
        sharePreferenceHelper = new SharePreferenceHelper(this);
        service = ServiceHelper.getClient(this);

        ApplyRF();

        //locate the search view
        editsearch = (SearchView)rootView.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product> reqProdlist = new ArrayList<>();
                rfViewmodel = new RequisitionViewModel();
                // rfViewmodel.requisitionForm = new RequisitionForm();
                //search selected product list
                for (int i = 0; i < productlistAdapter.productArrayList.size(); i++){
                    if(productlistAdapter.productArrayList.get(i).getSelected()) {
                        reqProdlist.add( productlistAdapter.productArrayList.get(i));
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
        });

    }

    private void ApplyRF() {

        Call<RequisitionViewModel> callrfdetails = service.ApplyRF();
        callrfdetails.enqueue(new Callback<RequisitionViewModel>() {
            @Override
            public void onResponse(Call<RequisitionViewModel> call, Response<RequisitionViewModel> response) {
                if (response.isSuccessful()) {

                    RequisitionViewModel rfvm = response.body();
                    productlistAdapter = new ProductlistAdapter(ApplyRequistionActivity.this, rfvm.productList);
                    productlistAdapter.setProductArrayList(rfvm.productList);
                    prodModelList = rfvm.productList;
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
                        Intent intent = new Intent(ApplyRequistionActivity.this, EmployeeRequisitionSummarySelectionActivity.class);
                        intent.putExtra("empType",sharePreferenceHelper.getUserRole());
                        startActivity(intent);
                    }
                } else {
                    Log.e(TAG, "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<RequisitionViewModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;

        productlistAdapter.filter(text);
        return false;
    }

}