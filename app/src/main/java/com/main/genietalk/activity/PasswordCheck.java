package com.main.genietalk.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.main.genietalk.R;
import com.main.genietalk.util.KeyboardInApp;
import com.main.genietalk.util.SharedPreferenceUtils;
import com.main.genietalk.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gt19 on 21/12/17.
 */

public class PasswordCheck extends BaseActivity {

    @BindView(R.id.tv_check_password)
    TextView tv_check_password;

    @BindView(R.id.pedt_check_password)
    PinEntryEditText pedt_check_password;

    String str_check_password = "", str_get_password = "";
    SharedPreferenceUtils sharedPreferenceUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_check_activity);
        ButterKnife.bind(this);
        Log.e("Password Check :", "Open ");



        KeyboardInApp keyboard = (KeyboardInApp) findViewById(R.id.keyboard_check_password);
        sharedPreferenceUtils = SharedPreferenceUtils.getInstance(this);
        str_get_password = sharedPreferenceUtils.getStringValue(Utils.PASSWORD, "");


        if (pedt_check_password != null) {
            pedt_check_password.setRawInputType(InputType.TYPE_CLASS_TEXT);
            pedt_check_password.setTextIsSelectable(true);
            pedt_check_password.setAnimateText(true);
            pedt_check_password.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {

                    if (str.toString().length() == 4) {
                        str_get_password = str.toString();
                        Log.e("Length  :", " L " + str.toString().length() + "  str_confirm_password  --  " + str_check_password);

                        Intent intent = new Intent(PasswordCheck.this, ChatActivity.class);
                        intent.putExtra("PASSWORD", str.toString());
                        startActivityForResult(intent, Utils.REQUEST_CODE_ENABLE);

                    } else {
                        Toast.makeText(PasswordCheck.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                        pedt_check_password.setError(true);
                        pedt_check_password.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Animation animation = AnimationUtils.loadAnimation(
                                        PasswordCheck.this, R.anim.shake);
                                pedt_check_password.startAnimation(animation);
                                pedt_check_password.setText(null);
                            }
                        }, 100);
                    }

                }
            });
        }


        InputConnection icc = pedt_check_password.onCreateInputConnection(new EditorInfo());
        keyboard.setInputConnection(icc);
        init();
    }

    private void init() {
        String themeArra[] = Utils.convertString2Array(this);
        screenId = (LinearLayout) findViewById(R.id.screenId);
        screenId.setBackgroundColor(Color.parseColor(themeArra[1]));
        headerRL = (RelativeLayout) findViewById(R.id.headerRL);
        headerRL.setBackgroundColor(Color.parseColor(themeArra[5]));

        headerTV = (TextView) findViewById(R.id.headerTV);
        headerTV.setText(getString(R.string.check_password));
        headerTV.setTextColor(Color.parseColor(themeArra[6]));

        tv_check_password.setTextColor(Color.parseColor(themeArra[18]));

    }

}
