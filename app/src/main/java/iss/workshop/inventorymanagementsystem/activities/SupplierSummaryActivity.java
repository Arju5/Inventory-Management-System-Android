package iss.workshop.inventorymanagementsystem.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import iss.workshop.inventorymanagementsystem.R;
import iss.workshop.inventorymanagementsystem.adapter.SupplierSummaryAdapter;
import iss.workshop.inventorymanagementsystem.helper.ServiceHelper;
import iss.workshop.inventorymanagementsystem.model.Supplier;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupplierSummaryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "SupplierSummaryActivity";

    private ServiceHelper.ApiService service;
    private SupplierSummaryAdapter supplierSummaryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);
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