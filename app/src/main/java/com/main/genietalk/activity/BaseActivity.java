package com.main.genietalk.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.util.SharedPreferenceUtils;
import com.main.genietalk.util.Utils;

/**
 * Created by gt9 on 30/11/17.
 */

public class BaseActivity extends AppCompatActivity{
    SharedPreferenceUtils sharedPreferenceUtils;
    LinearLayout screenId;
    TextView headerTV;
    RelativeLayout headerRL;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
       // setTheme(R.style.AppTheme1);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sharedPreferenceUtils = SharedPreferenceUtils.getInstance(this);
        /*if(sharedPreferenceUtils.getBoolanValue(Utils.IS_DARK_THEME,true)){
            setTheme(R.style.AppTheme);
        }else{
            setTheme(R.style.AppThemeLight);
        }*/
        super.onCreate(savedInstanceState);

    }
}
