package nvc.bcit.employee_mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import nvc.bcit.employee_mysql.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{
    
    
}
