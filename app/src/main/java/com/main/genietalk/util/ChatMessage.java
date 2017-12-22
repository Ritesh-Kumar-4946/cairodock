package com.main.genietalk.util;

/**
 * Created by NT on 8/26/2017.
 */

public class ChatMessage {
    private long id;

    private boolean isMe;

//    public ChatMessage(long id, boolean isMe, boolean gifload, String message, int viewType, Long userId, String dateTime) {
//        this.id = id;
//        this.isMe = isMe;
//        this.gifload = gifload;
//        this.message = message;
//        ViewType = viewType;
//        this.userId = userId;
//        this.dateTime = dateTime;
//    }


//    public ChatMessage() {
//
//    }

    public boolean isGifload() {
        return gifload;
    }

    public void setGifload(boolean gifload) {
        this.gifload = gifload;
    }

    private boolean gifload;
    private String message;
    private int ViewType;
    private Long userId;
    private String dateTime;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public int getViewType() {
        return ViewType;
    }
    public void setViewType(int ViewType) {
        this.ViewType = ViewType;
    }
    public boolean getIsme() {
        return isMe;
    }
    public void setMe(boolean isMe) {
        this.isMe = isMe;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getDate() {
        return dateTime;
    }
    public void setDate(String dateTime) {
        this.dateTime = dateTime;
    }
}