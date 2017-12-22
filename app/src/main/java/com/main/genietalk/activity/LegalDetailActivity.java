package com.main.genietalk.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.main.genietalk.R;
import com.main.genietalk.util.Utils;

/**
 * Created by gt9 on 30/11/17.
 */

public class LegalDetailActivity extends BaseActivity {

    FragmentManager fragmentManager;
    Fragment fragment;
    String openFragmentStr;
    TextView header;
    WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.legal_detail_activity_layout);
        header = (TextView)findViewById(R.id.header);
        webView = (WebView)findViewById(R.id.webView);
        if(getIntent()!=null && getIntent().getStringExtra(Utils.LEGAL_DETAIL_KEY)!=null){

            openFragmentStr = getIntent().getStringExtra(Utils.LEGAL_DETAIL_KEY);
        }

        if(openFragmentStr.equalsIgnoreCase(Utils.ABOUT_US)) {
            header.setText(Utils.ABOUT_US);
        }else if(openFragmentStr.equalsIgnoreCase(Utils.TERM_AND_CONDITIONS)){
            header.setText(Utils.TERM_AND_CONDITIONS);
        }else if(openFragmentStr.equalsIgnoreCase(Utils.PRIVACY_POLICY)){
            header.setText(Utils.PRIVACY_POLICY);

        }else if(openFragmentStr.equalsIgnoreCase(Utils.REFUND_CANCELLATION_POLICY)){
            header.setText(Utils.REFUND_CANCELLATION_POLICY);

        }else if(openFragmentStr.equalsIgnoreCase(Utils.LOCATION_INFORMATION_POLICY)){
            header.setText(Utils.LOCATION_INFORMATION_POLICY);

        }else if(openFragmentStr.equalsIgnoreCase(Utils.DATA_PROVIDER)){
            header.setText(Utils.DATA_PROVIDER);

        }else if(openFragmentStr.equalsIgnoreCase(Utils.SOFTWARE_LICENCE)){
            header.setText(Utils.SOFTWARE_LICENCE);

        }else if(openFragmentStr.equalsIgnoreCase(Utils.COPY_RIGHT)){
            header.setText(Utils.COPY_RIGHT);

        }

        webView.setWebViewClient(new myWebClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://genietalk.com/");
    }
    public void init(){
        fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.container,fragment);
        //ft.addToBackStack(null);
        ft.commit();
    }
    public void backAction(View view){
        finish();
    }
    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }
    }
}
