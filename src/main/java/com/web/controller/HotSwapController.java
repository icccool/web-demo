package com.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hotswap")
public class HotSwapController {

	@RequestMapping("/swap")
	public String toLogin(HttpServletRequest request, HttpServletResponse response) {
		return "jsp/hotswap/hotswap";
	}
}
