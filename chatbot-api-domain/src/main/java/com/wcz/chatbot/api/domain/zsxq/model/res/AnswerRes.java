package com.wcz.chatbot.api.domain.zsxq.model.res;

public class AnswerRes {

    private boolean succeeded;

    public AnswerRes(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public AnswerRes() {
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

}