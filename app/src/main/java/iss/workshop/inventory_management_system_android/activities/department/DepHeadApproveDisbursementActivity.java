package iss.workshop.inventory_management_system_android.activities.department;

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

import java.util.List;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.activities.disbursement.DisbursementFormDetailsActivity;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.model.DisbursementForm;
import iss.workshop.inventory_management_system_android.model.DisbursementFormProduct;
import iss.workshop.inventory_management_system_android.model.DisbursementFormRequisitionForm;
import iss.workshop.inventory_management_system_android.viewmodel.DisbursementViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepHeadApproveDisbursementActivity extends BaseActivity {

    private static final String TAG = "Approve Disbursement Activi";
    private ServiceHelper.ApiService service;
    private int count = 1;
    private DisbursementViewModel disbursementViewModel;
    private List<DisbursementForm> disbursementForms;
    SharePreferenceHelper sharePreferenceHelper;
    String currentStatus;
    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().inflate(R.layout.activity_dep_head_approve_disbursement, frameLayout);
        txt_menuTitle.setText("APPROVE DISBURSEMENT");
        Log.d(TAG, "onResponse: Disbursement Summary Activity - Details");
        service = ServiceHelper.getClient(this);
        Intent intent = getIntent();
        currentStatus = intent.getStringExtra("CurrentStatus");
        sharePreferenceHelper = new SharePreferenceHelper(this);


        Call<List<DisbursementForm>> serviceDVMAPICall = null;
        serviceDVMAPICall = service.getCreatedDFByDepRep(sharePreferenceHelper.getuserId());
        serviceDVMAPICall.enqueue(new Callback<List<DisbursementForm>>() {
            @Override
            public void onResponse(Call<List<DisbursementForm>> call, Response<List<DisbursementForm>> response) {
                if (response.isSuccessful()) {
                    disbursementForms = (List<DisbursementForm>) response.body();
                    Log.d(TAG, "onResponse : Success - " + disbursementViewModel.disbursementForm.dfCode);
                    Log.d(TAG, "onResponse : Success - " + disbursementViewModel.disbursementForm.dfStatus);
                    putDataToLayout(disbursementViewModel);
                }
            }
            @Override
            public void onFailure(Call<List<DisbursementForm>> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
            }
        });

        Button mSubmitbutton = (Button) rootView.findViewById(R.id.df_ApprovedisbursementFormSubmit);
        mSubmitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DepHeadApproveDisbursementActivity.this, "Approving Disbursement", Toast.LENGTH_SHORT).show();
                Call<DisbursementViewModel> serviceDVMAPICall = null;
                serviceDVMAPICall = service.approveDFByDeptRep(disbursementViewModel);
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
            }
        });


    }

    public void putDataToLayout(DisbursementViewModel disbursementViewModel) {
        TextView disbursementFormCode = rootView.findViewById(R.id.df_ApprovedisbursementFormCode);
        disbursementFormCode.append(disbursementViewModel.disbursementForm.dfCode);
        TextView departmentName = rootView.findViewById(R.id.df_ApprovedepartmentName);
        departmentName.append(disbursementViewModel.disbursementForm.deptRep.department.departmentName);
        TextView departmentRepresentative = rootView.findViewById(R.id.df_ApprovedepartmentRepresentative);
        departmentRepresentative.append(disbursementViewModel.disbursementForm.deptRep.firstname + " " + disbursementViewModel.disbursementForm.deptRep.lastname);
        TextView collectionPoint = rootView.findViewById(R.id.df_ApprovecollectionPoint);
        collectionPoint.append(disbursementViewModel.disbursementForm.collectionPoint.collectionName);
        TextView collectionDateTime = rootView.findViewById(R.id.df_ApprovecollectionDateTime);
        collectionDateTime.append(disbursementViewModel.disbursementForm.dfDeliveryDate);
        TableLayout disbursementRequisitionFormsTable = (TableLayout) rootView.findViewById(R.id.df_ApprovedisbursementRequisitionForms);
        TableLayout disbursementFormProductsTable = (TableLayout) rootView.findViewById(R.id.df_ApprovedisbursementFormProducts);
        TableRow.LayoutParams parameters = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        for (DisbursementFormRequisitionForm disbursementFormRequisitionForm : disbursementViewModel.disbursementFormRequisitionForms) {
            TableRow disbursementRequisitionForm = new TableRow(DepHeadApproveDisbursementActivity.this);
            TextView requisitionSerialnumber = new TextView(DepHeadApproveDisbursementActivity.this);
            TextView requisitionCode = new TextView(DepHeadApproveDisbursementActivity.this);
            TextView requisitionDeliveryDate = new TextView(DepHeadApproveDisbursementActivity.this);
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
            TableRow disbursementFormProductrow = new TableRow(DepHeadApproveDisbursementActivity.this);
            TextView productSerialnumber = new TextView(DepHeadApproveDisbursementActivity.this);
            TextView productDescription = new TextView(DepHeadApproveDisbursementActivity.this);
            TextView productQuantityRequested = new TextView(DepHeadApproveDisbursementActivity.this);
            TextView productQuantityReceived = new TextView(DepHeadApproveDisbursementActivity.this);
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