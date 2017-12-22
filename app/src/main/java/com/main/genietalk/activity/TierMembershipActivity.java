package com.main.genietalk.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.adapter.NotificationAdapter;
import com.main.genietalk.adapter.TierMembershipAdapter;
import com.main.genietalk.extra.RecyclerTouchListener;
import com.main.genietalk.model.Movie;
import com.main.genietalk.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gt9 on 30/11/17.
 */

public class TierMembershipActivity extends BaseActivity {
    RecyclerView recycler_view_notifications;
    private List<Movie> movieList = new ArrayList<>();
    private TierMembershipAdapter mAdapter;
    ImageView backBtn;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);

        /*if(getIntent()!=null && getIntent().getStringExtra(Utils.TIER_BENIFIT_KEY)!=null){
            headerTV.setText("Tier Membership");
        }*/
        init();
        recycler_view_notifications = (RecyclerView)findViewById(R.id.recycler_view_notifications);
        backBtn = (ImageView)findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mAdapter = new TierMembershipAdapter(this,movieList);

        recycler_view_notifications.setHasFixedSize(true);

        // vertical RecyclerView
        // keep movie_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());


        recycler_view_notifications.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recycler_view_notifications.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        recycler_view_notifications.setItemAnimator(new DefaultItemAnimator());

        recycler_view_notifications.setAdapter(mAdapter);

        // row click listener
        recycler_view_notifications.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recycler_view_notifications, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = movieList.get(position);
                //Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(TierMembershipActivity.this,MemberBenifitsActivity.class);
                 intent.putExtra(Utils.TIER_BENIFIT_KEY,movie.getTitle());
                 startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareMovieData();

    }

    private void init() {
        String themeArra[] = Utils.convertString2Array(this);
        screenId = (LinearLayout)findViewById(R.id.screenId);
        screenId.setBackgroundColor(Color.parseColor(themeArra[1]));
        headerRL = (RelativeLayout)findViewById(R.id.headerRL);
        headerRL.setBackgroundColor(Color.parseColor(themeArra[5]));
        headerTV = (TextView)findViewById(R.id.headerTV);
        headerTV.setText(getString(R.string.tier_member));
        headerTV.setTextColor(Color.parseColor(themeArra[6]));
    }

    private void prepareMovieData() {
        Movie movie = new Movie(Utils.TierBenifits.noMember, "21 sept 2017,9:30 PM", "2015");
        movie.setImgId(R.drawable.nomember);
        movieList.add(movie);

        movie = new Movie(Utils.TierBenifits.baseMember, "21 sept 2017,9:30 PM", "2015");
        movie.setImgId(R.drawable.ic_basemember);
        movieList.add(movie);

        movie = new Movie(Utils.TierBenifits.silverMember, "21 sept 2017,9:30 PM", "2015");
        movie.setImgId(R.drawable.silver);
        movieList.add(movie);

        movie = new Movie(Utils.TierBenifits.goldMember, "21 sept 2017,9:30 PM", "2015");
        movie.setImgId(R.drawable.gold);
        movieList.add(movie);

        movie = new Movie(Utils.TierBenifits.platinumMember, "21 sept 2017,9:30 PM", "2015");
        movie.setImgId(R.drawable.platinum);
        movieList.add(movie);

        movie = new Movie(Utils.TierBenifits.eliteGoldMember, "21 sept 2017,9:30 PM", "2015");
        movie.setImgId(R.drawable.elite_gold);
        movieList.add(movie);

        movie = new Movie(Utils.TierBenifits.elitePlatinumMember, "21 sept 2017,9:30 PM", "2015");
        movie.setImgId(R.drawable.elite_platinum);
        movieList.add(movie);


        // notify adapter about data set changes
        // so that it will render the list with new data
        mAdapter.notifyDataSetChanged();
    }
}
