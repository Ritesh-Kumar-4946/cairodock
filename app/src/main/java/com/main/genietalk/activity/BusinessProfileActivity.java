package com.main.genietalk.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.fragment.AddBusinessProfileFragment;
import com.main.genietalk.fragment.BusinessProfileFragment;
import com.main.genietalk.fragment.FaqFragment;
import com.main.genietalk.fragment.ReportAnIssueFragment;
import com.main.genietalk.util.Utils;



public class BusinessProfileActivity extends BaseActivity {
    ImageView backBtn;
    FragmentManager fragmentManager;
    Fragment fragment;
    String openFragmentStr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.help_detail_activity);
        backBtn = (ImageView)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        headerTV = (TextView)findViewById(R.id.headerTV);
        if(getIntent()!=null && getIntent().getStringExtra(Utils.BUSSINESS_PROFILE_KEY)!=null){

            openFragmentStr = getIntent().getStringExtra(Utils.BUSSINESS_PROFILE_KEY);
        }
        if(openFragmentStr.equalsIgnoreCase(Utils.BUSSINESS_PROFILE)) {
            headerTV.setText(Utils.BUSSINESS_PROFILE);
            //fragment = new ReportAnIssueFragment();
            fragment = new BusinessProfileFragment();
        }else if(openFragmentStr.equalsIgnoreCase(Utils.ADD_BUSSINESS_PROFILE)){
            headerTV.setText(Utils.ADD_BUSSINESS_PROFILE);
            fragment = new AddBusinessProfileFragment();
        }
        init();
        initialize();
    }

    private void initialize() {
        String themeArra[] = Utils.convertString2Array(this);
        screenId = (LinearLayout)findViewById(R.id.screenId);
        screenId.setBackgroundColor(Color.parseColor(themeArra[1]));
        headerRL = (RelativeLayout)findViewById(R.id.headerRL);
        headerRL.setBackgroundColor(Color.parseColor(themeArra[5]));
        headerTV.setTextColor(Color.parseColor(themeArra[6]));
    }

    public void init(){
        fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.container,fragment);
        //ft.addToBackStack(null);
        ft.commit();
    }

}
