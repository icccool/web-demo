package com.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.annotation.EmpAnnotation;
import com.web.model.Employees;
import com.web.service.impl.EmpServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/***
 *
 * http://localhost:8090/web/emp/getEmpList.do  ?cacheId=123456
 *
 * @author 2017-12-05 17:49:30 WANG
 */
@Controller
@RequestMapping("/emp")
public class EmpController {

	private static Logger logger = LoggerFactory.getLogger(EmpController.class);

	@Autowired
	private EmpServiceImpl empServiceImpl;

	@RequestMapping("/getEmpList.do")
	public ModelAndView getEmpList(HttpServletRequest request, HttpServletResponse response)  {
		System.out.println("--------------------START------------------------");
		String ip = getRemoteHost(request);
		logger.info("======= ip => {}" , ip);
		logger.info("======= classLoader => {}" , empServiceImpl.getClass().getClassLoader());
		System.out.println("--------------------END------------------------");

		ModelAndView model = new ModelAndView("/jsp/emp/empList");
		PageHelper.startPage(1, 10);
		List<Employees> empList = empServiceImpl.selectAll(null);
		PageInfo<Employees> pageInfo = new PageInfo<Employees>(empList);
		model.addObject("page", pageInfo);
		model.addObject("empList",empList);
		return model;
	}

	@EmpAnnotation
	@RequestMapping("/getEmpListTable.do")
	public List getEmpListTable(HttpServletRequest request, HttpServletResponse response)  {
		//ModelAndView model = new ModelAndView("/jsp/emp/empList");
		PageHelper.startPage(1, 10);
		return empServiceImpl.selectAll(null);
	}


	public String getRemoteHost(javax.servlet.http.HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}

}
