package com.currency.change.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "currency_exchange")
public class CurrencyExchange implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "origin_currency")
    private String originCurrency;

    @Column(name = "destiny_currency")
    private String destinyCurrency;

    @Column(name = "value_exchange")
    private Double valueExchange;
}
