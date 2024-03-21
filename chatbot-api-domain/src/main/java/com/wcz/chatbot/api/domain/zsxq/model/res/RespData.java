package com.wcz.chatbot.api.domain.zsxq.model.res;

import com.wcz.chatbot.api.domain.zsxq.model.vo.Topics;

import java.util.List;

public class RespData {

    private List<Topics> topics;

    public RespData() {
    }

    public RespData(List<Topics> topics) {
        this.topics = topics;
    }

    public List<Topics> getTopics() {
        return topics;
    }

    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }

}