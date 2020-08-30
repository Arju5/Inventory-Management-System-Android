package iss.workshop.inventorymanagementsystem.activities.stationery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import iss.workshop.inventorymanagementsystem.R;
import iss.workshop.inventorymanagementsystem.activities.BaseActivity;

public class SF_StationeryRetrievalSummaryActivity extends BaseActivity {
    private static final String TAG = "SF_SRSummaryActivity";

    /*private ServiceHelper.ApiService service;
    private SRSummaryAdapter srSummaryAdapter;*/

    //for Back Button
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sf_stationery_retrieval_summary);

        View rootView = getLayoutInflater().inflate(R.layout.activity_sf_stationery_retrieval_summary, frameLayout);
        txt_menuTitle.setText("SRF Summary");

        //for Back Button
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button openSRbutton = (Button)rootView.findViewById(R.id.opensr);
        Button pendingSRbutton = (Button)rootView.findViewById(R.id.pendingsr);
        Button completedSRbutton = (Button)rootView.findViewById(R.id.completedsr);
        Button createSRbutton = (Button)rootView.findViewById(R.id.createsr);

        openSRbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onResponse: OpenSRSummary &&&&&& : ");
                Intent intent = new Intent(SF_StationeryRetrievalSummaryActivity.this, SF_StationeryRetrievalFormSummaryListViewActivity.class);
                intent.putExtra("Status", "OPEN");
                startActivity(intent);
            }
        });

        pendingSRbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onResponse: PendingSRSummary &&&&&& : ");
                Intent intent = new Intent(SF_StationeryRetrievalSummaryActivity.this, SF_StationeryRetrievalFormSummaryListViewActivity.class);
                intent.putExtra("Status", "PENDING");
                startActivity(intent);
            }
        });

        completedSRbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e(TAG, "onResponse: CompletedSRSummary &&&&&& : ");
                Intent intent = new Intent(SF_StationeryRetrievalSummaryActivity.this, SF_StationeryRetrievalFormSummaryListViewActivity.class);
                intent.putExtra("Status", "COMPLETED");
                startActivity(intent);
            }
        });

        createSRbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SF_StationeryRetrievalSummaryActivity.this,SF_SRFActivity.class);
                startActivity(intent);
            }
        });
    }




}