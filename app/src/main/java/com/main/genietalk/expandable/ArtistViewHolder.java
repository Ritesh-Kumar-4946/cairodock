package com.main.genietalk.expandable;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.activity.BusinessProfileActivity;
import com.main.genietalk.util.Utils;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;


public class ArtistViewHolder extends ChildViewHolder {

  private TextView childTextView;
  Context mContext;
  ImageView floatBtn,imgIcon;
  LinearLayout layoutLL,layoutLL1;
  TextView gstNoTV,emailTV,cmpNameTV;
  TextView familySharingTV;
  public ArtistViewHolder(View itemView) {
    super(itemView);
    childTextView = (TextView) itemView.findViewById(R.id.list_item_artist_name);
    floatBtn = (ImageView)itemView.findViewById(R.id.floatBtn);
    imgIcon = (ImageView)itemView.findViewById(R.id.imgIcon);
    layoutLL = (LinearLayout)itemView.findViewById(R.id.layoutLL);
    gstNoTV = (TextView)itemView.findViewById(R.id.gstNoTV);
    emailTV = (TextView)itemView.findViewById(R.id.emailTV);
    cmpNameTV = (TextView)itemView.findViewById(R.id.cmpNameTV);
    layoutLL1 = (LinearLayout)itemView.findViewById(R.id.layoutLL1);
    familySharingTV = (TextView)itemView.findViewById(R.id.familySharingTV);
  }

  public void setArtistName(Context context,int drawableId,String name,boolean isLeftIconVisible,boolean isFloatVisible,boolean isTextViewClickable) {
    childTextView.setText(Html.fromHtml(name), TextView.BufferType.SPANNABLE);
    familySharingTV.setText(Html.fromHtml(name), TextView.BufferType.SPANNABLE);
    mContext = context;

    String themeArra[] = Utils.convertString2Array(context);
    childTextView.setTextColor(Color.parseColor(themeArra[18]));
    emailTV.setTextColor(Color.parseColor(themeArra[19]));
    gstNoTV.setTextColor(Color.parseColor(themeArra[18]));
    cmpNameTV.setTextColor(Color.parseColor(themeArra[18]));
    familySharingTV.setTextColor(Color.parseColor(themeArra[18]));
    imgIcon.setImageResource(drawableId);

    if(isFloatVisible){
      layoutLL1.setVisibility(View.GONE);
      childTextView.setVisibility(View.GONE);
      imgIcon.setVisibility(View.GONE);
      floatBtn.setVisibility(View.VISIBLE);
      floatBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent = new Intent(mContext, BusinessProfileActivity.class);
          intent.putExtra(Utils.BUSSINESS_PROFILE_KEY,Utils.ADD_BUSSINESS_PROFILE);
          mContext.startActivity(intent);
        }
      });
    }else{
      layoutLL1.setVisibility(View.VISIBLE);
      childTextView.setVisibility(View.VISIBLE);
      imgIcon.setVisibility(View.VISIBLE);
      floatBtn.setVisibility(View.GONE);
    }
    if(isLeftIconVisible){
      layoutLL1.setVisibility(View.GONE);
      imgIcon.setVisibility(View.VISIBLE);
      childTextView.setVisibility(View.VISIBLE);
    }else{
      imgIcon.setVisibility(View.GONE);
      layoutLL1.setVisibility(View.VISIBLE);
      childTextView.setVisibility(View.GONE);
    }
    if(isTextViewClickable){
      layoutLL1.setVisibility(View.GONE);
      childTextView.setVisibility(View.GONE);
      imgIcon.setVisibility(View.GONE);
      layoutLL.setVisibility(View.VISIBLE);
      layoutLL.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Intent intent = new Intent(mContext, BusinessProfileActivity.class);
          intent.putExtra(Utils.BUSSINESS_PROFILE_KEY,Utils.BUSSINESS_PROFILE);
          mContext.startActivity(intent);
        }
      });
    }else {
      layoutLL.setVisibility(View.GONE);

    }

  }

}
