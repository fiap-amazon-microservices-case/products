package br.com.fiap.aoj.products.interfaces;

import br.com.fiap.aoj.products.applications.AddProductUseCase;
import br.com.fiap.aoj.products.applications.FindProductsUseCase;
import br.com.fiap.aoj.products.applications.GetProductUseCase;
import br.com.fiap.aoj.products.domain.ProductDomain;
import br.com.fiap.aoj.products.interfaces.converters.ProductDomainToProductDtoConverter;
import br.com.fiap.aoj.products.interfaces.converters.ProductDtoToProductDomainConverter;
import br.com.fiap.aoj.products.interfaces.dtos.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(path = "${api.version.v1:/v1}/products")
class ProductsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductsController.class);

	private final AddProductUseCase addProductUseCase;
	private final GetProductUseCase getProductUseCase;
	private final FindProductsUseCase findProductsUseCase;
	private final ProductDtoToProductDomainConverter productDtoToProductDomainConverter;
	private final ProductDomainToProductDtoConverter productDomainToProductDtoConverter;

	ProductsController(final AddProductUseCase addProductUseCase,
			final GetProductUseCase getProductUseCase,
			final FindProductsUseCase findProductsUseCase,
			final ProductDtoToProductDomainConverter productDtoToProductDomainConverter,
			final ProductDomainToProductDtoConverter productDomainToProductDtoConverter) {
		this.addProductUseCase = addProductUseCase;
		this.getProductUseCase = getProductUseCase;
		this.findProductsUseCase = findProductsUseCase;
		this.productDtoToProductDomainConverter = productDtoToProductDomainConverter;
		this.productDomainToProductDtoConverter = productDomainToProductDtoConverter;
	}

	@PostMapping
	@ResponseStatus(CREATED)
	public Mono<ProductDto> add(@RequestBody @Valid final ProductDto productDto){
		LOGGER.debug("m=add(productDto={})", productDto);

		final ProductDomain productDomain = productDtoToProductDomainConverter.convert(productDto);
		final Optional<ProductDomain> optionalOfProductDomain = addProductUseCase.add(productDomain);

		return optionalOfProductDomain.map(productDomainToProductDtoConverter::convert)//
				.map(Mono::just) //
				.orElseThrow(() -> new RuntimeException());
	}

	@GetMapping("{id}")
	@ResponseStatus(OK)
	public Mono<ProductDto> get(@PathVariable("id") String id){
		LOGGER.debug("m=get(id={})", id);

		return getProductUseCase.get(UUID.fromString(id)) //
				.map(productDomainToProductDtoConverter::convert)//
				.map(Mono::just) //
				.orElseThrow(() -> new IllegalArgumentException());
	}

	@GetMapping
	@ResponseStatus(OK)
	public Mono<Page<ProductDto>> find(
			@RequestParam(name = "categoryId") final String categoryId,
			@RequestParam(name = "page", defaultValue = "0") final Integer page,
			@RequestParam(name = "size", defaultValue = "10") final Integer size){
		LOGGER.debug("m=find(categoryId={}, page={}, size={})", categoryId, page, size);

		final Page<ProductDomain> pageOfProductDomain = findProductsUseCase.find(categoryId, page, size);
		final List<ProductDto> productsDto = pageOfProductDomain.getContent().stream()
				.map(productDomainToProductDtoConverter::convert) //
				.collect(Collectors.toList());

		return Mono.just(new PageImpl(productsDto, pageOfProductDomain.getPageable(), pageOfProductDomain.getTotalElements()));
	}
}