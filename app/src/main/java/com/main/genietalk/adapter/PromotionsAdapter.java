package com.main.genietalk.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.model.Movie;

import java.util.List;

/**
 * Created by gt9 on 29/11/17.
 */

public class PromotionsAdapter extends RecyclerView.Adapter<PromotionsAdapter.MyViewHolder> {

    private List<Movie> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;

        public MyViewHolder(View view) {
            super(view);
            /*title = (TextView)view.findViewById(R.id.title);
            description = (TextView)view.findViewById(R.id.description);*/

        }
    }


    public PromotionsAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public PromotionsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_promotions, parent, false);

        return new PromotionsAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PromotionsAdapter.MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        /*holder.title.setText(movie.getTitle());
        holder.description.setText(movie.getGenre());*/

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
