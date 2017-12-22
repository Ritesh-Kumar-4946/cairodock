package com.main.genietalk.activity;

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
import com.main.genietalk.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LegalActivity extends BaseActivity {
    ImageView backBtn;
    RelativeLayout aboutUsRL,termsConditionRL,privacyPolicyRL,refundCancellationRL
            ,locationInformationRL,dataProviderRL,softwareLicenceRL,copyRightRL;
    LinearLayout screenId;
    TextView headerTV;
    RelativeLayout headerRL;
    TextView aboutusTV,termsConditionTV,privacyPolicyTV,refundCancellationTV,locationInformationTV,dataProviderTV,softwareLicenceTV,copyRightTV;

    @BindView(R.id.dividerLine1)View dividerLine1;
    @BindView(R.id.dividerLine2)View dividerLine2;
    @BindView(R.id.dividerLine3)View dividerLine3;
    @BindView(R.id.dividerLine4)View dividerLine4;
    @BindView(R.id.dividerLine5)View dividerLine5;
    @BindView(R.id.dividerLine6)View dividerLine6;
    @BindView(R.id.dividerLine7)View dividerLine7;
    @BindView(R.id.dividerLine8)View dividerLine8;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.legal_activity_layout);
        ButterKnife.bind(this);

        init();

        backBtn = (ImageView)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        aboutUsRL = (RelativeLayout)findViewById(R.id.aboutUsRL);
        aboutUsRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LegalActivity.this,LegalDetailActivity.class);
                intent.putExtra(Utils.LEGAL_DETAIL_KEY,Utils.ABOUT_US);
                startActivity(intent);
            }
        });
        termsConditionRL = (RelativeLayout)findViewById(R.id.termsConditionRL);
        termsConditionRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(LegalActivity.this,LegalDetailActivity.class);
                 intent.putExtra(Utils.LEGAL_DETAIL_KEY,Utils.TERM_AND_CONDITIONS);
                 startActivity(intent);
            }
        });
        privacyPolicyRL = (RelativeLayout)findViewById(R.id.privacyPolicyRL);
        privacyPolicyRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LegalActivity.this,LegalDetailActivity.class);
                intent.putExtra(Utils.LEGAL_DETAIL_KEY,Utils.PRIVACY_POLICY);
                startActivity(intent);
            }
        });
        refundCancellationRL = (RelativeLayout)findViewById(R.id.refundCancellationRL);
        refundCancellationRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LegalActivity.this,LegalDetailActivity.class);
                intent.putExtra(Utils.LEGAL_DETAIL_KEY,Utils.REFUND_CANCELLATION_POLICY);
                startActivity(intent);
            }
        });
        locationInformationRL = (RelativeLayout)findViewById(R.id.locationInformationRL);
        locationInformationRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LegalActivity.this,LegalDetailActivity.class);
                intent.putExtra(Utils.LEGAL_DETAIL_KEY,Utils.LOCATION_INFORMATION_POLICY);
                startActivity(intent);
            }
        });
        dataProviderRL = (RelativeLayout)findViewById(R.id.dataProviderRL);
        dataProviderRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LegalActivity.this,LegalDetailActivity.class);
                intent.putExtra(Utils.LEGAL_DETAIL_KEY,Utils.DATA_PROVIDER);
                startActivity(intent);
            }
        });
        softwareLicenceRL = (RelativeLayout)findViewById(R.id.softwareLicenceRL);
        softwareLicenceRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LegalActivity.this,LegalDetailActivity.class);
                intent.putExtra(Utils.LEGAL_DETAIL_KEY,Utils.SOFTWARE_LICENCE);
                startActivity(intent);
            }
        });
        copyRightRL = (RelativeLayout)findViewById(R.id.copyRightRL);
        copyRightRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LegalActivity.this,LegalDetailActivity.class);
                intent.putExtra(Utils.LEGAL_DETAIL_KEY,Utils.COPY_RIGHT);
                startActivity(intent);
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
        headerTV.setText(getString(R.string.legal));
        headerTV.setTextColor(Color.parseColor(themeArra[6]));

        aboutusTV = (TextView)findViewById(R.id.aboutusTV);
        aboutusTV.setTextColor(Color.parseColor(themeArra[17]));
        termsConditionTV = (TextView)findViewById(R.id.termsConditionTV);
        termsConditionTV.setTextColor(Color.parseColor(themeArra[17]));
        privacyPolicyTV = (TextView)findViewById(R.id.privacyPolicyTV);
        privacyPolicyTV.setTextColor(Color.parseColor(themeArra[17]));
        refundCancellationTV = (TextView)findViewById(R.id.refundCancellationTV);
        refundCancellationTV.setTextColor(Color.parseColor(themeArra[17]));
        locationInformationTV = (TextView)findViewById(R.id.locationInformationTV);
        locationInformationTV.setTextColor(Color.parseColor(themeArra[17]));
        dataProviderTV = (TextView)findViewById(R.id.dataProviderTV);
        dataProviderTV.setTextColor(Color.parseColor(themeArra[17]));
        softwareLicenceTV = (TextView)findViewById(R.id.softwareLicenceTV);
        softwareLicenceTV.setTextColor(Color.parseColor(themeArra[17]));
        copyRightTV = (TextView)findViewById(R.id.copyRightTV);
        copyRightTV.setTextColor(Color.parseColor(themeArra[17]));

        dividerLine1.setBackgroundColor(Color.parseColor(themeArra[15]));
        dividerLine2.setBackgroundColor(Color.parseColor(themeArra[15]));
        dividerLine3.setBackgroundColor(Color.parseColor(themeArra[15]));
        dividerLine4.setBackgroundColor(Color.parseColor(themeArra[15]));
        dividerLine5.setBackgroundColor(Color.parseColor(themeArra[15]));
        dividerLine6.setBackgroundColor(Color.parseColor(themeArra[15]));
        dividerLine7.setBackgroundColor(Color.parseColor(themeArra[15]));
        dividerLine8.setBackgroundColor(Color.parseColor(themeArra[15]));

    }
}