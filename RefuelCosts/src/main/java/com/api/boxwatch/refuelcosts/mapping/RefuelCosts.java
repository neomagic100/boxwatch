package com.api.boxwatch.refuelcosts.mapping;

import org.springframework.stereotype.Component;

@Component
public class RefuelCosts {

	private String date;
	private float gasCost;
	private float elecCost;
	private float natGasCost;

	public RefuelCosts(String date, float gasCost, float elecCost, float natGasCost) {
		this.date = date;
		this.gasCost = gasCost;
		this.elecCost = elecCost;
		this.natGasCost = natGasCost;
	}

	public RefuelCosts() {
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public float getGasCost() {
		return gasCost;
	}

	public void setGasCost(float gasCost) {
		this.gasCost = gasCost;
	}

	public float getElecCost() {
		return elecCost;
	}

	public void setElecCost(float elecCost) {
		this.elecCost = elecCost;
	}

	public float getNatGasCost() {
		return natGasCost;
	}

	public void setNatGasCost(float natGasCost) {
		this.natGasCost = natGasCost;
	}

}
