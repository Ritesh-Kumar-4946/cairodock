package com.main.genietalk.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.main.genietalk.R;

import java.lang.reflect.Field;

/**
 * Created by gt9 on 30/11/17.
 */

public class Utils {
    public static final String BASEURL = "http://192.168.10.116/genieapptest/api/version1/";


    public static final int REQUEST_CODE_ENABLE = 11;
    public static final String BENIFIT_KEY = "benifit_key";
    public static final String BENIFIT = "Benifit";
    public static final String TIER_BENIFIT_KEY = "tier_benifit_key";
    public static final String TIER_BENIFIT = "Tier Benifit";

    public static final String HELP_DETAIL_KEY = "help_detail_key";

    public static final String REPORT_AN_ISSUE = "Report an Issue";
    public static final String FAQ = "FAQ";
    public static final String SUBMIT_FEEDBACK = "Submit Feedback";

    public static final String LEGAL_DETAIL_KEY = "legal_detail_key";

    public static final String ABOUT_US = "About Us";
    public static final String TERM_AND_CONDITIONS = "Term and Condition";
    public static final String PRIVACY_POLICY = "Privacy Policy";
    public static final String REFUND_CANCELLATION_POLICY = "Refund & Cancellation Policy";
    public static final String LOCATION_INFORMATION_POLICY = "Location Information Policy";
    public static final String DATA_PROVIDER = "Data Provider";
    public static final String SOFTWARE_LICENCE = "Software Licence";
    public static final String COPY_RIGHT = "Copy Right";

    public static final String BUSSINESS_PROFILE_KEY = "business_profile_key";

    public static final String BUSSINESS_PROFILE = "Bussiness Profile";
    public static final String ADD_BUSSINESS_PROFILE = "Add Bussiness Profile";

    /*shared preference*/
    public static final String IS_DARK_THEME = "is_dark_theme";
    public static final String USERID = "userid";
    public static final String RAPARAM = "ra_param";
    public static final String THEMEARRAY = "themearray";
    public static final String PASSWORD = "password";
    public static final String ISPASSWORD = "ispassword";


    public interface TierBenifits{
        String noMember = "No Member";
        String baseMember = "Base Member";
        String silverMember = "Silver Member";
        String goldMember = "Gold Member";
        String platinumMember = "Platinum Member";
        String eliteGoldMember = "Elite Gold";
        String elitePlatinumMember = "Elite Platinum";
    }
    public interface TierBenifitsImage{
        int noMember = R.drawable.nomember;
        int baseMember = R.drawable.ic_basemember;
        int silverMember = R.drawable.silver;
        int goldMember = R.drawable.gold;
        int platinumMember = R.drawable.platinum;
        int eliteGoldMember = R.drawable.elite_gold;
        int elitePlatinumMember = R.drawable.elite_platinum;
    }
    public static String getDeviceId(Context mContext){
        TelephonyManager telephonyManager = (TelephonyManager)mContext.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }
    public static String getVersion(Context context) {
        String version = "";
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(
                    context.getPackageName(), 0);
             version = info.versionName;
            return version;
        }catch (Exception e){
            e.printStackTrace();
        }
        return version;
    }

    public static int getCommand(){

        return 0;
    }

    public static String[] convertString2Array(Context mContext){
        SharedPreferenceUtils sharedPreferenceUtils = SharedPreferenceUtils.getInstance(mContext);
        String s = sharedPreferenceUtils.getStringValue(Utils.THEMEARRAY,"");
        String[] str = s.split("\\|\\|\\|");
        for(String str1:str){
            Log.d("str",str1);
        }
        return str;
    }

    public static String convertArray2String(Context mContext,String[] str){

        SharedPreferenceUtils sharedPreferenceUtils = SharedPreferenceUtils.getInstance(mContext);
        String delemeter = "|||";
        StringBuilder b = new StringBuilder();

        for(String s:str){
            b.append(s+"|||");
        }
        sharedPreferenceUtils.setValue(THEMEARRAY,b.toString());
        return b.toString();

    }
    //getId("icon", R.drawable.class);
    public static int getId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }
    }
}
