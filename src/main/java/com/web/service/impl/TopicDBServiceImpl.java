package com.web.service.impl;

import com.web.mapper.topic.TopicMapper;
import com.web.model.Topic;
import com.web.model.vo.TopicVo;
import com.web.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WANG on 2018/8/16.
 */
@Service
public class TopicDBServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    public int insert(Topic topic) {
        return topicMapper.insert(topic);
    }

    @Override
    public List<TopicVo> getTopicList() {
        return topicMapper.getTopicList(null);
    }
}
