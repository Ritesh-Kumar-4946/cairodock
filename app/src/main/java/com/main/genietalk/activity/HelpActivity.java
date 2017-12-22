package com.main.genietalk.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gt9 on 29/11/17.
 */

public class HelpActivity extends BaseActivity{
    RelativeLayout reportAnIssueRL,faqRL,submitFeedbackRL;
    ImageView backBtn;

    LinearLayout screenId;
    TextView headerTV;
    RelativeLayout headerRL;

    TextView reportAnIssueTV,FAQTV,submitFeedbackTV;

    @BindView(R.id.dividerLine1)View dividerLine1;
    @BindView(R.id.dividerLine2)View dividerLine2;
    @BindView(R.id.dividerLine3)View dividerLine3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_activity_layout);
        ButterKnife.bind(this);
        init();

        reportAnIssueRL = (RelativeLayout)findViewById(R.id.reportAnIssueRL);
        faqRL = (RelativeLayout)findViewById(R.id.faqRL);
        submitFeedbackRL = (RelativeLayout)findViewById(R.id.submitFeedbackRL);
        backBtn = (ImageView)findViewById(R.id.backBtn);
        reportAnIssueRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelpActivity.this,ReportAnIssueActivity.class);
                //intent.putExtra(Utils.HELP_DETAIL_KEY,Utils.REPORT_AN_ISSUE);
                startActivity(intent);
            }
        });
        faqRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelpActivity.this,HelpDetailActivity.class);
                intent.putExtra(Utils.HELP_DETAIL_KEY,Utils.FAQ);
                startActivity(intent);
            }
        });
        submitFeedbackRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelpActivity.this,HelpDetailActivity.class);
                intent.putExtra(Utils.HELP_DETAIL_KEY,Utils.SUBMIT_FEEDBACK);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private void init() {
        String themeArra[] = Utils.convertString2Array(this);
        screenId = (LinearLayout)findViewById(R.id.screenId);
        screenId.setBackgroundColor(Color.parseColor(themeArra[1]));
        headerRL = (RelativeLayout)findViewById(R.id.headerRL);
        headerRL.setBackgroundColor(Color.parseColor(themeArra[5]));
        headerTV = (TextView)findViewById(R.id.headerTV);
        headerTV.setText(getString(R.string.help));
        headerTV.setTextColor(Color.parseColor(themeArra[6]));

        reportAnIssueTV = (TextView)findViewById(R.id.reportAnIssueTV);
        reportAnIssueTV.setTextColor(Color.parseColor(themeArra[17]));
        FAQTV = (TextView)findViewById(R.id.FAQTV);
        FAQTV.setTextColor(Color.parseColor(themeArra[17]));
        submitFeedbackTV = (TextView)findViewById(R.id.submitFeedbackTV);
        submitFeedbackTV.setTextColor(Color.parseColor(themeArra[17]));

        dividerLine1.setBackgroundColor(Color.parseColor(themeArra[15]));
        dividerLine2.setBackgroundColor(Color.parseColor(themeArra[15]));
        dividerLine3.setBackgroundColor(Color.parseColor(themeArra[15]));

    }

}
