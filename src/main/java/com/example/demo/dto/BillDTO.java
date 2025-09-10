package com.example.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class BillDTO {
    private Long id;

    @NotNull(message = "Amount cannot be null")
    private Double amount;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    private String description;

    @NotNull(message = "Employee ID cannot be null")
    private Long employeeId;

	public BillDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BillDTO(Long id, @NotNull(message = "Amount cannot be null") Double amount,
			@NotNull(message = "Date cannot be null") LocalDate date, String description,
			@NotNull(message = "Employee ID cannot be null") Long employeeId) {
		super();
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.employeeId = employeeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
    
}

