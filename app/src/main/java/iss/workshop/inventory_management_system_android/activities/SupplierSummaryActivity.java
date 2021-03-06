package iss.workshop.inventory_management_system_android.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.adapters.SupplierSummaryAdapter;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.model.Supplier;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupplierSummaryActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "SupplierSummaryActivity";

    private ServiceHelper.ApiService service;
    private SupplierSummaryAdapter supplierSummaryAdapter;
    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().inflate(R.layout.activity_supplier, frameLayout);
        txt_menuTitle.setText("DASHBOARD");
        service = ServiceHelper.getClient(this);
        supplierSummaryAdapter = new SupplierSummaryAdapter(SupplierSummaryActivity.this, R.layout.supplierlist_row);
        getSupplierList();
    }

    private void getSupplierList() {
        supplierSummaryAdapter.clear();

        final ListView supplierListView = (ListView) findViewById(R.id.supplist);
        Call<ArrayList<Supplier>> callsupplierlist = service.getSupplierList();
        callsupplierlist.enqueue(new Callback<ArrayList<Supplier>>() {
            @Override
            public void onResponse(Call<ArrayList<Supplier>> call, Response<ArrayList<Supplier>> response) {
                if(response.isSuccessful()) {
                    ArrayList<Supplier> supplierList = response.body();
                    supplierSummaryAdapter.addSupplier(supplierList);
                    supplierListView.setAdapter(supplierSummaryAdapter);
                    supplierListView.setOnItemClickListener(SupplierSummaryActivity.this);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Supplier>> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> av,
                            View v, int pos, long id) {

        TextView textView = v.findViewById(R.id.suppliername);
        String expr = textView.getText().toString();

        Toast toast = Toast.makeText(this, expr, Toast.LENGTH_LONG);
        toast.show();
    }
}