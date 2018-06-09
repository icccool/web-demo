package com.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/outOfMemTestController")
public class OutOfMemTestController {

	static class OOMObject{
		
	}
	
	@RequestMapping("/outOfMemTest.do")
	@ResponseBody
	public String outOfMemTest() {
		List<OOMObject> list = new ArrayList<OOMObject>();
		while(true) {
			list.add(new OOMObject());
		}
	}
}
