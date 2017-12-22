package com.main.genietalk.expandable;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.QuickContactBadge;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.Toast;

import com.main.genietalk.R;
import com.main.genietalk.activity.EliteClubActivity;
import com.main.genietalk.activity.HelpActivity;
import com.main.genietalk.activity.LegalActivity;
import com.main.genietalk.activity.NotificationActivity;
import com.main.genietalk.activity.PromotionsActivity;
import com.main.genietalk.activity.ReferEarn;
import com.main.genietalk.activity.SettingActivity;
import com.main.genietalk.activity.TieGenieActivity;
import com.main.genietalk.activity.TransactionActivity;
import com.main.genietalk.activity.WalletActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener,SimpleGestureFilter.SimpleGestureListener {

    RelativeLayout profileEvent,settings,referEarn,notificationRL,helpRL,legalRL,tieGenieRL
            ,eliteClubRL,promotionsRL,transactionRL,walletRL;
    private SimpleGestureFilter detector;
   // SlidingDrawer slidingDrawer;
    LinearLayout handle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
       // slidingDrawer = (SlidingDrawer) findViewById(R.id.slidingDrawer1);

        profileEvent = (RelativeLayout)findViewById(R.id.profileEvent);
        profileEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        settings = (RelativeLayout)findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });
        referEarn = (RelativeLayout)findViewById(R.id.referEarn);
        referEarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ReferEarn.class);
                startActivity(intent);
            }
        });
        notificationRL = (RelativeLayout)findViewById(R.id.notificationRL);
        notificationRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NotificationActivity.class);
                startActivity(intent);
            }
        });
        helpRL = (RelativeLayout)findViewById(R.id.helpRL);
        helpRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,HelpActivity.class);
                startActivity(intent);
            }
        });
        legalRL = (RelativeLayout)findViewById(R.id.legalRL);
        legalRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LegalActivity.class);
                startActivity(intent);
            }
        });
        tieGenieRL = (RelativeLayout)findViewById(R.id.tieGenieRL);
        tieGenieRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TieGenieActivity.class);
                startActivity(intent);
            }
        });
        eliteClubRL = (RelativeLayout)findViewById(R.id.eliteClubRL);
        eliteClubRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,EliteClubActivity.class);
                startActivity(intent);
            }
        });
        promotionsRL = (RelativeLayout)findViewById(R.id.promotionsRL);
        promotionsRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PromotionsActivity.class);
                startActivity(intent);
            }
        });
        transactionRL = (RelativeLayout)findViewById(R.id.transactionRL);
        transactionRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TransactionActivity.class);
                startActivity(intent);
            }
        });
        walletRL = (RelativeLayout)findViewById(R.id.walletRL);
        walletRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,WalletActivity.class);
                startActivity(intent);
            }
        });
        detector = new SimpleGestureFilter(this,this);

        handle = (LinearLayout)findViewById(R.id.handle);
        handle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.stay,R.anim.slide_down);
            }
        });

    }

    protected void onResume() {
        super.onResume();

    }

    protected void onPause() {
        super.onPause();

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent me){
        // Call onTouchEvent of SimpleGestureFilter class
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }

    @Override
    public void onSwipe(int direction) {
        String str = "";

        switch (direction) {

            case SimpleGestureFilter.SWIPE_RIGHT : str = "Swipe Right";
                break;
            case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
                break;
            case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
                  finish();
                  overridePendingTransition(R.anim.stay,R.anim.slide_down);
            //slidingDrawer.open();
                break;
            case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
                //slidingDrawer.close();
            break;

        }
        // Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDoubleTap() {
       // Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
    }
}