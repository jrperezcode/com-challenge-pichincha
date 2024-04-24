package com.currency.change.controller;

import com.currency.change.model.AuditControl;
import com.currency.change.service.ServiceAudit;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/support/audit")
@RequiredArgsConstructor
public class AuditController {


    @Autowired
    private ServiceAudit serviceAudit;

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<AuditControl>> registerTransactionExchange(@RequestBody AuditControl auditRequest){
        return serviceAudit.registerAuditControl(auditRequest)
                .map(a -> new ResponseEntity<>(a, HttpStatus.CREATED));
    }
}
