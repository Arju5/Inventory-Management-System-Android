package iss.workshop.inventory_management_system_android.activities.requisition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.activities.requisition.RequisitionLandingActivity;
import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.adapters.requisition.ReqProductAdapter;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.model.Employee;
import iss.workshop.inventory_management_system_android.model.Product;
import iss.workshop.inventory_management_system_android.model.RequisitionForm;
import iss.workshop.inventory_management_system_android.model.RequisitionFormsProduct;
import iss.workshop.inventory_management_system_android.viewmodel.RequisitionSummaryViewModel;
import iss.workshop.inventory_management_system_android.viewmodel.RequisitionViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequisitionFormActivity extends BaseActivity implements View.OnClickListener {

    TextView rfcmt,rfcmt_label,headreply_label;
    EditText head_reply;
    Button btn_approve,btn_reject,btnCancel;
    private static final String TAG = "ReqFormActivity";
    private ServiceHelper.ApiService service;
    private ReqProductAdapter rfproductadapter;
    SharePreferenceHelper sharePreferenceHelper;
    ListView rfproductListview;
    int reqId;
    String username;
    private RequisitionViewModel rfViewmodel;
    List<RequisitionFormsProduct> reqproductlist;
    String headcomment;
    private RequisitionForm getrf;
    String Status;
    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().inflate(R.layout.activity_requistion_form, frameLayout);
        txt_menuTitle.setText("DASHBOARD");
        //Get UserName by sharePreference
        sharePreferenceHelper = new SharePreferenceHelper(this);
        username = sharePreferenceHelper.getUserName();

        service = ServiceHelper.getClient(this);
        rfproductadapter = new ReqProductAdapter(RequisitionFormActivity.this, R.layout.reqform_row,reqproductlist);
        rfproductListview = rootView.findViewById(R.id.reqProductlistView);

        //get req Id of chosen item from req summary
        Intent intent = getIntent();
        String requisitionId = intent.getStringExtra("reqId");
        reqId = Integer.parseInt(requisitionId);
        getReqByid();

        ViewApprovalRF();

        //Buttons
        btn_approve = (Button) rootView.findViewById(R.id.btn_approve);
        btn_reject = (Button) rootView.findViewById(R.id.btn_reject);
        btnCancel = (Button)rootView.findViewById(R.id.cancelBtn);
        if(btnCancel != null){
            btnCancel.setOnClickListener(this);
        }

        btn_approve.setOnClickListener(this);

        btn_reject.setOnClickListener(this);

    }

    private void getReqByid() {
        Call<RequisitionForm> callrf = service.getReqByid(reqId);
        callrf.enqueue(new Callback<RequisitionForm>() {
            @Override
            public void onResponse(Call<RequisitionForm> call, Response<RequisitionForm> response) {
                if(response.isSuccessful()) {
                    RequisitionForm rf = response.body();
                    getrf = rf;
                }
            }

            @Override
            public void onFailure(Call<RequisitionForm> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    private void Approve(RequisitionViewModel rfViewmodel) {
        Call<RequisitionViewModel> callapproveRF = service.Approve(rfViewmodel);
        callapproveRF.enqueue(new Callback<RequisitionViewModel>() {
            @Override
            public void onResponse(Call<RequisitionViewModel> call, Response<RequisitionViewModel> response) {

                if (response.isSuccessful()) {
                    RequisitionViewModel rfViewmodel = response.body();

                    if(rfViewmodel != null){
                        Toast.makeText(getApplicationContext(), "Approve Requisition successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RequisitionFormActivity.this, RequisitionLandingActivity.class);
                        intent.putExtra("empType",sharePreferenceHelper.getUserRole());
                        startActivity(intent);
                    }
                } else {
                    Log.e(TAG, "onResponse APPROVE: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<RequisitionViewModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
    private void Reject(RequisitionViewModel rfViewmodel) {
        Call<RequisitionViewModel> callrejectRF = service.Reject(rfViewmodel);
        callrejectRF.enqueue(new Callback<RequisitionViewModel>() {
            @Override
            public void onResponse(Call<RequisitionViewModel> call, Response<RequisitionViewModel> response) {

                if (response.isSuccessful()) {
                    RequisitionViewModel rfViewmodel = response.body();

                    if(rfViewmodel != null){
                        Toast.makeText(getApplicationContext(), "Reject Requisition successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RequisitionFormActivity.this, RequisitionLandingActivity.class);
                        intent.putExtra("empType",sharePreferenceHelper.getUserRole());
                        startActivity(intent);
                    }
                } else {
                    Log.e(TAG, "onResponse REJECT: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<RequisitionViewModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
    private void ViewApprovalRF() {
        rfproductadapter.clear();

        Call<RequisitionViewModel> callrfdetails = service.ViewApprovalRF(reqId);
        callrfdetails.enqueue(new Callback<RequisitionViewModel>() {
            @Override
            public void onResponse(Call<RequisitionViewModel> call, Response<RequisitionViewModel> response) {
                if (response.isSuccessful()) {

                    RequisitionViewModel rfdetails = response.body();
                    TextView req_form_code = rootView.findViewById(R.id.req_form_code);
                    Log.d(TAG,"rf code in viewapprovalrf method::::"+rfdetails.requisitionForm.rfCode);
                    req_form_code.setText(rfdetails.requisitionForm.rfCode);

                    TextView req_form_status = rootView.findViewById(R.id.req_form_status);
                    switch (rfdetails.requisitionForm.rfStatus) {
                        case 0:
                            req_form_status.setText("Submitted");
                            Status = "Submitted";
                            break;
                        case 1:
                            req_form_status.setText("Approved");
                            Status = "Approved";
                            break;
                        case 2:
                            req_form_status.setText("Completed");
                            Status = "Completed";
                            break;
                        case 3:
                            req_form_status.setText("Not Completed");
                            Status = "Not Completed";
                            break;
                        case 4:
                            req_form_status.setText("Cancelled");
                            Status = "Cancelled";
                            break;
                        case 5:
                            req_form_status.setText("Rejected");
                            Status = "Rejected";
                            break;
                        case 6:
                            req_form_status.setText("Ongoing");
                            Status = "Ongoing";
                            break;
                    }

                    //list of products
                    rfproductadapter.setrfProductList(rfdetails.rfpList);
                    rfproductadapter.setrfStatus(Status);
                    if (rfproductListview != null) {
                        rfproductListview.setAdapter(rfproductadapter);
                    } else {
                        Log.d(TAG, "list view is null");
                    }
                    //comment boxes
                    //rfcmt raised by employee
                    rfcmt_label = (TextView)rootView.findViewById(R.id.rf_cmt_label);
                    rfcmt = (TextView)rootView.findViewById(R.id.req_comment_txt);
                    if(rfcmt!=null)
                    {
                        if(rfdetails.requisitionForm.rfComments == null){
                            rfcmt_label.setVisibility(View.GONE);
                            rfcmt.setVisibility(View.GONE);
                        }
                        else {
                            rfcmt.setText(rfdetails.requisitionForm.rfComments);
                        }
                    }
                    //cmt raised by dept head
                    headreply_label=(TextView)rootView.findViewById(R.id.head_reply_label);
                    head_reply = (EditText)rootView.findViewById(R.id.head_reply);
                    if(head_reply!=null){
                        //emp cannot edit cmt & depthead cannot edit except submitted stage
                        if(sharePreferenceHelper.getUserRole().equals("Employee") || Status!="Submitted"){
                            head_reply.setEnabled(false);
                        }
                        //only show when there is cmt
                        if(rfdetails.requisitionForm.rfHeadReply == null && Status!="Submitted"){
                            headreply_label.setVisibility(View.GONE);
                            head_reply.setVisibility(View.GONE);
                        }
                        else if(rfdetails.requisitionForm.rfHeadReply != null){
                            head_reply.setText(rfdetails.requisitionForm.rfHeadReply);
                        }
                        headcomment = head_reply.getText().toString();
                        Log.d(TAG,"head reply in VM:: "+ headcomment);
                        rfViewmodel = new RequisitionViewModel();
                        if(!headcomment.isEmpty()){
                            rfViewmodel.setRfHeadReply(headcomment);
                        }
                        else{
                            Log.d(TAG,"headcomment is empty");
                        }
                    }

                    if(sharePreferenceHelper.getUserRole().equals("Employee")||sharePreferenceHelper.getUserRole().equals("Department Representative")|| Status!="Submitted"){
                        btn_approve.setVisibility(View.GONE);
                        btn_reject.setVisibility(View.GONE);
                    }
                    if( sharePreferenceHelper.getUserRole().equals("Department Head") || sharePreferenceHelper.getUserRole().equals("Temporary Department Head") || Status!= "Submitted" ){
                        btnCancel.setVisibility(View.GONE);
                    }
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
        if(btnid == R.id.btn_approve){
            List<RequisitionFormsProduct> reqProdlist = new ArrayList<>();
            //search selected product list
            for (int i = 0; i < rfproductadapter.reqproductlist.size(); i++){

                reqProdlist.add( rfproductadapter.reqproductlist.get(i));
                Log.d(TAG,"approve req qty ::::"+rfproductadapter.reqproductlist.get(i).productApproved);
            }

            rfViewmodel = new RequisitionViewModel();
            rfViewmodel.requisitionForm = getrf;
            rfViewmodel.setRfpList(reqProdlist); //set the requisition prod list with approved qty

            Employee emp = new Employee();
            emp.setId(sharePreferenceHelper.getuserId());
            rfViewmodel.setEmployee(emp);


            if(headcomment!="")
            { rfViewmodel.rfHeadReply = headcomment;  }

            Approve(rfViewmodel);
        }
        else if(btnid == R.id.btn_reject){
            rfViewmodel = new RequisitionViewModel();
            rfViewmodel.requisitionForm = getrf;

            Employee emp = new Employee();
            emp.setId(sharePreferenceHelper.getuserId());
            rfViewmodel.setEmployee(emp);

            if(headcomment!="")
            { rfViewmodel.rfHeadReply = headcomment;}
            Reject(rfViewmodel);
        }
        else if(btnid == R.id.cancelBtn) {
            Cancel();
        }

    }
    private void Cancel() {
        Call<RequisitionForm> callcancel = service.Cancel(reqId);

        callcancel.enqueue(new Callback<RequisitionForm>() {
            @Override
            public void onResponse(Call<RequisitionForm> call, Response<RequisitionForm> response) {
                if(response.isSuccessful()) {
                    RequisitionForm rf = response.body();
                    if(rf != null)
                    {
                        Toast.makeText(getApplicationContext(), "Cancel Requisition successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RequisitionFormActivity.this, RequisitionLandingActivity.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<RequisitionForm> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
}
