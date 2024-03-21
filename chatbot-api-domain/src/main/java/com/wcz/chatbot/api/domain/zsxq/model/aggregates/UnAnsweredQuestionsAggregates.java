package com.wcz.chatbot.api.domain.zsxq.model.aggregates;


import com.wcz.chatbot.api.domain.zsxq.model.res.RespData;

public class UnAnsweredQuestionsAggregates {

    private boolean succeeded;
    private RespData resp_data;

    public UnAnsweredQuestionsAggregates() {
    }

    public UnAnsweredQuestionsAggregates(boolean succeeded, RespData resp_data) {
        this.succeeded = succeeded;
        this.resp_data = resp_data;
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public void setSucceeded(boolean succeeded) {
        this.succeeded = succeeded;
    }

    public RespData getResp_data() {
        return resp_data;
    }

    public void setResp_data(RespData resp_data) {
        this.resp_data = resp_data;
    }

}