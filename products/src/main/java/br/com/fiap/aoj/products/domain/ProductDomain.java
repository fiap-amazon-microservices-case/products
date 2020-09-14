package br.com.fiap.aoj.products.domain;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.UUID;

public class ProductDomain implements Serializable {

	private static final long serialVersionUID = -9175533454350356130L;

	@MongoId
	private UUID productId;

	@Indexed
	private String categoryId;

	private String name;

	private String description;

	private PriceDomain price;

	private ProductDomain(final Builder builder){
		this.productId = builder.id;
		this.categoryId = builder.categoryId;
		this.name = builder.name;
		this.description = builder.description;
		this.price = builder.price;
	}

	//Construtor padrão para serialização do mongo
	public ProductDomain(){}

	public UUID getProductId() {
		return productId;
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

	public PriceDomain getPrice() {
		return price;
	}

	public static final CategoryId builder(){
		return new Builder();
	}

	public static final class Builder implements CategoryId, Name, Description, Price, Build{
		private UUID id = UUID.randomUUID();
		private String categoryId;
		private String name;
		private String description;
		private PriceDomain price;

		@Override
		public Build id(final UUID id) {
			this.id = id;
			return this;
		}

		@Override
		public Name categoryId(final String categoryId) {
			this.categoryId = categoryId;
			return this;
		}

		@Override
		public Description name(final String name) {
			this.name = name;
			return this;
		}

		@Override
		public Price description(final String description) {
			this.description = description;
			return this;
		}

		@Override
		public Build price(final PriceDomain priceDomain) {
			this.price = priceDomain;
			return this;
		}

		@Override
		public ProductDomain builder() {
			return new ProductDomain(this);
		}
	}

	public interface CategoryId{
		public Name categoryId(final String categoryId);
	}

	public interface Name{
		public Description name(final String name);
	}

	public interface Description{
		public Price description(final String description);
	}

	public interface Price{
		public Build price(final PriceDomain priceDomain);
	}

	public interface Build{
		public Build id(final UUID id);
		public ProductDomain builder();
	}

}
