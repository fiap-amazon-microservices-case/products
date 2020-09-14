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
public class ProductDomainToProductDtoConverter implements Converter<ProductDomain, ProductDto> {

	@Override
	public ProductDto convert(final ProductDomain source) {
		final ProductDto productDto = new ProductDto();
		productDto.setId(source.getProductId().toString());
		productDto.setName(source.getName());
		productDto.setDescription(source.getDescription());
		productDto.setCategoryId(source.getCategoryId());
		productDto.setPrice(buildPriceDto(source));

		return productDto;
	}

	private PriceDto buildPriceDto(final ProductDomain source) {
		final PriceDomain priceDomain = source.getPrice();
		final PriceDto priceDto = new PriceDto();
		priceDto.setPrice(priceDomain.getPrice());
		priceDto.setCurrency(priceDomain.getCurrency().getCurrencyCode());

		return priceDto;
	}
}