package com.banking.app.account.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.banking.app.account.entity.Account;

public interface iAccountService {

	public Account createAccount(Account account);
	
	public Account getAccount(Long id);
	
	public List<Account> getAllAccounts();
	
	public ByteArrayInputStream generateExcelForAccount();
}
