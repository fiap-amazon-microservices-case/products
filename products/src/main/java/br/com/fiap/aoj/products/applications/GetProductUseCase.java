package br.com.fiap.aoj.products.applications;

import br.com.fiap.aoj.products.data.ProductRepository;
import br.com.fiap.aoj.products.domain.ProductDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetProductUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetProductUseCase.class);

	private final ProductRepository productRepository;

	public GetProductUseCase(final ProductRepository productRepository) {
		this.productRepository = productRepository;
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
		//TODO: Implemenar evento de visualização do produto
	}
}