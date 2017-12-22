package com.main.genietalk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.main.genietalk.R;
import com.main.genietalk.activity.MemberBenifitsActivity;

import com.main.genietalk.activity.TierMembershipActivity;
import com.main.genietalk.adapter.GenieMoneyAdapter;

import com.main.genietalk.extra.RecyclerTouchListener;
import com.main.genietalk.model.Movie;


import java.util.ArrayList;
import java.util.List;


public class GenieRewardFragment extends Fragment {

    private List<Movie> movieList = new ArrayList<>();
    private GenieMoneyAdapter mAdapter;
    RecyclerView recycler_view_notifications;
    LinearLayout tieBenifits;
    ImageView centreImg,leftImg,rightImg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.genie_reward_layout,container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recycler_view_notifications = (RecyclerView)view.findViewById(R.id.recycler_view);
        tieBenifits = (LinearLayout)view.findViewById(R.id.tieBenifits);
        mAdapter = new GenieMoneyAdapter(getActivity(),movieList);

        recycler_view_notifications.setHasFixedSize(true);

        // vertical RecyclerView
        // keep movie_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        // horizontal RecyclerView
        // keep movie_list_row.xml width to `wrap_content`
        // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);

        recycler_view_notifications.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recycler_view_notifications.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        // adding custom divider line with padding 16dp
        // recyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        recycler_view_notifications.setItemAnimator(new DefaultItemAnimator());

        recycler_view_notifications.setAdapter(mAdapter);

        // row click listener
        recycler_view_notifications.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recycler_view_notifications, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Movie movie = movieList.get(position);
                //Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        tieBenifits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MemberBenifitsActivity.class);
                //intent.putExtra(Utils.TIER_BENIFIT_KEY,Utils.TIER_BENIFIT);
                startActivity(intent);
            }
        });
        centreImg = (ImageView)view.findViewById(R.id.centreImg);
        centreImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MemberBenifitsActivity.class);
                //intent.putExtra(Utils.TIER_BENIFIT_KEY,Utils.TIER_BENIFIT);
                startActivity(intent);
            }
        });
        leftImg = (ImageView)view.findViewById(R.id.leftImg);
        leftImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TierMembershipActivity.class);
                //intent.putExtra(Utils.TIER_BENIFIT_KEY,Utils.TIER_BENIFIT);
                startActivity(intent);
            }
        });
        rightImg = (ImageView)view.findViewById(R.id.rightImg);
        rightImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TierMembershipActivity.class);
                //intent.putExtra(Utils.TIER_BENIFIT_KEY,Utils.TIER_BENIFIT);
                startActivity(intent);
            }
        });
        prepareMovieData();

        // RecyclerView has some built in animations to it, using the DefaultItemAnimator.
        // Specifically when you call notifyItemChanged() it does a fade animation for the changing
        // of the data in the ViewHolder. If you would like to disable this you can use the following:

    }

    private void prepareMovieData() {
        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);

        movie = new Movie("Inside Out", "Animation, Kids & Family", "2015");
        movieList.add(movie);

        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        movieList.add(movie);

        movie = new Movie("Shaun the Sheep", "Animation", "2015");
        movieList.add(movie);

        movie = new Movie("The Martian", "Science Fiction & Fantasy", "2015");
        movieList.add(movie);

        movie = new Movie("Mission: Impossible Rogue Nation", "Action", "2015");
        movieList.add(movie);

        movie = new Movie("Up", "Animation", "2009");
        movieList.add(movie);

        movie = new Movie("Star Trek", "Science Fiction", "2009");
        movieList.add(movie);

        movie = new Movie("The LEGO Movie", "Animation", "2014");
        movieList.add(movie);

        movie = new Movie("Iron Man", "Action & Adventure", "2008");
        movieList.add(movie);

        movie = new Movie("Aliens", "Science Fiction", "1986");
        movieList.add(movie);

        movie = new Movie("Chicken Run", "Animation", "2000");
        movieList.add(movie);

        movie = new Movie("Back to the Future", "Science Fiction", "1985");
        movieList.add(movie);

        movie = new Movie("Raiders of the Lost Ark", "Action & Adventure", "1981");
        movieList.add(movie);

        movie = new Movie("Goldfinger", "Action & Adventure", "1965");
        movieList.add(movie);

        movie = new Movie("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        movieList.add(movie);

        // notify adapter about data set changes
        // so that it will render the list with new data
        mAdapter.notifyDataSetChanged();
    }
    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adapter.onRestoreInstanceState(savedInstanceState);
    }*/
}

