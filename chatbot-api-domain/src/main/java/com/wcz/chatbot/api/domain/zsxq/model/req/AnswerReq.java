package com.wcz.chatbot.api.domain.zsxq.model.req;

public class AnswerReq {

    private ReqData req_data;

    public AnswerReq() {
    }

    public AnswerReq(ReqData req_data) {
        this.req_data = req_data;
    }

    public ReqData getReq_data() {
        return req_data;
    }

    public void setReq_data(ReqData req_data) {
        this.req_data = req_data;
    }

}