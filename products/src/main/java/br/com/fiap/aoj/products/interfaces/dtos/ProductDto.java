package br.com.fiap.aoj.products.interfaces.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

public class ProductDto {

	@JsonProperty(access = READ_ONLY)
	private String id;

	@NotNull(message = "Campo obrigatório")
	private String categoryId;

	@NotNull(message = "Campo obrigatório")
	@Size(min = 2, max = 64, message = "Campo deve ter entre {min} e {max} caracteres.")
	private String name;

	@NotNull(message = "Campo obrigatório")
	@Size(min = 2, max = 512, message = "Campo deve ter entre {min} e {max} caracteres.")
	private String description;

	@Valid
	@NotNull(message = "Campo obrigatório")
	private PriceDto price;

	public String getId() {
		return id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public PriceDto getPrice() {
		return price;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPrice(PriceDto price) {
		this.price = price;
	}
}
