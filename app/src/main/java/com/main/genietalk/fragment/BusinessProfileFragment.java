package com.main.genietalk.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.activity.AddCardActivity;
import com.main.genietalk.activity.AddPaymentMethodActivity;
import com.main.genietalk.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gt9 on 1/12/17.
 */

public class BusinessProfileFragment extends Fragment {
    RelativeLayout addPaymentRL;
    RelativeLayout headerRL1;

    @BindView(R.id.cmpnameTV) TextView cmpnameTV;
    @BindView(R.id.cmpnemailTV) TextView cmpnemailTV;
    @BindView(R.id.cmpnGSTV) TextView cmpnGSTV;
    @BindView(R.id.cmpnContactTV) TextView cmpnContactTV;
    @BindView(R.id.visaTV1) TextView visaTV1;
    @BindView(R.id.visaTV2) TextView visaTV2;
    @BindView(R.id.addPaymentTV) TextView addPaymentTV;
    @BindView(R.id.headerTV1)TextView headerTV1;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        headerRL1 = (RelativeLayout)view.findViewById(R.id.headerRL1);
        init();
        addPaymentRL = (RelativeLayout)view.findViewById(R.id.addPaymentRL);
        addPaymentRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddPaymentMethodActivity.class);
                startActivity(intent);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.business_profile_fragment,container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    private void init() {

        String themeArra[] = Utils.convertString2Array(getActivity());

        headerRL1.setBackgroundColor(Color.parseColor(themeArra[16]));
        headerTV1.setTextColor(Color.parseColor(themeArra[28]));

        cmpnameTV.setTextColor(Color.parseColor(themeArra[17]));
        cmpnemailTV.setTextColor(Color.parseColor(themeArra[17]));
        cmpnGSTV.setTextColor(Color.parseColor(themeArra[17]));
        cmpnContactTV.setTextColor(Color.parseColor(themeArra[17]));
        visaTV1.setTextColor(Color.parseColor(themeArra[17]));
        visaTV2.setTextColor(Color.parseColor(themeArra[17]));
        addPaymentTV.setTextColor(Color.parseColor(themeArra[17]));

    }
}