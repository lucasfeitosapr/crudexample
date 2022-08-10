package com.gebotech.crudexample.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
public class ExpenseRequest {

    private String name;
    private boolean payed;
    private int installments;
    private String expireAt;
    private boolean recurrent;
    private BigDecimal value;
    private long userId;

}
