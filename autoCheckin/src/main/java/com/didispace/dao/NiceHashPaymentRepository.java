package com.didispace.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.didispace.entity.GasPrice;
import com.didispace.entity.NiceHashPayment;

public interface NiceHashPaymentRepository extends JpaRepository<NiceHashPayment, Long> {
//	List<GasPrice> findAllOrderByPriceDesc();
    @Query(value="select * from nice_hash_payment order by id desc limit 1", nativeQuery = true)
    NiceHashPayment findOrderByIdDescLimit1();
}
