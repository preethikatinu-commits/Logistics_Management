package com.example.demo.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Amount is required")
    private Double amount;

    @NotNull(message = "Date is required")
    private LocalDate date;

    private String description;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    

	public Bill() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bill(Long id, @NotNull(message = "Amount is required") Double amount,
			@NotNull(message = "Date is required") LocalDate date, String description, Employee employee) {
		super();
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.description = description;
		this.employee = employee;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", amount=" + amount + ", date=" + date + ", description=" + description
				+ ", employee=" + employee + "]";
	}
	
    
}

