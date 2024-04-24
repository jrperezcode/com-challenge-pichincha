package com.currency.change.service;

import com.currency.change.model.CurrencyExchange;
import com.currency.change.repository.ExchangeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class ServiceExchangeImpl implements ServiceExchange{

    @Autowired
    private ExchangeRespository exchangeRespository;

    @Override
    public Mono<CurrencyExchange> registerExchange(CurrencyExchange currencyExchange) {
       return Mono.just(exchangeRespository.save(currencyExchange));
    }

    @Override
    public Flux<CurrencyExchange> listAllExchange() {
        return Flux.fromIterable(exchangeRespository.findAll());
    }

    @Override
    public Mono<CurrencyExchange> findExchangeByCodeCurrency(String currencyOrigin, String currencyDestiny) {
        CurrencyExchange e = new CurrencyExchange();
        e.setOriginCurrency(currencyOrigin);
        e.setDestinyCurrency(currencyDestiny);
        Optional<CurrencyExchange> e1 = exchangeRespository.findOne(Example.of(e));
        return Mono.just(e1.get());
    }

}
