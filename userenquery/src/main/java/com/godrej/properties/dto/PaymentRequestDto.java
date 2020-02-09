package com.godrej.properties.dto;

public class PaymentRequestDto {

	private String projectSfid;
	PaymentDto []payments;
	private Double plannedPayment;
	private Double plannedPaymentWithTax;
	private Double price;
	private Double priceWithTax;
	
	public String getProjectSfid() {
		return projectSfid;
	}
	public void setProjectSfid(String projectSfid) {
		this.projectSfid = projectSfid;
	}
	public PaymentDto[] getPayments() {
		return payments;
	}
	public void setPayments(PaymentDto[] payments) {
		this.payments = payments;
	}
	public Double getPlannedPayment() {
		return plannedPayment;
	}
	public void setPlannedPayment(Double plannedPayment) {
		this.plannedPayment = plannedPayment;
	}
	public Double getPlannedPaymentWithTax() {
		return plannedPaymentWithTax;
	}
	public void setPlannedPaymentWithTax(Double plannedPaymentWithTax) {
		this.plannedPaymentWithTax = plannedPaymentWithTax;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getPriceWithTax() {
		return priceWithTax;
	}
	public void setPriceWithTax(Double priceWithTax) {
		this.priceWithTax = priceWithTax;
	}	
}
