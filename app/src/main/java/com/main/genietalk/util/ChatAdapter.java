package com.main.genietalk.util;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.interfaceslistener.ChatInterfaceListener;

import java.util.List;

import lal.adhish.gifprogressbar.GifView;

/**
 * Created by NT on 8/26/2017.
 */

public class ChatAdapter extends BaseAdapter {

    private final List<ChatMessage> chatMessages;
    private Activity context;
    int pos;
    ThemeSettingManager themeSettingManager;
    ChatInterfaceListener chatInterfaceListener;

    public ChatAdapter(Activity context, List<ChatMessage> chatMessages, ChatInterfaceListener chatInterfaceListener) {
        this.context = context;
        this.chatMessages = chatMessages;
        themeSettingManager = ThemeSettingManager.getInstance();
        this.chatInterfaceListener = chatInterfaceListener;
    }

    @Override
    public int getCount() {
        if (chatMessages != null) {
            return chatMessages.size();
        } else {
            return 0;
        }
    }

    @Override
    public ChatMessage getItem(int position) {
        if (chatMessages != null) {
            return chatMessages.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ChatMessage chatMessage = getItem(position);
        ViewHolderText holder1;
        ViewHolderWeb holder2;
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        if (chatMessage.getViewType() == 0) {
            // if (convertView == null) {
            convertView = vi.inflate(R.layout.listitemtextview, null);
            holder1 = createViewHolderText(convertView);
            if (chatMessage.isGifload()) {
                try {
                    holder1.iv_logoimg.setVisibility(View.GONE);
                    //holder1.pGif.setVisibility(View.VISIBLE);
                    holder1.pGif.setImageResource(R.drawable.loading_spinner);
                } catch (Exception e) {
                    e.printStackTrace();
                    holder1.pGif.setVisibility(View.GONE);
                }
            } else {
                holder1.pGif.setVisibility(View.GONE);

            }

            convertView.setTag(holder1);

            boolean myMsg = chatMessage.getIsme();

            pos = position;
            setAlignmentText(holder1, myMsg);
            holder1.txtMessage.setText(chatMessage.getMessage());
            themeSettingManager.setTextColor(context, holder1.txtMessage);
            holder1.txtInfo.setText(chatMessage.getDate());
            //themeSettingManager.setTextColor(context,holder1.txtInfo);

        } else {

            convertView = vi.inflate(R.layout.listitemwebview, null);
            holder2 = createViewHolderWeb(convertView);
            convertView.setTag(holder2);

            holder2 = (ViewHolderWeb) convertView.getTag();

            boolean myMsg = chatMessage.getIsme();
            setAlignmentWeb(holder2, myMsg);
            final MyJavaScriptInterface myJavaScriptInterface
                    = new MyJavaScriptInterface(context, chatInterfaceListener);
            holder2.txtMessage.addJavascriptInterface(myJavaScriptInterface, "AndroidFunction");


            holder2.txtMessage.getSettings().setJavaScriptEnabled(true);
            holder2.txtMessage.setVisibility(View.VISIBLE);
            holder2.txtMessage.loadDataWithBaseURL("", chatMessage.getMessage(), "text/html", "UTF-8", "");
        }



        return convertView;
    }

    public void add(ChatMessage message) {
        chatMessages.add(message);
    }

    public void add(List<ChatMessage> messages) {
        chatMessages.addAll(messages);
    }






    private void setAlignmentText(ViewHolderText holder, boolean isMe) {
        if (!isMe) {

            LinearLayout.LayoutParams layoutParams =
                    (LinearLayout.LayoutParams) holder.contentWithBG.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            holder.contentWithBG.setLayoutParams(layoutParams);

            RelativeLayout.LayoutParams lp =
                    (RelativeLayout.LayoutParams) holder.content.getLayoutParams();
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.content.setLayoutParams(lp);
            layoutParams = (LinearLayout.LayoutParams) holder.txtMessage.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            holder.txtMessage.setLayoutParams(layoutParams);
            holder.txtMessage.setTextIsSelectable(true);
            layoutParams = (LinearLayout.LayoutParams) holder.txtInfo.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            holder.txtInfo.setLayoutParams(layoutParams);
            holder.iv_logoimg.setVisibility(View.GONE);
        } else {

            LinearLayout.LayoutParams layoutParams =
                    (LinearLayout.LayoutParams) holder.contentWithBG.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            holder.contentWithBG.setLayoutParams(layoutParams);

            RelativeLayout.LayoutParams lp =
                    (RelativeLayout.LayoutParams) holder.content.getLayoutParams();
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            holder.content.setLayoutParams(lp);
            layoutParams = (LinearLayout.LayoutParams) holder.txtMessage.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            holder.txtMessage.setLayoutParams(layoutParams);

            layoutParams = (LinearLayout.LayoutParams) holder.txtInfo.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            holder.txtInfo.setLayoutParams(layoutParams);
            if (!chatMessages.get(pos).isGifload())
                holder.iv_logoimg.setVisibility(View.VISIBLE);

        }
    }

    private void setAlignmentWeb(ViewHolderWeb holder, boolean isMe) {
        if (!isMe) {
            //holder.contentWithBG.setBackgroundResource(R.drawable.in_message_bg);

            LinearLayout.LayoutParams layoutParams =
                    (LinearLayout.LayoutParams) holder.contentWithBG.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            holder.contentWithBG.setLayoutParams(layoutParams);

            RelativeLayout.LayoutParams lp =
                    (RelativeLayout.LayoutParams) holder.content.getLayoutParams();
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.content.setLayoutParams(lp);
            layoutParams = (LinearLayout.LayoutParams) holder.txtMessage.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            holder.txtMessage.setLayoutParams(layoutParams);

            layoutParams = (LinearLayout.LayoutParams) holder.txtInfo.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            holder.txtInfo.setLayoutParams(layoutParams);
        } else {
            //holder.contentWithBG.setBackgroundResource(R.drawable.out_message_bg);

            LinearLayout.LayoutParams layoutParams =
                    (LinearLayout.LayoutParams) holder.contentWithBG.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            holder.contentWithBG.setLayoutParams(layoutParams);

            RelativeLayout.LayoutParams lp =
                    (RelativeLayout.LayoutParams) holder.content.getLayoutParams();
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            holder.content.setLayoutParams(lp);

            layoutParams = (LinearLayout.LayoutParams) holder.txtMessage.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            holder.txtMessage.setLayoutParams(layoutParams);

            layoutParams = (LinearLayout.LayoutParams) holder.txtInfo.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            holder.txtInfo.setLayoutParams(layoutParams);
        }
    }

    private ViewHolderText createViewHolderText(View v) {
        ViewHolderText holder = new ViewHolderText();
        holder.txtMessage = (TextView) v.findViewById(R.id.txtMessage);
        context.registerForContextMenu(v.findViewById(R.id.txtMessage));
        holder.content = (LinearLayout) v.findViewById(R.id.content);
        holder.contentWithBG = (LinearLayout) v.findViewById(R.id.contentWithBackground);
        holder.txtInfo = (TextView) v.findViewById(R.id.txtInfo);
        holder.pGif = (GifView) v.findViewById(R.id.progressBar_gif);
        holder.iv_logoimg = (ImageView) v.findViewById(R.id.iv_logoimg);
        return holder;
    }

    private static class ViewHolderText {
        public TextView txtMessage;
        public TextView txtInfo;
        public LinearLayout content;
        public LinearLayout contentWithBG;
        public GifView pGif;
        public ImageView iv_logoimg;
    }

    private static class ViewHolderWeb {
        public WebView txtMessage;
        public TextView txtInfo;
        public LinearLayout content;
        public LinearLayout contentWithBG;
    }

    private ViewHolderWeb createViewHolderWeb(View v) {
        ViewHolderWeb holder = new ViewHolderWeb();
        holder.txtMessage = (WebView) v.findViewById(R.id.txtMessage);
        holder.content = (LinearLayout) v.findViewById(R.id.content);
        holder.contentWithBG = (LinearLayout) v.findViewById(R.id.contentWithBackground);
        holder.txtInfo = (TextView) v.findViewById(R.id.txtInfo);


        return holder;
    }
}