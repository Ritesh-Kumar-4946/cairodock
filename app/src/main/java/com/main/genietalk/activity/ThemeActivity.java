package com.main.genietalk.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.util.SharedPreferenceUtils;
import com.main.genietalk.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



public class ThemeActivity extends BaseActivity {
    SharedPreferenceUtils sharedPreferenceUtils;
    ImageView backBtn;

    @BindView(R.id.lightCheckBox)CheckBox lightCheckBox;
    @BindView(R.id.darkCheckBox)CheckBox darkCheckBox;
    @BindView(R.id.lightLayoutRL)RelativeLayout lightLayoutRL;
    @BindView(R.id.darkLayoutRL)RelativeLayout darkLayoutRL;
    @BindView(R.id.headerTV1)TextView headerTV1;
    @BindView(R.id.headerTV2)TextView headerTV2;
    public static String[] themeArr;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme_activity);
        ButterKnife.bind(this);
        backBtn = (ImageView)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sharedPreferenceUtils = SharedPreferenceUtils.getInstance(this);
        if(sharedPreferenceUtils.getBoolanValue(Utils.IS_DARK_THEME,true)){
            darkCheckBox.setChecked(true);
            lightCheckBox.setChecked(false);

        }else{
            darkCheckBox.setChecked(false);
            lightCheckBox.setChecked(true);
        }

        init();
    }



    @OnClick(R.id.lightLayoutRL)
    public void lightLayoutRL(View view){
        sharedPreferenceUtils.setValue(Utils.IS_DARK_THEME,false);
        String[] themeArr = {"appbg_white",                                   // (0) background image
                "#ffffff",                                                    // (1) background color
                "#222423",                                                    // (2) user reply color
                "#222423",                                                    // (3) genie reply color
                "#A3A3A3",                                                    // (4) time stamp color
                "#000000",                                                    // (5) header bg color
                "#F0C894",                                                    // (6) header title color
                "loading_spinner",                                            // (7) loader gif
                "keybordwhite",                                               // (8) chat keyboard icon
                "microphonewhite",                                            // (9) chat mic icon
                "menuwhite",                                                  // (10)chat menu icon
                "#000000",                                                    // (11) tab bg color
                "#F6F6F6",                                                    // (12) tab general text color
                "#F0C894",                                                    // (13) tab selected text color
                "#4e4e4e",                                                    // (14) View header Bottomline color
                "#ebebeb",                                                    // (15) tv cell saperator line color
                "#e7e7e7",                                                    // (16) tv cell section header color
                "#333333",                                                    // (17) lbl title black color
                "#666666",                                                    // (18) lbl title color
                "#a3a3a3",                                                    // (19) lbl subtitle color
                "#000000",                                                    // (20) lbl title gold color
                "education",                                                  // (21) profile education
                "location",                                                   // (22) profile location
                "address",                                                    // (23) profile address
                "mobileno",                                                   // (24) profile mobile
                "profileemail",                                               // (25) profile email
                "creditcard",                                                 // (26) profile credit card
                "creditcard",                                                 // (27) profile credit card
                "#000000",                                                    // (28) tv cell section header text color
                "speedometerwhite",                                           // (29) memeber benifit icon1
                "moviewhite",                                                 // (30) memeber benifit icon2
                "loungewhite",                                                // (31) memeber benifit icon3
                "emailwhite",                                                 // (32) refer & earn
                "#000000"                                                   // (33) Keyboard text color
        };

        Utils.convertArray2String(this,themeArr);

        lightCheckBox.setChecked(true);
        darkCheckBox.setChecked(false);
        startAct();
    }

    @OnClick(R.id.darkLayoutRL)
    public void darkLayoutRL(View view){
        sharedPreferenceUtils.setValue(Utils.IS_DARK_THEME,true);
        String[] themeArr = {"appbg",                                            // (0) background image
                "#212121",                                                       // (1) background color
                "#F0C894",                                                       // (2) user reply color
                "#F0C894",                                                       // (3) genie reply color
                "#F6F6F6",                                                       // (4) time stamp color
                "#000000",                                                       // (5) header bg color
                "#F0C894",                                                       // (6) header title color
                "loading_spinner",                                               // (7) loader gif
                "keyboard",                                                      // (8) chat keyboard icon
                "microphone",                                                    // (9) chat mic icon
                "menu",                                                          // (10) chat menu icon
                "#000000",                                                       // (11) tab bg color
                "#F6F6F6",                                                       // (12) tab general text color
                "#F0C894",                                                       // (13) tab selected text color
                "#4e4e4e",                                                       // (14) View header Bottomline color
                "#2b2a2e",                                                       // (15) tv cell saperator line color
                "#2a2a2a",                                                       // (16) tv cell section header color
                "#FFFFFF",                                                       // (17) lbl title white color
                "#cacaca",                                                       // (18) lbl title color
                "#a3a3a3",                                                       // (19) lbl subtitle color
                "#F0C894",                                                       // (20) lbl title gold color
                "education",                                                     // (21) profile education
                "location",                                                      // (22) profile location
                "address",                                                       // (23) profile address
                "mobileno",                                                      // (24) profile mobile
                "profileemail",                                                  // (25) profile email
                "creditcard",                                                    // (26) profile credit card
                "creditcard",                                                    // (27) profile credit card
                "#F0C894",                                                       // (28) tv cell section header text color
                "speedometer",                                                   // (29) memeber benifit icon1
                "movie",                                                         // (30) memeber benifit icon2
                "lounge",                                                        // (31) memeber benifit icon3
                "email",                                                          // (32) refer & earn
                "#000000"                                                        // (33) Keyboard text color
        };

        Utils.convertArray2String(this,themeArr);

        lightCheckBox.setChecked(false);
        darkCheckBox.setChecked(true);
        startAct();
    }

    public void startAct(){
        Intent intent = new Intent(ThemeActivity.this,ChatActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void init() {
        String themeArra[] = Utils.convertString2Array(this);
        screenId = (LinearLayout)findViewById(R.id.screenId);
        screenId.setBackgroundColor(Color.parseColor(themeArra[1]));
        headerRL = (RelativeLayout)findViewById(R.id.headerRL);
        headerRL.setBackgroundColor(Color.parseColor(themeArra[5]));
        headerTV = (TextView)findViewById(R.id.headerTV);
        headerTV.setText(getString(R.string.theme));
        headerTV.setTextColor(Color.parseColor(themeArra[6]));

        darkLayoutRL.setBackgroundColor(Color.parseColor(themeArra[16]));
        lightLayoutRL.setBackgroundColor(Color.parseColor(themeArra[16]));

        headerTV1.setTextColor(Color.parseColor(themeArra[28]));
        headerTV2.setTextColor(Color.parseColor(themeArra[28]));

    }

    public void backAction(View view){
        finish();
    }
}