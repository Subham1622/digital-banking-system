package com.banking.app.transaction.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.banking.app.transaction.dto.TransferRequest;
import com.banking.app.transaction.entity.Transaction;

public interface iTransactionService {

	public String tranfer(TransferRequest request);
	
	public List<Transaction> getAllTransactionHistory();
	
	public List<Transaction> getTransactionHistoryOfAccount(Long id);
		
	public ByteArrayInputStream generateExcelForTrasaction(Long id);
}
