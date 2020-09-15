package br.com.fiap.aoj.products.events;

import br.com.fiap.aoj.products.domain.ProductDomain;

public interface PublishEvent {

	public void publish(ProductDomain productDomain);

}