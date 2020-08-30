package iss.workshop.inventory_management_system_android.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.util.Date;

import iss.workshop.inventory_management_system_android.R;
import iss.workshop.inventory_management_system_android.helper.MyDateFormat;
import iss.workshop.inventory_management_system_android.model.RequisitionForm;

public class SF_PARequisitionListAdapter extends BaseAdapter {

    private static final String TAG = "SF_PARequisitionListAda";
    @Override
    protected RecyclerView.ViewHolder onCreateCustomViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sfitem_requisitionlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).bindProduct((RequisitionForm) getItemsList().get(position),position);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateCustomHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void onBindCustomHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView rfcode;
        TextView rfdate;
        TextView rfstatus;
        ImageView selectedReq;
        //LinearLayout linearLayout;
        MyDateFormat dateFormat;
        Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rfcode = itemView.findViewById(R.id.reqnumbervalue);
            rfdate = itemView.findViewById(R.id.date_value);
            rfstatus = itemView.findViewById(R.id.status);
            selectedReq = itemView.findViewById(R.id.checkreq);
            //linearLayout = itemView.findViewById(R.id.lineartest);
            dateFormat = new MyDateFormat();
            context = itemView.getContext();
        }

        public void bindProduct(final RequisitionForm model, final int position){
            //txtproduct.setText(model.productName);

            try {
                Date date = dateFormat.DATE_FORMAT_YMD_HMS.parse(dateFormat.removeTfromServerDate(model.getRfDate())) ;
                rfdate.setText(dateFormat.DATE_FORMAT_DMY_HMS_AAA.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
                rfdate.setText(model.getRfDate());
            }

            rfcode.setText(model.getRfCode());
            if(model.getRfStatus() == 1){
                rfstatus.setText("Approved");
                //rfstatus.setTextColor(context.getResources().getColor(R.color.colorGreen));
                rfstatus.setBackground(context.getResources().getDrawable(R.drawable.badgegreen));
            }
            else{
                rfstatus.setText("Not Completed");
                //rfstatus.setTextColor(context.getResources().getColor(R.color.colorRed));
                rfstatus.setBackground(context.getResources().getDrawable(R.drawable.badgered));
            }

            /*if(model.isSelected){
                selectedReq.setVisibility(View.VISIBLE);
            }
            else{
                selectedReq.setVisibility(View.GONE);
            }*/

            if(model.isSelected){
                selectedReq.setSelected(true);
            }
            else{
                selectedReq.setSelected(false);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(model.isSelected){
                        selectedReq.setSelected(false);
                    }
                    else{
                        selectedReq.setSelected(true);
                    }

                    RequisitionForm changeModel = model;
                    changeModel.setSelected(!model.isSelected);

                    update(changeModel,position);
                }
            });
        }
    }

}


