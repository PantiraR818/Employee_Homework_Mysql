package nvc.bcit.employee_mysql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import nvc.bcit.employee_mysql.model.Department;
import nvc.bcit.employee_mysql.model.Employee;
import nvc.bcit.employee_mysql.model.Project;
import nvc.bcit.employee_mysql.repository.DepartmentRepository;
import nvc.bcit.employee_mysql.repository.ProjectRepository;
import nvc.bcit.employee_mysql.service.EmployeeService;

@Controller
public class MainController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    ProjectRepository projectRepository;
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/employee")
    public ModelAndView employee(){
        List<Employee> employees = employeeService.findAll();
        return new ModelAndView("employee","employees",employees);
    }

    @GetMapping("/department/employee")
    public ModelAndView department(){
        List<Department> departments = departmentRepository.findAll();
        return new ModelAndView("department","departments",departments);
    }

    @GetMapping("/project/employee")
    public ModelAndView project(){
        List<Project> projects = projectRepository.findAll();
        return new ModelAndView("project","projects",projects);
    }

    @GetMapping("/employee/name/{name}")
    public ModelAndView findEmployeeByName (@PathVariable("name") String name){
        List<Employee> employees = employeeService.findByName(name);
        return new ModelAndView("employee","employees",employees);
    }

    @GetMapping("/employee/salary/{salary}")
    public ModelAndView findEmployeeBySalary (@PathVariable("salary") Double salary){
        List<Employee> employees = employeeService.findBySalary(salary);
        return new ModelAndView("employee","employees",employees);
    }
    
}
