package com.main.genietalk.fragment;


import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.main.genietalk.R;
import com.main.genietalk.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ReportAnIssueFragment extends Fragment {
    @BindView(R.id.reportExpandContainerLL)LinearLayout reportExpandContainerLL;
    @BindView(R.id.reportExpandHandleRL)RelativeLayout reportExpandHandleRL;
    @BindView(R.id.reportExpandHandleArrowImg)ImageView reportExpandHandleArrowImg;
    @BindView(R.id.handlerRL1)RelativeLayout handlerRL1;
    @BindView(R.id.handlerRL2)RelativeLayout handlerRL2;
    @BindView(R.id.handlerRL3)RelativeLayout handlerRL3;
    @BindView(R.id.containerTV1)TextView containerTV1;
    @BindView(R.id.containerTV2)TextView containerTV2;
    @BindView(R.id.containerTV3)TextView containerTV3;
    boolean isExpand;
    boolean isExpandHandleFAQ1,isExpandHandleFAQ2,isExpandHandleFAQ3;

    @BindView(R.id.transactionTV)TextView transactionTV;
    @BindView(R.id.questionTV3)TextView questionTV3;
    @BindView(R.id.questionTV2)TextView questionTV2;
    @BindView(R.id.questionTV1)TextView questionTV1;

    @BindView(R.id.merchantNameTV)TextView merchantNameTV;
    @BindView(R.id.merchantDateTV)TextView merchantDateTV;

    @BindView(R.id.tataSkyCallTV)TextView tataSkyCallTV;
    @BindView(R.id.tataSkyCallNoTV)TextView tataSkyCallNoTV;

    @BindView(R.id.problemTV)TextView problemTV;

    @BindView(R.id.headerRL1)RelativeLayout headerRL1;

    /*@OnClick(R.id.reportExpandHandleRL)
    public void reportExpandHandleLL(){
        if(!isExpand){
            isExpand = true;
            reportExpandContainerLL.setVisibility(View.VISIBLE);
            reportExpandHandleArrowImg.setImageResource(R.drawable.ic_arrow_down);
            reportExpandHandleArrowImg.setColorFilter(getResources().getColor(R.color.icons), PorterDuff.Mode.SRC_IN);
        }else{
            isExpand = false;
            reportExpandContainerLL.setVisibility(View.GONE);
            reportExpandHandleArrowImg.setImageResource(R.drawable.topbackarrow);
            reportExpandHandleArrowImg.setRotation(180);
            reportExpandHandleArrowImg.setColorFilter(getResources().getColor(R.color.icons), PorterDuff.Mode.SRC_IN);
        }
    }*/


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reportExpandHandleRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isExpand){
                    isExpand = true;
                    reportExpandContainerLL.setVisibility(View.VISIBLE);
                    reportExpandHandleArrowImg.setImageResource(R.drawable.ic_arrow_down);
                    reportExpandHandleArrowImg.setColorFilter(getResources().getColor(R.color.icons), PorterDuff.Mode.SRC_IN);
                }else{
                    isExpand = false;
                    reportExpandContainerLL.setVisibility(View.GONE);
                    reportExpandHandleArrowImg.setImageResource(R.drawable.ic_right_arrow);
                    reportExpandHandleArrowImg.setColorFilter(getResources().getColor(R.color.icons), PorterDuff.Mode.SRC_IN);
                }
            }
        });
        handlerRL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isExpandHandleFAQ1){
                    isExpandHandleFAQ1 = true;
                    containerTV1.setVisibility(View.VISIBLE);
                }else{
                    isExpandHandleFAQ1 = false;
                    containerTV1.setVisibility(View.GONE);
                }
            }
        });
        handlerRL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isExpandHandleFAQ2){
                    isExpandHandleFAQ2 = true;
                    containerTV2.setVisibility(View.VISIBLE);
                }else{
                    isExpandHandleFAQ2 = false;
                    containerTV2.setVisibility(View.GONE);
                }
            }
        });
        handlerRL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isExpandHandleFAQ3){
                    isExpandHandleFAQ3 = true;
                    containerTV3.setVisibility(View.VISIBLE);
                }else{
                    isExpandHandleFAQ3 = false;
                    containerTV3.setVisibility(View.GONE);
                }
            }
        });
        init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_an_issue_fragment_layout,container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    private void init() {
        String themeArra[] = Utils.convertString2Array(getActivity());

        transactionTV.setTextColor(Color.parseColor(themeArra[17]));
        containerTV1.setTextColor(Color.parseColor(themeArra[17]));
        containerTV2.setTextColor(Color.parseColor(themeArra[17]));
        containerTV3.setTextColor(Color.parseColor(themeArra[17]));
        tataSkyCallTV.setTextColor(Color.parseColor(themeArra[17]));
        tataSkyCallNoTV.setTextColor(Color.parseColor(themeArra[17]));
        questionTV1.setTextColor(Color.parseColor(themeArra[6]));
        questionTV2.setTextColor(Color.parseColor(themeArra[6]));
        questionTV3.setTextColor(Color.parseColor(themeArra[6]));
        merchantNameTV.setTextColor(Color.parseColor(themeArra[18]));
        merchantDateTV.setTextColor(Color.parseColor(themeArra[19]));
        problemTV.setTextColor(Color.parseColor(themeArra[17]));

        headerRL1.setBackgroundColor(Color.parseColor(themeArra[5]));
    }
}
