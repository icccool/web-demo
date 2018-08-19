package com.web.service.impl;

import com.web.mapper.emp.EmployeesMapper;
import com.web.model.Employees;
import com.web.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmployeesMapper employeesMapper;

	@Override
	public List<Employees> selectAll(Employees employees) {
		return employeesMapper.selectAll(employees);
	}
}
