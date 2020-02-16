package com.service;

import java.util.List;

import com.data.TransactionDetails;
import com.data.AccountBalance;

public interface TransactionService {
	public AccountBalance relativeAccBalCalculation(String accountId, String fromTime, String toTime,
			List<TransactionDetails> txDetails);

}
