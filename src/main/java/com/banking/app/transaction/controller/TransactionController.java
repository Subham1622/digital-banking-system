package com.banking.app.transaction.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.app.transaction.dto.TransferRequest;
import com.banking.app.transaction.entity.Transaction;
import com.banking.app.transaction.service.iTransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private iTransactionService transactionService;
	
	@PostMapping("/transfer")
	public ResponseEntity<String> tranfer(@RequestBody TransferRequest request) {
		String response = transactionService.tranfer(request);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/history")
	public ResponseEntity<List<Transaction>> getAllTransactionHistory() {
		List<Transaction> historyRecord = transactionService.getAllTransactionHistory();
		return ResponseEntity.status(HttpStatus.OK).body(historyRecord);
	}
	
	@PostMapping("/history/{id}")
	public ResponseEntity<List<Transaction>> getTransactionHistoryOfAccount(@PathVariable Long id) {
		List<Transaction> historyRecord = transactionService.getTransactionHistoryOfAccount(id);
		return ResponseEntity.status(HttpStatus.OK).body(historyRecord);
	}
	
	@GetMapping("/excel")
	public ResponseEntity<InputStreamResource> downloadExcelForTransaction(
			@RequestParam(required = false) Long id) {
		
		ByteArrayInputStream excel = transactionService.generateExcelForTrasaction(id);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=account.xlsx");
		headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	
		return ResponseEntity
				.status(HttpStatus.OK)
				.headers(headers)
				.body(new InputStreamResource(excel));
	}

}
