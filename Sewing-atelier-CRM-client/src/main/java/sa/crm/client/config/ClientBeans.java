package sa.crm.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;

import sa.crm.client.client.RestClientProductsRestClient;

@Configuration
public class ClientBeans {
	
	@Bean
	public RestClientProductsRestClient productsRestClient(
			@Value("${sa.service.crm.uri:http://localhost:8081}") String crmBaseUri,
			@Value("${sa.service.crm.username:}") String crmUsername,
			@Value("${sa.service.crm.password:}") String crmPassword) {
		
		return new RestClientProductsRestClient(RestClient.builder()
				.baseUrl(crmBaseUri)
				.requestInterceptor(
						new BasicAuthenticationInterceptor(crmUsername, crmPassword))
				.build());
	}
}
