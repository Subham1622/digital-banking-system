package com.banking.app.account.enums;

import java.util.function.Function;

import com.banking.app.account.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountExcelHeader {

	ID("ID", acc -> String.valueOf(acc.getId())),
	NAME("NAME", acc -> acc.getName()),
	TO_ACCOUNT("BALANCE", acc -> acc.getBalance().toString());
	
	private final String displayName;
	private final Function<Account, String> extractor;
}
