package com.main.genietalk.activity;

import android.app.Activity;
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

public class PasswordConfirmActivity extends BaseActivity {

    @BindView(R.id.tv_confirm_password)
    TextView tv_confirm_password;

    @BindView(R.id.pedt_confirm_password)
    PinEntryEditText pedt_confirm_password;


    String str_confirm_password = "", strGet_confirm_password = "";
    SharedPreferenceUtils sharedPreferenceUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_confirm_activity);
        ButterKnife.bind(this);
        KeyboardInApp keyboard = (KeyboardInApp) findViewById(R.id.keyboard_confirm_password);
        sharedPreferenceUtils = SharedPreferenceUtils.getInstance(this);

        strGet_confirm_password = getIntent().getStringExtra("PASSWORD");


        if (pedt_confirm_password != null) {
            pedt_confirm_password.setRawInputType(InputType.TYPE_CLASS_TEXT);
            pedt_confirm_password.setTextIsSelectable(true);
            pedt_confirm_password.setAnimateText(true);
            pedt_confirm_password.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {

                    if (str.toString().length() == 4) {
                        str_confirm_password = str.toString();
                        Log.e("Length  :", " L " + str.toString().length() + "  str_confirm_password  --  " + str_confirm_password);
//                        Toast.makeText(PasswordConfirmActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();

                        if (str_confirm_password.equalsIgnoreCase(strGet_confirm_password)) {
//                            Toast.makeText(PasswordConfirmActivity.this, "Password Set", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent();
//                            intent.putExtra("result","DUMMY");
//                            setResult(RESULT_OK,intent);
//                            finish();

//                            Intent intent = new Intent(PasswordConfirmActivity.this, SettingActivity.class);
//                            intent.putExtra("SETPASSWORD", "ok");
//                            startActivity(intent);
//                            finish();

                            sharedPreferenceUtils.setValue(Utils.PASSWORD, str_confirm_password);


                            Intent returnIntent = new Intent();
//                            returnIntent.putExtra("result",result);
                            setResult(Activity.RESULT_OK,returnIntent);
                            finish();




                        } else {
                            Toast.makeText(PasswordConfirmActivity.this, "Password Not Match", Toast.LENGTH_SHORT).show();
                            pedt_confirm_password.setError(true);
                            pedt_confirm_password.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Animation animation = AnimationUtils.loadAnimation(
                                            PasswordConfirmActivity.this, R.anim.shake);
                                    pedt_confirm_password.startAnimation(animation);
                                    pedt_confirm_password.setText(null);
                                }
                            }, 100);
                        }

                    }


//                    if (str.toString().equals("1234")) {
//                        Toast.makeText(PasswordSetActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent();
//                        setResult(11, intent);
//                        finish();
//                    } else {
//                        pedt_confirm_password.setError(true);
//                        Toast.makeText(PasswordSetActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
//                        pedt_confirm_password.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                Animation animation = AnimationUtils.loadAnimation(
//                                        PasswordSetActivity.this, R.anim.shake);
//                                pedt_confirm_password.startAnimation(animation);
//                                pedt_confirm_password.setText(null);
//                            }
//                        }, 100);
//                    }


                }
            });
        }


        InputConnection icc = pedt_confirm_password.onCreateInputConnection(new EditorInfo());
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
        headerTV.setText(getString(R.string.set_password));
        headerTV.setTextColor(Color.parseColor(themeArra[6]));

        tv_confirm_password.setTextColor(Color.parseColor(themeArra[18]));

    }
}