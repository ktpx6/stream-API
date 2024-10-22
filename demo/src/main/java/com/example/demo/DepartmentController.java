package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final EmployeeService employeeService;

    @Autowired
    public DepartmentController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/max-salary")
    public Employee getEmployeeWithMaxSalary(@RequestParam int department) {
        return employeeService.getEmployeeWithMaxSalary(department);
    }

    @GetMapping("/min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam int department) {
        return employeeService.getEmployeeWithMinSalary(department);
    }

    @GetMapping("/all")
    public Set<Employee> getEmployeesByDepartment(@RequestParam(required = false) Integer department) {
        if (department != null) {
            return employeeService.getEmployeesByDepartment(department);
        } else {
            return employeeService.getAllEmployeesGroupedByDepartment();
        }
    }
}
