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



public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.MyViewHolder> {

    private List<Movie> moviesList;
    Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cardNumberLblTV,cardNumberTV,expireDateLblTV,expireDateTV,
                setLimitLblTV,setLimitTV,cardHolderLblTV,cardHolderTV;

        public MyViewHolder(View view) {
            super(view);

            cardNumberLblTV = (TextView)view.findViewById(R.id.cardNumberLblTV);
            cardNumberTV = (TextView)view.findViewById(R.id.cardNumberTV);
            expireDateLblTV = (TextView)view.findViewById(R.id.expireDateLblTV);
            expireDateTV = (TextView)view.findViewById(R.id.expireDateTV);
            setLimitLblTV = (TextView)view.findViewById(R.id.setLimitLblTV);
            setLimitTV = (TextView)view.findViewById(R.id.setLimitTV);
            cardHolderLblTV = (TextView)view.findViewById(R.id.cardHolderLblTV);
            cardHolderTV = (TextView)view.findViewById(R.id.cardHolderTV);

        }
    }


    public PaymentMethodAdapter(Context mContext, List<Movie> moviesList) {
        this.moviesList = moviesList;
        this.mContext = mContext;
    }

    @Override
    public PaymentMethodAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_payment_method, parent, false);

        return new PaymentMethodAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PaymentMethodAdapter.MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        setColorsFromArray(holder);

    }

    public void setColorsFromArray(PaymentMethodAdapter.MyViewHolder holder){
        String themeArra[] = Utils.convertString2Array(mContext);

        holder.cardNumberLblTV.setTextColor(Color.parseColor(themeArra[19]));
        //holder.cardNumberTV.setTextColor(Color.parseColor(themeArra[18]));

        holder.expireDateLblTV.setTextColor(Color.parseColor(themeArra[19]));
        //holder.expireDateTV.setTextColor(Color.parseColor(themeArra[18]));

        holder.setLimitLblTV.setTextColor(Color.parseColor(themeArra[19]));
        //holder.setLimitTV.setTextColor(Color.parseColor(themeArra[18]));

        holder.cardHolderLblTV.setTextColor(Color.parseColor(themeArra[19]));
        //holder.cardHolderTV.setTextColor(Color.parseColor(themeArra[18]));

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}

