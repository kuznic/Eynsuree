package com.meedra.eynsuree.implementation;


import com.meedra.eynsuree.enums.TransactionStatus;
import com.meedra.eynsuree.model.Organization;
import com.meedra.eynsuree.model.Transaction;
import com.meedra.eynsuree.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * This logs a payment request iin the database
 */
@Service
public class TransactionHandlerService {


    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionHandlerService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    public void createTransaction(
                                  String beneficiaryName,
                                  String customerID,
                                  Long transactionAmount,
                                  String payerReference,
                                  String externalReference,
                                  String transactionId) {

        var transaction = Transaction.builder()
                .beneficiaryName(beneficiaryName)
                .customerID(customerID)
                .transactionAmount(transactionAmount)
                .payerReference(payerReference)
                .externalReference(externalReference)
                .transactionDateTime(LocalDateTime.now())
                .status(TransactionStatus.PENDING)
                .transactionId(transactionId)
                .build();


        transactionRepository.save(transaction);

    }







}
