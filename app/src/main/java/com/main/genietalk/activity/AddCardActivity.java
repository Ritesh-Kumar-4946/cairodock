package com.main.genietalk.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddCardActivity extends BaseActivity {
    ImageView backBtn;
    RelativeLayout aboutUsRL,termsConditionRL,privacyPolicyRL,refundCancellationRL
            ,locationInformationRL,dataProviderRL,softwareLicenceRL,copyRightRL;
    @BindView(R.id.cardNumberET)EditText cardNumberET;
    @BindView(R.id.expireDateET)EditText expireDateET;
    @BindView(R.id.cvcET)EditText cvcET;
    @BindView(R.id.descriptionET)EditText descriptionET;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_card_activity_layout);
        ButterKnife.bind(this);
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

        headerTV = (TextView)findViewById(R.id.headerTV);
        headerTV.setText(getString(R.string.add_card));
        headerTV.setTextColor(Color.parseColor(themeArra[6]));

        cardNumberET.setTextColor(Color.parseColor(themeArra[17]));
        expireDateET.setTextColor(Color.parseColor(themeArra[17]));
        cvcET.setTextColor(Color.parseColor(themeArra[17]));
        descriptionET.setTextColor(Color.parseColor(themeArra[17]));
    }

}