package com.main.genietalk.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.adapter.SpinnerAdapter;
import com.main.genietalk.util.Utils;

import java.util.ArrayList;

public class TieGenieActivity extends BaseActivity {

    ImageView backBtn;
    TextView descriptionTV;
    Button payBtn;
    ImageView imgDropDown;
    SpinnerAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tie_genie_activity_layout);
        payBtn = (Button)findViewById(R.id.payBtn);
        String colors[] = {"$","rs"};
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("$");
        arrayList.add("rs");

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        adapter = new SpinnerAdapter(TieGenieActivity.this, R.layout.spinner_item, arrayList);
        spinner.setAdapter(adapter);
        imgDropDown = (ImageView)findViewById(R.id.imgDropDown);
        imgDropDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.performClick();
            }
        });
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        backBtn = (ImageView)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String themeArra[] = Utils.convertString2Array(this);

        screenId = (LinearLayout)findViewById(R.id.screenId);
        screenId.setBackgroundColor(Color.parseColor(themeArra[1]));
        headerRL = (RelativeLayout)findViewById(R.id.headerRL);
        headerRL.setBackgroundColor(Color.parseColor(themeArra[5]));
        headerTV = (TextView)findViewById(R.id.headerTV);
        headerTV.setText(getString(R.string.tip_genie));
        headerTV.setTextColor(Color.parseColor(themeArra[6]));

        descriptionTV = (TextView) findViewById(R.id.descriptionTV);
        descriptionTV.setTextColor(Color.parseColor(themeArra[17]));

    }

}