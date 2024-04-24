package com.currency.change.service;

import com.currency.change.model.AuditControl;
import com.currency.change.model.CurrencyExchange;
import com.currency.change.model.TransactionExchangeRequest;
import com.currency.change.model.TransactionExchangeResponse;
import com.currency.change.repository.TransactionExchangeRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class ServiceTransactionExchangeImpl implements ServiceTransactionExchange{

    @Autowired
    private TransactionExchangeRespository exchangeRespository;

    @Autowired
    private WebClient.Builder webclient;

    @Override
    public Mono<TransactionExchangeResponse> registerTransactionExchange(TransactionExchangeRequest transactionExchangeRequest) {
        CurrencyExchange serviceCurrencyExchange = webclient.build().get()
                .uri("http://localhost:8080/support/exchange/"
                        + transactionExchangeRequest.getCurrencyOrigin() + "/"
                        +transactionExchangeRequest.getCurrencyDestiny())
                .retrieve().bodyToMono(CurrencyExchange.class).block();

        double montoUsd;
        if (transactionExchangeRequest.getCurrencyOrigin().equals("USD")){
            montoUsd = transactionExchangeRequest.getAmountInit();
        } else{
            montoUsd = transactionExchangeRequest.getAmountInit() * serviceCurrencyExchange.getValueExchange();
        }

        double montoDestino;
        if (transactionExchangeRequest.getCurrencyDestiny().equals("USD")){
            montoDestino = montoUsd;
        } else{
            montoDestino = montoUsd * serviceCurrencyExchange.getValueExchange();
        }

        TransactionExchangeResponse transacExchangeResponse = TransactionExchangeResponse.builder()
                .id(transactionExchangeRequest.getId())
                .idUser(transactionExchangeRequest.getIdUser())
                .currencyOrigin(transactionExchangeRequest.getCurrencyOrigin())
                .amountInit(transactionExchangeRequest.getAmountInit())
                .currencyDestiny(transactionExchangeRequest.getCurrencyDestiny())
                .amountFinal(montoDestino)
                .valueExchange(serviceCurrencyExchange.getValueExchange())
                .build();

        AuditControl audit = AuditControl.builder()
                .id(transactionExchangeRequest.getId())
                .idUser(transactionExchangeRequest.getIdUser())
                .currencyOrigin(transactionExchangeRequest.getCurrencyOrigin())
                .amountInit(transactionExchangeRequest.getAmountInit())
                .currencyDestiny(transactionExchangeRequest.getCurrencyDestiny())
                .amountFinal(montoDestino)
                .valueExchange(serviceCurrencyExchange.getValueExchange())
                .date_register(LocalDateTime.now())
                .date_modify(null)
                .build();


        AuditControl auditControlService =  webclient.build().post()
                        .uri("http://localhost:8080/support/audit/register")
                        .body(Mono.just(audit), AuditControl.class)
                        .retrieve()
                        .bodyToMono(AuditControl.class).block();

        System.out.println("###############" + auditControlService);

        return Mono.just(exchangeRespository.save(transacExchangeResponse));
    }
}