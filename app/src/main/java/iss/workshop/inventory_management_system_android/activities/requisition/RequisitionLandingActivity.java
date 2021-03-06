package iss.workshop.inventory_management_system_android.activities.requisition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;
import iss.workshop.inventory_management_system_android.helper.SharePreferenceHelper;

public class RequisitionLandingActivity extends BaseActivity
        implements View.OnClickListener {

    private static final String TAG = "ReqLandingActivity";
    SharePreferenceHelper sharePreferenceHelper;
    String empType;
    View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().inflate(R.layout.activity_requsition_landing, frameLayout);
        txt_menuTitle.setText("DASHBOARD");

        sharePreferenceHelper = new SharePreferenceHelper(this);
        empType = sharePreferenceHelper.getUserRole();

        Button pending_req_btn = findViewById(R.id.view_pending_req);
        if (pending_req_btn != null)
            pending_req_btn.setOnClickListener(this);

        Button processed_req_btn = findViewById(R.id.view_processed_req);
        if (processed_req_btn != null)
            processed_req_btn.setOnClickListener(this);

        //Button create_req_btn = findViewById(R.id.create_req);

        /*if(empType.equals("Employee"))
        {
            if(create_req_btn != null)
                create_req_btn.setOnClickListener(this);
        }
        else{
            create_req_btn.setVisibility(View.GONE);
        }*/
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.view_pending_req || id == R.id.view_processed_req) {
            Intent intent = new Intent(this, RequisitionSummaryActivity.class);
            intent.putExtra("btnId", id);
            startActivity(intent);
        }
        /*else if(id == R.id.create_req)
        {
            Intent intent = new Intent(this, ApplyRequistionActivity.class);
            startActivity(intent);
        }*/

    }
}