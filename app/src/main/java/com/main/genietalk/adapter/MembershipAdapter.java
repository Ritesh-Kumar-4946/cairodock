package com.main.genietalk.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.model.Movie;

import java.util.List;

/**
 * Created by gt9 on 29/11/17.
 */

public class MembershipAdapter extends RecyclerView.Adapter<MembershipAdapter.MyViewHolder> {

    private List<Movie> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        RelativeLayout cardRL;
        public MyViewHolder(View view) {
            super(view);
            /*title = (TextView)view.findViewById(R.id.title);
            description = (TextView)view.findViewById(R.id.description);*/
            cardRL = (RelativeLayout)view.findViewById(R.id.cardRL);
        }
    }


    public MembershipAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MembershipAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_membership, parent, false);

        return new MembershipAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MembershipAdapter.MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
       // holder.cardRL.setBackgroundColor(randomColor(7));

    }
    public int randomColor(int alpha) {

        int r = (int) (0xff * Math.random());
        int g = (int) (0xff * Math.random());
        int b = (int) (0xff * Math.random());

        return Color.argb(alpha, r, g, b);
    }
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}

