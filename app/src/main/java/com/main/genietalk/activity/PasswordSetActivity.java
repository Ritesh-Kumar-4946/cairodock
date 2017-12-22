package com.main.genietalk.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.main.genietalk.util.KeyboardInApp;
import com.main.genietalk.R;
import com.main.genietalk.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gt19 on 21/12/17.
 */

public class PasswordSetActivity extends BaseActivity {

    @BindView(R.id.tv_enter_password)
    TextView tv_enter_password;

    @BindView(R.id.pedt_enter_password)
    PinEntryEditText pedt_enter_password;

    String str_enter_password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_set_activity);
        ButterKnife.bind(this);
        KeyboardInApp keyboard = (KeyboardInApp) findViewById(R.id.keyboard_enter_password);

        if (pedt_enter_password != null) {
            pedt_enter_password.setRawInputType(InputType.TYPE_CLASS_TEXT);
            pedt_enter_password.setTextIsSelectable(true);
            pedt_enter_password.setAnimateText(true);
            pedt_enter_password.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {

                    if (str.toString().length() == 4) {
                        str_enter_password = str.toString();
                        Log.e("Length  :", " L " + str.toString().length() + "  str_confirm_password  --  " + str_enter_password);

                        Intent intent = new Intent(PasswordSetActivity.this, PasswordConfirmActivity.class);
                        intent.putExtra("PASSWORD", str.toString());
                        startActivityForResult(intent, Utils.REQUEST_CODE_ENABLE);



                    }


//                    if (str.toString().equals("1234")) {
//                        Toast.makeText(PasswordSetActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent();
//                        setResult(11, intent);
//                        finish();
//                    } else {
//                        pedt_enter_password.setError(true);
//                        Toast.makeText(PasswordSetActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
//                        pedt_enter_password.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                Animation animation = AnimationUtils.loadAnimation(
//                                        PasswordSetActivity.this, R.anim.shake);
//                                pedt_enter_password.startAnimation(animation);
//                                pedt_enter_password.setText(null);
//                            }
//                        }, 100);
//                    }


                }
            });
        }


        InputConnection icc = pedt_enter_password.onCreateInputConnection(new EditorInfo());
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

        tv_enter_password.setTextColor(Color.parseColor(themeArra[18]));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Utils.REQUEST_CODE_ENABLE) {
            if (resultCode == Activity.RESULT_OK) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }else {
                Toast.makeText(PasswordSetActivity.this, "No Code Found", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
