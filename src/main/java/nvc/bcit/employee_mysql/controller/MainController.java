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
    DepartmentRepository departmentRepository;
    @Autowired
    ProjectRepository projectRepository;
    @GetMapping("/")
    public String index(){
        return "index";
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

    
}
