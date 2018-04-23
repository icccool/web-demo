package com.web.service.impl;

import java.util.List;

import com.web.mapper.EmployeesMapper;
import com.web.model.Employees;
import com.web.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.mapper.SalariesMapper;
import com.web.model.Salaries;
import com.web.service.SalariesService;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmployeesMapper employeesMapper;

	@Override
	public List<Employees> selectAll(Employees employees) {
		return employeesMapper.selectAll(employees);
	}
}
