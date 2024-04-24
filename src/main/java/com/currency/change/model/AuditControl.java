package com.currency.change.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "audit_control")
public class AuditControl implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer idUser;
    private String currencyOrigin;
    private Double amountInit;
    private String currencyDestiny;
    private Double amountFinal;
    private Double valueExchange;
    private LocalDateTime date_register;
    private LocalDateTime date_modify;

}
