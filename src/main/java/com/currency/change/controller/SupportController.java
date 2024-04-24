package com.currency.change.controller;

import com.currency.change.model.CurrencyExchange;
import com.currency.change.service.ServiceExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/support/exchange")
@RequiredArgsConstructor
public class SupportController {

    @Autowired
    private ServiceExchange serviceExchange;

    @PostMapping(value = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<CurrencyExchange>> registerCurrencyExchange(@RequestBody CurrencyExchange currencyExchange){
        return serviceExchange.registerExchange(currencyExchange)
                .map(c -> new ResponseEntity<>(c, HttpStatus.CREATED));
    }

    @PutMapping(value = "/update")
    public Mono<ResponseEntity<CurrencyExchange>> updateCurrencyExchange(@RequestBody CurrencyExchange currencyExchange){
        return serviceExchange.registerExchange(currencyExchange)
                .map(c -> new ResponseEntity<>(c, HttpStatus.CREATED));
    }
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public Flux<CurrencyExchange> listCurrencyExchange(){
        return serviceExchange.listAllExchange();
    }

    @GetMapping("/{currencyOrigin}/{currencyDestiny}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CurrencyExchange> findExchangeByCodeCurrency(@PathVariable String currencyOrigin, @PathVariable String currencyDestiny){
        return serviceExchange.findExchangeByCodeCurrency(currencyOrigin, currencyDestiny);
    }
}
