package com.web.service;

import java.util.List;

import com.web.model.Salaries;

public interface SalariesService {

	List<Salaries> selectByPrimaryKey(Integer key);
}
