package com.web.service.impl;

import com.web.mapper.emp.SalariesMapper;
import com.web.model.Salaries;
import com.web.service.SalariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalariesServiceImpl implements SalariesService {

	@Autowired
	private SalariesMapper salariesMapper;
	
	public List<Salaries> selectByPrimaryKey(Integer key) {
		return salariesMapper.selectByPrimaryKey(key);
	}
	
	
}
