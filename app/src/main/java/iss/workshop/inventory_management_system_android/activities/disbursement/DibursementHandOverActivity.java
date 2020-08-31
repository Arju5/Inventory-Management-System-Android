package iss.workshop.inventory_management_system_android.activities.disbursement;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
//import iss.workshop.inventorymanagementsystem.activities.DashboardActivity;
import iss.workshop.inventory_management_system_android.helper.MyDateFormat;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.model.DisbursementFormProduct;
import iss.workshop.inventory_management_system_android.model.DisbursementFormRequisitionForm;
import iss.workshop.inventory_management_system_android.model.Employee;
import iss.workshop.inventory_management_system_android.viewmodel.DisbursementViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DibursementHandOverActivity extends BaseActivity {

    private static final String TAG = "DFHandOverActivity";
    public static final String IE_list = "list";
    private static DisbursementViewModel disbursementViewModel;
    Button btn_SigninClerk,btn_SigninDepRep, btn_Delivered;
    TextView passStoreClerk, passDepRep, mstoreclerknamefordelivery, mdf_depRepName, mdf_dfcode, mdf_collectionpointName, mdf_Status, mdf_DeliveryDateTime;
    private ServiceHelper.ApiService service;
    private int count = 1;


    public static void setDisbursementViewModel(DisbursementViewModel dfViewModel) {
        DibursementHandOverActivity.disbursementViewModel = dfViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_dibursement_hand_over, frameLayout);
        txt_menuTitle.setText("DISBURSEMENT DETAILS");

        Log.d(TAG, "Hand Over Activity - " + disbursementViewModel.disbursementFormProducts.size());
        service = ServiceHelper.getClient(this);
        mstoreclerknamefordelivery = rootView.findViewById(R.id.df_handOverstoreclerknamefordelivery);
        mdf_depRepName = rootView.findViewById(R.id.df_handOverdepRepName);
        mdf_dfcode = rootView.findViewById(R.id.df_handOverdfcode);
        mdf_collectionpointName = rootView.findViewById(R.id.df_handOvercollectionpointName);
        mdf_Status = rootView.findViewById(R.id.df_handOverDisbursementStatus);
        mdf_DeliveryDateTime = rootView.findViewById(R.id.df_handOverDeliveryDateTime);

        mstoreclerknamefordelivery.setText(disbursementViewModel.disbursementForm.storeClerk.firstname + " " + disbursementViewModel.disbursementForm.storeClerk.lastname);
        mdf_depRepName.setText(disbursementViewModel.disbursementForm.deptRep.firstname + " " + disbursementViewModel.disbursementForm.deptRep.lastname);
        mdf_dfcode.setText(disbursementViewModel.disbursementForm.dfCode);
        mdf_collectionpointName.setText(disbursementViewModel.disbursementForm.collectionPoint.collectionName);
        mdf_Status.setText(String.valueOf(disbursementViewModel.disbursementForm.dfStatus));
        try {
            MyDateFormat dateFormat = new MyDateFormat();
            Date date = dateFormat.DATE_FORMAT_YMD_HMS.parse(dateFormat.removeTfromServerDate(disbursementViewModel.disbursementForm.dfDeliveryDate));
            mdf_DeliveryDateTime.append(dateFormat.DATE_FORMAT_DMY_HMS_AAA.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            mdf_DeliveryDateTime.setText(disbursementViewModel.disbursementForm.dfDeliveryDate);
        }


        passStoreClerk = rootView.findViewById(R.id.df_handOverpasswordclerk);
        passDepRep = rootView.findViewById(R.id.df_handOverpasswordDepRep);
        btn_SigninClerk = (Button) rootView.findViewById(R.id.df_handOversignForClerk);
        btn_SigninDepRep = (Button) rootView.findViewById(R.id.df_handOversignForDepRep);
        btn_Delivered = (Button) rootView.findViewById(R.id.df_handOverdisbursementFormSubmit);

        TableLayout disbursementRequisitionFormsTable = (TableLayout) findViewById(R.id.df_handOverdisbursementRequisitionForms);
        TableLayout disbursementFormProductsTable = (TableLayout)rootView.findViewById(R.id.df_handOverdfFormProducts);
        TableRow.LayoutParams parameters = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);

        for (DisbursementFormRequisitionForm disbursementFormRequisitionForm : disbursementViewModel.disbursementFormRequisitionForms) {
            TableRow disbursementRequisitionForm = new TableRow(DibursementHandOverActivity.this);
            TextView requisitionSerialnumber = new TextView(DibursementHandOverActivity.this);
            TextView requisitionCode = new TextView(DibursementHandOverActivity.this);
            TextView requisitionDeliveryDate = new TextView(DibursementHandOverActivity.this);
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
            TableRow disbursementFormProductrow = new TableRow(this);
            TextView productSerialnumber = new TextView(this);
            TextView productDescription = new TextView(this);
            TextView productQuantityRequested = new TextView(this);
            TextView productQuantityReceived = new TextView(this);
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

        btn_SigninClerk.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                showCustomDialog(1);

                btn_SigninClerk.setBackgroundColor(R.drawable.badgegreen);
            }
        });
        btn_SigninDepRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog(2);
                btn_SigninDepRep.setBackgroundColor(R.drawable.badgegreen);
            }
        });



        btn_Delivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Employee empclerk = new Employee();
                empclerk.setUsername(btn_SigninClerk.getText().toString());
                empclerk.setPassword(passStoreClerk.getText().toString());

                Employee empdeprep = new Employee();
                empdeprep.setUsername((btn_SigninDepRep.getText().toString()));
                empdeprep.setPassword(passDepRep.getText().toString());
                disbursementViewModel.setStoreclerk(empdeprep);
                disbursementViewModel.setDeptrep(empdeprep);
                disbursementViewModel.setComment("");
                Toast.makeText(DibursementHandOverActivity.this, "Calling API Deliver DF", Toast.LENGTH_SHORT).show();
                Call<DisbursementViewModel> callsaveReceivedQty = service.DeliverDF(disbursementViewModel);
                callsaveReceivedQty.enqueue(new Callback<DisbursementViewModel>() {
                    @Override
                    public void onResponse(Call<DisbursementViewModel> call, Response<DisbursementViewModel> response) {
                        Log.d(TAG, "Response Code - " + response.code());
                        Log.d(TAG, "Response Code - " + response.message());
                        Log.d(TAG, "Response Code - " + response.toString());
                        if (response.isSuccessful()) {
                            DisbursementViewModel disbursementViewModel = response.body();
                            if(disbursementViewModel != null){
                                Intent intent = new Intent(DibursementHandOverActivity.this, DisbursementSummaryStatusSelectionActivity.class);
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

    public void showCustomDialog(final int i){
        final Dialog dialog = new Dialog(DibursementHandOverActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog);

        final EditText username = dialog.findViewById(R.id.username);
        final EditText psw = dialog.findViewById(R.id.password);
        Button submitButton = dialog.findViewById(R.id.btnsignfordialog);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String password = psw.getText().toString();

                Log.e(TAG, "onClick: Password : " + password );

                if(i == 1){
                    btn_SigninClerk.setText(name.toString());
                    passStoreClerk.setText(String.valueOf(password));

                }
                else{
                    btn_SigninDepRep.setText(name.toString());
                    passDepRep.setText(password.toString());
                    /*srform.getWarehousepacker().setUsername(name);
                    srform.getWarehousepacker().setPassword(password);*/
                }
                dialog.dismiss();
            }
            //dialog.dismiss();
        });
        dialog.show();
    }



}