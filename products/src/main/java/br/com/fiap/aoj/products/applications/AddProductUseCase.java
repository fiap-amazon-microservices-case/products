package br.com.fiap.aoj.products.applications;

import br.com.fiap.aoj.products.data.ProductRepository;
import br.com.fiap.aoj.products.domain.ProductDomain;
import br.com.fiap.aoj.products.events.PublishEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddProductUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddProductUseCase.class);

	private final ProductRepository productRepository;
	private final PublishEvent publishEvent;

	public AddProductUseCase(final ProductRepository productRepository,
			@Qualifier("createdProductEvent") final PublishEvent publishEvent) {
		this.productRepository = productRepository;
		this.publishEvent = publishEvent;
	}

	public Optional<ProductDomain> add(final ProductDomain productDomain){
		try{
			LOGGER.debug("m=add(productDomain={})", productDomain);

			productRepository.insert(productDomain);
			pushCreatedProductEvent(productDomain);
			return Optional.of(productDomain);
		}catch (Exception exception){
			LOGGER.error("ex(message={}, cause={})", exception.getMessage(), exception);
			return Optional.empty();
		}
	}

	private void pushCreatedProductEvent(final ProductDomain productDomain) {
		publishEvent.publish(productDomain);
	}
}