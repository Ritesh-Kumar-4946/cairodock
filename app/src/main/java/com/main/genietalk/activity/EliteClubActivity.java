package com.main.genietalk.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.util.Utils;


public class EliteClubActivity extends BaseActivity {
     ImageView backBtn;

    TextView headerTV;
    LinearLayout screenId;
    RelativeLayout headerRL;
    TextView pointsTV,eliteClubTV,subScriptionTV,remainingDaysTV;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elite_club_activity_layout);

        String themeArra[] = Utils.convertString2Array(this);

        screenId = (LinearLayout)findViewById(R.id.screenId);
        screenId.setBackgroundColor(Color.parseColor(themeArra[1]));
        headerRL = (RelativeLayout)findViewById(R.id.headerRL);
        headerRL.setBackgroundColor(Color.parseColor(themeArra[5]));
        headerTV = (TextView)findViewById(R.id.headerTV);
        headerTV.setText(getString(R.string.elite_club));
        headerTV.setTextColor(Color.parseColor(themeArra[6]));

        backBtn = (ImageView)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pointsTV = (TextView)findViewById(R.id.pointsTV);
        pointsTV.setTextColor(Color.parseColor(themeArra[17]));
        eliteClubTV = (TextView)findViewById(R.id.eliteClubTV);
        eliteClubTV.setTextColor(Color.parseColor(themeArra[6]));
        subScriptionTV = (TextView)findViewById(R.id.subScriptionTV);
        subScriptionTV.setTextColor(Color.parseColor(themeArra[19]));
        remainingDaysTV = (TextView)findViewById(R.id.remainingDaysTV);
        remainingDaysTV.setTextColor(Color.parseColor(themeArra[17]));

    }
    public void showBenifits(View view){
        Intent intent = new Intent(EliteClubActivity.this,PromotionsActivity.class);
        intent.putExtra(Utils.BENIFIT_KEY,Utils.BENIFIT);
        startActivity(intent);
    }
}
