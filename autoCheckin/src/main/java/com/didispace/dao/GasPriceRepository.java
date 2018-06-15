package com.didispace.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.didispace.entity.GasPrice;

public interface GasPriceRepository extends JpaRepository<GasPrice, Long> {
//	List<GasPrice> findAllOrderByPriceDesc();
    @Query(value="select * from gas_price g order by g.price desc limit 1", nativeQuery = true)
    GasPrice findByPriceOrderByPriceDescLimit1();
    @Query(value="select * from gas_price g order by g.price limit 1", nativeQuery = true)
    GasPrice findByPriceOrderByPriceLimit1();
}
