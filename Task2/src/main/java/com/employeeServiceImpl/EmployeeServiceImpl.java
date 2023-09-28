package com.employeeServiceImpl;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.primefaces.model.SortOrder;

import com.employeemodel.EmployeeModel;

@Stateful
public class EmployeeServiceImpl {
  
	public static  Object INSTANCE ;
	
	@PersistenceContext(unitName="applicationTodoPU", type= PersistenceContextType.EXTENDED)
	private EntityManager eManager;
	private EntityManager eManager2;
	
	private EntityManager getEntityManager() {
		
		try {
			
			EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("applicationTodoLocalPU");
			eManager2=entityManagerFactory.createEntityManager();
			return eManager2;
			
		} catch (Exception e) {
			System.out.println("Exception in getEntityManager() "+ e);
			return null;
		}
	}

public EmployeeModel createEmployee(EmployeeModel employee) {
		
		eManager.persist(employee);
		return employee;
	} 
	
	public List<EmployeeModel> findAll() {
		
		try {
			TypedQuery<EmployeeModel> typedQuery=eManager.createNamedQuery(EmployeeModel.FIND_ALL, EmployeeModel.class);
			return typedQuery.getResultList();
		} catch (Exception e) {
			
			System.out.println("Exception in findAll() " + e);
			return null;
		}
	}
//	
//	 public int getEmployeeTotalCount() {
//	      Query query = eManager.createQuery("Select count(e.id) From EmployeeModel e");
//	      return ((Long)query.getSingleResult()).intValue();
//	  }
//	
	
	 public List<EmployeeModel> getEmployeeList(int start, int size, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		    CriteriaBuilder cb = eManager.getCriteriaBuilder();
		    CriteriaQuery<EmployeeModel> query = cb.createQuery(EmployeeModel.class);
		    Root<EmployeeModel> root = query.from(EmployeeModel.class);
		    CriteriaQuery<EmployeeModel> select = query.select(root);
      
		    if (sortField != null) {
		          query.orderBy(sortOrder == SortOrder.DESCENDING ?
		                  cb.asc(root.get(sortField)) : cb.desc(root.get(sortField)));
		      }
		    if (filters != null && filters.size() > 0) {
		          List<Predicate> predicates = new ArrayList<>();
		          for (Map.Entry<String, Object> entry : filters.entrySet()) {
		              String field = entry.getKey();
		              Object value = entry.getValue();
		              if (value == null) {
		                  continue;
		              }

		              Expression<String> expr = root.get(field).as(String.class);
		              Predicate p = cb.like(cb.lower(expr),
		                      "%" + value.toString().toLowerCase() + "%");
		              predicates.add(p);
		          }
		          if (predicates.size() > 0) {
		              query.where(cb.and(predicates.toArray
		                      (new Predicate[predicates.size()])));
		          }
		      }
		    

	 TypedQuery<EmployeeModel> tq = eManager.createQuery(query);
	    tq.setFirstResult(start);
	    tq.setMaxResults(size);
	    List<EmployeeModel> list = tq.getResultList();
	    return list;
		   
		}

		public int getFilteredRowCount(Map<String, Object> filters) {
	        CriteriaBuilder cb = eManager.getCriteriaBuilder();
	        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
	        Root<EmployeeModel> root = criteriaQuery.from(EmployeeModel.class);
	        CriteriaQuery<Long> select = criteriaQuery.select(cb.count(root));
	        if (filters != null && filters.size() > 0) {
	            List<Predicate> predicates = new ArrayList<>();
	            for (Map.Entry<String, Object> entry : filters.entrySet()) {
	                String field = entry.getKey();
	                Object value = entry.getValue();
	                if (value == null) {
	                    continue;
	                }

	                Expression<String> expr = root.get(field).as(String.class);
	                Predicate p = cb.like(cb.lower(expr),
	                        "%" + value.toString().toLowerCase() + "%");
	                predicates.add(p);
	            }
	            if (predicates.size() > 0) {
	                criteriaQuery.where(cb.and(predicates.toArray
	                        (new Predicate[predicates.size()])));
	            }
	        }
	        Long count = eManager.createQuery(select).getSingleResult();
	        return count.intValue();
	    }
	      
	    
    
	
	public EmployeeModel deleteEmployee(EmployeeModel employee) {
		try {
			if (employee!=null) {
				eManager.remove(eManager.merge(employee));
				return employee;
			} 
			else {
      return null;
			}
			
		} catch (Exception e) {
			System.out.println("Exception in delete()"+e);
			return null;
		}
	}
       
	
	
	public EmployeeModel editEmployee(EmployeeModel employee) {
		try {
		
		EmployeeModel	merged= eManager.merge(employee);
		
			return merged;
		}
		catch (Exception e) {
			System.out.println("Exception in editTodo()" + e);
		    return null;
		}
	}

	

	
}
