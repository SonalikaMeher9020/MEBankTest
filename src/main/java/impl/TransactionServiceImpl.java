package impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;

import data.TransactionDetails;
import data.TransactionDetails.Transaction_Type;

public class TransactionServiceImpl {

	public void relativeAccBalCalculation(String accountId, Date fromTime, Date toTime,
			List<TransactionDetails> txDetails) {

		List<TransactionDetails> accountTxDetails = txDetails.stream()
				.filter(txDetail -> txDetail.getFromAccountId().equalsIgnoreCase(accountId)
						|| txDetail.getToAccountId().equalsIgnoreCase(accountId))
				.collect(Collectors.toList());
		List<TransactionDetails> finalTxDetails = removeReversalTx(accountTxDetails);

		double debitBalance = 0.00 ;
		double creditBalance = 0.00 ;
		int txCount = 0;
		for (TransactionDetails accountTxDetail : finalTxDetails) {
			if (accountTxDetail.getCreatedAt().after(fromTime) && accountTxDetail.getCreatedAt().before(toTime)) {
				if (accountTxDetail.getFromAccountId().equalsIgnoreCase(accountId)) {

					debitBalance += accountTxDetail.getAmount();

				} else if (accountTxDetail.getToAccountId().equalsIgnoreCase(accountId)) {

					creditBalance += accountTxDetail.getAmount();
				}
				txCount += 1;
			}
		}
		double relativeBalance = creditBalance - debitBalance;
		System.out.println("Relative balance for the period is:- $" + relativeBalance);
		System.out.println("Number of transactions included is:" + txCount);

	}

	private List<TransactionDetails> removeReversalTx(List<TransactionDetails> accountTxDetails) {
		List<String> reversalTxDetails = accountTxDetails.stream()
				.filter(txDetail -> txDetail.getType().equals(Transaction_Type.REVERSAL))
				.map(tx -> tx.getReversalTxnId()).collect(Collectors.toList());
		if (!reversalTxDetails.isEmpty())
			accountTxDetails.removeIf((TransactionDetails accountTxDetail) -> reversalTxDetails.contains(
					accountTxDetail.getTransactionId()) || accountTxDetail.getType().equals(Transaction_Type.REVERSAL));
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

				TransactionDetails transaction_record = new TransactionDetails(record);

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

}
