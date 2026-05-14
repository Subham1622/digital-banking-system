package com.banking.app.transaction.service;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.app.account.entity.Account;
import com.banking.app.account.repository.AccountRepository;
import com.banking.app.common.service.ExcelGeneratorService;
import com.banking.app.transaction.dto.TransferRequest;
import com.banking.app.transaction.entity.Transaction;
import com.banking.app.transaction.enums.TransactionExcelHeader;
import com.banking.app.transaction.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService implements iTransactionService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private ExcelGeneratorService excelGeneratorService;
	
	@Override
	@Transactional
	public String tranfer(TransferRequest request) {
		
		Account fromAccount = accountRepository.findById(request.getFromAccountId())
				.orElseThrow(() -> new RuntimeException("Sender not Found"));
		
		Account toAccount = accountRepository.findById(request.getToAccountId())
				.orElseThrow(() -> new RuntimeException("Receiver not Found"));
		
//		BALANCE CHECK
		if(fromAccount.getBalance().compareTo(request.getBalance()) < 0) {
			throw new RuntimeException("Insufficient balance");
		}
		
//		DEBIT
		fromAccount.setBalance(fromAccount.getBalance().subtract(request.getBalance()));
//		CREDIT
		toAccount.setBalance(toAccount.getBalance().add(request.getBalance()));
		
		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);
		
		Transaction transaction = new Transaction();
		transaction.setFromAccountId(request.getFromAccountId());
		transaction.setToAccountId(request.getToAccountId());
		transaction.setAmmount(request.getBalance());
		transaction.setTimestamp(LocalDateTime.now());
			
		transactionRepository.save(transaction);
		
		return "Transfer Successful";
	}

	@Override
	public List<Transaction> getAllTransactionHistory() {
		
		return transactionRepository.findAll();
	}

	@Override
	public List<Transaction> getTransactionHistoryOfAccount(Long id) {
		
		return transactionRepository.findByFromAccountIdOrToAccountIdOrderByTimestampDesc(id,id);
	}

	@Override
	public ByteArrayInputStream generateExcelForTrasaction(Long id) {
		String sheetName = "Transaction";
		List<Transaction> transactionList;
		if(id == null) {
			transactionList = transactionRepository.findAll();
		} else {
			transactionList = this.getTransactionHistoryOfAccount(id);
		}
		
		List<String> headers = Arrays.stream(TransactionExcelHeader.values())
				.map(TransactionExcelHeader::getDisplayName)
				.toList();
		
		List<List<String>> data = transactionList.stream().map(tnx -> {
			List<String> row = new ArrayList<String>();
			for(TransactionExcelHeader transactionHeaders : TransactionExcelHeader.values() ) {
				row.add(transactionHeaders.getExtractor().apply(tnx));
			}
			
		return row;
		}).toList();
		
		return excelGeneratorService.generateExcel(sheetName, headers, data);
	}
	
}
