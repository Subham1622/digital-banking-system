package com.banking.app.transaction.enums;

import java.util.function.Function;

import com.banking.app.transaction.entity.Transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionExcelHeader {

	ID("ID", tnx -> String.valueOf(tnx.getId())),
	FROM_ACCOUNT("FROM ACCOUNT", tnx -> String.valueOf(tnx.getFromAccountId())),
	TO_ACCOUNT("TO ACCOUNT", tnx -> String.valueOf(tnx.getToAccountId())),
	AMOUNT("AMOUNT", tnx -> tnx.getAmmount().toString()),
	TIMESTAMP("TRANSACTION DATE & TIME", tnx -> tnx.getTimestamp().toString());
	
	private final String displayName;
	private final Function<Transaction, String> extractor;
}
