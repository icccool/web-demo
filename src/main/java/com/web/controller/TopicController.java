package com.web.controller;

import com.web.model.Topic;
import com.web.model.vo.TopicVo;
import com.web.service.TopicService;
import com.web.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
	public JsonResult addTopic(@RequestBody @Valid Topic topic, BindingResult result, HttpServletRequest request) {
		logger.info("addTopic() start");
		long t = System.currentTimeMillis();
		if (result.hasErrors()) {
			StringBuffer sbf = new StringBuffer();
			for (ObjectError err : result.getAllErrors()) {
				sbf.append(err.getObjectName() + err.getDefaultMessage());
			}
			return new JsonResult(JsonResult.FAL_CODE, sbf.toString());
		}
		//验证码校验
		String ip = getIpAddr(request);
		topic.setIp(ip);
		topic.setCreateTime(new Date());
		int i = topicService.insert(topic);
		logger.info("addTopic() ip={},i={},cost={}", ip, i, System.currentTimeMillis() - t);
		if (i > 0) {
			return new JsonResult(JsonResult.SUC_CODE, JsonResult.SUC_MSG);
		} else {
			return new JsonResult(JsonResult.FAL_CODE, JsonResult.FAL_MSG);
		}
	}


	@RequestMapping(value = "/toTopicList", method = RequestMethod.GET)
	public String toTopicList(HttpServletRequest request) {
		return "jsp/topic/topicList";
	}


	@RequestMapping(value = "/topicList", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult topicList(HttpServletRequest request) {
		long t = System.currentTimeMillis();
		logger.info("topicList() start");
		//生成验证码
		String ip = getIpAddr(request);
		List<TopicVo> list = topicService.getTopicList();
		if (!CollectionUtils.isEmpty(list)) {
			logger.info("topicList() ip={},list.size={},cost={}", ip, list.size(), System.currentTimeMillis() - t);
			return new JsonResult(JsonResult.SUC_CODE, JsonResult.SUC_MSG, list);
		} else {
			logger.info("topicList() list is empty.");
			return new JsonResult(JsonResult.FAL_CODE, JsonResult.FAL_MSG);
		}
	}

	/***
	 * IP
	 * 
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
