package com.main.genietalk.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.activity.HelpActivity;
import com.main.genietalk.activity.ReportAnIssueActivity;
import com.main.genietalk.model.Movie;
import com.main.genietalk.util.Utils;

import java.util.List;

/**
 * Created by gt9 on 29/11/17.
 */

public class UpComingAdapter extends RecyclerView.Adapter<UpComingAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView transactionTV, flightNameTV,flightDateTV,flightPriceTV,helpBtn;


        public MyViewHolder(View view) {
            super(view);

            helpBtn = (TextView)view.findViewById(R.id.helpBtn);
            transactionTV = (TextView)view.findViewById(R.id.transactionTV);
            flightNameTV = (TextView)view.findViewById(R.id.flightNameTV);
            flightDateTV = (TextView)view.findViewById(R.id.flightDateTV);
            flightPriceTV = (TextView)view.findViewById(R.id.flightPriceTV);
        }
    }


    public UpComingAdapter(Context mContext, List<Movie> moviesList) {
        this.moviesList = moviesList;
        this.mContext = mContext;
    }

    @Override
    public UpComingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_upcoming, parent, false);

        return new UpComingAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UpComingAdapter.MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        setColorsFromArray(holder);
        holder.helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,ReportAnIssueActivity.class);
                //intent.putExtra(Utils.HELP_DETAIL_KEY,Utils.REPORT_AN_ISSUE);
                mContext.startActivity(intent);
            }
        });

    }
    public void setColorsFromArray(UpComingAdapter.MyViewHolder holder){
        String themeArra[] = Utils.convertString2Array(mContext);

        holder.transactionTV.setTextColor(Color.parseColor(themeArra[19]));

        holder.flightNameTV.setTextColor(Color.parseColor(themeArra[18]));
        holder.flightPriceTV.setTextColor(Color.parseColor(themeArra[18]));
        holder.flightDateTV.setTextColor(Color.parseColor(themeArra[19]));


    }
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}

