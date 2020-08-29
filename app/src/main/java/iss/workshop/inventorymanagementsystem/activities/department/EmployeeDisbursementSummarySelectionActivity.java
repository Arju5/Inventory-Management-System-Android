package iss.workshop.inventorymanagementsystem.activities.department;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import iss.workshop.inventorymanagementsystem.R;
import iss.workshop.inventorymanagementsystem.activities.BaseActivity;

public class EmployeeDisbursementSummarySelectionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(R.layout.activity_employee_disbursement_summary_selection, frameLayout);
        txt_menuTitle.setText("REQUISITION SUMMARY SELECTION");
        Button memp_PendingRequisitions = (Button)rootView.findViewById(R.id.emp_PendingRequisitions);
        Button memp_ApprovedRequisitions = (Button)rootView.findViewById(R.id.emp_ApprovedRequisitions);
        Button memp_CompletedRequisitions = (Button)rootView.findViewById(R.id.emp_CompletedRequisitions);
    }
}