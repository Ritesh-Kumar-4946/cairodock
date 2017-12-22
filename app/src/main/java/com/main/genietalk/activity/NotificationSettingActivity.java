package com.main.genietalk.activity;

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

/**
 * Created by gt9 on 1/12/17.
 */

public class NotificationSettingActivity extends BaseActivity {

    ImageView backBtn;
    RelativeLayout headerRL1,headerRL2,headerRL3;
    TextView headerTV1,headerTV2,headerTV3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_setting_activity);
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
        headerTV.setText(getString(R.string.notification_setting));
        headerTV.setTextColor(Color.parseColor(themeArra[6]));

        headerRL1 = (RelativeLayout)findViewById(R.id.headerRL1);
        headerRL2 = (RelativeLayout)findViewById(R.id.headerRL2);
        headerRL3 = (RelativeLayout)findViewById(R.id.headerRL3);

        headerTV1 = (TextView)findViewById(R.id.headerTV1);
        headerTV2 = (TextView)findViewById(R.id.headerTV2);
        headerTV3 = (TextView)findViewById(R.id.headerTV3);

        headerRL1.setBackgroundColor(Color.parseColor(themeArra[16]));
        headerRL2.setBackgroundColor(Color.parseColor(themeArra[16]));
        headerRL3.setBackgroundColor(Color.parseColor(themeArra[16]));

        headerTV1.setTextColor(Color.parseColor(themeArra[28]));
        headerTV2.setTextColor(Color.parseColor(themeArra[28]));
        headerTV3.setTextColor(Color.parseColor(themeArra[28]));

    }

}