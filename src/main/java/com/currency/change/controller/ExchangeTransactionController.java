package com.currency.change.controller;

import com.currency.change.model.TransactionExchangeRequest;
import com.currency.change.model.TransactionExchangeResponse;
import com.currency.change.service.ServiceTransactionExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/support/transaction")
@RequiredArgsConstructor
public class ExchangeTransactionController {

    @Autowired
    private ServiceTransactionExchange serviceTransactionExchange;

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<TransactionExchangeResponse>> registerTransactionExchange(@RequestBody TransactionExchangeRequest transactionExchangeRequest){

        return serviceTransactionExchange.registerTransactionExchange(transactionExchangeRequest)
                .map(c -> new ResponseEntity<>(c, HttpStatus.CREATED));
    }
}
