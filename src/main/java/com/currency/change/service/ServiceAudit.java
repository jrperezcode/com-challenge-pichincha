package com.currency.change.service;

import com.currency.change.model.AuditControl;
import reactor.core.publisher.Mono;

public interface ServiceAudit {

    Mono<AuditControl> registerAuditControl(AuditControl auditControl);

}
