package com.main.genietalk.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.expandable.Main2Activity;
import com.main.genietalk.expandable.ProfileFragment;
import com.main.genietalk.expandable.RelationsFragment;
import com.main.genietalk.util.Utils;

import java.util.ArrayList;
import java.util.List;



public class ReferEarn extends BaseActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    //private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    TabLayout tabLayout;
    ImageView backBtn;

    LinearLayout screenId;
    TextView headerTV;
    RelativeLayout headerRL;

    RelativeLayout headerRL1,headerRL2;
    TextView headerTV1,headerTV2;
    TextView whatsappTV,messagerTV,emailTV,smsTV,twitterTV,facebookTV,googleTV;
    TextView headerTV3,headerTV4;
    View seprator1,seprator2,seprator3,seprator4,seprator5,seprator6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.refer_earn);
        backBtn = (ImageView)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        init();

    }
    private void init() {
        String themeArra[] = Utils.convertString2Array(this);
        screenId = (LinearLayout)findViewById(R.id.screenId);
        screenId.setBackgroundColor(Color.parseColor(themeArra[1]));
        headerRL = (RelativeLayout)findViewById(R.id.headerRL);
        headerRL.setBackgroundColor(Color.parseColor(themeArra[5]));

        headerRL1 = (RelativeLayout)findViewById(R.id.headerRL1);
        headerRL1.setBackgroundColor(Color.parseColor(themeArra[16]));
        headerRL2 = (RelativeLayout)findViewById(R.id.headerRL2);
        headerRL2.setBackgroundColor(Color.parseColor(themeArra[16]));

        headerTV = (TextView)findViewById(R.id.headerTV);
        headerTV.setText(getString(R.string.refer_earn));
        headerTV.setTextColor(Color.parseColor(themeArra[6]));

        headerTV1 = (TextView)findViewById(R.id.headerTV1);
        headerTV1.setTextColor(Color.parseColor(themeArra[28]));
        headerTV2 = (TextView)findViewById(R.id.headerTV2);
        headerTV2.setTextColor(Color.parseColor(themeArra[28]));

        whatsappTV = (TextView)findViewById(R.id.whatsappTV);
        whatsappTV.setTextColor(Color.parseColor(themeArra[17]));

        messagerTV = (TextView)findViewById(R.id.messagerTV);
        messagerTV.setTextColor(Color.parseColor(themeArra[17]));

        emailTV = (TextView)findViewById(R.id.emailTV);
        emailTV.setTextColor(Color.parseColor(themeArra[17]));
        emailTV.setCompoundDrawablesWithIntrinsicBounds(Utils.getId(themeArra[32],R.drawable.class), 0, 0, 0);

        smsTV = (TextView)findViewById(R.id.smsTV);
        smsTV.setTextColor(Color.parseColor(themeArra[17]));

        twitterTV = (TextView)findViewById(R.id.twitterTV);
        twitterTV.setTextColor(Color.parseColor(themeArra[17]));

        facebookTV = (TextView)findViewById(R.id.facebookTV);
        facebookTV.setTextColor(Color.parseColor(themeArra[17]));

        googleTV = (TextView)findViewById(R.id.googleTV);
        googleTV.setTextColor(Color.parseColor(themeArra[17]));

        headerTV3 = (TextView)findViewById(R.id.headerTV3);
        headerTV3.setTextColor(Color.parseColor(themeArra[17]));
        headerTV4 = (TextView)findViewById(R.id.headerTV4);
        headerTV4.setTextColor(Color.parseColor(themeArra[28]));

        seprator1 = (View)findViewById(R.id.seprator1);
        seprator1.setBackgroundColor(Color.parseColor(themeArra[15]));
        seprator2 = (View)findViewById(R.id.seprator2);
        seprator2.setBackgroundColor(Color.parseColor(themeArra[15]));
        seprator3 = (View)findViewById(R.id.seprator3);
        seprator3.setBackgroundColor(Color.parseColor(themeArra[15]));
        seprator4 = (View)findViewById(R.id.seprator4);
        seprator4.setBackgroundColor(Color.parseColor(themeArra[15]));
        seprator5 = (View)findViewById(R.id.seprator5);
        seprator5.setBackgroundColor(Color.parseColor(themeArra[15]));
        seprator6 = (View)findViewById(R.id.seprator6);
        seprator6.setBackgroundColor(Color.parseColor(themeArra[15]));
    }


}

