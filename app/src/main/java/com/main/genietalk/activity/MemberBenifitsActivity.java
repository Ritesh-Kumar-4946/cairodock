package com.main.genietalk.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.util.Utils;

/**
 * Created by gt9 on 30/11/17.
 */

public class MemberBenifitsActivity extends BaseActivity {
    ImageView backBtn;
    LinearLayout headerLL1,headerLL2,headerLL3;
    LinearLayout screenId;
    TextView headerTV;
    RelativeLayout headerRL;
    TextView pointsTV,descriptionTV;
    TextView lougeAssests,movieTicket,speedoMeter;
    ImageView lougeImg,movieTicketImg,speedoMeterImg;
    View seprator1,seprator2,seprator3;
    String headerString;
    ImageView baseMemberImg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memberbenifitslayout);
        backBtn = (ImageView)findViewById(R.id.backBtn);
        baseMemberImg = (ImageView)findViewById(R.id.baseMemberImg);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if(getIntent()!=null && getIntent().getStringExtra(Utils.TIER_BENIFIT_KEY)!=null){
            if(getIntent().getStringExtra(Utils.TIER_BENIFIT_KEY).equalsIgnoreCase(Utils.TierBenifits.noMember)){
                headerString = Utils.TierBenifits.noMember+" Benifits";
                baseMemberImg.setImageResource(Utils.TierBenifitsImage.noMember);
            }else if(getIntent().getStringExtra(Utils.TIER_BENIFIT_KEY).equalsIgnoreCase(Utils.TierBenifits.baseMember)){
                headerString = Utils.TierBenifits.baseMember+" Benifits";
                baseMemberImg.setImageResource(Utils.TierBenifitsImage.baseMember);
            }else if(getIntent().getStringExtra(Utils.TIER_BENIFIT_KEY).equalsIgnoreCase(Utils.TierBenifits.silverMember)){
                headerString = Utils.TierBenifits.silverMember+" Benifits";
                baseMemberImg.setImageResource(Utils.TierBenifitsImage.silverMember);
            }else if(getIntent().getStringExtra(Utils.TIER_BENIFIT_KEY).equalsIgnoreCase(Utils.TierBenifits.goldMember)){
                headerString = Utils.TierBenifits.goldMember+" Benifits";
                baseMemberImg.setImageResource(Utils.TierBenifitsImage.goldMember);
            }else if(getIntent().getStringExtra(Utils.TIER_BENIFIT_KEY).equalsIgnoreCase(Utils.TierBenifits.platinumMember)){
                headerString = Utils.TierBenifits.platinumMember+" Benifits";
                baseMemberImg.setImageResource(Utils.TierBenifitsImage.platinumMember);
            }else if(getIntent().getStringExtra(Utils.TIER_BENIFIT_KEY).equalsIgnoreCase(Utils.TierBenifits.eliteGoldMember)){
                headerString = Utils.TierBenifits.eliteGoldMember+" Benifits";
                baseMemberImg.setImageResource(Utils.TierBenifitsImage.eliteGoldMember);
            }else if(getIntent().getStringExtra(Utils.TIER_BENIFIT_KEY).equalsIgnoreCase(Utils.TierBenifits.elitePlatinumMember)){
                headerString = Utils.TierBenifits.elitePlatinumMember+" Benifits";
                baseMemberImg.setImageResource(Utils.TierBenifitsImage.elitePlatinumMember);
            }else{
                headerString = "Base Member Benifits";
                baseMemberImg.setImageResource(R.drawable.ic_basemember);
            }
        }else{
            headerString = "Base Member Benifits";
            baseMemberImg.setImageResource(R.drawable.ic_basemember);
        }

        init();

    }

    private void init() {
        String themeArra[] = Utils.convertString2Array(this);
        screenId = (LinearLayout)findViewById(R.id.screenId);
        screenId.setBackgroundColor(Color.parseColor(themeArra[1]));
        headerRL = (RelativeLayout)findViewById(R.id.headerRL);
        headerRL.setBackgroundColor(Color.parseColor(themeArra[5]));
        headerTV = (TextView)findViewById(R.id.headerTV);
        headerTV.setText((headerString));
        headerTV.setTextColor(Color.parseColor(themeArra[6]));

        lougeAssests = (TextView)findViewById(R.id.lougeAssests);
        lougeAssests.setTextColor(Color.parseColor(themeArra[18]));
        movieTicket = (TextView)findViewById(R.id.movieTicket);
        movieTicket.setTextColor(Color.parseColor(themeArra[18]));
        speedoMeter = (TextView)findViewById(R.id.speedoMeter);
        speedoMeter.setTextColor(Color.parseColor(themeArra[18]));

        pointsTV = (TextView) findViewById(R.id.pointsTV);
        pointsTV.setTextColor(Color.parseColor(themeArra[17]));
        descriptionTV = (TextView)findViewById(R.id.descriptionTV);
        descriptionTV.setTextColor(Color.parseColor(themeArra[18]));

        lougeImg = (ImageView)findViewById(R.id.lougeImg);
        lougeImg.setImageResource(Utils.getId(themeArra[31], R.drawable.class));
        movieTicketImg = (ImageView)findViewById(R.id.movieTicketImg);
        movieTicketImg.setImageResource(Utils.getId(themeArra[30], R.drawable.class));
        speedoMeterImg = (ImageView)findViewById(R.id.speedoMeterImg);
        speedoMeterImg.setImageResource(Utils.getId(themeArra[29], R.drawable.class));

        seprator1 = (View)findViewById(R.id.seprator1);
        seprator1.setBackgroundColor(Color.parseColor(themeArra[15]));
        seprator2 = (View)findViewById(R.id.seprator2);
        seprator2.setBackgroundColor(Color.parseColor(themeArra[15]));
        seprator3 = (View)findViewById(R.id.seprator3);
        seprator3.setBackgroundColor(Color.parseColor(themeArra[15]));

    }

}
