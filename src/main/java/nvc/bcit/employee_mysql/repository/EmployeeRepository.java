package nvc.bcit.employee_mysql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nvc.bcit.employee_mysql.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//  Get name 
    public List<Employee> findByNameContaining(String name);
//  Get salary
    public List<Employee> findBySalaryGreaterThanEqual(Double salary);
}

    

