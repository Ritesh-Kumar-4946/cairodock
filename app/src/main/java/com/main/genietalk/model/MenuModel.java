package com.main.genietalk.model;

import java.util.ArrayList;

/**
 * Created by NT on 9/8/2017.
 */

public class MenuModel {
    private String menuName;
    private ArrayList<String> subMenu=new ArrayList<String>();
    private String menuImageName;

    public MenuModel(String menuName, ArrayList<String> subMenu, String menuImageName) {
        this.menuName = menuName;
        this.subMenu = subMenu;
        this.menuImageName = menuImageName;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public ArrayList<String> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(ArrayList<String> subMenu) {
        this.subMenu = subMenu;
    }

    public String getMenuImageName() {
        return menuImageName;
    }

    public void setMenuImageName(String menuImageName) {
        this.menuImageName = menuImageName;
    }
}
