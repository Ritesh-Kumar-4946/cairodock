package com.main.genietalk.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.orangegangsters.lollipin.lib.PinActivity;
import com.github.orangegangsters.lollipin.lib.managers.AppLock;
import com.github.orangegangsters.lollipin.lib.managers.LockManager;
import com.main.genietalk.R;
import com.main.genietalk.finger_prints.AuthenticationCallback;
import com.main.genietalk.finger_prints.FingerPrintHelperBuilder;
import com.main.genietalk.finger_prints.IFingerPrintUiHelper;
import com.main.genietalk.util.SharedPreferenceUtils;
import com.main.genietalk.util.Utils;

public class SplashScreen extends AppCompatActivity implements AuthenticationCallback {

    private static final int REQUEST_CODE_SUCCESS = 12;
    // Splash screen timer
    PrefManager prefManager ;
    private static final int REQUEST_FINGER_PRINT_PERMISSION = 234;
    Dialog dialog=null;
    private IFingerPrintUiHelper fingerPrintUIHelper;
    TextView versionTV;
    SharedPreferenceUtils sharedPreferenceUtils;
    RelativeLayout screenId;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        screenId = (RelativeLayout)findViewById(R.id.screenId);
        sharedPreferenceUtils = SharedPreferenceUtils.getInstance(this);
        if(sharedPreferenceUtils.getBoolanValue(Utils.IS_DARK_THEME,true)){
            setDefaultTheme();
        }
        String themeArra[] = Utils.convertString2Array(this);


        versionTV = (TextView)findViewById(R.id.versionTV);
        versionTV.setTextColor(Color.parseColor(themeArra[17]));

        try {
            versionTV.setText("V"+Utils.getVersion(SplashScreen.this));
        }catch (Exception e){
            e.printStackTrace();
        }

        init();

        prefManager = new PrefManager(this);

        LockManager<CustomPinActivity> lockManager = LockManager.getInstance();
        if(sharedPreferenceUtils.getBoolanValue(Utils.ISPASSWORD,false)) {

            /*Intent intent = new Intent(SplashScreen.this, CustomPinActivity.class);
            intent.putExtra(AppLock.EXTRA_TYPE, AppLock.UNLOCK_PIN);
            startActivityForResult(intent,REQUEST_CODE_SUCCESS);*/
            Intent intent = new Intent(this,PasswordCheck.class);
            startActivity(intent);
            finish();
        }
        else
        if(prefManager.getFingerPrintEnable())
        {
            prefManager.setOnPaus(false);
            initFingerPrint();
        }
        else{
            gotoWelcome();
        }

    }

    public void init(){
        if(sharedPreferenceUtils.getBoolanValue(Utils.IS_DARK_THEME,true)){
            screenId.setBackgroundResource(R.drawable.appbg);
        }else{
            screenId.setBackgroundResource(R.drawable.appbg_white);
        }
    }
    public void setDefaultTheme(){
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
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode==RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_SUCCESS:
                    gotoWelcome();
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void gotoWelcome() {
        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(3*1000);


                    prefManager.setFirstTimeLaunch(true);
                    prefManager.setOnPaus(false);

                    // After 5 seconds redirect to another intent
                    Intent i=new Intent(getBaseContext(),WelcomeActivity.class);
                    startActivity(i);

                    //Remove activity
                    finish();

                } catch (Exception e) {

                }
            }
        };
        // start thread
        background.start();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initFingerPrint() {

        fingerPrintUIHelper = FingerPrintHelperBuilder.getFingerPrintUIHelper(this,this);

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
       // if(fingerPrintUIHelper.initCipher()){
            fingerPrintUIHelper.startListening();
       // }else {
            //show error
         //   Toast.makeText(this, getString(R.string.errore_generico), Toast.LENGTH_SHORT).show();

      //  }
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
       // Toast.makeText(this, getString(R.string.finger_prints_match), Toast.LENGTH_SHORT).show();

            if (dialog != null) {
                dialog.dismiss();
                fingerPrintUIHelper.stopListening();
                gotoWelcome();
            }

    }

    @Override
    public void onAuthenticationFailed() {
        Toast.makeText(this, getString(R.string.auth_failed), Toast.LENGTH_SHORT).show();
    }


}