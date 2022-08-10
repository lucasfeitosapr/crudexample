package com.gebotech.crudexample.model.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
public class ExpenseResponse {

    private String name;
    private boolean payed;
    private int installments;
    private LocalDateTime expireAt;
    private LocalDateTime createdAt;
    private boolean recurrent;
    private BigDecimal value;

}
