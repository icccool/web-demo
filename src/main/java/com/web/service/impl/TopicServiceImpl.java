package com.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.web.controller.TopicController;
import com.web.mapper.topic.TopicMapper;
import com.web.model.Topic;
import com.web.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by WANG on 2018/6/7.
 */
//@Service
public class TopicServiceImpl implements TopicService, DisposableBean {

    private final static Logger logger = LoggerFactory.getLogger(TopicController.class);
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    //缓存数据条数
    private static int LIST_SIZE = 300;
    //每页条数
    private static int PAGE_SIZE = 30;
    //缓存数据
    private List<Topic> topicQueue = new ArrayList<Topic>(LIST_SIZE);
    //监视锁
    private  Object obj = new Object();
    //redis key
    private String TOPIC_LIST_KEY = "TOPIC_LIST_KEY";

    ExecutorService singleThread = Executors.newFixedThreadPool(4);

    @Override
    public void destroy() throws Exception {
        logger.info("destroy() topicQueue.size={}", topicQueue.size());
        save();
    }

    @Override
    public int insert(Topic topic) {
        return topicMapper.insert(topic);
    }

    public void saveTopic(Topic topic) {
        logger.info("saveTopic() topic={}", topic);
        long start = System.currentTimeMillis();
        synchronized (obj) {
            if (topicQueue.size() >= LIST_SIZE) {
                save();
            }
        }
        topicQueue.add(topic);
        logger.info("saveTopic() topicQueue.size={},topic={},cost={}", topicQueue.size(), topic, System.currentTimeMillis() - start);
    }

    public void save(){
        final List<Topic> tmp = new ArrayList<Topic>(topicQueue);
        singleThread.execute(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                logger.info("destroy() start to save topics.");
                ListOperations<String, String> ls = redisTemplate.opsForList();
                if (!CollectionUtils.isEmpty(tmp)) {
                    for (Topic topic : tmp) {
                        ls.leftPush(TOPIC_LIST_KEY, JSON.toJSONString(topic));
                    }
                }
                logger.info("destroy() save topics finished. cost = {}", System.currentTimeMillis() - start);
            }
        });
        topicQueue.clear();
    }

    @Override
    public List<Topic> getTopicList() {
        logger.info("getTopicList() topicQueue.size={}",topicQueue.size());
        List<Topic> topicPage = new ArrayList<Topic>();
        int qsize = topicQueue.size();
        int loop_size = (qsize > PAGE_SIZE ? (qsize - PAGE_SIZE) : 0);

        for (int i = qsize - 1; i >= loop_size; i--) {
            topicPage.add(topicQueue.get(i));
        }
        //数据不足从redis取
        int size = PAGE_SIZE - qsize;
        long s = System.currentTimeMillis();
        if (size > 0) {
            ListOperations<String, String> ls = redisTemplate.opsForList();
            List<String> cacheList = ls.range(TOPIC_LIST_KEY, 0, size - 1);
            for (int i = 0; i < cacheList.size(); i++) {
                topicPage.add(JSON.parseObject(cacheList.get(i), Topic.class));
            }
        }
        logger.info("get from reids,size={},cost={}", size, System.currentTimeMillis() - s);
        return topicPage;
    }

}
