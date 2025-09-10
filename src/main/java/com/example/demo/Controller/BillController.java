package com.example.demo.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Bill;
import com.example.demo.Service.BillService;
import com.example.demo.dto.BillDTO;
import com.example.demo.dto.EmployeeBillSummaryDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bills")
public class BillController {
    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public ResponseEntity<List<Bill>> getAll() {
        return ResponseEntity.ok(billService.getAllBills());
    }

    @PostMapping
    public ResponseEntity<Bill> create(@Valid @RequestBody BillDTO dto) {
        return ResponseEntity.ok(billService.createBill(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bill> update(@PathVariable Long id, @Valid @RequestBody BillDTO dto) {
        return ResponseEntity.ok(billService.updateBill(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        billService.deleteBill(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Bills summary for today
    @GetMapping("/{employeeId}/summary/today")
    public ResponseEntity<EmployeeBillSummaryDTO> getSummaryToday(@PathVariable Long employeeId) {
        return ResponseEntity.ok(billService.getBillsSummaryToday(employeeId));
    }
}

