package com.currency.change.service;

import com.currency.change.model.CurrencyExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ServiceExchange {

    Mono<CurrencyExchange> registerExchange(CurrencyExchange currencyExchange);

    Flux<CurrencyExchange> listAllExchange();

    Mono<CurrencyExchange> findExchangeByCodeCurrency(String currencyOrigin, String currencyDestiny);
}
