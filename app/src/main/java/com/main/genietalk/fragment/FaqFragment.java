package com.main.genietalk.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.main.genietalk.R;
import com.main.genietalk.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FaqFragment extends Fragment {

    @BindView(R.id.handlerRL1)RelativeLayout handlerRL1;
    @BindView(R.id.handlerRL2)RelativeLayout handlerRL2;
    @BindView(R.id.handlerRL3)RelativeLayout handlerRL3;
    @BindView(R.id.containerTV1)TextView containerTV1;
    @BindView(R.id.containerTV2)TextView containerTV2;
    @BindView(R.id.containerTV3)TextView containerTV3;
    @BindView(R.id.faqTV)TextView faqTV;
    @BindView(R.id.questionTV1)TextView questionTV1;
    @BindView(R.id.questionTV2)TextView questionTV2;
    @BindView(R.id.questionTV3)TextView questionTV3;
    boolean isExpandHandleFAQ1,isExpandHandleFAQ2,isExpandHandleFAQ3;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handlerRL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isExpandHandleFAQ1){
                    isExpandHandleFAQ1 = true;
                    containerTV1.setVisibility(View.VISIBLE);
                   // handlerTV1.setText("-");
                }else{
                    isExpandHandleFAQ1 = false;
                    containerTV1.setVisibility(View.GONE);
                   // handlerTV1.setText("+");
                }
            }
        });
        handlerRL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isExpandHandleFAQ2){
                    isExpandHandleFAQ2 = true;
                    containerTV2.setVisibility(View.VISIBLE);
                   // handlerTV2.setText("-");
                }else{
                    isExpandHandleFAQ2 = false;
                    containerTV2.setVisibility(View.GONE);
                   // handlerTV2.setText("+");
                }
            }
        });
        handlerRL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isExpandHandleFAQ3){
                    isExpandHandleFAQ3 = true;
                    containerTV3.setVisibility(View.VISIBLE);
                   // handlerTV3.setText("-");
                }else{
                    isExpandHandleFAQ3 = false;
                    containerTV3.setVisibility(View.GONE);
                    //handlerTV3.setText("+");
                }
            }
        });
        init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.faq_fragment_layout,container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    private void init() {
        String themeArra[] = Utils.convertString2Array(getActivity());

        faqTV.setTextColor(Color.parseColor(themeArra[17]));
        containerTV1.setTextColor(Color.parseColor(themeArra[17]));
        containerTV2.setTextColor(Color.parseColor(themeArra[17]));
        containerTV3.setTextColor(Color.parseColor(themeArra[17]));
        questionTV1.setTextColor(Color.parseColor(themeArra[6]));
        questionTV2.setTextColor(Color.parseColor(themeArra[6]));
        questionTV3.setTextColor(Color.parseColor(themeArra[6]));
    }
}
