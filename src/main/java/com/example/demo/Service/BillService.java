package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Bill;
import com.example.demo.Entity.Employee;
import com.example.demo.Repository.BillRepository;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.dto.BillDTO;
import com.example.demo.dto.EmployeeBillSummaryDTO;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final EmployeeRepository employeeRepository;

    public BillService(BillRepository billRepository, EmployeeRepository employeeRepository) {
        this.billRepository = billRepository;
        this.employeeRepository = employeeRepository;
    }

    // ✅ Create Bill from Bill entity
    public Bill createBill(BillDTO dto) {
        Employee emp = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        Bill bill = new Bill();
        bill.setAmount(dto.getAmount());
        bill.setDate(dto.getDate());
        bill.setDescription(dto.getDescription());
        bill.setEmployee(emp);
        return billRepository.save(bill);
    }

    // ✅ Get Bill by ID
    public Bill getBillById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));
    }

    // ✅ Update Bill
    public Bill updateBill(Long id, BillDTO dto) {
        Bill existing = getBillById(id);
        Employee emp = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        existing.setAmount(dto.getAmount());
        existing.setDate(dto.getDate());
        existing.setDescription(dto.getDescription());
        existing.setEmployee(emp);
        return billRepository.save(existing);
    }
    // ✅ Delete Bill
    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }

    // ✅ Get all bills
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    // ✅ Get bills by employee
    public List<Bill> getBillsByEmployee(Long employeeId) {
        return billRepository.findByEmployeeId(employeeId);
    }

    // ✅ Count bills per employee per date
    public Long countBillsByEmployeeAndDate(Long employeeId, LocalDate date) {
        return billRepository.countBillsByEmployeeAndDate(employeeId, date);
    }

    // ✅ Optional: Bills summary DTO for today
    public EmployeeBillSummaryDTO getBillsSummaryToday(Long employeeId) {
        LocalDate today = LocalDate.now();
        List<Bill> bills = billRepository.findByEmployeeIdAndDate(employeeId, today);

        int totalBills = bills.size();
        double totalAmount = bills.stream().mapToDouble(Bill::getAmount).sum();

        EmployeeBillSummaryDTO dto = new EmployeeBillSummaryDTO();
        dto.setEmployeeId(employeeId);
        dto.setDate(today);
        dto.setTotalBills(totalBills);
        dto.setTotalAmount(totalAmount);
        return dto;
    }
}
