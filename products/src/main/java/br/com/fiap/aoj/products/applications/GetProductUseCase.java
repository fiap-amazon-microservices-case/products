package br.com.fiap.aoj.products.applications;

import br.com.fiap.aoj.products.data.ProductRepository;
import br.com.fiap.aoj.products.domain.ProductDomain;
import br.com.fiap.aoj.products.events.PublishEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetProductUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetProductUseCase.class);

	private final ProductRepository productRepository;
	private final PublishEvent publishEvent;

	public GetProductUseCase(final ProductRepository productRepository,
			@Qualifier("viewedProductEvent") final PublishEvent publishEvent) {
		this.productRepository = productRepository;
		this.publishEvent = publishEvent;
	}

	public Optional<ProductDomain> get(final UUID productId){
		try{
			LOGGER.debug("m=get(productId={})", productId);

			final Optional<ProductDomain> optionalOfProductDomain = productRepository.findById(productId);
			optionalOfProductDomain.ifPresent(this::pushViewedProductEvent);
			return optionalOfProductDomain;
		}catch (Exception exception){
			LOGGER.error("ex(message={}, cause={})", exception.getMessage(), exception);
			return Optional.empty();
		}
	}

	private void pushViewedProductEvent(final ProductDomain productDomain) {
		publishEvent.publish(productDomain);
	}
}