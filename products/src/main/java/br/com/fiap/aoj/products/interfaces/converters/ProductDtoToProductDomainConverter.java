package br.com.fiap.aoj.products.interfaces.converters;

import br.com.fiap.aoj.products.domain.PriceDomain;
import br.com.fiap.aoj.products.domain.ProductDomain;
import br.com.fiap.aoj.products.interfaces.dtos.PriceDto;
import br.com.fiap.aoj.products.interfaces.dtos.ProductDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Lazy
@Component
public class ProductDtoToProductDomainConverter implements Converter<ProductDto, ProductDomain> {

	@Override
	public ProductDomain convert(final ProductDto source) {
		return ProductDomain.builder() //
				.categoryId(source.getCategoryId()) //
				.name(source.getName()) //
				.description(source.getDescription()) //
				.price(buildPrice(source)) //
				.builder();
	}

	private PriceDomain buildPrice(final ProductDto source) {
		final PriceDto priceDto = source.getPrice();
		return PriceDomain.of(priceDto.getPrice());
	}
}