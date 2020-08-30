package iss.workshop.inventory_management_system_android.activities.disbursement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.activities.DashboardActivity;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.model.DisbursementFormProduct;
import iss.workshop.inventory_management_system_android.model.DisbursementFormRequisitionForm;
import iss.workshop.inventory_management_system_android.viewmodel.DisbursementViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisbursementAssignment extends BaseActivity {

    private static final String TAG = "DisburseAssignActivity";
    private ServiceHelper.ApiService service;
    private int count = 1;
    private  String currentStatus;
    private static DisbursementViewModel disbursementViewModel;
    View rootView;

    public static void setDisbursementViewModel(DisbursementViewModel dfViewModel) {
        DisbursementAssignment.disbursementViewModel = dfViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().inflate(R.layout.activity_disbursement_assignment, frameLayout);
        txt_menuTitle.setText("DISBURSEMENT ASSIGNMENT");
        Log.d(TAG, "Disbursement Assignment Activity - " + disbursementViewModel.disbursementFormProducts.size());

        Intent intent = getIntent();
        Log.d(TAG, "onResponse: Disbursement Summary Activity - Details");
        service = ServiceHelper.getClient(this);
        currentStatus = intent.getStringExtra("CurrentStatus");
        putDataToLayout(disbursementViewModel);
        Button mSubmitbutton = (Button)rootView.findViewById(R.id.df_AssignDisbursementFormSubmit);
        mSubmitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DisbursementAssignment.this, "Calling API Assign DF", Toast.LENGTH_SHORT).show();
                Call<DisbursementViewModel> callsaveReceivedQty = service.AssignDF(disbursementViewModel);
                callsaveReceivedQty.enqueue(new Callback<DisbursementViewModel>() {
                    @Override
                    public void onResponse(Call<DisbursementViewModel> call, Response<DisbursementViewModel> response) {
                        Log.d(TAG, "Response Code - " + response.code());
                        Log.d(TAG, "Response Code - " + response.message());
                        Log.d(TAG, "Response Code - " + response.toString());
                        if (response.isSuccessful()) {
                            DisbursementViewModel disbursementViewModel = response.body();
                            if(disbursementViewModel != null){
                                Intent intent = new Intent(DisbursementAssignment.this, DashboardActivity.class);
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
        });
    }

    public void putDataToLayout(DisbursementViewModel disbursementViewModel) {
        TextView disbursementstoreclerk = rootView.findViewById(R.id.df_AssignDisbursementStoreClerk);
        disbursementstoreclerk.append(disbursementViewModel.disbursementForm.storeClerk.firstname + " " + disbursementViewModel.disbursementForm.storeClerk.lastname);
        TextView departmentName = rootView.findViewById(R.id.df_AssignDisbursementDepName);
        departmentName.append(disbursementViewModel.disbursementForm.deptRep.department.departmentName);
        TextView departmentRepresentative = rootView.findViewById(R.id.df_AssignDisbursementDepRep);
        departmentRepresentative.append(disbursementViewModel.disbursementForm.deptRep.firstname + " " + disbursementViewModel.disbursementForm.deptRep.lastname);
        TextView disbursementFormCode = rootView.findViewById(R.id.df_AssignDisbursementCode);
        disbursementFormCode.append(disbursementViewModel.disbursementForm.deptRep.firstname + " " + disbursementViewModel.disbursementForm.deptRep.lastname);
        TextView collectionPoint = rootView.findViewById(R.id.df_AssignDisbursementCollection);
        collectionPoint.append(disbursementViewModel.disbursementForm.collectionPoint.collectionName);
        TextView disbursementStatus = rootView.findViewById(R.id.df_AssignDisbursementStatus);
        collectionPoint.append(String.valueOf(disbursementViewModel.disbursementForm.dfStatus));
        TextView collectionDateTime = rootView.findViewById(R.id.df_AssignDisbursementDeliveryDate1);
        collectionDateTime.append(disbursementViewModel.disbursementForm.dfDeliveryDate);
        TableLayout disbursementRequisitionFormsTable = (TableLayout) rootView.findViewById(R.id.df_AssignRequisitionForms);
        TableLayout disbursementFormProductsTable = (TableLayout) rootView.findViewById(R.id.df_AssignDisbursementFormProducts);
        TableRow.LayoutParams parameters = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        for (DisbursementFormRequisitionForm disbursementFormRequisitionForm : disbursementViewModel.disbursementFormRequisitionForms) {
            TableRow disbursementRequisitionForm = new TableRow(DisbursementAssignment.this);
            TextView requisitionSerialnumber = new TextView(DisbursementAssignment.this);
            TextView requisitionCode = new TextView(DisbursementAssignment.this);
            TextView requisitionDeliveryDate = new TextView(DisbursementAssignment.this);
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
            TableRow disbursementFormProductrow = new TableRow(DisbursementAssignment.this);
            TextView productSerialnumber = new TextView(DisbursementAssignment.this);
            TextView productDescription = new TextView(DisbursementAssignment.this);
            TextView productQuantityRequested = new TextView(DisbursementAssignment.this);
            TextView productQuantityReceived = new EditText(DisbursementAssignment.this);
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