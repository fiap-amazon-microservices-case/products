package br.com.fiap.aoj.products.domain;

import java.io.Serializable;
import java.util.Currency;

public class PriceDomain implements Serializable {

	private static final long serialVersionUID = -7673171489463263656L;

	private static final Currency DEFAULT_CURRENCY = Currency.getInstance("BRL");

	private Double price;

	private Currency currency;

	private PriceDomain(final Double price){
		this.price = price;
		this.currency = DEFAULT_CURRENCY;
	}

	public static final PriceDomain of(final Double price){
		return new PriceDomain(price);
	}

	public Double getPrice() {
		return price;
	}

	public Currency getCurrency() {
		return currency;
	}
}
