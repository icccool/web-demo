package com.web.mapper.topic;

import com.web.model.Topic;
import com.web.model.vo.TopicVo;

import java.util.List;

public interface TopicMapper {

    int insert(Topic topic);

    List<TopicVo> getTopicList(Topic topic);
}
