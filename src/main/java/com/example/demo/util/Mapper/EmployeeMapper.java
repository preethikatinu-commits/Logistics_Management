package com.example.demo.util.Mapper;


import com.example.demo.Entity.Employee;
import com.example.demo.dto.EmployeeDTO;

public class EmployeeMapper {

    public static EmployeeDTO toDTO(Employee employee) {
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getDepartment(),
                employee.getRole(),
                employee.getSalary()
        );
    }

    public static Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setDepartment(dto.getDepartment());
        employee.setRole(dto.getRole());
        employee.setSalary(dto.getSalary());
        return employee;
    }
}

