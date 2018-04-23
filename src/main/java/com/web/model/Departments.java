package com.web.model;

import java.io.Serializable;

public class Departments implements Serializable {

    private String deptNo;

    private String deptName;

    private static final long serialVersionUID = 1L;

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo == null ? null : deptNo.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }
}