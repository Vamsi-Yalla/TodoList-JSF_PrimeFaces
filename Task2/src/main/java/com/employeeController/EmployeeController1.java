package com.employeeController;

import java.util.ArrayList;



import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UICommand;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.CellEditEvent;
import org.primefaces.model.LazyDataModel;

import com.employeeServiceImpl.EmployeeServiceImpl;
import com.employeemodel.EmployeeModel;

@ManagedBean(name="empcontroller")
@SessionScoped
public class EmployeeController1 {
   
	private boolean editDisabled = false;
	
	private List<EmployeeModel> employees;
	private EmployeeModel employee;
	private EmployeeModel employeeEdit;
	private EmployeeModel newemployee;
	

	
	
	private UIForm form;
	private UIForm tableForm;
	private UICommand addCommand;

	public UICommand getAddCommand() {
		return addCommand;
	}

	public void setAddCommand(UICommand addCommand) {
		this.addCommand = addCommand;
	}
 
	public EmployeeModel getEmployeeEdit() {
		return employeeEdit;
	}

	public void setEmployeeEdit(EmployeeModel employeeEdit) {
		this.employeeEdit = employeeEdit;
	}

	public UIForm getForm() {
		return form;
	}

	public void setForm(UIForm form) {
		this.form = form;
	}

	

	@Inject
	private EmployeeServiceImpl employeeServiceImpl;

	public EmployeeController1() {
		
		employees = new ArrayList<EmployeeModel>();
		newemployee = new EmployeeModel();
			}
	
	@PostConstruct
	public void init() {
		employees=employeeServiceImpl.findAll();
		newemployee = new EmployeeModel();
		
	}
	
	
	
	public List<EmployeeModel> addNew() {
	     employeeServiceImpl.createEmployee(newemployee);
		employees = employeeServiceImpl.findAll();
		
		newemployee = new EmployeeModel();
	
		return employees;
		
	}
	
	
	
	public String cancel() {
		newemployee = null;
		form.setRendered(false);
		addCommand.setRendered(true);
		return null;
	}
	
	public String delete() {
		employeeServiceImpl.deleteEmployee(employee); 
		employees=employeeServiceImpl.findAll();
		return null;
	}
	
	  public String edit() {
		
		  setEditDisabled(true);
		  employee.setEdit(true);
		  employeeEdit = new EmployeeModel();
			employeeEdit.setId(employee.getId());
			employeeEdit.setEname(employee.getEname());
			employeeEdit.setDeprt(employee.getDeprt());
			employeeEdit.setEdit(false);
	      employeeEdit.setSal(employee.getSal());
	      System.out.println(employee);
	      return null;
	}
	   
	   
	   
	   
	  public String editSave() {
		   setEditDisabled(false);
			employee.setEdit(false);
		   employeeServiceImpl.editEmployee(employee);
		   employees = employeeServiceImpl.findAll();
		   
		   return null;
	   }
	   
	   
	  
	  public String editReset() {
			setEditDisabled(false);
			employee.setEdit(false);
			employee.setId(employeeEdit.getId());
			employee.setEname(employeeEdit.getEname());
			employee.setDeprt(employeeEdit.getDeprt());
			employee.setSal(employeeEdit.getSal());
			return null;
		}
	  
	  
	
	 public List<EmployeeModel> updateEmployee1(EmployeeModel employee) {
		
		employeeServiceImpl.editEmployee(employee);
		
		employees=employeeServiceImpl.findAll();
		employee = new EmployeeModel();
		return employees;
		 
	}  	   

	public List<EmployeeModel> getEmployees() {
		return employees;
	}

	public UIForm getTableForm() {
		return tableForm;
	}

	public void setTableForm(UIForm tableForm) {
		this.tableForm = tableForm;
	}

	public void setEmployees(List<EmployeeModel> employees) {
		this.employees = employees;
	}

	public EmployeeModel getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeModel employee) {
		this.employee = employee;
	}

	public EmployeeModel getNewemployee() {
		return newemployee;
	}

	public void setNewemployee(EmployeeModel newemployee) {
		this.newemployee = newemployee;
	}

	public EmployeeServiceImpl getEmployeeServiceImpl() {
		return employeeServiceImpl;
	}

	public void setEmployeeServiceImpl(EmployeeServiceImpl employeeServiceImpl) {
		this.employeeServiceImpl = employeeServiceImpl;
	}

	public boolean isEditDisabled() {
		return editDisabled;
	}

	public void setEditDisabled(boolean editDisabled) {
		this.editDisabled = editDisabled;
	}

	public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
  
	
}
