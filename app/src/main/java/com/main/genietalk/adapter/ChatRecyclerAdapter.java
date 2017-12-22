package com.main.genietalk.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.main.genietalk.R;
import com.main.genietalk.interfaceslistener.ChatInterfaceListener;
import com.main.genietalk.util.ChatMessage;
import com.main.genietalk.util.MyJavaScriptInterface;
import com.main.genietalk.util.ThemeSettingManager;

import java.util.List;

import lal.adhish.gifprogressbar.GifView;


/**
 * Created by gt19 on 19/12/17.
 */

public class ChatRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_TEXT = 0;
    private static final int TYPE_WEBVIEW = 1;

    private List<ChatMessage> chatMessageList;
    private static Activity context;
    private ChatInterfaceListener chatInterfaceListener;
    private ThemeSettingManager themeSettingManager;

    private int pos;

    public ChatRecyclerAdapter(Activity context, List<ChatMessage> chatMessageList, ChatInterfaceListener chatInterfaceListener) {
        this.chatMessageList = chatMessageList;
        this.context = context;
        this.chatInterfaceListener = chatInterfaceListener;
        this.themeSettingManager = ThemeSettingManager.getInstance();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case TYPE_TEXT:

                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitemtextview, parent, false);
                return new MyViewHolderTYPE_TEXT(view);


            case TYPE_WEBVIEW:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitemwebview, parent, false);
                return new MyViewHolderTYPE_WEBVIEW(view);


        }

        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ChatMessage object = chatMessageList.get(position);

        if (object != null) {

            switch (object.getViewType()) {

                case TYPE_TEXT:
                    Log.e("Type  :", "  T " + object.getViewType());
//                    Log.e("Position  :", " T_Pos " + chatMessageList.get(position));
//                    Log.e("Position  :", " T_Pos_Type " + chatMessageList.get(position).getViewType());
//                    Log.e("Position  :", " T_Pos_message " + chatMessageList.get(position).getMessage());
//                    Log.e("Position  :", " T_Pos_message " + ((MyViewHolderTYPE_WEBVIEW) holder).


                    if (object.isGifload()) {
                        try {

                            ((MyViewHolderTYPE_TEXT) holder).iv_logoimg.setVisibility(View.GONE);
                            ((MyViewHolderTYPE_TEXT) holder).pGif.setImageResource(R.drawable.loading_spinner);

                        } catch (Exception e) {
                            e.printStackTrace();
                            ((MyViewHolderTYPE_TEXT) holder).pGif.setVisibility(View.GONE);
                        }
                    } else {
                        ((MyViewHolderTYPE_TEXT) holder).pGif.setVisibility(View.GONE);

                    }

                    boolean myMsg = object.getIsme();
                    pos = position;
                    setAlignmentText(((MyViewHolderTYPE_TEXT) holder), myMsg);
                    ((MyViewHolderTYPE_TEXT) holder).txtMessage.setText(object.getMessage());


                    themeSettingManager.setTextColor(context, ((MyViewHolderTYPE_TEXT) holder).txtMessage);
                    ((MyViewHolderTYPE_TEXT) holder).txtInfo.setText(object.getDate());

                    break;


                case TYPE_WEBVIEW:
                    Log.e("Type  :", "  W " + object.getViewType());
//                    Log.e("Position  :", "  W_Pos " + chatMessageList.get(position));
//                    Log.e("Position  :", "  W_Pos_Type  " + chatMessageList.get(position).getViewType());
//                    Log.e("Position  :", "  W_Pos_message " + chatMessageList.get(position).getMessage());

                    boolean myMsgs = object.getIsme();
                    setAlignmentWeb(((MyViewHolderTYPE_WEBVIEW) holder), myMsgs);

                    final MyJavaScriptInterface myJavaScriptInterface = new MyJavaScriptInterface(context, chatInterfaceListener);
                    ((MyViewHolderTYPE_WEBVIEW) holder).txtMessage.addJavascriptInterface(myJavaScriptInterface, "AndroidFunction");
                    ((MyViewHolderTYPE_WEBVIEW) holder).txtMessage.getSettings().setJavaScriptEnabled(true);
                    ((MyViewHolderTYPE_WEBVIEW) holder).txtMessage.setVisibility(View.VISIBLE);
                    ((MyViewHolderTYPE_WEBVIEW) holder).txtMessage.loadDataWithBaseURL("", object.getMessage(), "text/html", "UTF-8", "");
//                    ((MyViewHolderTYPE_WEBVIEW) holder).txtMessage.loadData(object.getMessage(), "text/html", "UTF-8");
//                    wv.loadData(yourData, "text/html", "UTF-8");

                    Log.e("WebData :", " WEB  " + object.getMessage());
//                    Toast.makeText(context, "visible", Toast.LENGTH_SHORT).show();

                    break;
            }
        }

    }


    @Override
    public int getItemCount() {
//        return chatMessageList.size();
        if (chatMessageList == null)
            return 0;
        return chatMessageList.size();

    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemViewType(int position) {
//        if (chatMessageList != null) {
//            ChatMessage object = chatMessageList.get(position);
////                Log.e("getItemViewType :", " Object   " + object.getViewType());
//            if (object != null) {
//                return object.getViewType();
//            }
//        }
//        return 0;

        ChatMessage chatMessage = chatMessageList.get(position);
        if (chatMessage.getViewType() == 0) {
            return 0;
        } else if (chatMessage.getViewType() == 1) {
            return 1;
        } else {
            return -1;
        }

    }


    public static class MyViewHolderTYPE_TEXT extends RecyclerView.ViewHolder {
        public TextView txtMessage;
        public TextView txtInfo;
        public LinearLayout content;
        public LinearLayout contentWithBG;
        public GifView pGif;
        public ImageView iv_logoimg;

        public MyViewHolderTYPE_TEXT(View itemView) {
            super(itemView);
            txtMessage = (TextView) itemView.findViewById(R.id.txtMessage);
            context.registerForContextMenu(itemView.findViewById(R.id.txtMessage));
            content = (LinearLayout) itemView.findViewById(R.id.content);
            contentWithBG = (LinearLayout) itemView.findViewById(R.id.contentWithBackground);
            txtInfo = (TextView) itemView.findViewById(R.id.txtInfo);
            pGif = (GifView) itemView.findViewById(R.id.progressBar_gif);
            iv_logoimg = (ImageView) itemView.findViewById(R.id.iv_logoimg);
        }
    }


    public static class MyViewHolderTYPE_WEBVIEW extends RecyclerView.ViewHolder {
        public WebView txtMessage;
        public TextView txtInfo;
        public LinearLayout content;
        public LinearLayout contentWithBG;

        public MyViewHolderTYPE_WEBVIEW(View itemView) {
            super(itemView);
            txtMessage = (WebView) itemView.findViewById(R.id.txtMessage);
            content = (LinearLayout) itemView.findViewById(R.id.content);
            contentWithBG = (LinearLayout) itemView.findViewById(R.id.contentWithBackground);
            txtInfo = (TextView) itemView.findViewById(R.id.txtInfo);
        }
    }


    public void add(ChatMessage message) {
        chatMessageList.add(message);
    }


    public void add(List<ChatMessage> messages) {
        chatMessageList.addAll(messages);
    }


    private void setAlignmentText(RecyclerView.ViewHolder holder, boolean isMe) {
        if (!isMe) {

            LinearLayout.LayoutParams layoutParams =
                    (LinearLayout.LayoutParams) ((MyViewHolderTYPE_TEXT) holder).contentWithBG.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            ((MyViewHolderTYPE_TEXT) holder).contentWithBG.setLayoutParams(layoutParams);

            RelativeLayout.LayoutParams lp =
                    (RelativeLayout.LayoutParams) ((MyViewHolderTYPE_TEXT) holder).content.getLayoutParams();
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            ((MyViewHolderTYPE_TEXT) holder).content.setLayoutParams(lp);
            layoutParams = (LinearLayout.LayoutParams) ((MyViewHolderTYPE_TEXT) holder).txtMessage.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            ((MyViewHolderTYPE_TEXT) holder).txtMessage.setLayoutParams(layoutParams);
            ((MyViewHolderTYPE_TEXT) holder).txtMessage.setTextIsSelectable(true);
            layoutParams = (LinearLayout.LayoutParams) ((MyViewHolderTYPE_TEXT) holder).txtInfo.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            ((MyViewHolderTYPE_TEXT) holder).txtInfo.setLayoutParams(layoutParams);
            ((MyViewHolderTYPE_TEXT) holder).iv_logoimg.setVisibility(View.GONE);
        } else {

            LinearLayout.LayoutParams layoutParams =
                    (LinearLayout.LayoutParams) ((MyViewHolderTYPE_TEXT) holder).contentWithBG.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            ((MyViewHolderTYPE_TEXT) holder).contentWithBG.setLayoutParams(layoutParams);

            RelativeLayout.LayoutParams lp =
                    (RelativeLayout.LayoutParams) ((MyViewHolderTYPE_TEXT) holder).content.getLayoutParams();
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            ((MyViewHolderTYPE_TEXT) holder).content.setLayoutParams(lp);
            layoutParams = (LinearLayout.LayoutParams) ((MyViewHolderTYPE_TEXT) holder).txtMessage.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            ((MyViewHolderTYPE_TEXT) holder).txtMessage.setLayoutParams(layoutParams);

            layoutParams = (LinearLayout.LayoutParams) ((MyViewHolderTYPE_TEXT) holder).txtInfo.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            ((MyViewHolderTYPE_TEXT) holder).txtInfo.setLayoutParams(layoutParams);
            if (!chatMessageList.get(pos).isGifload())
                ((MyViewHolderTYPE_TEXT) holder).iv_logoimg.setVisibility(View.VISIBLE);

        }
    }


    private void setAlignmentWeb(RecyclerView.ViewHolder holder, boolean isMe) {
        if (!isMe) {
            //holder.contentWithBG.setBackgroundResource(R.drawable.in_message_bg);

            LinearLayout.LayoutParams layoutParams =
                    (LinearLayout.LayoutParams) ((MyViewHolderTYPE_WEBVIEW) holder).contentWithBG.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            ((MyViewHolderTYPE_WEBVIEW) holder).contentWithBG.setLayoutParams(layoutParams);

            RelativeLayout.LayoutParams lp =
                    (RelativeLayout.LayoutParams) ((MyViewHolderTYPE_WEBVIEW) holder).content.getLayoutParams();
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            ((MyViewHolderTYPE_WEBVIEW) holder).content.setLayoutParams(lp);
            layoutParams = (LinearLayout.LayoutParams) ((MyViewHolderTYPE_WEBVIEW) holder).txtMessage.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            ((MyViewHolderTYPE_WEBVIEW) holder).txtMessage.setLayoutParams(layoutParams);

            layoutParams = (LinearLayout.LayoutParams) ((MyViewHolderTYPE_WEBVIEW) holder).txtInfo.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            ((MyViewHolderTYPE_WEBVIEW) holder).txtInfo.setLayoutParams(layoutParams);
        } else {
            //holder.contentWithBG.setBackgroundResource(R.drawable.out_message_bg);

            LinearLayout.LayoutParams layoutParams =
                    (LinearLayout.LayoutParams) ((MyViewHolderTYPE_WEBVIEW) holder).contentWithBG.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            ((MyViewHolderTYPE_WEBVIEW) holder).contentWithBG.setLayoutParams(layoutParams);

            RelativeLayout.LayoutParams lp =
                    (RelativeLayout.LayoutParams) ((MyViewHolderTYPE_WEBVIEW) holder).content.getLayoutParams();
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            ((MyViewHolderTYPE_WEBVIEW) holder).content.setLayoutParams(lp);

            layoutParams = (LinearLayout.LayoutParams) ((MyViewHolderTYPE_WEBVIEW) holder).txtMessage.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            ((MyViewHolderTYPE_WEBVIEW) holder).txtMessage.setLayoutParams(layoutParams);

            layoutParams = (LinearLayout.LayoutParams) ((MyViewHolderTYPE_WEBVIEW) holder).txtInfo.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            ((MyViewHolderTYPE_WEBVIEW) holder).txtInfo.setLayoutParams(layoutParams);
        }
    }


}
