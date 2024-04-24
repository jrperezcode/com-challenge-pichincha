package com.currency.change.service;

import com.currency.change.model.AuditControl;
import com.currency.change.repository.AuditRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ServiceAuditImpl implements ServiceAudit{

    @Autowired
    private AuditRespository auditRespository;

    @Override
    public Mono<AuditControl> registerAuditControl(AuditControl auditControl) {
        return Mono.just(auditRespository.save(auditControl));
    }
}
