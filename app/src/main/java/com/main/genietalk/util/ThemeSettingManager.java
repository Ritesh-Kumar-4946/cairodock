package com.main.genietalk.util;

import android.content.Context;
import android.graphics.PorterDuff;
import android.widget.ImageView;
import android.widget.TextView;

import com.main.genietalk.R;

import lal.adhish.gifprogressbar.GifView;

/**
 * Created by gt9 on 6/12/17.
 */

public class ThemeSettingManager {
    public static ThemeSettingManager themeSettingManager;
    SharedPreferenceUtils sharedPreferenceUtils;
    private ThemeSettingManager(){

    }
    public static ThemeSettingManager getInstance(){
        if(themeSettingManager==null){
            themeSettingManager = new ThemeSettingManager();
        }
        return themeSettingManager;
    }

    public void setTextColor(Context mContext, TextView tv){
        sharedPreferenceUtils = SharedPreferenceUtils.getInstance(mContext);
        if(sharedPreferenceUtils.getBoolanValue(Utils.IS_DARK_THEME,true)){
              tv.setTextColor(mContext.getResources().getColor(R.color.gold));
        }else{
            tv.setTextColor(mContext.getResources().getColor(R.color.primary_text));
        }
    }

    public void setImageColor(Context mContext, ImageView imageView){
        sharedPreferenceUtils = SharedPreferenceUtils.getInstance(mContext);
        if(sharedPreferenceUtils.getBoolanValue(Utils.IS_DARK_THEME,true)){

            imageView.setColorFilter(mContext.getResources().getColor(R.color.gold), PorterDuff.Mode.SRC_IN);
        }else{
            imageView.setColorFilter(mContext.getResources().getColor(R.color.primary_text), PorterDuff.Mode.SRC_IN);
        }
    }
    public void setLoader(Context mContext, GifView imageView){
        sharedPreferenceUtils = SharedPreferenceUtils.getInstance(mContext);
        if(sharedPreferenceUtils.getBoolanValue(Utils.IS_DARK_THEME,true)){
            imageView.setImageResource(R.drawable.loader_black);
            //imageView.setImageResource(R.drawable.loader_black);
        }else{
            imageView.setImageResource(R.drawable.loader);
            //imageView.setImageResource(R.drawable.loader);

        }
    }
}
