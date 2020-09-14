package br.com.fiap.aoj.products.data;

import br.com.fiap.aoj.products.domain.ProductDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ProductRepository extends MongoRepository<ProductDomain, UUID> {

	public Page<ProductDomain> findByCategoryId(final String categoryId, final PageRequest pageRequest);

}