package com.didispace.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.didispace.entity.WeixinUsers;

public interface WeixinUsersRepository extends JpaRepository<WeixinUsers, Long> {
	Optional<WeixinUsers> findByOpenid(String openid);
}
