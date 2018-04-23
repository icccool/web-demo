package com.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.service.impl.SalariesServiceImpl;

/***
 *11223333
 * @author John
 */
@Controller
@RequestMapping("/main")
public class CacheController {

	@Autowired
	private SalariesServiceImpl salariesServiceImpl;
	@Autowired
	private ResourceLoader resourceLoader;

	@RequestMapping("/cache.do")
	public void cache(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		java.util.Date date = new java.util.Date();

	}
	
	@RequestMapping("/getFile.do")
	@ResponseBody
	public String testProperties() throws IOException {
		return "the content of resources:";
	}

}
