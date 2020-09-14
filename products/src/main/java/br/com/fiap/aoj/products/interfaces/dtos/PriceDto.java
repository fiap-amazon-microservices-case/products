package br.com.fiap.aoj.products.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

public class PriceDto {

	@NotNull(message = "Campo obrigatório")
	private Double price;

	@JsonProperty(access = READ_ONLY)
	private String currency;

	public Double getPrice() {
		return price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
