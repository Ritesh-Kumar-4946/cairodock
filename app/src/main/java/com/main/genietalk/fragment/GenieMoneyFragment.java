package com.main.genietalk.fragment;

import android.graphics.Color;
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
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.adapter.GenieMoneyAdapter;
import com.main.genietalk.adapter.UpComingAdapter;
import com.main.genietalk.extra.RecyclerTouchListener;
import com.main.genietalk.model.Movie;
import com.main.genietalk.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gt9 on 30/11/17.
 */

public class GenieMoneyFragment extends Fragment {
    private List<Movie> movieList = new ArrayList<>();
    private GenieMoneyAdapter mAdapter;
    RecyclerView recycler_view_notifications;
    TextView priceTV;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.genie_money_layout,container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String themeArra[] = Utils.convertString2Array(getActivity());
        priceTV = (TextView)view.findViewById(R.id.priceTV);
        priceTV.setTextColor(Color.parseColor(themeArra[17]));
        recycler_view_notifications = (RecyclerView)view.findViewById(R.id.recycler_view);

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

        prepareMovieData();
        // RecyclerView has some built in animations to it, using the DefaultItemAnimator.
        // Specifically when you call notifyItemChanged() it does a fade animation for the changing
        // of the data in the ViewHolder. If you would like to disable this you can use the following:

    }
    private void prepareMovieData() {
        Movie movie = new Movie("Tata sky recharge", "21 sep 2017,9:30 PM", "2015");
        movieList.add(movie);

        movie = new Movie("Tata sky recharge", "21 sep 2017,9:30 PM",  "2015");
        movieList.add(movie);

        movie = new Movie("Tata sky recharge", "21 sep 2017,9:30 PM",  "2015");
        movieList.add(movie);

        movie = new Movie("Tata sky recharge", "21 sep 2017,9:30 PM",  "2015");
        movieList.add(movie);

        movie = new Movie("Tata sky recharge", "21 sep 2017,9:30 PM",  "2015");
        movieList.add(movie);

        movie = new Movie("Tata sky recharge", "21 sep 2017,9:30 PM",  "2015");
        movieList.add(movie);

        movie = new Movie("Tata sky recharge", "21 sep 2017,9:30 PM",  "2009");
        movieList.add(movie);

        movie = new Movie("Tata sky recharge", "21 sep 2017,9:30 PM",  "2009");
        movieList.add(movie);

        movie = new Movie("Tata sky recharge", "21 sep 2017,9:30 PM",  "2014");
        movieList.add(movie);

        movie = new Movie("Tata sky recharge", "21 sep 2017,9:30 PM",  "2008");
        movieList.add(movie);

        movie = new Movie("Tata sky recharge", "21 sep 2017,9:30 PM",  "1986");
        movieList.add(movie);

        movie = new Movie("Tata sky recharge", "21 sep 2017,9:30 PM",  "2000");
        movieList.add(movie);

        movie = new Movie("Tata sky recharge", "21 sep 2017,9:30 PM", "1985");
        movieList.add(movie);

        movie = new Movie("Tata sky recharge", "21 sep 2017,9:30 PM",  "1981");
        movieList.add(movie);

        movie = new Movie("Tata sky recharge", "21 sep 2017,9:30 PM", "1965");
        movieList.add(movie);

        movie = new Movie("Tata sky recharge", "21 sep 2017,9:30 PM",  "2014");
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

