package sa.crm.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import sa.crm.client.client.RestClientProductsRestClient;

@Configuration
public class ClientBeans {
	
	@Bean
	public RestClientProductsRestClient productsRestClient(
			@Value("${sa.service.crm.uri:http://localhost:8081}") String crmBaseUri) {
		
		return new RestClientProductsRestClient(RestClient.builder()
				.baseUrl(crmBaseUri)
				.build());
	}
}

//@Configuration
//public class ClientBeans {
//
//    @Bean
//    public RestClientProductsRestClient productsRestClient(
//            @Value("${selmag.service.catalogue.uri:http://localhost:8081}") String catalogueBaseUri) {
//        return new RestClientProductsRestClient(RestClient.builder()
//                .baseUrl(catalogueBaseUri)
//                .build());
//    }
//}
