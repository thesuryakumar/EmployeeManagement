package in.surya.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.surya.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
