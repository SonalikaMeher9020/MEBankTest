package com.data;

public class AccountBalance {
	
	private String relativeBal;
	private int count;
	
	public String getRelativeBal() {
		return relativeBal;
	}
	public void setRelativeBal(String relativeBal) {
		this.relativeBal = relativeBal;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public AccountBalance(String relativeBal, int count){
		this.relativeBal = relativeBal;
		this.count = count;
		
	}

}
