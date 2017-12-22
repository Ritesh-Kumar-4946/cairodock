package com.main.genietalk.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.orangegangsters.lollipin.lib.managers.AppLock;
import com.github.orangegangsters.lollipin.lib.managers.LockManager;
import com.main.genietalk.R;
import com.main.genietalk.finger_prints.AuthenticationCallback;
import com.main.genietalk.finger_prints.FingerPrintHelperBuilder;
import com.main.genietalk.finger_prints.IFingerPrintUiHelper;
import com.main.genietalk.util.Utils;

public class SettingActivity extends BaseActivity implements AuthenticationCallback {
    //    private static final int REQUEST_CODE_ENABLE = 11;
    private static final int REQUEST_CODE_CHANGE = 12;
    private static final int REQUEST_CODE_UNLOCK_PIN = 13;
    private static final int REQUEST_FINGER_PRINT_PERMISSION = 234;
    LinearLayout pass_lay;
    LockManager<CustomPinActivity> lockManager;
    Dialog dialog = null;
    private Switch setpass_switch;
    private PrefManager prefManager;
    private boolean changePass;
    private Button radio_pin, radio_finger, btn_disablepin;
    RelativeLayout passwordRL, disablePasswordRL, fingerPrintRL;
    TextView passwordTV, disablePasswordTV, setFingerPrintTV;
    private boolean enabled;
    private IFingerPrintUiHelper fingerPrintUIHelper;
    private boolean disablefinger;
    private boolean alldisablebtn;
    RelativeLayout themeRL, notificationSettingRL, logoutRL, removeRL;
    LinearLayout headerLL1;
    TextView headerTV1, headerTV2, headerTV3, headerTV4, headerTV5;
    ImageView backBtn;

    String strGet_confirm_password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_setting);
        prefManager = new PrefManager(this);
        prefManager.setOnPaus(false);
        intInstance();
        themeRL = (RelativeLayout) findViewById(R.id.themeRL);
        themeRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, ThemeActivity.class);
                startActivity(intent);
            }
        });
        notificationSettingRL = (RelativeLayout) findViewById(R.id.notificationSettingRL);
        notificationSettingRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, NotificationSettingActivity.class);
                startActivity(intent);
            }
        });
        init();


