package iss.workshop.inventory_management_system_android.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.disbursement.DisbursementFormActivity;
import iss.workshop.inventory_management_system_android.activities.disbursement.DisbursementSummaryStatusSelectionActivity;
import iss.workshop.inventory_management_system_android.activities.requisition.ApplyRequistionActivity;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "Base Activity";
    protected FrameLayout frameLayout;
    private Context context;
    private Toolbar toolbar;
    public TextView txt_menuTitle, txt_username, txt_email, txt_change_pass;
    public ImageView img_menuOption, image_profile;
    SharePreferenceHelper sharePreferenceHelper;
    private DrawerLayout drawer;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        context=this;
        initView();
        frameLayout = (FrameLayout) findViewById(R.id.container);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        sharePreferenceHelper = new SharePreferenceHelper(this);
        if(sharePreferenceHelper.getUserRole().equals("Employee")) {
            //setText(sharePreferenceHelper.getUserName().toUpperCase());
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.base_drawer);
        } else if (sharePreferenceHelper.getUserRole().equals("Store Clerk")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.storeclerk_drawer);
        } else if (sharePreferenceHelper.getUserRole().equals("Department Head")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.dephead_drawer);
        } else if (sharePreferenceHelper.getUserRole().equals("Department Representative")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.deptrep_drawer);
        } else if (sharePreferenceHelper.getUserRole().equals("Temporary Department Head")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.tempdepthead_drawer);
        } else if (sharePreferenceHelper.getUserRole().equals("Store Manager")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.storemanager_drawer);
        } else if (sharePreferenceHelper.getUserRole().equals("Store Supervisor")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.storesupervisor_drawer);
        }

        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) BaseActivity.this);
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        txt_menuTitle = findViewById(R.id.txt_menuTitle);
        img_menuOption = findViewById(R.id.img_menuOption);
        img_menuOption.setBackgroundResource(R.drawable.ic_menu);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        txt_username = (TextView) headerview.findViewById(R.id.nav_header_empName);
        sharePreferenceHelper = new SharePreferenceHelper(BaseActivity.this);
        String username = sharePreferenceHelper.getUserName();
        Log.e(TAG, sharePreferenceHelper.getUserName());
        txt_username.setText(sharePreferenceHelper.getUserName().toUpperCase());
        txt_email = headerview.findViewById(R.id.nav_header_textView2);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener)this);
        img_menuOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        NavigationView nv= (NavigationView) findViewById(R.id.nav_view);
        Menu m=nv.getMenu();

        int id = item.getItemId();

        /*Store Clerk Menu Item Expansion*/
        if(id == R.id.storeclerk_Directory){
            boolean b=!m.findItem(R.id.storeclerk_DepartmentSummary).isVisible();
            //setting submenus visible state
            m.findItem(R.id.storeclerk_DepartmentSummary).setVisible(b);
            m.findItem(R.id.storeclerk_SupplierSummary).setVisible(b);
            return true;
        } else if (id == R.id.storeclerk_Inventory) {
            boolean b=!m.findItem(R.id.storeclerk_ProductCatalogue).isVisible();
            m.findItem(R.id.storeclerk_ProductCatalogue).setVisible(b);
            m.findItem(R.id.storeclerk_UpdateInventory).setVisible(b);
            m.findItem(R.id.storeclerk_InventorySummary).setVisible(b);
            m.findItem(R.id.storeclerk_InventoryTransaction).setVisible(b);
            return true;
        } else if (id == R.id.storeclerk_Requisitions) {
            boolean b=!m.findItem(R.id.storeclerk_DisbursementSummary).isVisible();
            m.findItem(R.id.storeclerk_DisbursementSummary).setVisible(b);
            m.findItem(R.id.storeclerk_StationeryRetrievalSummary).setVisible(b);
            m.findItem(R.id.storeclerk_CreateStationeryRetrieval).setVisible(b);
            m.findItem(R.id.storeclerk_CreateDisbursement).setVisible(b);
            return true;
        } else if(id == R.id.logout) {
            sharePreferenceHelper.logoutSharePreference();
            Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.base_nav_createRequisition && sharePreferenceHelper.getUserRole().equals("Employee")) {
            Toast.makeText(context, "Create Requisition", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(BaseActivity.this, ApplyRequistionActivity.class);
            startActivity(intent);
        } else if (id == R.id.base_nav_requisitionSummary && sharePreferenceHelper.getUserRole().equals("Employee")) {
            Toast.makeText(context, "Requisition Summary", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.storeclerk_DisbursementSummary && sharePreferenceHelper.getUserRole().equals("Store Clerk")) {
            Toast.makeText(context, "Disbursement Summary", Toast.LENGTH_SHORT).show();
            Intent intent   = new Intent(this, DisbursementSummaryStatusSelectionActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.storeclerk_CreateDisbursement && sharePreferenceHelper.getUserRole().equals("Store Clerk")) {
            Toast.makeText(context, "Create Disbursement", Toast.LENGTH_SHORT).show();
            Intent intent   = new Intent(this, DisbursementFormActivity.class);
            startActivity(intent);
            finish();
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}