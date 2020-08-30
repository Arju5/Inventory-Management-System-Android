package iss.workshop.inventory_management_system_android.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.stationery.SF_StationeryRetrievalSummaryActivity;
import iss.workshop.inventory_management_system_android.model.StationeryRetrieval;

public class SRSummaryAdapter extends ArrayAdapter {
    private Context context;
    private static final String TAG = "SRSummaryAdapter";
    List<StationeryRetrieval> stationeryRetrievalList;

    public SRSummaryAdapter (Context context, int resId) {
        super(context, resId);
        this.context = context;
        Log.d(TAG, "context = " + context);
        Log.d(TAG, "resId = " + resId);

    }

    public void addSR (List<StationeryRetrieval> stationeryRetrievalList){
        this.stationeryRetrievalList = stationeryRetrievalList;
        for(StationeryRetrieval stationeryRetrieval : stationeryRetrievalList)
            add(stationeryRetrieval);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(SF_StationeryRetrievalSummaryActivity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.sr_summary_row, null);

        TextView srId = view.findViewById(R.id.sfId);
        srId.setText(String.valueOf(stationeryRetrievalList.get(position).id));
        TextView srcode = view.findViewById(R.id.srCode);
        srcode.setText(stationeryRetrievalList.get(position).srCode);
        TextView srdate = view.findViewById(R.id.srDate);
        srdate.setText(stationeryRetrievalList.get(position).srDate);


        return view;
    }
}
