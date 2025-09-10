package com.example.demo.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findByEmployeeId(Long employeeId);

    List<Bill> findByEmployeeIdAndDate(Long employeeId, LocalDate date);

    @Query("SELECT COUNT(b) FROM Bill b WHERE b.employee.id = :employeeId AND b.date = :date")
    Long countBillsByEmployeeAndDate(@Param("employeeId") Long employeeId,
                                     @Param("date") LocalDate date);
}

   

    


