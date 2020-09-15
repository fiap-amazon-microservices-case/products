package br.com.fiap.aoj.products.events.dtos;

public class ItemDto {

	private String productId;
	private String categoryId;

	public String getProductId() {
		return productId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}
