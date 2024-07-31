package com.example.service;

import java.util.List;

import com.example.entity.Employee;

public interface IEmployeeService {

	public long create(Employee employee);

	public List<Employee> getAllEployees();

	public Employee getOneEmployee(long id);

	public void deleteById(long id);

	public void updateEmployee(Employee emp);

	public int updateEmployeeName(String ename, Long eid);

}
