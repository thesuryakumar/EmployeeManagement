package in.surya.service;

import java.util.List;
import java.util.Optional;

import in.surya.entity.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployee();
	Optional<Employee> getEmpById(int id);
	Employee createEmp(Employee emp);
	Optional<Employee> updateEmp(int id,Employee emp);
	boolean deleteEmp(int id);
	
}
