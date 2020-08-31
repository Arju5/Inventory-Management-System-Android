package iss.workshop.inventory_management_system_android.activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.adapters.ProductCatalogueAdapter;
import iss.workshop.inventory_management_system_android.helper.ServiceHelper;
import iss.workshop.inventory_management_system_android.model.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductCatalogue extends BaseActivity {

    private static final String TAG = "ProductCatalogue";


    RecyclerView rcv;

    private ProductCatalogueAdapter adapter;

    private ServiceHelper.ApiService service;
    private ArrayList<Product> pm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_product_catalogue);
        View rootView = getLayoutInflater().inflate(R.layout.activity_product_catalogue, frameLayout);
        txt_menuTitle.setText("PRODUCT CATALOGUE");

        adapter = new ProductCatalogueAdapter();

        rcv = findViewById(R.id.recycler);

        rcv.setAdapter(adapter);//Data source of recycler view
        rcv.setHasFixedSize(true);//recycler view => list view => fixed size
        rcv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));//or grid view => list (order)

        service = ServiceHelper.getClient(this);
        getProductList();
    }

    private void getProductList() {

        adapter.clear();//to clear adapter list

        Call<ArrayList<Product>> callproduct = service.getProductList();
        callproduct.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {

                if(response.isSuccessful()) {
                    ArrayList<Product> productList = response.body();
                    for(int i=0;i<productList.size();i++){
                        //System.out.println(productList.get(i).productName);
                        Log.d(TAG, "onResponse: product name = "+productList.get(i).productName);
                        adapter.add(productList.get(i));
                    }
                    rcv.setVisibility(View.GONE);
                    rcv.setVisibility(View.VISIBLE);
                } else {
                    //System.out.println(response.errorBody());
                    Log.e(TAG, "onResponse: "+ response.message() );
                }
            }


            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
            }
        });
    }
}