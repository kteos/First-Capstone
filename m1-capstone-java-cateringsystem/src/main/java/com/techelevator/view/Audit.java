package com.techelevator.view;

public class Audit {
	
	private String auditPhrase = "";
	private double userInputValue = 0.0;
	private double auditTotal = 0.0;
	
	public Audit (String auditPhrase, double userInputValue, double auditTotal) {
		this.auditPhrase = auditPhrase;
		this.userInputValue = userInputValue;
		this.auditTotal = auditTotal;
	}

	public String getAuditPhrase() {
		return auditPhrase;
	}

	public void setAuditPhrase(String auditPhrase) {
		this.auditPhrase = auditPhrase;
	}

	public double getUserInputValue() {
		return userInputValue;
	}

	public void setUserInputValue(double userInputValue) {
		this.userInputValue = userInputValue;
	}

	public double getAuditTotal() {
		return auditTotal;
	}

	public void setAuditTotal(double auditTotal) {
		this.auditTotal = auditTotal;
	}
	
	
	
}
