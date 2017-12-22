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

public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        View dividerLine;
        public MyViewHolder(View view) {
            super(view);
            title = (TextView)view.findViewById(R.id.title);
            description = (TextView)view.findViewById(R.id.description);
            dividerLine = (View)view.findViewById(R.id.dividerLine);
        }
    }


    public DocumentAdapter(Context mContext,List<Movie> moviesList) {
        this.moviesList = moviesList;
        this.mContext = mContext;
    }

    @Override
    public DocumentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_documentation, parent, false);

        return new DocumentAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DocumentAdapter.MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());
        holder.description.setText(movie.getGenre());
        setColorsFromArray(holder);
    }

    public void setColorsFromArray(DocumentAdapter.MyViewHolder holder){
        String themeArra[] = Utils.convertString2Array(mContext);
        holder.title.setTextColor(Color.parseColor(themeArra[18]));
        holder.description.setTextColor(Color.parseColor(themeArra[19]));
        //holder.dividerLine.setBackgroundColor(Color.parseColor(themeArra[15]));
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
