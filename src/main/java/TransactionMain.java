import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import data.TransactionDetails;
import impl.TransactionServiceImpl;

public class TransactionMain {

	public static void main(String[] args) {

		TransactionServiceImpl txService = new TransactionServiceImpl();
		TransactionDetails txDetail = new TransactionDetails();
		List<TransactionDetails> txDetails = txService.readCsv("./src/main/resources/transrec.csv");
		List<String> allAccountId = txDetails.stream().map(x -> x.getFromAccountId().toString()).distinct()
				.collect(Collectors.toList());
		
		String accountId = args[0];
		System.out.println("Account Id: "+ args[0]);
		if (!allAccountId.contains(accountId))
			System.out.println("Please enter valid Account Id.");
		
		String startDateTimeInput = args[1];
		System.out.println("From DateTime: "+ args[1]);
		Date startDateTime = txDetail.formatDate(startDateTimeInput.toString().trim());
		
		String endDateTimeInput = args[2];
		System.out.println("To DateTime: "+ args[2]);
		Date endDateTime = txDetail.formatDate(endDateTimeInput.toString().trim());

	    txService.relativeAccBalCalculation(accountId.toString(), startDateTime, endDateTime, txDetails);
		

	}

}
