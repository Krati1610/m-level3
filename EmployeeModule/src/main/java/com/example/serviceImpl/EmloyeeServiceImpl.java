package com.example.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Employee;
import com.example.exception.EmployeeNotFoundException;
import com.example.repository.EmployeeRepository;
import com.example.service.IEmployeeService;



@Service
public class EmloyeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository repo;

	@Override
	public long create(Employee employee) {

		Employee save = repo.save(employee);

		long empId = save.getEmpId();

		return empId;

	}

	@Override
	public List<Employee> getAllEployees() {

		return repo.findAll();
	}

	@Override
	public Employee getOneEmployee(long id) {

		Optional<Employee> opt = repo.findById((int) id);

		if (opt.isPresent()) {

			return opt.get();
		} else {
			throw new EmployeeNotFoundException("Employee with id " + id + " Not found ");
		}

		/*
		 * return repo.findById((int) id) .orElseThrow(() -> new
		 * EmployeeNotFoundException("Employee Not found with " + id + "Number "));
		 */
	}

	@Override
	public void deleteById(long id) {

		repo.delete(getOneEmployee(id));

	}

	@Override
	public void updateEmployee(Employee emp) {
		Integer empId = (int) emp.getEmpId();

		if ((empId != null && repo.existsById((empId)))) {

			repo.save(emp);

		} else {

			throw new EmployeeNotFoundException("Employee " + empId + " Not Exist ");

		}

	}

	@Override
	@org.springframework.transaction.annotation.Transactional
	public int updateEmployeeName(String ename, Long eid) {
		if (eid != null && repo.existsById(eid.intValue())) {
			int empId = repo.updateEmployeeName(ename, eid);
			return repo.updateEmployeeName(ename, eid);
		} else {
			throw new EmployeeNotFoundException("Employee " + eid + " Not Exist ");
		}
	}

}
