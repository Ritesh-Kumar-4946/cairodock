package com.main.genietalk.util;

import android.content.Context;
import android.widget.Toast;

import com.main.genietalk.interfaceslistener.ChatInterfaceListener;


public class MyJavaScriptInterface {

    Context mContext;
    ChatInterfaceListener chatInterfaceListener;
    public MyJavaScriptInterface(Context c, ChatInterfaceListener chatInterfaceListener) {
        mContext = c;
        this.chatInterfaceListener = chatInterfaceListener;
    }

    public void getIndex(String index){
       // Toast.makeText(mContext, index, Toast.LENGTH_SHORT).show();
        chatInterfaceListener.setIndex(index);
    }
}
