package com.didispace.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.didispace.entity.User;
import com.didispace.entity.WeiXinPaments;

public interface WeiXinPaymentsRepository extends JpaRepository<WeiXinPaments, Long> {
	Optional<WeiXinPaments> findByOrderid(String orderid);
}
