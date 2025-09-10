package com.example.demo.util.Mapper;


import com.example.demo.Entity.Bill;
import com.example.demo.Entity.Employee;
import com.example.demo.dto.BillDTO;

public class BillMapper {

    public static BillDTO toDTO(Bill bill) {
        return new BillDTO(
                bill.getId(),
                bill.getAmount(),
                bill.getDate(),
                bill.getDescription(),
                bill.getEmployee().getId()
        );
    }

    public static Bill toEntity(BillDTO dto, Employee employee) {
        Bill bill = new Bill();
        bill.setId(dto.getId());
        bill.setAmount(dto.getAmount());
        bill.setDate(dto.getDate());
        bill.setDescription(dto.getDescription());
        bill.setEmployee(employee);
        return bill;
    }
}
