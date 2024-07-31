package com.example.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Employee;
import com.example.exception.EmployeeNotFoundException;
import com.example.service.IEmployeeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController {

	@Autowired
	IEmployeeService service;

	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody Employee emp) {

		long l = service.create(emp);

		String responce = "Employee with " + l + " Is saved";

		return new ResponseEntity<String>(responce, HttpStatus.OK);

	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Employee>> getAllEmployee() {
		List<Employee> allEployees = service.getAllEployees();

		ResponseEntity<List<Employee>> data = new ResponseEntity<List<Employee>>(allEployees, HttpStatus.OK);

		return data;

	}

	@GetMapping("/find/{id}")
	public ResponseEntity<?> getOneEmployee(@PathVariable long id) {

		ResponseEntity<?> resp = null;

		try {

			Employee oneEmployee = service.getOneEmployee(id);

			resp = new ResponseEntity<Employee>(oneEmployee, HttpStatus.OK);
		} catch (EmployeeNotFoundException e) {

			e.printStackTrace();

			throw e;
		}

		return resp;

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable long id) {

		ResponseEntity<String> resp = null;

		try {

			service.deleteById(id);

			resp = new ResponseEntity<String>("Employee deleted", HttpStatus.OK);
		} catch (EmployeeNotFoundException e) {

			e.printStackTrace();

			throw e;

		}
		return resp;

	}

	@PutMapping("/modify")
	public ResponseEntity<String> update(@RequestBody Employee emp) {
		ResponseEntity<String> resp = null;

		try {

			service.updateEmployee(emp);

			resp = new ResponseEntity<String>("Employee Updated", HttpStatus.OK);
		} catch (EmployeeNotFoundException e) {

			e.printStackTrace();

			throw e;

		}
		return resp;

	}

	@PatchMapping("modify/name/{id}/{name}")
	public ResponseEntity<String> updateEmpName(@PathVariable Long id, @PathVariable String name) {

		ResponseEntity<String> resp = null;

		try {

			service.updateEmployeeName(name, id);

			resp = new ResponseEntity<String>("Employee Updated", HttpStatus.OK);
		} catch (EmployeeNotFoundException e) {

			e.printStackTrace();

			throw e;

		}
		return resp;

	}

}
