package service;

import java.util.Date;

public interface TransactionService {
	
	public abstract int relativeAccBalCalculation(String accountId, Date fromTime, Date toTime );
	

}
