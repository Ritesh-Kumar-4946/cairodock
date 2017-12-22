package com.main.genietalk.adapter;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.model.Movie;
import com.main.genietalk.util.Utils;

import java.util.List;

public class TierMembershipAdapter extends RecyclerView.Adapter<TierMembershipAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        ImageView membershipImg;
        View seprator1;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView)view.findViewById(R.id.title);
            description = (TextView)view.findViewById(R.id.description);
            membershipImg = (ImageView)view.findViewById(R.id.membershipImg);
            seprator1 = (View)view.findViewById(R.id.seprator1);
        }
    }


    public TierMembershipAdapter(Context mContext,List<Movie> moviesList) {
        this.moviesList = moviesList;
        this.mContext = mContext;
    }

    @Override
    public TierMembershipAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_tier_membership, parent, false);

        return new TierMembershipAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TierMembershipAdapter.MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.description.setText(movie.getGenre());
        holder.membershipImg.setImageResource(movie.getImgId());
        setColorsFromArray(holder);
    }
    public void setColorsFromArray(TierMembershipAdapter.MyViewHolder holder){
        String themeArra[] = Utils.convertString2Array(mContext);
        holder.title.setTextColor(Color.parseColor(themeArra[17]));
        holder.description.setTextColor(Color.parseColor(themeArra[19]));
        holder.seprator1.setBackgroundColor(Color.parseColor(themeArra[15]));
    }
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
