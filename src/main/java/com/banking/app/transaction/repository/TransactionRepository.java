package com.banking.app.transaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.app.transaction.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByFromAccountIdOrToAccountIdOrderByTimestampDesc(Long fromAccountId, Long toAccountId);

}
