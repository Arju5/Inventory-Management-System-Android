package iss.workshop.inventory_management_system_android.activities.podo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.activities.disbursement.DisbursementFormActivity;
import iss.workshop.inventory_management_system_android.adapters.DisbursementFormAdapter;
import iss.workshop.inventory_management_system_android.adapters.PurchaseOrderAdaptor;
import iss.workshop.inventory_management_system_android.adapters.requisition.ProductlistAdapter;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.model.Employee;
import iss.workshop.inventory_management_system_android.model.Product;
import iss.workshop.inventory_management_system_android.model.PurchaseOrderSupplierProduct;
import iss.workshop.inventory_management_system_android.model.StationeryRetrievalRequisitionForm;
import iss.workshop.inventory_management_system_android.viewmodel.DisbursementViewModel;
import iss.workshop.inventory_management_system_android.viewmodel.PODOViewModel;
import iss.workshop.inventory_management_system_android.viewmodel.RequisitionViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PurchaseOrderFormActivity extends BaseActivity
        implements  View.OnClickListener {

    private static final String TAG = "PurchaseOrderFormActivity";
    private ListView listviewPOSP;
    private ServiceHelper.ApiService service;
    private PODOViewModel podoViewModel;
    private PurchaseOrderAdaptor purchaseOrderAdaptor;
    SharePreferenceHelper sharePreferenceHelper;
    private Button btnSubmit;
    private TextView comments;


/*    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_purchase_order_form, frameLayout);
        txt_menuTitle.setText("CREATE PURCHASE ORDER");
        listviewPOSP = (ListView) findViewById(R.id.);
        comments = (TextView) findViewById(R.id.);
        btnSubmit = (Button) findViewById(R.id.);
        sharePreferenceHelper = new SharePreferenceHelper(this);
        service = ServiceHelper.getClient(this);
        getPOSPList();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<PurchaseOrderSupplierProduct> orderPOSTList = new ArrayList<>();
                for (PurchaseOrderSupplierProduct purchaseOrderSupplierProduct :
                        purchaseOrderAdaptor.poSPList) {
                    if (purchaseOrderSupplierProduct.getIsSelected())
                        orderPOSTList.add(purchaseOrderSupplierProduct);
                }
                podoViewModel = new PODOViewModel();
                podoViewModel.setPosList(orderPOSTList);
                podoViewModel.setComment(comments.getText().toString());
                Employee emp = new Employee();
                emp.setId(sharePreferenceHelper.getuserId());
                podoViewModel.setEmployee(emp);  // send the emp id
                for (PurchaseOrderSupplierProduct purchaseOrderSupplierProduct :
                        podoViewModel.posList)
                    Log.d(TAG,
                            "PO Supplier Product List - " + purchaseOrderSupplierProduct.supplierProduct.product.productCode);
                savePO(podoViewModel);
            }
        });

    }*/

/*    private void getPOSPList() {
        Call<ArrayList<PurchaseOrderSupplierProduct> poSPList> callPoSPList =
                service.getSRRFAssignedFormsToCreateDisbursement();
        callPoSPList.enqueue(new CallbackArrayList<PurchaseOrderSupplierProduct>>() {
            @Override
            public void onResponse( Call<ArrayList<PurchaseOrderSupplierProduct>> call,
                Response<ArrayList<PurchaseOrderSupplierProduct>> response) {
                if(response.isSuccessful()) {

                    poSPList = response.body();
                    Log.d(TAG, "SRRF List Size - " + poSPList.size());
                    purchaseOrderAdaptor = new PurchaseOrderAdaptor(PurchaseOrderFormActivity.this, poSPList);
                    purchaseOrderAdaptor.setPoSPList(poSPList);
                    if (listviewPOSP != null && poSPList.size() > 0) {
                        listviewPOSP.setAdapter(purchaseOrderAdaptor);
                    }else {
                        Log.e(TAG, " SRRF List is null");
                        Toast.makeText(PurchaseOrderFormActivity.this, "There is no current " +
                                        "Purchase Order",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PurchaseOrderSupplierProduct>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }*/

    private void createPO(){
    }

    private void savePO(){
    }


    @Override
    public void onClick(View view) {
    }
}