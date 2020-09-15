package br.com.fiap.aoj.products.events;

import br.com.fiap.aoj.products.domain.ProductDomain;
import br.com.fiap.aoj.products.events.dtos.ViewDto;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("viewedProductEvent")
class ViewedProductEvent implements PublishEvent{

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private final AmazonSNS amazonSNS;
	private final String topicArn;

	ViewedProductEvent(final AmazonSNS amazonSNS,
			@Value("${products.aws.sns.viewed-product}") final String topicArn) {
		this.amazonSNS = amazonSNS;
		this.topicArn = topicArn;
	}

	@Override
	public void publish(final ProductDomain productDomain) {
		final PublishRequest publishRequest = buildPublishRequest(productDomain);
		amazonSNS.publish(publishRequest);
	}

	private PublishRequest buildPublishRequest(final ProductDomain productDomain) {
		final String message = buildMessage(productDomain);
		return new PublishRequest(topicArn, message);
	}

	private String buildMessage(final ProductDomain productDomain) throws RuntimeException{
		try {
			return OBJECT_MAPPER.writeValueAsString(ViewDto.of(productDomain.getProductId().toString(), productDomain.getCategoryId()));
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}