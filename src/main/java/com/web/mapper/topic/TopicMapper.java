package com.web.mapper.topic;

import com.web.model.Topic;

import java.util.List;

public interface TopicMapper {

    int insert(Topic topic);

    List<Topic> getTopicList(Topic topic);
}
