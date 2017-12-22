package com.main.genietalk.activity;

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

/**
 * Created by gt9 on 1/12/17.
 */

public class AddMemberActivity extends BaseActivity {
    ImageView backBtn;
    LinearLayout screenId;
    TextView headerTV;
    RelativeLayout headerRL;
    @BindView(R.id.addMemberET) EditText addMemberET;
    @BindView(R.id.memberNameET) EditText memberNameET;
    @BindView(R.id.memberShipNoET) EditText memberShipNoET;
    @BindView(R.id.expireDateET) EditText expireDateET;
    @BindView(R.id.descriptionET) EditText descriptionET;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_member_layout);
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
        headerTV.setText(getString(R.string.add_member));
        headerTV.setTextColor(Color.parseColor(themeArra[6]));

        addMemberET.setTextColor(Color.parseColor(themeArra[17]));
        memberNameET.setTextColor(Color.parseColor(themeArra[17]));
        memberShipNoET.setTextColor(Color.parseColor(themeArra[17]));
        expireDateET.setTextColor(Color.parseColor(themeArra[17]));
        descriptionET.setTextColor(Color.parseColor(themeArra[17]));
    }
}