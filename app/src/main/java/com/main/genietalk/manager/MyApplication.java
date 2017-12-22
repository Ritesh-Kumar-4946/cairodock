package com.main.genietalk.manager;

import android.app.Application;
import android.support.multidex.MultiDex;

;
import com.github.orangegangsters.lollipin.lib.managers.LockManager;
import com.main.genietalk.R;
import com.main.genietalk.activity.CustomPinActivity;



/**
 * Created by oliviergoutay on 1/14/15.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        LockManager<CustomPinActivity> lockManager = LockManager.getInstance();
        lockManager.enableAppLock(this, CustomPinActivity.class);

      //  lockManager.getAppLock().setLogoId(R.drawable.security_lock);
    }

}
