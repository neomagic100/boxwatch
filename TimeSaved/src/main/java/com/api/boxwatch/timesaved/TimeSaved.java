package com.api.boxwatch.timesaved;

import org.springframework.stereotype.Component;

@Component
public class TimeSaved {
	private String expectedDeliveryDate;
	private String dateDamagedPackageDetected;
	private int differenceBetweenDates;
	
	public TimeSaved (String expectedDeliveryDate, String dateDamagedPackageDetected, int differenceBetweenDates) {
		this.expectedDeliveryDate = expectedDeliveryDate;
		this.dateDamagedPackageDetected = dateDamagedPackageDetected;
		this.differenceBetweenDates = differenceBetweenDates;
	}
	
	public TimeSaved() {}

	public String getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(String expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public String getDateDamagedPackageDetected() {
		return dateDamagedPackageDetected;
	}

	public void setDateDamagedPackageDetected(String dateDamagedPackageDetected) {
		this.dateDamagedPackageDetected = dateDamagedPackageDetected;
	}

	public int getDifferenceBetweenDates() {
		return differenceBetweenDates;
	}

	public void setDifferenceBetweenDates(int differenceBetweenDates) {
		this.differenceBetweenDates = differenceBetweenDates;
	}
	
}
