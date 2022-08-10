package com.gebotech.crudexample.model;

import lombok.Data;

@Data
public class NotaFiscalMessagem {

    private String salutation;
    private String tomador;
    private String description;
    private String bank;
    private String agency;
    private String account;
    private String favored;
    private String serviceCode;

}
