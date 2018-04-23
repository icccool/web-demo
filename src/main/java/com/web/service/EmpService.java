package com.web.service;

import com.web.model.Employees;
import com.web.model.Salaries;

import java.util.List;

public interface EmpService {

	List<Employees> selectAll(Employees employees);
}
