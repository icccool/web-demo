package com.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.mapper.SalariesMapper;
import com.web.model.Salaries;
import com.web.service.SalariesService;

@Service
public class SalariesServiceImpl implements SalariesService {

	@Autowired
	private SalariesMapper salariesMapper;
	
	public List<Salaries> selectByPrimaryKey(Integer key) {
		return salariesMapper.selectByPrimaryKey(key);
	}
	
	
}
