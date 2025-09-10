package com.example.demo.Controller;

import com.example.demo.Entity.Bill;
import com.example.demo.Entity.Employee;
import com.example.demo.Service.BillService;
import com.example.demo.Service.EmployeeService;
import com.example.demo.dto.EmployeeBillSummaryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/web/bills")
public class BillWebController {

    private final BillService billService;
    private final EmployeeService employeeService;

    public BillWebController(BillService billService, EmployeeService employeeService) {
        this.billService = billService;
        this.employeeService = employeeService;
    }

    // List bills for employee
    @GetMapping
    public String listBills(@RequestParam Long employeeId, Model model) {
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("bills", billService.getBillsByEmployee(employeeId));
        return "bills";  // bills.jsp
    }

    // Show create form
    @GetMapping("/new")
    public String showCreateForm(@RequestParam Long employeeId, Model model) {
        model.addAttribute("bill", new Bill());
        model.addAttribute("employeeId", employeeId);
        return "billForm";   // billForm.jsp
    }

    // Handle create
    @PostMapping
    public String createBill(@RequestParam Long employeeId, @ModelAttribute Bill bill) {
        Employee emp = employeeService.getEmployeeById(employeeId);

        com.example.demo.dto.BillDTO dto = new com.example.demo.dto.BillDTO(); // full-qualified to avoid import mistakes
        dto.setId(null);
        dto.setAmount(bill.getAmount());
        dto.setDate(bill.getDate());
        dto.setDescription(bill.getDescription());
        dto.setEmployeeId(emp.getId());

        billService.createBill(dto);
        return "redirect:/web/bills?employeeId=" + employeeId;
    }



    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Bill bill = billService.getBillById(id);
        model.addAttribute("bill", bill);
        model.addAttribute("employeeId", bill.getEmployee().getId());
        return "billForm";   // billForm.jsp
    }

    // Handle update
    @PostMapping("/update/{id}")
    public String updateBill(@PathVariable Long id, @RequestParam Long employeeId, @ModelAttribute Bill bill) {
        com.example.demo.dto.BillDTO dto = new com.example.demo.dto.BillDTO();
        dto.setId(id);
        dto.setAmount(bill.getAmount());
        dto.setDate(bill.getDate());
        dto.setDescription(bill.getDescription());
        dto.setEmployeeId(employeeId);

        billService.updateBill(id, dto);
        return "redirect:/web/bills?employeeId=" + employeeId;
    }

    // Delete bill
    @GetMapping("/delete/{id}")
    public String deleteBill(@PathVariable Long id, @RequestParam Long employeeId) {
        billService.deleteBill(id);
        return "redirect:/web/bills?employeeId=" + employeeId;
    }

    // Employee bill summary
    @GetMapping("/employee-bills-summary")
    public String showEmployeeBillSummary(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        LocalDate today = LocalDate.now();

        List<EmployeeBillSummaryDTO> summaryList = employees.stream()
                .map(emp -> {
                    Long count = billService.countBillsByEmployeeAndDate(emp.getId(), today);
                    EmployeeBillSummaryDTO dto = new EmployeeBillSummaryDTO();
                    dto.setEmployeeId(emp.getId());
                    dto.setTotalBills(count.intValue());                 
                    return dto;
                })
                .toList();

        model.addAttribute("summaryList", summaryList);
        return "employeebills";  // employeebills.jsp
    }
}

