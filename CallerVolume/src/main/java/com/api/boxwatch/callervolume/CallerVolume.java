package com.api.boxwatch.callervolume;

import org.springframework.stereotype.Component;

@Component
public class CallerVolume {
	private long id;
	private int numCalls;
	private String state;
	private String date;
	
	public CallerVolume (long id, int numCalls, String state, String date) {
		this.id = id;
		this.numCalls = numCalls;
		this.state = state;
		this.date = date;
	}
	
	public CallerVolume() {}
	
	public int getNumCalls() {
		return numCalls;
	}
	public void setNumCalls(int numCalls) {
		this.numCalls = numCalls;
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
