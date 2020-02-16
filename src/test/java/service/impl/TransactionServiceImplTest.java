package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.data.AccountBalance;
import com.data.TransactionDetails;
import com.service.TransactionService;
import com.service.impl.TransactionServiceImpl;

public class TransactionServiceImplTest {

	TransactionService txServiceTest = new TransactionServiceImpl();
	TransactionDetails tx = new TransactionDetails();
	List<TransactionDetails> txDetails = new ArrayList<>();

	/** Initializing the data to perform the testing */
	@Before
	public void initializeData() {

		TransactionDetails txDetail = new TransactionDetails("T1", "A1", "B1", "20/10/2018 9:10:00", "10", "PAYMENT",
				null);

		TransactionDetails txDetail1 = new TransactionDetails("T2", "A1", "B2", "20/10/2018 9:15:00", "30", "PAYMENT",
				null);

		TransactionDetails txDetail2 = new TransactionDetails("T3", "B1", "A1", "20/10/2018 9:20:00", "20", "PAYMENT",
				null);

		TransactionDetails txDetail3 = new TransactionDetails("T4", "A1", "B1", "20/10/2018 21:20:00", "10", "REVERSAL",
				"T1");

		txDetails.add(txDetail);
		txDetails.add(txDetail1);
		txDetails.add(txDetail2);
		txDetails.add(txDetail3);

	}

	/**
	 * Test case for validating nos of transaction available in test csv file
	 */
	@Test
	public void readCsv() throws Exception {
		TransactionServiceImpl txnService = new TransactionServiceImpl();
		List<TransactionDetails> transactions = txnService.readCsv("./src/main/resources/transrec.csv");
		assertEquals(5, transactions.size());
	}

	/** Testing the output with valid input */
	@Test
	public void relativeAccBalCalculationTest() {
		String AccountId = "A1";
		String fromDateTime = "20/10/2018 9:00:00";
		String toDateTime = "21/10/2018 19:00:00";
		String expectedRelativeBal = "-$10.0";
		int expected_txCount = 2;
		AccountBalance correctTxnOutputDetails = txServiceTest.relativeAccBalCalculation(AccountId, fromDateTime,
				toDateTime, txDetails);

		assertNotNull(correctTxnOutputDetails.getRelativeBal());
		assertNotNull(correctTxnOutputDetails.getCount());
		assertEquals(expectedRelativeBal, correctTxnOutputDetails.getRelativeBal());
		assertEquals(expected_txCount, correctTxnOutputDetails.getCount());
	}

	/**
	 * When the input account does not contain any transaction during the
	 * specified period
	 */
	@Test
	public void incorrectAccountIdTest() {
		String AccountId = "####";
		String fromDateTime = "20/10/2018 9:00:00";
		String toDateTime = "21/10/2018 19:00:00";
		AccountBalance correctTxnOutputDetails = txServiceTest.relativeAccBalCalculation(AccountId, fromDateTime,
				toDateTime, txDetails);
		boolean actualOutput = true;
		if (null == correctTxnOutputDetails)
			actualOutput = false;

		assertEquals(false, actualOutput);

	}

	/** When the input is given in the expected valid format */
	@Test
	public void incorrectDateFormatTest() {
		String AccountId = "A1";
		String fromDateTime = "20/10/2018";
		String toDateTime = "21/10/2018";
		AccountBalance correctTxnOutputDetails = txServiceTest.relativeAccBalCalculation(AccountId, fromDateTime,
				toDateTime, txDetails);
		boolean actualOutput = true;
		if (null == correctTxnOutputDetails)
			actualOutput = false;

		assertEquals(false, actualOutput);

	}

}
