package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Branch;
import com.example.demo.entity.BranchType;

public interface BranchRepository extends JpaRepository<Branch, Long> {
	 List<Branch> findByClientId(Long clientId);
	    List<Branch> findByType(BranchType type);
	    List<Branch> findByClientIdAndType(Long clientId, BranchType type);


}
