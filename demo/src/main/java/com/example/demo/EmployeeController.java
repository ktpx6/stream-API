package com.example.demo;

import com.example.demo.exception.EmployeeAlreadyAddedException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.exception.EmployeeStorageIsFullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
        import java.util.Set;
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.addEmployee(firstName, lastName);
    }
    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }
    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }
    @GetMapping("/all")
    public Set<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEmployeeNotFoundException(EmployeeNotFoundException e) {
        return e.getMessage();
    }
    @ExceptionHandler(EmployeeStorageIsFullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEmployeeStorageIsFullException(EmployeeStorageIsFullException e) {
        return e.getMessage();
    }
    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleEmployeeAlreadyAddedException(EmployeeAlreadyAddedException e) {
        return e.getMessage();
    }
}
