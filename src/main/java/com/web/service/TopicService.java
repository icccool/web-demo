package com.web.service;


import com.web.model.Topic;
import com.web.model.vo.TopicVo;

import java.util.List;

/**
 * Created by tongjizong on 2018/6/7.
 */
public interface TopicService {

    public int insert(Topic topic);

    public List<TopicVo> getTopicList();
}
