package com.web.mapper;

import com.web.mapper.emp.SalariesMapper;
import com.web.model.Salaries;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Log4jConfigurer;

import java.util.List;

public class SpringTest {

	private static final Logger logger = LoggerFactory.getLogger(SalariesMapperTest.class);

	private static SalariesMapper salariesMapper;

	@BeforeClass
	public static void setUp() {
		try {
			Log4jConfigurer.initLogging("classpath:config/log4j.properties");

			ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-context.xml");
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
