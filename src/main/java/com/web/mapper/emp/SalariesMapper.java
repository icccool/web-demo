package com.web.mapper.emp;

import com.web.model.Salaries;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface SalariesMapper {

	List<Salaries> selectByPrimaryKey(Integer key);
}
