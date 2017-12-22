package com.main.genietalk.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.adapter.PromotionsAdapter;
import com.main.genietalk.extra.RecyclerTouchListener;
import com.main.genietalk.model.Movie;
import com.main.genietalk.util.SharedPreferenceUtils;
import com.main.genietalk.util.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.main.genietalk.activity.ThemeActivity.themeArr;


public class PromotionsActivity extends BaseActivity {
    RecyclerView recycler_view_notifications;
    private List<Movie> movieList = new ArrayList<>();
    private PromotionsAdapter mAdapter;
    TextView promoHeader;
    LinearLayout screenId;
    RelativeLayout headerRL;
    SharedPreferenceUtils sharedPreferenceUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.promotions_activity_layout);

        sharedPreferenceUtils = SharedPreferenceUtils.getInstance(this);
       /* String themeArr = sharedPreferenceUtils.getStringValue("themeArr","");

        Log.d("theme",themeArr.toString());*/
       // Log.d("theme",themeArr[1]);
        String themeArra[] = Utils.convertString2Array(this);
        screenId = (LinearLayout)findViewById(R.id.screenId);
        screenId.setBackgroundColor(Color.parseColor(themeArra[1]));
        headerRL = (RelativeLayout)findViewById(R.id.headerRL);
        headerRL.setBackgroundColor(Color.parseColor(themeArra[5]));
        promoHeader = (TextView)findViewById(R.id.promoHeader);
        promoHeader.setTextColor(Color.parseColor(themeArra[6]));


        if(getIntent()!=null && getIntent().getStringExtra(Utils.BENIFIT_KEY)!=null){
            promoHeader.setText(getIntent().getStringExtra(Utils.BENIFIT_KEY));
        }

        recycler_view_notifications = (RecyclerView)findViewById(R.id.recycler_view_notifications);
        recycler_view_notifications.setVisibility(View.VISIBLE);
        mAdapter = new PromotionsAdapter(movieList);

        recycler_view_notifications.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recycler_view_notifications.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recycler_view_notifications.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        recycler_view_notifications.setItemAnimator(new DefaultItemAnimator());
        prepareMovieData();
        recycler_view_notifications.setAdapter(mAdapter);

        // row click listener
        recycler_view_notifications.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recycler_view_notifications, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
               // Movie movie = movieList.get(position);
               // Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));



    }
    public void backAction(View view){
        finish();
    }
    private void prepareMovieData() {
        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);

        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);




        // notify adapter about data set changes
        // so that it will render the list with new data
       // mAdapter.notifyDataSetChanged();
    }
}
