package com.wcz.chatbot.api.test;

import com.alibaba.fastjson.JSON;
import com.wcz.chatbot.api.domain.zsxq.IZsxqApi;
import com.wcz.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.wcz.chatbot.api.domain.zsxq.model.vo.Topics;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest  {
    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);
    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;
    @Resource
    private IZsxqApi iZsxqApi;
    @Test
    public void test_zsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = iZsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
        logger.info("测试结果 {}",JSON.toJSONString(unAnsweredQuestionsAggregates));
        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic : topics) {
            String  topicId = topic.getTopicId();
            String text = topic.getTalk().getText();
            logger.info("topicId :{} text{}",topicId,text);
        }
    }
}
