package com.didispace.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.didispace.entity.MailReciever;


public interface MailRecieverRepository extends JpaRepository<MailReciever, Long> {
    List<MailReciever> findByIsEnableTrueAndWeatherAndXianhaoAndYoujia(
    		boolean weather, boolean xianhao, 
    		boolean youjia);
    List<MailReciever> findByIsEnableTrueAndYoujia(boolean youjia);
    List<MailReciever> findByIsEnableTrueAndWeather(boolean Weather);
    List<MailReciever> findByIsEnableTrueAndXianhao(boolean Xianhao);
    Optional<MailReciever> findByMail(String mail);

}
