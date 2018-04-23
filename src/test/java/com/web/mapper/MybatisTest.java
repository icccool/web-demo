package com.web.mapper;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Log4jConfigurer;

import com.web.model.Salaries;

public class MybatisTest {
	private static final Logger logger = LoggerFactory.getLogger(SalariesMapperTest.class);

	private static SalariesMapper salariesMapper;

	@BeforeClass
	public static void setUp() {
		try {
			Log4jConfigurer.initLogging("classpath:config/log4j.properties");

			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
			salariesMapper = ctx.getBean("salariesMapper", SalariesMapper.class);

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	@Test
	public void selectByPrimaryKey() {
		if (salariesMapper != null) {
			List<Salaries> salaries = salariesMapper.selectByPrimaryKey(10001);
			for (int i = 0; i < salaries.size(); i++) {
				Salaries s = salaries.get(i);
				System.out.println(s.getEmpNo() + " --> " + s.getSalary());
			}
		}

	}
}
