package iss.workshop.inventory_management_system_android.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.model.Product;

public class ProductCatalogueDetails extends BaseActivity {

    TextView txtproduct;
    TextView txtuom;
    TextView txtinventory;
    TextView txtlevel;
    TextView txtqty;
    TextView txtlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_product_catalogue_details);

        View rootView = getLayoutInflater().inflate(R.layout.activity_product_catalogue_details, frameLayout);
        txt_menuTitle.setText("Product Details");

        Product product = (Product) getIntent().getSerializableExtra("productdetails");

        txtproduct = findViewById(R.id.txtproduct);
        txtuom = findViewById(R.id.txtuom);
        txtinventory = findViewById(R.id.txtinventory);
        txtlevel = findViewById(R.id.txtlevel);
        txtqty = findViewById(R.id.txtqty);
        txtlocation = findViewById(R.id.txtlocation);

        txtproduct.setText(product.getProductName());
        txtuom.setText(product.getUnits());
        txtinventory.setText(String.valueOf(product.getInventoryQuantity()));
        txtlevel.setText(String.valueOf(product.getReorderLevel()));
        txtqty.setText(String.valueOf(product.getReorderQuantity()));
        txtlocation.setText(product.getInventoryLocation());
    }
}