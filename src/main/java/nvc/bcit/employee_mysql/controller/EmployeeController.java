package nvc.bcit.employee_mysql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import nvc.bcit.employee_mysql.model.Department;
import nvc.bcit.employee_mysql.model.Employee;
import nvc.bcit.employee_mysql.model.Project;
import nvc.bcit.employee_mysql.repository.DepartmentRepository;
import nvc.bcit.employee_mysql.repository.ProjectRepository;
import nvc.bcit.employee_mysql.service.EmployeeService;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("")
    public ModelAndView employee(){
        List<Employee> employees = employeeService.findAll();
        return new ModelAndView("employee","employees",employees);
    }
    // HOMEWORK2
    @GetMapping("/new")
    public String newemployee(ModelMap modelMap){
        Employee employee = new Employee();
        //                 ชื่อต้องการจะเรียก  เก็บไว้ที่นี่ Class 
        modelMap.addAttribute("employee", employee);
        return "newemployee";
    }

    @GetMapping("/edit")
    public String editemployee(){
        return "editemployee";
    }

    @GetMapping("/name/{name}")
    public ModelAndView findEmployeeByName (@PathVariable("name") String name){
        List<Employee> employees = employeeService.findByName(name);
        return new ModelAndView("employee","employees",employees);
    }

    @GetMapping("/salary/{salary}")
    public ModelAndView findEmployeeBySalary (@PathVariable("salary") Double salary){
        List<Employee> employees = employeeService.findBySalary(salary);
        return new ModelAndView("employee","employees",employees);
    }
    
     // ดึง depaertment  เปลี่ยนจาก เอกพจน์ เป็น พหูพจน์
    // เพิ่ม Attr department เข้าไปใน Model เวลาเรียกใช้ Request ทุกอัน 
    @ModelAttribute("departments")
    public List<Department> loaddepartment(){
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }
    @ModelAttribute("projects")
    public List<Project> loadproject(){
        List<Project> projects = projectRepository.findAll();
        return projects;
    }


    // รับมาจาก method POST
    @PostMapping("/add")
    public String saveEmployee(Employee employee, BindingResult result){
        if(result.hasErrors()){
            return "newemployee";
        }else{
            // ถ้าถูกต้อง ครบถ้วน 
            employeeService.save(employee);
        }
        return "redirect:/employee";
    }
    
    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable int id, ModelMap modelMap){
        // ดึง employee ที่ต้องการแก้ไข
        Employee employee = employeeService.getById(id);
        modelMap.addAttribute("employee", employee);
        return "editemployee";
    }

    @PostMapping("/update")
    public String updateEmployeeByEmployee(@ModelAttribute("employee") Employee employee, BindingResult result){
        if(result.hasErrors()){
            return "editemployee";
        }else{
            // ดึง employeeจาก id
            Employee emp = employeeService.getById(employee.getId());
            emp.setName(employee.getName());
            emp.setSalary(employee.getSalary());
            emp.setProject(employee.getProject());
            emp.setDepartment(employee.getDepartment());

            employeeService.save(emp);
            return "redirect:/employee";
            
        }
    }
   
    @GetMapping("/delete/{id}")
    public ModelAndView deleteEmployee(@PathVariable int id){
        // ดึง employee ที่ต้องการ delete ลบจาก id
        Employee employee = employeeService.getById(id);
        employeeService.delete(employee);
        return new ModelAndView("redirect:/employee");

    }

    @PostMapping("/name")
    public ModelAndView searchEmployeeByName(String name,ModelMap modelMap){
        List<Employee> employees = employeeService.findByName(name);
        modelMap.addAttribute("name", name);
        return new ModelAndView("employee","employees",employees);
    }
}
