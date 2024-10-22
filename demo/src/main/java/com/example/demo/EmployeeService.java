package com.example.demo;

import com.example.demo.exception.EmployeeAlreadyAddedException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private static final int MAX_EMPLOYEES = 10;
    private final Set<Employee> employees = new HashSet<>();

    public Employee addEmployee(String firstName, String lastName, int salary, int department) {
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Employee storage is full");
        }
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (!employees.add(employee)) {
            throw new EmployeeAlreadyAddedException("Employee already added");
        }
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (!employees.remove(employee)) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        return employee;
    }

    public Employee findEmployee(String firstName, String lastName, int salary, int department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        return employee;
    }

    public Set<Employee> getAllEmployees() {
        return new HashSet<>(employees);
    }

    public Employee getEmployeeWithMaxSalary(int department) {
        return employees.stream()
                .filter(e -> e.department() == department)
                .max(Comparator.comparingInt(Employee::salary))
                .orElseThrow(() -> new EmployeeNotFoundException("No employees found in department " + department));
    }

    public Employee getEmployeeWithMinSalary(int department) {
        return employees.stream()
                .filter(e -> e.department() == department)
                .min(Comparator.comparingInt(Employee::salary))
                .orElseThrow(() -> new EmployeeNotFoundException("No employees found in department " + department));
    }

    public Set<Employee> getEmployeesByDepartment(int department) {
        return employees.stream()
                .filter(e -> e.department() == department)
                .collect(Collectors.toSet());
    }

    public Set<Employee> getAllEmployeesGroupedByDepartment() {
        return new HashSet<>(employees);
    }
}
