package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Modifying
	@Query("UPDATE Employee e SET e.empName = :empName WHERE e.empId = :empId")
	public int updateEmployeeName(@Param("empName") String empName, @Param("empId") Long empId);

}
