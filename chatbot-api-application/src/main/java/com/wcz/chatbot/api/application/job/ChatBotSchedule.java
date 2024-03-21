package com.wcz.chatbot.api.application.job;

import com.alibaba.fastjson.JSON;
import com.wcz.chatbot.api.domain.ai.IOpenAI;
import com.wcz.chatbot.api.domain.zsxq.IZsxqApi;
import com.wcz.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.wcz.chatbot.api.domain.zsxq.model.vo.Topics;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@EnableScheduling
@Configuration
public class ChatBotSchedule {
        private Logger logger = LoggerFactory.getLogger(ChatBotSchedule.class);
        @Value("${chatbot-api.groupId}")
        private String groupId;
        @Value("${chatbot-api.cookie}")
        private String cookie;
        @Resource
        private IZsxqApi iZsxqApi;
        @Resource
        private IOpenAI iOpenAI;
        @Scheduled(cron = "0 0/1 * * * ?")
        public void run(){
            try{
                UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = iZsxqApi.queryUnAnsweredQuestionsTopicId(groupId, cookie);
                logger.info("测试结果 {}", JSON.toJSONString(unAnsweredQuestionsAggregates));
                List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
                if(null == topics||topics.isEmpty()){
                    return;
                }
                Topics topic = topics.get(0);
                String answer = iOpenAI.doChatGpt(topic.getTalk().getText());

                iZsxqApi.answer(groupId,cookie,topic.getTopicId(),answer,false);


            }catch (Exception e){
                logger.error("异常",e);
            }
        }

}
