package com.LazyEmployee;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.employeeServiceImpl.EmployeeServiceImpl;
import com.employeemodel.EmployeeModel;

public class EmployeeLazy extends LazyDataModel<EmployeeModel> {

	private EmployeeServiceImpl employeeServiceImpl;
	
	public EmployeeLazy(EmployeeServiceImpl employeeServiceImpl) {
	
		this.employeeServiceImpl=employeeServiceImpl;
		
	}
@Override
	public List<EmployeeModel> load(int first,int pageSize, String sortField,SortOrder sortOrder, Map<String, Object> filters){
	
	List<EmployeeModel> list=employeeServiceImpl.getEmployeeList(first,pageSize,sortField,sortOrder,filters);
	
 	this.setRowCount(employeeServiceImpl.getFilteredRowCount(filters));
	
//	this.setRowCount(employeeServiceImpl.getEmployeeTotalCount());
		return list;
	}
}
