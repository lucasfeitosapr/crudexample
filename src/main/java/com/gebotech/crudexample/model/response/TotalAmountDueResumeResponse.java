package com.gebotech.crudexample.model.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Builder
@Getter
public class TotalAmountDueResumeResponse {

    private BigDecimal totalDue;
    private BigDecimal totalPaid;
    private String reminiscentAmount;
    private HashMap<String, BigDecimal> unpaid;
    private HashMap<String, BigDecimal> paid;


}
