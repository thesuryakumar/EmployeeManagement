package in.surya.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.surya.entity.Employee;
import in.surya.repo.EmployeeRepo;
import in.surya.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepo repo;
	@Override
	public List<Employee> getAllEmployee() {
		return repo.findAll();
	}

	@Override
	public Optional<Employee> getEmpById(int id) {
		return repo.findById(id);
	}

	@Override
	public Employee createEmp(Employee emp) {
		if (emp.getRole() == null) {
            emp.setRole("EMPLOYEE");
        }
        return repo.save(emp);
	}

	@Override
	public Optional<Employee> updateEmp(int id, Employee emp) {
		return repo.findById(id)
                .map(e -> {
                    e.setName(emp.getName());
                    e.setEmail(emp.getEmail());
                    e.setRole(emp.getRole());
                    return repo.save(e);
                });
	}

	@Override
	public boolean deleteEmp(int id) {
		if (!repo.existsById(id)) {
            return false;
        }
        repo.deleteById(id);
        return true;
	}
	
	
}
