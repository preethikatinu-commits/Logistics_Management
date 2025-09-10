
package com.example.demo.dto;

import java.time.LocalDate;

public class EmployeeBillSummaryDTO {
    private Long employeeId;
    private LocalDate date;
    private int totalBills;
    private double totalAmount;

    public EmployeeBillSummaryDTO() {}  // 👈 needed for new EmployeeBillSummaryDTO()

    public EmployeeBillSummaryDTO(Long employeeId, LocalDate date, int totalBills, double totalAmount) {
        this.employeeId = employeeId;
        this.date = date;
        this.totalBills = totalBills;
        this.totalAmount = totalAmount;
    }

    // Getters and Setters
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public int getTotalBills() { return totalBills; }
    public void setTotalBills(int totalBills) { this.totalBills = totalBills; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}
