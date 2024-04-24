package com.currency.change.repository;

import com.currency.change.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRespository extends JpaRepository<CurrencyExchange, Integer> {
}
