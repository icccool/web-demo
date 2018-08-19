package com.web.mapper.emp;


import com.web.model.Employees;

import java.util.List;

public interface EmployeesMapper {

    List<Employees> selectAll(Employees emp);

    int deleteByPrimaryKey(Integer empNo);

    int insert(Employees record);

    int insertSelective(Employees record);

    Employees selectByPrimaryKey(Integer empNo);

    int updateByPrimaryKeySelective(Employees record);

    int updateByPrimaryKey(Employees record);

}