package com.main.genietalk.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.fragment.FaqFragment;
import com.main.genietalk.fragment.ReportAnIssueFragment;
import com.main.genietalk.fragment.SubmitFeedback;
import com.main.genietalk.util.Utils;

/**
 * Created by gt9 on 30/11/17.
 */

public class HelpDetailActivity extends BaseActivity{

    FragmentManager fragmentManager;
    Fragment fragment;
    String openFragmentStr;
    TextView header;
    ImageView backBtn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_detail_activity);
        header = (TextView)findViewById(R.id.headerTV);
        backBtn = (ImageView)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if(getIntent()!=null && getIntent().getStringExtra(Utils.HELP_DETAIL_KEY)!=null){
            openFragmentStr = getIntent().getStringExtra(Utils.HELP_DETAIL_KEY);
        }
        if(openFragmentStr.equalsIgnoreCase(Utils.FAQ)){
            header.setText(Utils.FAQ);
            fragment = new FaqFragment();
        }else if(openFragmentStr.equalsIgnoreCase(Utils.SUBMIT_FEEDBACK)){
            header.setText(Utils.SUBMIT_FEEDBACK);
            fragment = new SubmitFeedback();
        }

        init();
        initialize();
    }
    public void init(){
        fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.container,fragment);
        //ft.addToBackStack(null);
        ft.commit();
    }
    public void backAction(View view){
            finish();
    }

    private void initialize() {
        String themeArra[] = Utils.convertString2Array(this);
        screenId = (LinearLayout)findViewById(R.id.screenId);
        screenId.setBackgroundColor(Color.parseColor(themeArra[1]));
        headerRL = (RelativeLayout)findViewById(R.id.headerRL);
        headerRL.setBackgroundColor(Color.parseColor(themeArra[5]));
        headerTV = (TextView)findViewById(R.id.headerTV);
        headerTV.setTextColor(Color.parseColor(themeArra[6]));
    }

}
