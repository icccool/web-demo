package com.web.controller;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.web.service.TopicService;
import com.web.utils.JsonResult;
import com.web.utils.PathUtil;

/**
 * Created by WANG on 2018/6/7.
 */
@Controller
@RequestMapping(value = "/path")
public class PathController {

    private final static Logger logger = LoggerFactory.getLogger(PathController.class);

    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "/showPath", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult addTopic(HttpServletRequest request) {

        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        // 得到文件绝对路径
        String realPath = servletContext.getRealPath("/config");
        System.out.println("realPath1===" + PathUtil.getWebRootPath());

        return new JsonResult(JsonResult.SUC_CODE,this.getClass().getClassLoader().getResource("").getPath());
    }
}
