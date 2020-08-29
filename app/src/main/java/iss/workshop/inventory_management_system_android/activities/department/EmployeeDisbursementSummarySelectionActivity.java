package iss.workshop.inventory_management_system_android.activities.department;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.activities.BaseActivity;

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