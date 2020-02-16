package com;

import java.util.List;

import com.data.TransactionDetails;
import com.service.impl.TransactionServiceImpl;
import com.data.AccountBalance;

public class TransactionMain {

	public static void main(String[] args) {

		TransactionServiceImpl txService = new TransactionServiceImpl();
		List<TransactionDetails> txDetails = txService.readCsv("./src/main/resources/transrec.csv");
		if (args.length < 3) {
			System.out.println("Please enter agruments for AccountId, FromDateTime and ToDateTime.");
			System.exit(0);
		}

		/** Getting the input from the user. Assumption is that the input
		 sequence will be AccountId, FromDateTime, ToDateTime.*/
		String accountId = args[0];
		String fromDateString = args[1];
		String toDateString = args[2];

		System.out.println("Account Id: " + accountId);
		System.out.println("From DateTime: " + fromDateString);
		System.out.println("To DateTime: " + toDateString);

		/** Calling the calculation method to derive the relative balance */
		AccountBalance txnOutput = txService.relativeAccBalCalculation(accountId, fromDateString,
				toDateString, txDetails);
		
		// printing the output into the console
		if (txnOutput != null) {
			System.out.println("Relative balance for the period is: " + txnOutput.getRelativeBal());
			System.out.println("Number of transactions included is: " + txnOutput.getCount());
		}

	}

}
