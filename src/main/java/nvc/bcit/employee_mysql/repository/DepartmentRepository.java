package nvc.bcit.employee_mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nvc.bcit.employee_mysql.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{
    
    
}
