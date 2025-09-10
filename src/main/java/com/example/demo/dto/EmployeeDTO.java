package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    private String department;
    private String role;
    private Double salary;
	public EmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeDTO(Long id, @NotBlank(message = "Name cannot be empty") String name, String department, String role,
			Double salary) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.role = role;
		this.salary = salary;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
    
}


