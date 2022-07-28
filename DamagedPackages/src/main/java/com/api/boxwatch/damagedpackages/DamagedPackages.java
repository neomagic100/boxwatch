package com.api.boxwatch.damagedpackages;

import org.springframework.stereotype.Component;

@Component
public class DamagedPackages {
	private String date;
	private int numDetected;
	private int numReported;
	
	public DamagedPackages (String date, int numDetected, int numReported) {
		this.date = date;
		this.numDetected = numDetected;
		this.numReported = numReported;
	}
	
	public DamagedPackages() {}
	
	public int getNumDetected() {
		return numDetected;
	}
	
	public void setNumDetected(int numDetected) {
		this.numDetected = numDetected;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public void setNumReported(int numReported) {
		this.numReported = numReported;
	}
	
	public long getNumReported() {
		return numReported;
	}

}