//        strGet_confirm_password = getIntent().getStringExtra("SETPASSWORD");
//        if (strGet_confirm_password.equalsIgnoreCase("ok")) {
//
//            enabled = true;
//            Toast.makeText(this, "PinCode enabled", Toast.LENGTH_SHORT).show();
//            //radio_pin.setChecked(false);
//            passwordTV.setText("Change PIN");
//            disablePasswordRL.setVisibility(View.VISIBLE);
//            changePass = true;
//
//        } else {
//            Toast.makeText(this, "No UI change", Toast.LENGTH_SHORT).show();
//        }


    }

    public void init() {
        String themeArra[] = Utils.convertString2Array(this);

        screenId = (LinearLayout) findViewById(R.id.screenId);
        screenId.setBackgroundColor(Color.parseColor(themeArra[1]));
        headerRL = (RelativeLayout) findViewById(R.id.headerRL);
        headerRL.setBackgroundColor(Color.parseColor(themeArra[5]));
        headerTV = (TextView) findViewById(R.id.headerTV);
        headerTV.setText(getString(R.string.settings));
        headerTV.setTextColor(Color.parseColor(themeArra[6]));

        headerLL1 = (LinearLayout) findViewById(R.id.headerLL1);
        headerLL1.setBackgroundColor(Color.parseColor(themeArra[16]));
        passwordRL.setBackgroundColor(Color.parseColor(themeArra[16]));
        disablePasswordRL.setBackgroundColor(Color.parseColor(themeArra[16]));
        fingerPrintRL.setBackgroundColor(Color.parseColor(themeArra[16]));
        themeRL.setBackgroundColor(Color.parseColor(themeArra[16]));
        notificationSettingRL.setBackgroundColor(Color.parseColor(themeArra[16]));
        logoutRL.setBackgroundColor(Color.parseColor(themeArra[16]));
        removeRL.setBackgroundColor(Color.parseColor(themeArra[16]));

        headerTV1 = (TextView) findViewById(R.id.headerTV1);
        headerTV1.setTextColor(Color.parseColor(themeArra[28]));

        headerTV2 = (TextView) findViewById(R.id.headerTV2);
        headerTV2.setTextColor(Color.parseColor(themeArra[28]));

        headerTV3 = (TextView) findViewById(R.id.headerTV3);
        headerTV3.setTextColor(Color.parseColor(themeArra[28]));

        headerTV4 = (TextView) findViewById(R.id.headerTV4);
        headerTV4.setTextColor(Color.parseColor(themeArra[28]));

        /*headerTV5 = (TextView)findViewById(R.id.headerTV5);
        headerTV5.setTextColor(Color.parseColor(themeArra[28]));*/

        passwordTV.setTextColor(Color.parseColor(themeArra[28]));
        setFingerPrintTV.setTextColor(Color.parseColor(themeArra[28]));
        disablePasswordTV.setTextColor(Color.parseColor(themeArra[28]));

        backBtn = (ImageView) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void intInstance() {
        logoutRL = (RelativeLayout) findViewById(R.id.logoutRL);
        removeRL = (RelativeLayout) findViewById(R.id.removeRL);
        setpass_switch = (Switch) findViewById(R.id.setpass_switch);
        passwordTV = (TextView) findViewById(R.id.passwordTV);
        disablePasswordTV = (TextView) findViewById(R.id.changePasswordTV);
        setFingerPrintTV = (TextView) findViewById(R.id.setFingerPrintTV);
        passwordRL = (RelativeLayout) findViewById(R.id.passwordRL);
        disablePasswordRL = (RelativeLayout) findViewById(R.id.changePasswordRL);
        fingerPrintRL = (RelativeLayout) findViewById(R.id.fingerPrintRL);
        radio_pin = (Button) findViewById(R.id.radio_pin);
        radio_finger = (Button) findViewById(R.id.radio_finger);
        btn_disablepin = (Button) findViewById(R.id.btn_disablepin);
        pass_lay = (LinearLayout) findViewById(R.id.pass_lay);

        lockManager = LockManager.getInstance();
        if (lockManager.isAppLockEnabled() && lockManager.getAppLock().isPasscodeSet()) {
            setpass_switch.setChecked(true);
            changePass = true;
            passwordTV.setText("Change PIN");
            disablePasswordRL.setVisibility(View.VISIBLE);
            pass_lay.setVisibility(View.VISIBLE);
        } else {
            changePass = false;
        }
        if (prefManager.getFingerPrintEnable()) {
            pass_lay.setVisibility(View.VISIBLE);
            setpass_switch.setChecked(true);
            setFingerPrintTV.setText("Disable Finger Lock");
        }

        setpass_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pass_lay.setVisibility(View.VISIBLE);
                    disablefinger = false;
                    // lockManager.enableAppLock(SettingActivity.this, CustomPinActivity.class);
                } else {
                    if (lockManager.isAppLockEnabled() && lockManager.getAppLock().isPasscodeSet()) {
                        Intent intent = new Intent(SettingActivity.this, CustomPinActivity.class);
                        intent.putExtra(AppLock.EXTRA_TYPE, AppLock.UNLOCK_PIN);
                        startActivityForResult(intent, REQUEST_CODE_UNLOCK_PIN);
                        alldisablebtn = true;
                    } else {
                        if (prefManager.getFingerPrintEnable()) {
                            alldisablebtn = true;
                            disablefinger = true;
                            initFingerPrint();
                        } else {
                            lockManager.disableAppLock();
                            pass_lay.setVisibility(View.GONE);
                            // radio_pin.setChecked(false);
                            passwordTV.setText("Set PIN");
                            disablePasswordRL.setVisibility(View.GONE);
                            changePass = false;
                        }
                    }


                }
            }
        });

        passwordRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (changePass) {
//
//                    Intent intent = new Intent(SettingActivity.this, CustomPinActivity.class);
//                    intent.putExtra(AppLock.EXTRA_TYPE, AppLock.CHANGE_PIN);
//                    startActivityForResult(intent, REQUEST_CODE_CHANGE);
//                } else {
//
//                    Intent intent = new Intent(SettingActivity.this, CustomPinActivity.class);
//                    intent.putExtra(AppLock.EXTRA_TYPE, AppLock.ENABLE_PINLOCK);
//                    startActivityForResult(intent, REQUEST_CODE_ENABLE);
//                }

                Intent intent = new Intent(SettingActivity.this, PasswordSetActivity.class);
                startActivityForResult(intent, Utils.REQUEST_CODE_ENABLE);


            }
        });
        disablePasswordRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lockManager.isAppLockEnabled() && lockManager.getAppLock().isPasscodeSet()) {
                    Intent intent = new Intent(SettingActivity.this, CustomPinActivity.class);
                    intent.putExtra(AppLock.EXTRA_TYPE, AppLock.UNLOCK_PIN);
                    startActivityForResult(intent, REQUEST_CODE_UNLOCK_PIN);
                }
            }
        });
        fingerPrintRL.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if (prefManager.getFingerPrintEnable()) {
                    disablefinger = true;
                    initFingerPrint();
                } else {

                    initFingerPrint();
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initFingerPrint() {

        fingerPrintUIHelper = FingerPrintHelperBuilder.getFingerPrintUIHelper(this, this);

        // crypto = new FingerprintManagerCompat.CryptoObject(mChifer);


        if (!fingerPrintUIHelper.isHardwareDetected()) {
            // Device doesn't support fingerprint authentication
            Toast.makeText(this, getString(R.string.no_finger_senso), Toast.LENGTH_SHORT).show();

        } else if (!fingerPrintUIHelper.hasEnrolledFingerprints()) {
            // User hasn't enrolled any fingerprints to authenticate with
            Toast.makeText(this, getString(R.string.note), Toast.LENGTH_SHORT).show();

        } else {
            // Everything is ready for fingerprint authentication

            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.USE_FINGERPRINT) !=
                    PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.USE_FINGERPRINT},
                        REQUEST_FINGER_PRINT_PERMISSION);

            } else {

                openDialogForFingerPrint();
                startListeningFingerPrint();
            }

        }
    }

    private void startListeningFingerPrint() {
        // if (fingerPrintUIHelper.initCipher()) {
        fingerPrintUIHelper.startListening();
        // } else {
        //show error
        //   Toast.makeText(this, getString(R.string.errore_generico), Toast.LENGTH_SHORT).show();

        // }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void openDialogForFingerPrint() {
//        initFingerPrint();
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_finger_print);
        dialog.setCanceledOnTouchOutside(true);

        dialog.show();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case Utils.REQUEST_CODE_ENABLE:
                    enabled = true;
                    Toast.makeText(this, "PinCode enabled", Toast.LENGTH_SHORT).show();
                    //radio_pin.setChecked(false);
                    passwordTV.setText("Change PIN");
                    disablePasswordRL.setVisibility(View.VISIBLE);
                    changePass = true;

                    sharedPreferenceUtils.setValue(Utils.ISPASSWORD,true);
                    break;
                case REQUEST_CODE_CHANGE:
                    Toast.makeText(this, "PinCode Changed", Toast.LENGTH_SHORT).show();
                    // radio_pin.setChecked(false);
                    passwordTV.setText("Change PIN");
                    disablePasswordRL.setVisibility(View.VISIBLE);
                    changePass = true;

                    break;
                case REQUEST_CODE_UNLOCK_PIN:


                    changePass = false;
                    //  pass_lay.setVisibility(View.GONE);
                    // radio_pin.setChecked(false);
                    passwordTV.setText("Set PIN");
                    disablePasswordRL.setVisibility(View.GONE);
                    lockManager.getAppLock().setPasscode(null);


                    if (prefManager.getFingerPrintEnable()) {
                        disablefinger = true;
                        initFingerPrint();
                    } else if (alldisablebtn) {
                        alldisablebtn = false;
                        pass_lay.setVisibility(View.GONE);
                    }


                    break;
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
//put your logic here
        //  prefManager.setOnPaus(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        prefManager.setOnPaus(false);
    }


    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        Toast.makeText(this, errString, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        Toast.makeText(this, helpString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        Toast.makeText(this, getString(R.string.finger_prints_match), Toast.LENGTH_SHORT).show();


        if (disablefinger) {
            disablefinger = false;
            setFingerPrintTV.setText("Set FingerPrint");
            fingerPrintUIHelper.stopListening();
            prefManager.saveFingerPrintEnable(false);
            if (lockManager != null)
                lockManager.getAppLock().setFingerprintAuthEnabled(false);
            Toast.makeText(SettingActivity.this, R.string.finger_print_disable, Toast.LENGTH_LONG).show();
            if (dialog != null) {
                dialog.dismiss();
            }
            if (alldisablebtn) {
                alldisablebtn = false;
                pass_lay.setVisibility(View.GONE);
            }

        } else {


            fingerPrintUIHelper.stopListening();
            if (lockManager != null)
                lockManager.getAppLock().setFingerprintAuthEnabled(true);
            prefManager.saveFingerPrintEnable(true);

            if (dialog != null) {
                dialog.dismiss();
                setFingerPrintTV.setText("Disable Finger Lock");
            }

        }
    }

    @Override
    public void onAuthenticationFailed() {
        Toast.makeText(this, getString(R.string.auth_failed), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_FINGER_PRINT_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startListeningFingerPrint();
        }
    }

}