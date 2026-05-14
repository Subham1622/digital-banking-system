package com.banking.app.account.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.banking.app.account.entity.Account;
import com.banking.app.account.service.iAccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private iAccountService accountService;
	
	@PostMapping
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		Account savedAccount = accountService.createAccount(account);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
		Account recordedAccount = accountService.getAccount(id);
		return ResponseEntity.ok(recordedAccount);
	}
	
	@GetMapping
	public ResponseEntity<List<Account>> getAllAccounts() {
		List<Account> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}
	
	@GetMapping("/excel")
	public ResponseEntity<InputStreamResource> downloadAccountExcel() {
		
		ByteArrayInputStream excel = accountService.generateExcelForAccount();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=account.xlsx");
		headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.headers(headers)
				.body(new InputStreamResource(excel));
	}
}
