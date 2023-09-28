package com.employeeService;

import java.util.List;

import com.employeemodel.EmployeeModel;

public interface EmployeeService {
   
	public EmployeeModel createEmployee(EmployeeModel employee);
	public List<EmployeeModel> findAll() ;
	public void deleteEmployee(EmployeeModel employee) ;
	public EmployeeModel updateEmployee(EmployeeModel employee);
	}

