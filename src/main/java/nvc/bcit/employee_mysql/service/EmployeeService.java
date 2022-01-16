package nvc.bcit.employee_mysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nvc.bcit.employee_mysql.model.Employee;
import nvc.bcit.employee_mysql.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee getById(int id){
        return employeeRepository.getById(id);
    }

    public List<Employee> findByName(String name){
        return employeeRepository.findByNameContaining(name);
    }

    public List<Employee> findBySalary(Double salary){
        return employeeRepository.findBySalaryGreaterThanEqual(salary);
    }
}
