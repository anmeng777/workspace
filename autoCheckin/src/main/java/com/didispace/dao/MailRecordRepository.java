package com.didispace.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.didispace.entity.MailReciever;
import com.didispace.entity.MailRecord;


public interface MailRecordRepository extends JpaRepository<MailRecord, Long> {

    List<MailReciever> findByIsSuccessFalse();

}
