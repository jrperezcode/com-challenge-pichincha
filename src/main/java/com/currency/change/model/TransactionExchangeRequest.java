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
@Table(name = "transaction_exchange_request")
public class TransactionExchangeRequest implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "currency_origin")
    private String currencyOrigin;

    @Column(name = "amount_init")
    private Double amountInit;

    @Column(name = "currency_destiny")
    private String currencyDestiny;
}
