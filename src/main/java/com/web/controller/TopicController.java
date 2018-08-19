package com.web.controller;


import com.web.model.Topic;
import com.web.service.TopicService;
import com.web.utils.DateUtil;
import com.web.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by WANG on 2018/6/7.
 */
@Controller
@RequestMapping(value = "/topic")
public class TopicController {

    private final static Logger logger = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "/addTopic", method = RequestMethod.POST)
    @ResponseBody
    public  JsonResult addTopic(@RequestBody Topic topic, HttpServletRequest request) {
        JsonResult result = new JsonResult();
        topic.setIp(getIpAddr(request));
        topic.setCreateTimeStr(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss SSS"));
        topicService.insert(topic);
        result.setStatus(JsonResult.SUC_CODE);
        return result;
    }

    @RequestMapping(value = "/topicList", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult topicList(HttpServletRequest request) {
        String path = this.getClass().getResource("/").toString();
        String ip = getIpAddr(request);
        List list = topicService.getTopicList();
        logger.info("topicList() ip={},list.size={}", ip, list.size());
        JsonResult result = new JsonResult();
        result.setStatus(JsonResult.SUC_CODE);
        result.setResult(list);
        return result;
    }

    /***
     * IP
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
