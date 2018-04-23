package com.web.controller;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.model.Salaries;
import com.web.service.impl.SalariesServiceImpl;


/***
 * 
 * @author John
 *
 */
@Controller
@RequestMapping("/main")
public class LoginController {

	@Autowired
	private SalariesServiceImpl salariesServiceImpl;

	@RequestMapping("/login.do")
	public String toLogin(HttpServletRequest request, HttpServletResponse response) {
		String csn = Charset.defaultCharset().name();
		System.out.println(csn);
		System.out.println("77777777");
		List<Salaries> salaries = salariesServiceImpl.selectByPrimaryKey(10001);
		for (int i = 0; i < salaries.size(); i++) {
			Salaries s = salaries.get(i);
			System.out.println(s.getEmpNo() + " --> " + s.getSalary());
		}
		return "/jsp/page/login";
	}
}
