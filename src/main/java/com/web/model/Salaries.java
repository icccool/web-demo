package com.web.model;

import java.io.Serializable;
import java.util.Date;

public class Salaries implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer salary;
	private Date toDate;
	private Integer empNo;
	private Date fromDate;

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

}
