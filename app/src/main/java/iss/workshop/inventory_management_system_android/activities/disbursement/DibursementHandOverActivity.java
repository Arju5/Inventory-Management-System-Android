package iss.workshop.inventory_management_system_android.activities.disbursement;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.activities.DashboardActivity;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
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
        mstoreclerknamefordelivery = findViewById(R.id.storeclerknamefordelivery);
        mdf_depRepName = findViewById(R.id.df_depRepName);
        mdf_dfcode = findViewById(R.id.df_dfcode);
        mdf_collectionpointName = findViewById(R.id.df_collectionpointName);
        mdf_Status = findViewById(R.id.df_DisbursementStatus);
        mdf_DeliveryDateTime = findViewById(R.id.df_DeliveryDateTime);

        mstoreclerknamefordelivery.setText(disbursementViewModel.disbursementForm.storeClerk.firstname + " " + disbursementViewModel.disbursementForm.storeClerk.lastname);
        mdf_depRepName.setText(disbursementViewModel.disbursementForm.deptRep.firstname + " " + disbursementViewModel.disbursementForm.deptRep.lastname);
        mdf_dfcode.setText(disbursementViewModel.disbursementForm.dfCode);
        mdf_collectionpointName.setText(disbursementViewModel.disbursementForm.collectionPoint.collectionName);
        mdf_Status.setText(String.valueOf(disbursementViewModel.disbursementForm.dfStatus));
        mdf_DeliveryDateTime.setText(disbursementViewModel.disbursementForm.dfDeliveryDate);

        passStoreClerk = findViewById(R.id.handOver_passwordclerk);
        passDepRep = findViewById(R.id.handOver_passwordDepRep);
        btn_SigninClerk = (Button) findViewById(R.id.handOver_signForClerk);
        btn_SigninDepRep = (Button) findViewById(R.id.handOver_signForDepRep);
        btn_Delivered = (Button) findViewById(R.id.handOver_Deliver);

        btn_SigninClerk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog(1);
            }
        });
        btn_SigninDepRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog(2);
            }
        });

        btn_Delivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                                Intent intent = new Intent(DibursementHandOverActivity.this, DashboardActivity.class);
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
                    btn_SigninClerk.setBackgroundColor(R.color.colorGreen);
                    passStoreClerk.setText(String.valueOf(password));

                }
                else{
                    btn_SigninDepRep.setText(name.toString());
                    btn_SigninDepRep.setBackgroundColor(R.color.colorGreen);
                    /*//pswwarehouse.setText(password.toString());
                    srform.getWarehousepacker().setUsername(name);
                    srform.getWarehousepacker().setPassword(password);*/
                }
                dialog.dismiss();
            }
            //dialog.dismiss();
        });
        dialog.show();
    }



}