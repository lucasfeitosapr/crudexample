package com.gebotech.crudexample.security.services;

import com.gebotech.crudexample.model.NotaFiscalMessagem;
import org.springframework.stereotype.Service;

@Service
public class MessageBuilder {

    private String salutations = "Olá pessoal, %s! Gostaria de solicitar a emissão de uma nota com as seguintes informações: ";


    public String buildMessage(NotaFiscalMessagem menssagem) {
        String newMessage = "";

        newMessage = String.format(salutations, menssagem.getSalutation()) + "\nTOMADOR: " +  menssagem.getTomador() + "\nDESCRIÇÃO: " + menssagem.getDescription() + " BANCO: " +
                menssagem.getBank() + " AGENCIA: " + menssagem.getAgency() + " CONTA JURIDICA: " + menssagem.getAccount() +
                " FAVORECIDO: " + menssagem.getFavored() + "\nCÓDIGO DE SERVIÇO: " + menssagem.getServiceCode();


        return newMessage;
    }

//    [17:22, 28/01/2022] Lucas Feitosa: [17:22, 28/01/2022] Lucas Feitosa: Tomador: 33.077.724/0001-92 KASPPER SISTEMA E CONSULTORIA LTDA
//    [17:22, 28/01/2022] Lucas Feitosa: Descrição: REFERENTE A SERVIÇOS PRESTADOS NO PERÍODO DE 25/12/2021 a 24/01/2022 COM TOTAL DE HORAS PRESTADAS EM :
//    PROJETO: PWC - Alocação - 689496 HORAS: 128.00 VALOR HORA: R$85.00 SOMA: R$10880,00
//    BANCO: Nubank
//    AGÊNCIA: 0001
//    CONTA JURÍDICA: 90061689-2
//    FAVORECIDO: LUCAS FEITOSA OLIVEIRA
//    [17:23, 28/01/2022] Lucas Feitosa: Código de serviço: 14.02 assistência técnica

}
