package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionDetails {

	private String transactionId;
	private String fromAccountId;
	private String toAccountId;
	private Date createdAt;
	private double amount;
	private String reversalTxnId;
	private Transaction_Type type;
	
	
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	
	public enum Transaction_Type {
		PAYMENT, REVERSAL
	}	

	public TransactionDetails(String[] record) {
		this.transactionId = record[0].trim();
		this.fromAccountId = record[1].trim();
		this.toAccountId = record[2].trim();
		this.createdAt = formatDate(record[3].trim());
		this.amount = Double.parseDouble(record[4].trim());		
		this.type = Transaction_Type.valueOf(record[5].trim());
		if(this.type.equals(Transaction_Type.REVERSAL)){
			this.reversalTxnId = record[6].trim();
		}
		
	}
	public Transaction_Type getType() {
		return type;
	}
	public void setType(Transaction_Type type) {
		this.type = type;
	}
	public TransactionDetails(){}

	public Date formatDate(String createdAt) {
		
		try {
			return dateFormat.parse(createdAt);
		} catch (ParseException e) {
			System.out.println("Invalid DateTime ");
			System.out.println("Please enter start dateTime in dd-MM-yyyy hh:mm:ss format eg: 20/10/2018 12:47:55");
		}
		return null;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public String getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getReversalTxnId() {
		return reversalTxnId;
	}

	public void setReversalTxnId(String reversalTxnId) {
		this.reversalTxnId = reversalTxnId;
	}

}
