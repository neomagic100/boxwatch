package com.api.boxwatch.callervolume;

import org.springframework.stereotype.Component;

@Component
public class CallerVolumeView {
	private int numberOfCalls;
	private String state;
	private String date;
	
	public CallerVolumeView (int numberOfCalls, String state, String date) {
		this.numberOfCalls = numberOfCalls;
		this.state = state;
		this.date = date;
	}
	
	public CallerVolumeView () {}

	public int getNumberOfCalls() {
		return numberOfCalls;
	}

	public void setNumberOfCalls(int numberOfCalls) {
		this.numberOfCalls = numberOfCalls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
