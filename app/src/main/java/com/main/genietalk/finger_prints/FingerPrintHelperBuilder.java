package com.main.genietalk.finger_prints;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by angelomoroni on 06/02/17.
 */

public class FingerPrintHelperBuilder {

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static IFingerPrintUiHelper getFingerPrintUIHelper(Context context, AuthenticationCallback authenticationCallback){
        IFingerPrintUiHelper fingerPrintUIHelper;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            fingerPrintUIHelper = new FingerPrintUIHelper(context, authenticationCallback);
        }else {
            fingerPrintUIHelper = new OldFingerPrntUIHelper();
        }

        return fingerPrintUIHelper;
    }
}
