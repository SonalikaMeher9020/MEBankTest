package com.data;

public class TransactionDetails {

	private String transactionId;
	private String fromAccountId;
	private String toAccountId;
	private String createdAt;
	private double amount;
	private String reversalTxnId;
	private TransactionType type;

	public TransactionDetails(String transactionId, String fromAccountId, String toAccountId, String createdAt,
			String amount, String type, String reversalTxnId) {
		this.transactionId = transactionId.trim();
		this.fromAccountId = fromAccountId.trim();
		this.toAccountId = toAccountId.trim();
		this.createdAt = createdAt.trim();
		this.amount = Double.parseDouble(amount.trim());
		this.type = TransactionType.valueOf(type.trim());
		if (this.type.equals(TransactionType.REVERSAL)) {
			this.reversalTxnId = reversalTxnId.trim();
		}
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public TransactionDetails() {
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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
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
