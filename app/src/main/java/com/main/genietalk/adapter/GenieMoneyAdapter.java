package com.main.genietalk.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.model.Movie;
import com.main.genietalk.util.Utils;

import java.util.List;


public class GenieMoneyAdapter extends RecyclerView.Adapter<GenieMoneyAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView)view.findViewById(R.id.title);
            description = (TextView)view.findViewById(R.id.description);

        }
    }


    public GenieMoneyAdapter(Context mContext, List<Movie> moviesList) {
        this.moviesList = moviesList;
        this.mContext = mContext;
    }

    @Override
    public GenieMoneyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_geniemoney, parent, false);

        return new GenieMoneyAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GenieMoneyAdapter.MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.description.setText(movie.getGenre());
        setColorsFromArray(holder);
    }
    public void setColorsFromArray(GenieMoneyAdapter.MyViewHolder holder){
        String themeArra[] = Utils.convertString2Array(mContext);
        holder.title.setTextColor(Color.parseColor(themeArra[18]));
        holder.description.setTextColor(Color.parseColor(themeArra[19]));
    }
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
