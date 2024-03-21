package com.wcz.chatbot.api.domain.zsxq.service;

import com.alibaba.fastjson.JSON;
import com.wcz.chatbot.api.domain.zsxq.IZsxqApi;
import com.wcz.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import com.wcz.chatbot.api.domain.zsxq.model.req.AnswerReq;
import com.wcz.chatbot.api.domain.zsxq.model.req.ReqData;
import com.wcz.chatbot.api.domain.zsxq.model.res.AnswerRes;
import net.sf.json.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ZsxqApi implements IZsxqApi {

    private Logger logger = LoggerFactory.getLogger(ZsxqApi.class);

    @Override
    public UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/" + groupId + "/topics?scope=all&count=20");

        get.addHeader("cookie", cookie);
        get.addHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("拉取提问数据。groupId：{} jsonStr：{}",groupId,jsonStr);
            return JSON.parseObject(jsonStr, UnAnsweredQuestionsAggregates.class);
        } else {
            throw new RuntimeException("queryUnAnsweredQuestionsTopicId Err Code is" + response.getStatusLine().getStatusCode());
        }
    }

    @Override
    public boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/" + topicId + "/comments?sort=asc&count=30&with_sticky=true");
        post.addHeader("cookie", "zsxq_access_token=B49B4F46-BAFA-FA70-470B-D49BA9AFA430_00AB280773148AB0; abtest_env=product; zsxqsessionid=0138bae14a5a64f53714fd0aee51cfc2; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22184582414218222%22%2C%22first_id%22%3A%2218e56a76f33e5-028aa3ec9d9a716-4c657b58-2073600-18e56a76f341688%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThlNTZhNzZmMzNlNS0wMjhhYTNlYzlkOWE3MTYtNGM2NTdiNTgtMjA3MzYwMC0xOGU1NmE3NmYzNDE2ODgiLCIkaWRlbnRpdHlfbG9naW5faWQiOiIxODQ1ODI0MTQyMTgyMjIifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22184582414218222%22%7D%2C%22%24device_id%22%3A%2218e56a76f33e5-028aa3ec9d9a716-4c657b58-2073600-18e56a76f341688%22%7D");
        post.addHeader("Content-Type", "application/json;charset=utf8");

//        String paramJson = "{\n" +
//                "  \"req_data\": {\n" +
//                "    \"text\": \"12\\n\",\n" +
//                "    \"image_ids\": [],\n" +
//                "    \"mentioned_user_ids\": []\n" +
//                "  }\n" +
//                "}";
        AnswerReq answerReq = new AnswerReq(new ReqData(text, silenced));
        String paramJson = JSONObject.fromObject(answerReq).toString();


        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("回答问题结果。groupId：{} topicId: {} jsonStr：{}",groupId,topicId,jsonStr);
            AnswerRes answerRes = JSON.parseObject(jsonStr, AnswerRes.class);
            return answerRes.isSucceeded();
        } else {
            throw new RuntimeException("answer Err Code is" + response.getStatusLine().getStatusCode());
        }
    }

}