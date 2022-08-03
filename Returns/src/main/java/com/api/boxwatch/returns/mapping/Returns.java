package com.api.boxwatch.returns.mapping;

import org.springframework.stereotype.Component;

@Component
public class Returns {
	private String date;
	private int numReturns;

	public Returns(String date, int numReturns) {
		this.date = date;
		this.numReturns = numReturns;
	}

	public Returns() {
	}

	public int getNumReturns() {
		return numReturns;
	}

	public void setNumReturns(int numReturns) {
		this.numReturns = numReturns;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
