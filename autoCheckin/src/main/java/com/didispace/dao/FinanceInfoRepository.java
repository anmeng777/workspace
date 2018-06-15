package com.didispace.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.didispace.entity.FinanceInfo;

public interface FinanceInfoRepository extends JpaRepository<FinanceInfo, Long> {
	List<FinanceInfo> findByStartDateContainingAndIsBackFalse(String startDate);
	
}
