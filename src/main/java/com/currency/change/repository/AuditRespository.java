package com.currency.change.repository;

import com.currency.change.model.AuditControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRespository extends JpaRepository<AuditControl, Integer> {
}
