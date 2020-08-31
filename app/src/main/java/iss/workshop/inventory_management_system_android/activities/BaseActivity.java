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
import iss.workshop.inventory_management_system_android.activities.delegation.AssignDeptRepActivity;
import iss.workshop.inventory_management_system_android.activities.delegation.DelegateDepHeadActivity;
import iss.workshop.inventory_management_system_android.activities.delegation.DelegationSummaryActivity;
import iss.workshop.inventory_management_system_android.activities.disbursement.DisbursementSummaryStatusSelectionActivity;
import iss.workshop.inventory_management_system_android.activities.requisition.ApplyRequistionActivity;
import iss.workshop.inventory_management_system_android.activities.requisition.RequisitionFormActivity;
import iss.workshop.inventory_management_system_android.activities.requisition.RequisitionLandingActivity;
import iss.workshop.inventory_management_system_android.activities.requisition.RequisitionSummaryActivity;
import iss.workshop.inventory_management_system_android.activities.stationery.SF_SRFActivity;
import iss.workshop.inventory_management_system_android.activities.stationery.SF_StationeryRetrievalSummaryActivity;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;
import iss.workshop.inventory_management_system_android.model.Department;

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
    public String getUserRole;

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
        getUserRole = sharePreferenceHelper.getUserRole();
        if(getUserRole.equals("Employee")) {
            //setText(sharePreferenceHelper.getUserName().toUpperCase());
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.base_drawer);
        } else if (getUserRole.equals("Store Clerk")||getUserRole.equals("Store Manager")||getUserRole.equals("Store Supervisor")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.storeclerk_drawer);
        } else if (getUserRole.equals("Department Head")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.dephead_drawer);
        } else if (getUserRole.equals("Department Representative")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.deptrep_drawer);
        } else if (getUserRole.equals("Temporary Department Head")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.tempdepthead_drawer);
        } else if (getUserRole.equals("Store Manager")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.storemanager_drawer);
        } else if (getUserRole.equals("Store Supervisor")) {
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

<<<<<<<<< Temporary merge branch 1
        /*Store Clerk Menu Item Expansion*/
        if(id == R.id.storeclerk_Directory){
=========
        /*Store side Menu Item Expansion*/
        if (id == R.id.base_nav_products && getUserRole.equals("Store Clerk")||getUserRole.equals("Store Supervisor")||getUserRole.equals("Store Manager")) {
            boolean b=!m.findItem(R.id.base_nav_product_catalogue).isVisible();
            m.findItem(R.id.base_nav_product_catalogue).setVisible(b);
            m.findItem(R.id.storeclerk_InventorySummary).setVisible(b);
            return true;
        } else if(id == R.id.storeclerk_Directory){
            boolean b=!m.findItem(R.id.storeclerk_DepartmentSummary).isVisible();
            //setting submenus visible state
            m.findItem(R.id.storeclerk_DepartmentSummary).setVisible(b);
            m.findItem(R.id.storeclerk_SupplierSummary).setVisible(b);
            return true;
        } else if (id == R.id.storeclerk_forms) {
            boolean b=!m.findItem(R.id.storeclerk_StationeryRetrievalSummary).isVisible();
            m.findItem(R.id.storeclerk_StationeryRetrievalSummary).setVisible(b);
            m.findItem(R.id.storeclerk_DisbursementSummary).setVisible(b);
            m.findItem(R.id.storeclerk_CreateStationeryRetrieval).setVisible(b);
            m.findItem(R.id.storeclerk_CreateDisbursement).setVisible(b);
            return true;
        }
        /*Store side Menu Item Expansion ends*/
        /*Dept side Menu Item Expansion starts*/
        else if (id == R.id.base_nav_products && (getUserRole.equals("Employee")||getUserRole.equals("Department Head")||getUserRole.equals("Department Representative"))) {
            boolean b=!m.findItem(R.id.base_nav_product_catalogue).isVisible();
            m.findItem(R.id.base_nav_product_catalogue).setVisible(b);
            return true;
        }
        else if (id == R.id.base_nav_forms && getUserRole.equals("Department Head")) {
            boolean b=!m.findItem(R.id.base_nav_requisitionSummary).isVisible();
            m.findItem(R.id.base_nav_requisitionSummary).setVisible(b);
            return true;
        }
        else if (id == R.id.base_nav_forms && getUserRole.equals("Employee")) {
            boolean b=!m.findItem(R.id.base_nav_requisitionSummary).isVisible();
            m.findItem(R.id.base_nav_requisitionSummary).setVisible(b);
            m.findItem(R.id.base_nav_applyrequisitions).setVisible(b);
            return true;
        }
<<<<<<<<< Temporary merge branch 1
=========
        else if (id == R.id.base_nav_forms && getUserRole.equals("Department Representative")) {
            boolean b=!m.findItem(R.id.base_nav_requisitionSummary).isVisible();
            m.findItem(R.id.base_nav_requisitionSummary).setVisible(b);
            m.findItem(R.id.base_nav_applyrequisitions).setVisible(b);
            m.findItem(R.id.base_nav_disbursements).setVisible(b);
            return true;
        }
        else if(id==R.id.dephead_delegations && getUserRole.equals("Department Head")){
            boolean b=!m.findItem(R.id.dephead_delegationSummary).isVisible();
            m.findItem(R.id.dephead_delegationSummary).setVisible(b);
            m.findItem(R.id.dephead_delegateDepRep).setVisible(b);
            m.findItem(R.id.dephead_DelegateDepHead).setVisible(b);
            return true;
        }
        else if(id==R.id.dephead_delegations && getUserRole.equals("Temporary Department Head")){
            boolean b=!m.findItem(R.id.dephead_delegationSummary).isVisible();
            m.findItem(R.id.dephead_delegationSummary).setVisible(b);
            m.findItem(R.id.dephead_delegateDepRep).setVisible(b);
            return true;
        }
        /*Dept side Menu Item Expansion ends*/
        /*------*/


        if(id == R.id.logout) {
            sharePreferenceHelper.logoutSharePreference();
            Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.base_nav_product_catalogue) {
            Toast.makeText(context, "Product Catalogue Activity", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.storeclerk_InventorySummary) {
            Toast.makeText(context, "Inventory Summary Activity", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.storeclerk_DepartmentSummary ) {
            Toast.makeText(context, "Department Summary", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(BaseActivity.this, DepartmentSummaryActivity.class);
            startActivity(intent);
        }else if (id == R.id.storeclerk_SupplierSummary ) {
            Toast.makeText(context, "Supplier Summary", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(BaseActivity.this, SupplierSummaryActivity.class);
            startActivity(intent);
        }else if(id == R.id.storeclerk_StationeryRetrievalSummary ){
            Intent intent = new Intent(BaseActivity.this, SF_StationeryRetrievalSummaryActivity.class);
            startActivity(intent);
        }else if(id == R.id.storeclerk_CreateStationeryRetrieval ){
            Intent intent = new Intent(BaseActivity.this, SF_SRFActivity.class);
            startActivity(intent);
        }else if (id == R.id.storeclerk_DisbursementSummary ) {
            Toast.makeText(context, "Disbursement Summary", Toast.LENGTH_SHORT).show();
            Intent intent   = new Intent(this, DisbursementSummaryStatusSelectionActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.storeclerk_CreateDisbursement) {
            Toast.makeText(context, "Create Disbursement", Toast.LENGTH_SHORT).show();
            //Intent intent   = new Intent(this, DisbursementFormActivity.class);
            //startActivity(intent);
            //finish();
        }
        /* Dept side */
        else if(id== R.id.base_nav_requisitionSummary ){
            Toast.makeText(context, "Requisition Summary", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, RequisitionLandingActivity.class);
            startActivity(intent);
        }else if(id== R.id.base_nav_applyrequisitions ){
            Toast.makeText(context, "Apply Requisition", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ApplyRequistionActivity.class);
            startActivity(intent);
        }else if(id== R.id.base_nav_disbursements ){
            Toast.makeText(context, "Disbursement Summary", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DisbursementSummaryStatusSelectionActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.dephead_delegationSummary) {
            Toast.makeText(context, "Delegation Summary", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(BaseActivity.this, DelegationSummaryActivity.class);
            startActivity(intent);
        } else if (id == R.id.dephead_delegateDepRep) {
            Toast.makeText(context, "Assign Department Representative", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AssignDeptRepActivity.class);
            startActivity(intent);
        }else if (id == R.id.dephead_DelegateDepHead) {
            Toast.makeText(context, "Delegate Department Head", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DelegateDepHeadActivity.class);
            startActivity(intent);
        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}