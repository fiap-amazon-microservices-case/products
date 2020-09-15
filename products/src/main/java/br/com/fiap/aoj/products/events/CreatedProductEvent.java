package br.com.fiap.aoj.products.events;

import br.com.fiap.aoj.products.domain.ProductDomain;
import br.com.fiap.aoj.products.events.dtos.ItemDto;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("createdProductEvent")
class CreatedProductEvent implements PublishEvent{

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private final AmazonSNS amazonSNS;
	private final String topicArn;

	CreatedProductEvent(final AmazonSNS amazonSNS, @Value("${products.aws.sns.created-product}") final String topicArn) {
		this.amazonSNS = amazonSNS;
		this.topicArn = topicArn;
	}

	@Override
	public void publish(ProductDomain productDomain) {
		final PublishRequest publishRequest = buildPublishRequest(productDomain);
		amazonSNS.publish(publishRequest);
	}


	private PublishRequest buildPublishRequest(final ProductDomain productDomain) {
		final String message = buildMessage(productDomain);
		return new PublishRequest(topicArn, message);
	}

	private String buildMessage(final ProductDomain productDomain) throws RuntimeException{
		try {
			final ItemDto itemDto = new ItemDto();
			itemDto.setProductId(productDomain.getProductId().toString());
			itemDto.setCategoryId(productDomain.getCategoryId());

			return OBJECT_MAPPER.writeValueAsString(itemDto);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}