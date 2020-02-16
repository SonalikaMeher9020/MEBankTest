package com.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.data.TransactionDetails;
import com.data.TransactionType;
import com.data.AccountBalance;
import com.opencsv.CSVReader;
import com.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {

	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public AccountBalance relativeAccBalCalculation(String accountId, String fromTime, String toTime,
			List<TransactionDetails> txDetails) {
		try {

			double debitBalance = 0.00;
			double creditBalance = 0.00;
			int txCount = 0;

			// Converting the input date string to Date format
			Date startDate = formatDate(fromTime.trim());
			Date toDate = formatDate(toTime.trim());

			// Validating the date inputs
			validateDates(startDate, toDate);

			// Creating a sub-list of transactions containing transactions for
			// the input account only
			boolean allAccount = txDetails.stream().anyMatch(x -> x.getFromAccountId().equalsIgnoreCase(accountId)
					|| x.getToAccountId().equalsIgnoreCase(accountId));

			if (!allAccount) {
				System.out.println("No transaction Found with the input account id");
				return null;
			}

			List<TransactionDetails> accountTxDetails = txDetails.stream()
					.filter(txDetail -> txDetail.getFromAccountId().equalsIgnoreCase(accountId)
							|| txDetail.getToAccountId().equalsIgnoreCase(accountId))
					.collect(Collectors.toList());
			List<TransactionDetails> finalTxDetails = removeReversalTx(accountTxDetails);

			for (TransactionDetails accountTxDetail : finalTxDetails) {

				Date createdDate = formatDate(accountTxDetail.getCreatedAt());
				if (createdDate.after(startDate) && createdDate.before(toDate)) {
					if (accountTxDetail.getFromAccountId().equalsIgnoreCase(accountId)) {

						debitBalance += accountTxDetail.getAmount();

					} else if (accountTxDetail.getToAccountId().equalsIgnoreCase(accountId)) {

						creditBalance += accountTxDetail.getAmount();
					}
					txCount += 1;
				}
			}					
			String relativeBalance = (creditBalance > debitBalance) ? "$"+ (creditBalance - debitBalance) :  "-$"+ (debitBalance - creditBalance);	
			AccountBalance txnOutputDetails = new AccountBalance(relativeBalance, txCount);

			return txnOutputDetails;
		} catch (Exception e) {
			return null;
		}
	}

	private List<TransactionDetails> removeReversalTx(List<TransactionDetails> accountTxDetails) {
		List<String> reversalTxDetails = accountTxDetails.stream()
				.filter(txDetail -> txDetail.getType().equals(TransactionType.REVERSAL))
				.map(tx -> tx.getReversalTxnId()).collect(Collectors.toList());
		if (!reversalTxDetails.isEmpty())
			accountTxDetails.removeIf((TransactionDetails accountTxDetail) -> reversalTxDetails.contains(
					accountTxDetail.getTransactionId()) || accountTxDetail.getType().equals(TransactionType.REVERSAL));
		return accountTxDetails;
	}

	/**
	 * Parsing the .csv file. It will throw FileNotFound Exception if the file
	 * will not be present in the location. Also, it will throw Exception in
	 * case of any parsing failure or validation failures.
	 * 
	 * @param csv_filename
	 *            Name of the csv file to read the print job details
	 * @return The list of print job details
	 */
	public List<TransactionDetails> readCsv(String csv_filename) {

		try (CSVReader reader = new CSVReader(new FileReader(csv_filename), ',', '"', 1)) {

			List<TransactionDetails> transaction_details = new ArrayList<TransactionDetails>();

			String[] record = null;

			while ((record = reader.readNext()) != null) {
				
				TransactionDetails transaction_record = new TransactionDetails(record[0],record[1], record[2], record[3], record[4], record[5], (record.length>6 ? record[6] : null));
				transaction_details.add(transaction_record);
			}
			return transaction_details;
		} catch (FileNotFoundException e) {
			System.out.println("No file found from the specified location " + csv_filename);
			System.exit(0);
		} catch (Exception e) {
			System.out.println("Error occured while reading the file " + csv_filename);
			System.exit(0);
		}
		return null;

	}

	public void validateDates(Date startDate, Date toDate) throws Exception {
		if (startDate == null || toDate == null) {
			System.out.println("Please enter a valid Date in format DD/MM/YYYY hh:mm:ss.");
			throw new Exception("Exception");
		}

		if (startDate.after(toDate)) {
			System.out.println("Start Date cannot be greater than End Date");
			throw new Exception("Exception");
		}

	}

	public Date formatDate(String createdAt) throws Exception {

		try {
			return dateFormat.parse(createdAt);
		} catch (ParseException e) {
			System.out.println("Invalid DateTime ");
			System.out.println("Please enter dateTime in dd-MM-yyyy hh:mm:ss format eg: 20/10/2018 12:47:55");
			throw new Exception("Exception in date parse");
		}
	}

}
