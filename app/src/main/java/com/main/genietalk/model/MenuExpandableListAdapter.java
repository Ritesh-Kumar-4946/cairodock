package com.main.genietalk.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.main.genietalk.R;

import java.util.ArrayList;

/**
 * Created by NT on 9/8/2017.
 */

public class MenuExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<MenuModel> expandableList;

    public MenuExpandableListAdapter(Context context, ArrayList<MenuModel> menuList  ) {
        this.context = context;
        this.expandableList = menuList;
        Log.e("menu list ", menuList.size()+
                "");
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableList.get(listPosition)
                .getSubMenu().get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = expandableList.get(listPosition).getSubMenu().get(expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.menu_child, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.menuChildName);
        expandedListTextView.setText(expandedListText);

        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableList.get(listPosition).getSubMenu()
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableList.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableList.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = expandableList.get(listPosition).getMenuName();
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.menu_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.menuGroupName);
        ImageView imaeview =(ImageView)convertView.findViewById(R.id.menu_dot);
        if (expandableList.get(listPosition).getSubMenu().size()>0){
            imaeview.setVisibility(View.VISIBLE);
        }else{
            imaeview.setVisibility(View.GONE);
        }
        listTitleTextView.setText(expandableList.get(listPosition).getMenuName());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}
