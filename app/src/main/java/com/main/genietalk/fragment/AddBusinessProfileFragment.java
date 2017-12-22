package com.main.genietalk.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.main.genietalk.R;
import com.main.genietalk.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gt9 on 1/12/17.
 */

public class AddBusinessProfileFragment extends Fragment {

    @BindView(R.id.nameET)EditText nameET;
    @BindView(R.id.gstET)EditText gstET;
    @BindView(R.id.emailET)EditText emailET;
    @BindView(R.id.countryET)EditText countryET;
    @BindView(R.id.stateET)EditText stateET;
    @BindView(R.id.cityET)EditText cityET;
    @BindView(R.id.zipET)EditText zipET;
    @BindView(R.id.contactET)EditText contactET;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_business_profile_fragment,container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    private void init() {

        String themeArra[] = Utils.convertString2Array(getActivity());
        nameET.setTextColor(Color.parseColor(themeArra[17]));
        gstET.setTextColor(Color.parseColor(themeArra[17]));
        emailET.setTextColor(Color.parseColor(themeArra[17]));
        countryET.setTextColor(Color.parseColor(themeArra[17]));
        stateET.setTextColor(Color.parseColor(themeArra[17]));
        cityET.setTextColor(Color.parseColor(themeArra[17]));
        zipET.setTextColor(Color.parseColor(themeArra[17]));
        contactET.setTextColor(Color.parseColor(themeArra[17]));
    }
}