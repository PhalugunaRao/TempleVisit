package com.temples.holders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.temples.R;
import com.temples.utils.NoDataFoundCommonModel;
import com.temples.utils.PreferenceHelper;

public class ViewHolderNoDataAction extends BaseViewHolder {

    public final TextView label1, label2, callToAction;
    public final ImageView imageView;
    private TextView viewSample;

    public ViewHolderNoDataAction(View v) {
        super(v);
        label1 = v.findViewById(R.id.no_data_title);
        label2 = v.findViewById(R.id.no_data_discription);
        callToAction = v.findViewById(R.id.no_data_call_to_action);
        imageView = v.findViewById(R.id.no_data_image);
        viewSample = v.findViewById(R.id.no_data_view_sample);
    }

    @Override
    public void bind(Object object, final Context context, PreferenceHelper prefs,  int pos) {
        {
            NoDataFoundCommonModel noDataFoundModel = (NoDataFoundCommonModel) object;
            if (noDataFoundModel != null) {
                imageView.setImageResource(noDataFoundModel.getResourceId());
                label1.setText(noDataFoundModel.getTitle());
                label2.setText(noDataFoundModel.getDiscription());
                if (noDataFoundModel.getButtonText().isEmpty() ||
                        noDataFoundModel.getButtonText().equalsIgnoreCase("")) {
                    callToAction.setVisibility(View.INVISIBLE);

                } else {
                    callToAction.setVisibility(View.VISIBLE);
                    callToAction.setText(noDataFoundModel.getButtonText());
                }
                if (noDataFoundModel.getSampleText().isEmpty() ||
                        noDataFoundModel.getSampleText().equalsIgnoreCase("")) {
                    viewSample.setVisibility(View.INVISIBLE);

                } else {
                    viewSample.setVisibility(View.VISIBLE);
                    viewSample.setText(noDataFoundModel.getSampleText());
                }
                viewSample.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                callToAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        }
    }
}