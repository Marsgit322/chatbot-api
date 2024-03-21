package com.wcz.chatbot.api.domain.zsxq.model.vo;

public class UserSpecific {

    private boolean liked;

    private boolean subscribed;

    public UserSpecific() {
    }

    public UserSpecific(boolean liked, boolean subscribed) {
        this.liked = liked;
        this.subscribed = subscribed;
    }

    public void setLiked(boolean liked){
        this.liked = liked;
    }
    public boolean getLiked(){
        return this.liked;
    }
    public void setSubscribed(boolean subscribed){
        this.subscribed = subscribed;
    }
    public boolean getSubscribed(){
        return this.subscribed;
    }

}