package com.currency.change.service;

import com.currency.change.model.TransactionExchangeRequest;
import com.currency.change.model.TransactionExchangeResponse;
import reactor.core.publisher.Mono;

public interface ServiceTransactionExchange {

    Mono<TransactionExchangeResponse> registerTransactionExchange(TransactionExchangeRequest currencyExchange);

}
