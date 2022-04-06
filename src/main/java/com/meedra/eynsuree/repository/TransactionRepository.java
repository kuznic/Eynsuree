package com.meedra.eynsuree.repository;

import com.meedra.eynsuree.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}