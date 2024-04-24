package com.currency.change.repository;

import com.currency.change.model.CurrencyExchange;
import com.currency.change.model.TransactionExchangeResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionExchangeRespository extends JpaRepository<TransactionExchangeResponse, Integer> {
}
