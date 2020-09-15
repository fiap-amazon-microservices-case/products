package br.com.fiap.aoj.products.configurations;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AwsConfiguration {

	private final String region;
	private final String accessKey;
	private final String secretKey;

	public AwsConfiguration( @Value("${cloud.aws.region.static}") final String region,
			@Value("${cloud.aws.credentials.accessKey}") final String accessKey,
			@Value("${cloud.aws.credentials.secretKey}") final String secretKey) {
		this.region = region;
		this.accessKey = accessKey;
		this.secretKey = secretKey;
	}

	@Bean("amazonSNSClient")
	public AmazonSNS amazonSNSClient() {
		return AmazonSNSClient
				.builder()
				.withCredentials(getAwsCredentials(accessKey, secretKey))
				.withRegion(getRegion(region))
				.build();
	}



	private Regions getRegion(final String region) {
		return Regions.fromName(region);
	}

	private AWSCredentialsProvider getAwsCredentials(final String accessKey, final String secretKey) {
		final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
		return new AWSStaticCredentialsProvider(basicAWSCredentials);
	}



}
