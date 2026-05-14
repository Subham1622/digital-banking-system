package com.banking.app.account.service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.app.account.entity.Account;
import com.banking.app.account.enums.AccountExcelHeader;
import com.banking.app.account.repository.AccountRepository;
import com.banking.app.common.service.ExcelGeneratorService;
import com.banking.app.exception.BankingException;

@Service
public class AccountService implements iAccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private ExcelGeneratorService exeExcelGeneratorService;

	@Override
	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public Account getAccount(Long id) {
		return accountRepository.findById(id)
				.orElseThrow(() -> new BankingException("Account does not exists for id : "+id));
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public ByteArrayInputStream generateExcelForAccount() {
		
		String sheetname = "Accounts";
		List<Account> accountList = accountRepository.findAll();
		
		List<String> header = Arrays.stream(AccountExcelHeader.values())
				.map(AccountExcelHeader::getDisplayName)
				.toList();
		
		List<List<String>> data = accountList.stream().map(acc -> {
			List<String> row = new ArrayList<String>();
			for(AccountExcelHeader accHeader : AccountExcelHeader.values()) {
				row.add(accHeader.getExtractor().apply(acc));
			}
			return row;
		}
		).toList();
		
		return exeExcelGeneratorService.generateExcel(sheetname, header, data);
	}
	
	
}
