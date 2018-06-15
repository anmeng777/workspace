package com.didispace.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.didispace.entity.ActivityLog;


public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {

    List<ActivityLog> findByLogDateAndType(String logDate, short type);
    List<ActivityLog> findByLogDate(String logDate);
}
