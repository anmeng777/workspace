package com.didispace.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.didispace.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByUserName(String userName);
}
