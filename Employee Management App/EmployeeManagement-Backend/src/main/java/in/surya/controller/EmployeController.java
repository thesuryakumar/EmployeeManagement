package in.surya.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.surya.entity.Employee;
import in.surya.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeController {
	@Autowired
	private EmployeeService service;
	
	@GetMapping
	public List<Employee> getall(){
		return service.getAllEmployee();
	}
	
	@PostMapping
	public Employee create(@RequestBody Employee emp) {
			return service.createEmp(emp);
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable int id,@RequestBody Employee update){
		return service.updateEmp(id, update)
                .map(updated -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "Employee updated successfully");
                    response.put("status", true);
                    response.put("data", updated);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("message", "Employee not found");
                    response.put("status", false);
                    return ResponseEntity.ok(response);
                });
	}
	@DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        boolean deleted = service.deleteEmp(id);
        Map<String, Object> response = new HashMap<>();
        response.put("status", deleted);

        if (deleted) {
            response.put("message", "Employee deleted successfully");
        } else {
            response.put("message", "Employee not found");
        }

        return ResponseEntity.ok(response);
	}
}
