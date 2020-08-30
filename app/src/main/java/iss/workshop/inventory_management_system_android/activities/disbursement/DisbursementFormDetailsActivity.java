package iss.workshop.inventory_management_system_android.activities.disbursement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.model.DisbursementFormProduct;
import iss.workshop.inventory_management_system_android.model.DisbursementFormRequisitionForm;
import iss.workshop.inventory_management_system_android.viewmodel.DisbursementViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisbursementFormDetailsActivity extends BaseActivity {

    private static final String TAG = "DisburseDetailActivity";
    private ServiceHelper.ApiService service;
    private int count = 1;
    private DisbursementViewModel disbursementViewModel;
    private  String currentStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_disbursement_form_details, frameLayout);
        txt_menuTitle.setText("DISBURSEMENT DETAILS");
        Intent intent = getIntent();
        Log.d(TAG, "onResponse: Disbursement Summary Activity - Details");
        service = ServiceHelper.getClient(this);
        currentStatus = intent.getStringExtra("CurrentStatus");
        Call<DisbursementViewModel> serviceDVMAPICall = null;
        serviceDVMAPICall = service.getDisbursementFormFullDetailsByDFCode(intent.getStringExtra("DFCode"));
        serviceDVMAPICall.enqueue(new Callback<DisbursementViewModel>() {
            @Override
            public void onResponse(Call<DisbursementViewModel> call, Response<DisbursementViewModel> response) {
                if (response.isSuccessful()) {
                    disbursementViewModel = response.body();
                    Log.d(TAG, "onResponse : Success - " + disbursementViewModel.disbursementForm.dfCode);
                    Log.d(TAG, "onResponse : Success - " + disbursementViewModel.disbursementForm.dfStatus);
                    putDataToLayout(disbursementViewModel);
                }
            }
            @Override
            public void onFailure(Call<DisbursementViewModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
            }
        });

        Button mSubmitbutton = (Button) findViewById(R.id.disbursementFormSubmit);
        mSubmitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "View Model - " + disbursementViewModel.disbursementFormProducts.size() + " Status - " + currentStatus);
                if(disbursementViewModel != null && currentStatus.contains("PENDING")) {
                    DibursementHandOverActivity.setDisbursementViewModel(disbursementViewModel);
                    Toast.makeText(DisbursementFormDetailsActivity.this, "Proceeding to DELIVERY", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DisbursementFormDetailsActivity.this, DibursementHandOverActivity.class);
                    //intent.putExtra("DisbursementViewModel", disbursementViewModel);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(DisbursementFormDetailsActivity.this, "Oops! Not Approved by Department Rep yet.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void putDataToLayout(DisbursementViewModel disbursementViewModel) {
        TextView disbursementFormCode = findViewById(R.id.disbursementFormCode);
        disbursementFormCode.append(disbursementViewModel.disbursementForm.dfCode);
        TextView departmentName = findViewById(R.id.departmentName);
        departmentName.append(disbursementViewModel.disbursementForm.deptRep.department.departmentName);
        TextView departmentRepresentative = findViewById(R.id.departmentRepresentative);
        departmentRepresentative.append(disbursementViewModel.disbursementForm.deptRep.firstname + " " + disbursementViewModel.disbursementForm.deptRep.lastname);
        TextView collectionPoint = findViewById(R.id.collectionPoint);
        collectionPoint.append(disbursementViewModel.disbursementForm.collectionPoint.collectionName);
        TextView collectionDateTime = findViewById(R.id.collectionDateTime);
        collectionDateTime.append(disbursementViewModel.disbursementForm.dfDeliveryDate);
        TableLayout disbursementRequisitionFormsTable = (TableLayout) findViewById(R.id.disbursementRequisitionForms);
        TableLayout disbursementFormProductsTable = (TableLayout) findViewById(R.id.disbursementFormProducts);
        TableRow.LayoutParams parameters = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        for (DisbursementFormRequisitionForm disbursementFormRequisitionForm : disbursementViewModel.disbursementFormRequisitionForms) {
            TableRow disbursementRequisitionForm = new TableRow(DisbursementFormDetailsActivity.this);
            TextView requisitionSerialnumber = new TextView(DisbursementFormDetailsActivity.this);
            TextView requisitionCode = new TextView(DisbursementFormDetailsActivity.this);
            TextView requisitionDeliveryDate = new TextView(DisbursementFormDetailsActivity.this);
            requisitionSerialnumber.setText(String.valueOf(count));
            requisitionSerialnumber.setLayoutParams(parameters);
            requisitionSerialnumber.setGravity(Gravity.CENTER);
            requisitionCode.setText(disbursementFormRequisitionForm.requisitionForm.rfCode);
            requisitionCode.setLayoutParams(parameters);
            requisitionCode.setGravity(Gravity.CENTER);
            requisitionDeliveryDate.setText(disbursementFormRequisitionForm.disbursementForm.dfDeliveryDate);
            requisitionDeliveryDate.setLayoutParams(parameters);
            requisitionDeliveryDate.setGravity(Gravity.CENTER);
            disbursementRequisitionForm.addView(requisitionSerialnumber);
            disbursementRequisitionForm.addView(requisitionCode);
            disbursementRequisitionForm.addView(requisitionDeliveryDate);
            disbursementRequisitionFormsTable.addView(disbursementRequisitionForm);
            count++;
        }
        count = 1;
        for (DisbursementFormProduct disbursementFormProduct : disbursementViewModel.disbursementFormProducts) {
            TableRow disbursementFormProductrow = new TableRow(DisbursementFormDetailsActivity.this);
            TextView productSerialnumber = new TextView(DisbursementFormDetailsActivity.this);
            TextView productDescription = new TextView(DisbursementFormDetailsActivity.this);
            TextView productQuantityRequested = new TextView(DisbursementFormDetailsActivity.this);
            TextView productQuantityReceived = new TextView(DisbursementFormDetailsActivity.this);
            productSerialnumber.setText(String.valueOf(count));
            productSerialnumber.setLayoutParams(parameters);
            productSerialnumber.setGravity(Gravity.CENTER);
            productDescription.setText(disbursementFormProduct.product.productName);
            productDescription.setLayoutParams(parameters);
            productDescription.setGravity(Gravity.CENTER);
            productQuantityRequested.setText(String.valueOf(disbursementFormProduct.productToDeliverTotal));
            productQuantityRequested.setLayoutParams(parameters);
            productQuantityRequested.setGravity(Gravity.CENTER);
            productQuantityReceived.setText(String.valueOf(disbursementFormProduct.productDeliveredTotal));
            productQuantityReceived.setLayoutParams(parameters);
            productQuantityReceived.setGravity(Gravity.CENTER);
            disbursementFormProductrow.addView(productSerialnumber);
            disbursementFormProductrow.addView(productDescription);
            disbursementFormProductrow.addView(productQuantityRequested);
            disbursementFormProductrow.addView(productQuantityReceived);
            disbursementFormProductsTable.addView(disbursementFormProductrow);
            count++;
        }
    }
}