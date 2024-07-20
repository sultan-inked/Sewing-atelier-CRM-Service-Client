package sa.crm.client.client;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import lombok.RequiredArgsConstructor;
import sa.crm.client.controller.payload.NewProductPayload;
import sa.crm.client.controller.payload.UpdateProductPayload;
import sa.crm.client.entity.Product;

@RequiredArgsConstructor
public class RestClientProductsRestClient implements ProductsRestClient{
	
	private static final ParameterizedTypeReference<List<Product>> PRODUCTS_TYPE_REFERENCE =
																new ParameterizedTypeReference<>() {
	};
	
	private final RestClient restClient;
	
	@Override
	public List<Product> findAllProducts(String filter) {
		return this.restClient
				.get()
				.uri("/catalogue-api/products?filter={filter}", filter)
				.retrieve()
				.body(PRODUCTS_TYPE_REFERENCE);
	}

	//@SuppressWarnings("unchecked")
	@Override
	public Product createProduct(String title, String details) {
		try {
			return this.restClient
					.post()
					.uri("/catalogue-api/products")
					.contentType(MediaType.APPLICATION_JSON)
					.body(new NewProductPayload(title, details))
					.retrieve()
					.body(Product.class);
		} catch (HttpClientErrorException.BadRequest exception) {
            ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
            throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
        }
	}
	/*
	 * Безопасный вариант метода выше, безопасность в приведении типов,
	 * если problemDetail не содержит String, то программа остановится с ошибкой
	 * 
	@Override
	public Product createProduct(String title, String details) {
	    try {
	        return this.restClient
	                .post()
	                .uri("/catalogue-api/products")
	                .contentType(MediaType.APPLICATION_JSON)
	                .body(new NewProductPayload(title, details))
	                .retrieve()
	                .body(Product.class);
	    } catch (HttpClientErrorException.BadRequest exception) {
	        ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
	        Object errors = problemDetail.getProperties().get("errors");
	        if (errors instanceof List<?>) {
	            List<?> errorsList = (List<?>) errors;
	            if (errorsList.isEmpty() || errorsList.get(0) instanceof String) {
	                throw new BadRequestException((List<String>) errors);
	            }
	        }
	        throw new BadRequestException("Unknown error format");
	    }
	}
	*/
	
	
	@Override
	public Optional<Product> findProduct(int productId) {
		try {
			return Optional.ofNullable(this.restClient.get()
					.uri("/catalogue-api/products/{productId}", productId)
					.retrieve()
					.body(Product.class));
		} catch (HttpClientErrorException.NotFound exception) {
			return Optional.empty();
		}
	}

	@Override
	public void updateProduct(int productId, String title, String details) {
		try {
			this.restClient
				.patch()
				.uri("/catalogue-api/products/{productId}", productId)
				.contentType(MediaType.APPLICATION_JSON)
				.body(new UpdateProductPayload(title, details))
				.retrieve()
				.toBodilessEntity();
		} catch (HttpClientErrorException.BadRequest exception) {
			ProblemDetail problemDetail = exception.getResponseBodyAs(ProblemDetail.class);
			throw new BadRequestException((List<String>) problemDetail.getProperties().get("errors"));
		}
	}

	@Override
	public void deleteProduct(int productId) {
		try {
			this.restClient
					.delete()
					.uri("/catalogue-api/products/{productId}", productId)
					.retrieve()
					.toBodilessEntity();
		} catch (HttpClientErrorException.NotFound exception) {
			throw new NoSuchElementException(exception);
		}
		
	}
}













