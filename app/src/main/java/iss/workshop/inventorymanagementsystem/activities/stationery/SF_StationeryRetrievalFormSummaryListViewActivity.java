package iss.workshop.inventorymanagementsystem.activities.stationery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import iss.workshop.inventorymanagementsystem.R;
import iss.workshop.inventorymanagementsystem.activities.BaseActivity;
import iss.workshop.inventorymanagementsystem.adapter.SRSummaryAdapter;
import iss.workshop.inventorymanagementsystem.helper.ServiceHelper;
import iss.workshop.inventorymanagementsystem.viewmodel.StationeryRetrievalSummaryViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SF_StationeryRetrievalFormSummaryListViewActivity extends BaseActivity
        implements AdapterView.OnItemClickListener {
    private static final String TAG = "SF_SRSummaryActivity";

    private ServiceHelper.ApiService service;
    private SRSummaryAdapter srSummaryAdapter;
    String status;
    ListView srListView;

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
        //setContentView(R.layout.srsummary_listview);

        View rootView = getLayoutInflater().inflate(R.layout.srsummary_listview, frameLayout);
        txt_menuTitle.setText("Create SRF");

        //for Back Button
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        service = ServiceHelper.getClient(this);
        srListView = rootView.findViewById(R.id.srsummarylistview);

        srSummaryAdapter = new SRSummaryAdapter(SF_StationeryRetrievalFormSummaryListViewActivity.this, R.layout.sr_summary_row);

        Intent intent = getIntent();
        Log.e(TAG, "onCreate: Status :" + intent.getStringExtra("Status") );
        status = intent.getStringExtra("Status");
        getSRSummary(status);
    }

    private void getSRSummary(String status) {
        srSummaryAdapter.clear();
        Call<StationeryRetrievalSummaryViewModel> stationeryRetrievalSummaryViewModelCall;
        if(status.equals("OPEN")){
            Log.e(TAG, "getSRSummary: "+ "HI HI" );
            stationeryRetrievalSummaryViewModelCall = service.getOpenSRSummary();}
        else if (status.equals("PENDING")){
            Log.e(TAG, "getSRSummary: "+ "HI HI" );
           stationeryRetrievalSummaryViewModelCall = service.getPendingSRSummary();}
        else
            stationeryRetrievalSummaryViewModelCall = service.getCompletedSRSummary();

        stationeryRetrievalSummaryViewModelCall.enqueue(new Callback<StationeryRetrievalSummaryViewModel>() {
            @Override
            public void onResponse(Call<StationeryRetrievalSummaryViewModel> call, Response<StationeryRetrievalSummaryViewModel> response) {
                if(response.isSuccessful()) {
                    StationeryRetrievalSummaryViewModel stationeryRetrievalSummaryViewModel = response.body();
                    srListView.setAdapter(srSummaryAdapter);
                    srListView.setOnItemClickListener(SF_StationeryRetrievalFormSummaryListViewActivity.this);
                    if (stationeryRetrievalSummaryViewModel.getPendingSROpens() != null )
                        srSummaryAdapter.addSR(stationeryRetrievalSummaryViewModel.getPendingSROpens());
                    else if (stationeryRetrievalSummaryViewModel.getPendingSRAssignments() != null)
                        srSummaryAdapter.addSR((stationeryRetrievalSummaryViewModel.getPendingSRAssignments()));
                    else
                        srSummaryAdapter.addSR(stationeryRetrievalSummaryViewModel.getCompletedSRs());

                }
            }

            @Override
            public void onFailure(Call<StationeryRetrievalSummaryViewModel> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView sf_id = view.findViewById(R.id.sfId);

        Log.e(TAG,"from summary to form sf-code sending :"+ sf_id.getText().toString());

        if(status.equals("OPEN")){
            Intent intent = new Intent(this, SF_SRFOpenSRFActivity.class);

            intent.putExtra("SFId", sf_id.getText().toString());
            startActivity(intent);
        }
        else if(status.equals("PENDING")){
            Intent intent = new Intent(this, SF_SRFPendingSRFActivity.class);

            intent.putExtra("SFId", sf_id.getText().toString());
            startActivity(intent);
        }
        else if(status.equals("COMPLETED")){
            Intent intent = new Intent(this, SF_SRFCompletedSRFActivity.class);

            intent.putExtra("SFId", sf_id.getText().toString());
            startActivity(intent);
        }
        else{

        }

    }
}
