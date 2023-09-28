package com.employeemodel;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;



@Entity
@Table(schema="employee")
@NamedQueries({ @NamedQuery(name = EmployeeModel.FIND_ALL, query = "SELECT t FROM EmployeeModel t order by t.id") })
@XmlRootElement
public class EmployeeModel {
    
	public static final String FIND_ALL = "EmployeeModel.findAll";
	
	@Id
	@GeneratedValue
	@Column

	private String id;
	@Column
	private String ename;
	@Column
	private String deprt;
	@Column
	private String sal;
	@Transient
	private boolean edit = false;
	public EmployeeModel() {
		super();
	}

	public EmployeeModel(String ename, String deprt, String sal) {
		
		
		this.ename = ename;
		this.deprt = deprt;
		this.sal = sal;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getDeprt() {
		return deprt;
	}

	public void setDeprt(String deprt) {
		this.deprt = deprt;
	}

	public String getSal() {
		return sal;
	}

	public void setSal(String sal) {
		this.sal = sal;
	}

	@Override
	public String toString() {
		return "EmployeeModel [id=" + id + ", ename=" + ename + ", deprt=" + deprt + ", sal=" + sal + "]";
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	
	
	
	
}
