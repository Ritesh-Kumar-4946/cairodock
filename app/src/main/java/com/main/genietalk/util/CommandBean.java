package com.main.genietalk.util;

/**
 * Created by gt9 on 19/12/17.
 */

public class CommandBean {

    public static CommandBean commandBean;
    int ct = 1;
    public static CommandBean getInstance(){
        if(commandBean==null)
            commandBean = new CommandBean();
        return commandBean;
    }

    public int getCt() {
        return ct;
    }

    public void setCt(int ct) {
        this.ct = ct;
    }
}
