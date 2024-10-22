package com.example.demo;

import com.example.demo.exception.EmployeeAlreadyAddedException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
@Service
public class EmployeeService {
    private static final int MAX_EMPLOYEES = 10;
    private final Set<Employee> employees = new HashSet<>();
    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Employee storage is full");
        }
        Employee employee = new Employee(firstName, lastName);
        if (!employees.add(employee)) {
            throw new EmployeeAlreadyAddedException("Employee already added");
        }
        return employee;
    }
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.remove(employee)) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        return employee;
    }
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        return employee;
    }
    public Set<Employee> getAllEmployees() {
        return new HashSet<>(employees);
    }
}
