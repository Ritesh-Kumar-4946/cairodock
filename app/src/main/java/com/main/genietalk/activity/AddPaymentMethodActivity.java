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

/**
 * Created by gt9 on 1/12/17.
 */

public class AddPaymentMethodActivity extends BaseActivity {
    ImageView backBtn;
    RelativeLayout aboutUsRL,termsConditionRL,privacyPolicyRL,refundCancellationRL
            ,locationInformationRL,dataProviderRL,softwareLicenceRL,copyRightRL;
    TextView upiTV,creditCardTv,debitCardTv,netBankingTv,walletTV;

    @BindView(R.id.dividerLine1)View dividerLine1;
    @BindView(R.id.dividerLine2)View dividerLine2;
    @BindView(R.id.dividerLine3)View dividerLine3;
    @BindView(R.id.dividerLine4)View dividerLine4;
    @BindView(R.id.dividerLine5)View dividerLine5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_payment_method_layout);
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
                Intent intent = new Intent(AddPaymentMethodActivity.this,AddCardActivity.class);
                //intent.putExtra(Utils.LEGAL_DETAIL_KEY,Utils.ABOUT_US);
                startActivity(intent);
            }
        });
        termsConditionRL = (RelativeLayout)findViewById(R.id.termsConditionRL);
        termsConditionRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPaymentMethodActivity.this,AddCardActivity.class);
                //intent.putExtra(Utils.LEGAL_DETAIL_KEY,Utils.TERM_AND_CONDITIONS);
                startActivity(intent);
            }
        });
        privacyPolicyRL = (RelativeLayout)findViewById(R.id.privacyPolicyRL);
        privacyPolicyRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPaymentMethodActivity.this,AddCardActivity.class);
                //intent.putExtra(Utils.LEGAL_DETAIL_KEY,Utils.PRIVACY_POLICY);
                startActivity(intent);
            }
        });
        refundCancellationRL = (RelativeLayout)findViewById(R.id.refundCancellationRL);
        refundCancellationRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPaymentMethodActivity.this,AddCardActivity.class);
                //intent.putExtra(Utils.LEGAL_DETAIL_KEY,Utils.REFUND_CANCELLATION_POLICY);
                startActivity(intent);
            }
        });
        locationInformationRL = (RelativeLayout)findViewById(R.id.locationInformationRL);
        locationInformationRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPaymentMethodActivity.this,AddCardActivity.class);
                //intent.putExtra(Utils.LEGAL_DETAIL_KEY,Utils.LOCATION_INFORMATION_POLICY);
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
        headerTV.setText(getString(R.string.add_payment_method));
        headerTV.setTextColor(Color.parseColor(themeArra[6]));

        upiTV = (TextView)findViewById(R.id.upiTV);
        upiTV.setTextColor(Color.parseColor(themeArra[17]));
        creditCardTv = (TextView)findViewById(R.id.creditCardTv);
        creditCardTv.setTextColor(Color.parseColor(themeArra[17]));
        debitCardTv = (TextView)findViewById(R.id.debitCardTv);
        debitCardTv.setTextColor(Color.parseColor(themeArra[17]));
        netBankingTv = (TextView)findViewById(R.id.netBankingTv);
        netBankingTv.setTextColor(Color.parseColor(themeArra[17]));
        walletTV = (TextView)findViewById(R.id.walletTV);
        walletTV.setTextColor(Color.parseColor(themeArra[17]));

        dividerLine1.setBackgroundColor(Color.parseColor(themeArra[15]));
        dividerLine2.setBackgroundColor(Color.parseColor(themeArra[15]));
        dividerLine3.setBackgroundColor(Color.parseColor(themeArra[15]));
        dividerLine4.setBackgroundColor(Color.parseColor(themeArra[15]));
        dividerLine5.setBackgroundColor(Color.parseColor(themeArra[15]));
    }
}