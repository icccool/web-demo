package com.web.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.web.model.Salaries;

@MapperScan
public interface SalariesMapper {

	List<Salaries> selectByPrimaryKey(Integer key);
}
