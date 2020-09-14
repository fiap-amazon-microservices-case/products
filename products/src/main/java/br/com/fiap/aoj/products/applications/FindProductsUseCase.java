package br.com.fiap.aoj.products.applications;

import br.com.fiap.aoj.products.data.ProductRepository;
import br.com.fiap.aoj.products.domain.ProductDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class FindProductsUseCase {

	private static final Logger LOGGER = LoggerFactory.getLogger(FindProductsUseCase.class);

	private final ProductRepository productRepository;

	public FindProductsUseCase(final ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Page<ProductDomain> find(final String categoryId, final int page, final int size){
		try{
			LOGGER.debug("m=find(categoryId={}, page={}, size={})", categoryId, page, size);

			final PageRequest pageRequest = PageRequest.of(page, size);
			return productRepository.findByCategoryId(categoryId, pageRequest);
		}catch (Exception exception){
			LOGGER.error("ex(message={}, cause={})", exception.getMessage(), exception);
			return Page.empty();
		}
	}
}