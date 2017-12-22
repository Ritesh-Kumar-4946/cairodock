package com.main.genietalk.activity;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lincoln on 05/05/16.
 */
public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "androidhive-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String TIME_DELAY = "TIME_DELAY";
    private static final String ONPAUSE = "ONPAUSE";
    private static final String KEY_USER_FINFER_PRINT = "KEY_USER_FINFER_PRINT";
    private static final String PASSWORDENABLE = "OK";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public void setTimeOnPaus(long milisec) {
        editor.putLong(TIME_DELAY, milisec);
        editor.commit();
    }

    public void setOnPaus(boolean bool) {
        editor.putBoolean(ONPAUSE, bool);
        editor.commit();
    }

    public boolean getOnPaus() {
        return pref.getBoolean(ONPAUSE, false);
    }

    public void saveFingerPrintEnable(boolean value) {
        // preferences.putBoolean(Preferences.Key.KEY_USER_FINFER_PRINT, value);
        editor.putBoolean(KEY_USER_FINFER_PRINT, value);
        editor.commit();
    }

    public boolean getFingerPrintEnable() {
        return pref.getBoolean(KEY_USER_FINFER_PRINT, false);
    }

    public long getTimeOnResume() {
        return pref.getLong(TIME_DELAY, 0);
    }

}
