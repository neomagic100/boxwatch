package com.api.boxwatch.callervolume.mapping;

import org.springframework.stereotype.Component;

@Component
public class CallerVolumeView {
	private int numberOfCalls;
	private String state;

	public CallerVolumeView(int numberOfCalls, String state) {
		this.numberOfCalls = numberOfCalls;
		this.state = state;
	}

	public CallerVolumeView() {
	}

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

	@Override
	public String toString() {
		return "CallerVolume [ State: " + state + ", NumberOfCalls: " + numberOfCalls + " ]";
	}

}
